Ext.define('Eway.controller.monitor.transaction.TransactionMonitor', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'monitor.transaction.Transaction','monitor.transaction.BlacklistDict','monitor.transaction.TransType','monitor.transaction.HostRet'],

	models : [ 'monitor.transaction.Transaction','Dict','monitor.transaction.TransType','monitor.transaction.HostRet'],

	views : [ 'monitor.transaction.TransactionMonitorView' ],

	refs : [ {
        ref : 'ewayView',
        selector : 'transactionMonitorView',
        autoCreate : true,
        xtype : 'transactionMonitorView'
    }, {
        ref : 'transactionGrid',
        selector : 'monitor_transaction_transactionGrid'
    }, {
        ref : 'fiveMinutesGrid',
        selector : 'monitor_transaction_fiveMinutesGrid'
    }, {
        ref : 'transactionFilterForm',
        selector  :'transactionFilterForm'
    }, {
		ref: 'desWin',
		selector: 'monitor_transaction_colorset_describe'
	}  ],
	
    config : {
        ticking : true,

        // 滚屏方式：DOWN向下,UP向上
        roll : 'UP',
        gridBodyId : ''
    },

	init : function() {
		this.control({
			'monitor_transaction_transactionGrid button[action=start]' : {
				click : this.startMonitor
			},
			'monitor_transaction_transactionGrid button[action=stop]' : {
				click : this.stopMonitor
			},
			'transactionMonitorView ' : {
				deactivate : this.stopMonitor,
				render : this.initLoad
			},
			'monitor_transaction_transactionGrid button[action=clear]' : {
                click : this.clear
            },
            'transactionMonitorView transactionFilterForm radiogroup' : {
			    change : this.onChange,
			    scope : this
			}
		});
	},
	
	
	/**
     * 初始化
     */
    initLoad : function() {
         this.initUserParam();
    },
	initUserParam : function() {
        this.listTran = new Array();
        this.list200Tran = new Array();

        var form = this.getTransactionFilterForm().getForm();
        Ext.Ajax.request({
            url: 'api/msg/transaction/queryTransType',
            success: function(response){
                var text = response.responseText;
                form.findField("transType").setValue(text);
            }
        });

        Ext.Ajax.request({
            url: 'api/msg/transaction/queryHostRet',
            success: function(response){
                var text = response.responseText;
                form.findField("hostRet").setValue(text);
            }
        });
    },
	
	/**
     * 改变滚动方式
     */
    onChange : function( This, newValue, oldValue, eOpts ) {

        this.config.roll = newValue.rbAuto;

        if (this.config.roll == 'DOWN') {
            // 得到需要刷新的body区域id
            if (!this.config.gridBodyId) {
                this.config.gridBodyId = this.getTransactionGrid().body.dom.firstChild.id;
            }
            // 滚动条自动刷新到最上面
			Ext.get(this.config.gridBodyId).scroll('t', 1000);
        }

    },
	
	// 启动监控
    startMonitor : function() {
    	
    	var grid = this.getTransactionGrid();
    	grid.down('button[action="start"]').setDisabled(true);
    	grid.down('button[action="stop"]').setDisabled(false);
    	
    	var form = this.getTransactionFilterForm().getForm();
        form.findField('terminalId').setDisabled(true);
        var data = form.getValues();
       
        this.cometdConnect(data);
        
        this.startrAf();
    },
	
	// 停止监控
    stopMonitor : function() {

        this.getTransactionGrid().down('button[action="start"]').setDisabled(false);
        this.getTransactionGrid().down('button[action="stop"]').setDisabled(true);

        // 删除缓存中的交易数据
        this.listTran = [];

        // 停止执行
        this.config.ticking = false;

        // 停止监控后,开放条件
        var form = this.getTransactionFilterForm();
        form.getForm().findField('terminalId').setDisabled(false);

        this.cometdDisconnect();
    },

    cometdConnect : function(data) {
        var cometURL = location.protocol + "//" + location.host + Ext.cxtPath + "/cometd";
        this.cometd = Ext.Cometd;
        this.cometd.configure(cometURL, 'debug');

        if(this.config._TransactionMartixHandsubSub){
            this.cometd.removeListener(this.config._TransactionMartixHandsubSub);
        }

        this.config._TransactionMartixHandsubSub = this.cometd.addListener('/meta/handshake', Ext.bind(this._metaHandshake, this, [data], true));
        this.cometd.handshake();
    },

    _metaHandshake : function(channel, data) {
        this.cometd.publish("/service/transaction/join",{
            organizationId : Eway.user.getOrgId(), // 用户登录所属机构
            terminalId : data.terminalId // 设备号
        });
        if (this.config._transactionSub) {
            Ext.Cometd.removeListener(this.config._transactionSub);
        }
        this.config._transactionSub = this.cometd.subscribe("/service/transaction/join", this, this.onReceive);
    },

    cometdDisconnect : function() {
        if (this.config._transactionSub) {
            Ext.Cometd.removeListener(this.config._transactionSub);
            this.cometd = null;
            this.config._transactionSub = null;
        }

        Ext.Cometd.disconnect();
    },
	

	onReceive : function(message) {
		var data = Ext.decode(message.data);
		var ewayView = this.getEwayView();

		var form = ewayView.down('transactionFilterForm').getForm();
		var transType = Ext.decode(form.findField("transType").getValue());
		var hostRet = Ext.decode(form.findField("hostRet").getValue());

		Ext.Array.each(hostRet.data,function(iter,index,dataSelf){
			if(data.hostRet==iter.hostRet){
				data.hostRet = iter.hostRet;
			}
		});
		Ext.Array.each(transType.data,function(iter,index,dataSelf){
			if(data.transCode==iter.transCode){
				data.transCode = iter.codeDesc;
			}
		});


		// 将交易数据放到缓存池中,当缓存池中超过80条时,将清除最先累积的数据
        this.listTran.push(data);

        var len = this.listTran.length;
        if (len >= 80) {
            this.listTran.splice(0, 1);
        }

        // 垃圾回收
        message = null;
        data = null;
	},
	
	startrAf : function() {

        var rAF = (function(){
            return  window.requestAnimationFrame       ||
              window.webkitRequestAnimationFrame ||
              window.mozRequestAnimationFrame    ||
              window.oRequestAnimationFrame      ||
              window.msRequestAnimationFrame     ||
              function( callback ){
                window.setTimeout(callback, 1000 / 60);
              };
            })();
        var me = this;
        var store = me.getTransactionGrid().getStore();

        // 始终执行
        me.config.ticking = true;

        // 更新交易情况
        function update() {
            var ar = me.list200Tran.splice(0, 1);
            Ext.Array.each(ar, function(iter, index, dataSelf) {
                var record = Ext.create('Eway.model.monitor.transaction.Transaction', iter);

                if (me.config.roll == 'DOWN') {
                    store.insert(0, record);
                } else {
					store.add(record);
                    // 得到需要刷新的body区域id
                    if (!me.config.gridBodyId) {
                        me.config.gridBodyId = me.getTransactionGrid().body.dom.firstChild.id;
                    }
                    // 滚动条自动刷新到最下面
					Ext.get(me.config.gridBodyId).scroll('b', 20);
                }

                record = null;
            });
            ar = null;
            if (me.config.ticking) {
                rAF(update);
            }
        };

        // 获取交易信息
        function getTransaction() {
            var ar = me.listTran.splice(0, 40);
            me.listTran = [];

            // 当显示池数据超过160条时，只取最新的80条
            var len = me.list200Tran.length;
            if (len >= 160) {
                me.list200Tran = me.list200Tran.splice(len - 80, len);
            }

            if (ar.length >= 40) {
                var tempArray = [];
                Ext.Array.each(me.list200Tran, function(iter, index, dataSelf) {
                    var hostRet = iter.hostRet;
                    if ('00' != hostRet) {
                        tempArray.push(iter);
                    }
                });

                me.list200Tran = [];
                me.list200Tran = tempArray.concat(ar);
                tempArray = null;

            } else {
                me.list200Tran = me.list200Tran.concat(ar);
            }

            ar = null;
            len = null;

            if (me.config.ticking) {
                setTimeout(function() {
                    rAF(getTransaction);
                }, 1500);
            }
        }

        // 清除交易列表超过40条的信息
        function clearTransaction() {
            while(store.getCount() > 40) {

                if (me.config.roll == 'DOWN') {
                    store.remove(store.getAt(store.getCount() - 1));
                } else {
                    store.remove(store.getAt(0));
                }
            }
            if (me.config.ticking) {
                rAF(clearTransaction);
            }
        }

        rAF(update);
        rAF(getTransaction);
        rAF(clearTransaction);
    },
    
    /**
     * 清除交易列表
     */
    clear : function() {
        var me = this;
        var store = me.getTransactionGrid().getStore();

        store.removeAll();
    }
});