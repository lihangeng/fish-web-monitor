Ext.define('Eway.controller.monitor.newTransaction.NewTransactionMonitor', {
    extend : 'Eway.controller.base.FishController',

    stores : [ 'monitor.newTransaction.NewTransaction',
               'monitor.newTransaction.TransType',
               'monitor.newTransaction.HostRet',
               'monitor.newTransaction.TransactionTPS',
               'monitor.newTransaction.Chart',
               'monitor.transaction.colorset.ColorSet' ],

    models : [ 'monitor.newTransaction.NewTransaction',
               'monitor.transaction.TransType',
               'monitor.transaction.HostRet',
               'monitor.newTransaction.TransactionTPS',
               'monitor.newTransaction.Chart', 
               'monitor.transaction.colorset.ColorSet' ],

    views : [ 'monitor.newTransaction.View', 
              'Eway.view.monitor.transaction.colorset.Describe' ],

    refs : [ {
        ref : 'ewayView',
        selector : 'monitor_new_transaction_view',
        autoCreate : true,
        xtype : 'monitor_new_transaction_view'
    }, {
//        ref : 'transactionGrid',
        ref : 'newTransactionGrid',
        selector : 'monitor_new_transaction_grid'
    }, {
//        ref : 'fiveMinutesGrid',
        ref : 'rightGrid',
        selector : 'monitor_new_transaction_rightgrid'
    }, {
        ref: 'desWin',
        selector: 'monitor_transaction_colorset_describe'
    } ],

    config : {
        ticking : true, // 是否持续滚动
        roll : 'DOWN', // 滚屏方式：DOWN向下,UP向上
        gridBodyId : '', // 交易数据表格bodyID
        transType: '', // 交易类型
        hostRet:'', // 主机返回码
    },
    
    init : function() {
        this.control({
            'monitor_new_transaction_grid button[action=start]' : {
                click : this.startMonitor
            },
            'monitor_new_transaction_grid button[action=stop]' : {
                click : this.stopMonitor
            },
            'monitor_new_transaction_view ' : {
                close : this.stopMonitor,
                deactivate : this.stopMonitor,
                activate : this.initLoad,
                render : this.initUserParam
            },
            'monitor_new_transaction_grid button[action=clear]' : {
                click : this.clear
            },
			'monitor_new_transaction_grid radiogroup' : {
			    change : this.onChangeRadio,
			    scope : this
			}
        });
    },

	
    /**
     * 改变滚动方式
     */
	onChangeRadio : function( This, newValue, oldValue, eOpts ) {

     	this.config.roll = newValue.rbAuto;

        if (this.config.roll == 'DOWN') {
            // 得到需要刷新的body区域id
            if (!this.config.gridBodyId) {
                this.config.gridBodyId = this.getNewTransactionGrid().body.dom.firstChild.id;
            }
            // 滚动条自动刷新到最上面
			Ext.get(this.config.gridBodyId).scroll('t', 1000);
        }

    },


    /**
     * 初始化
     */
    initLoad : function() {
         this.initStyle();
    },

    /**
     * 初始化主机返回码、本地返回码样式
     */
    initStyle : function() {

        // 交易颜色控制
        Ext.Ajax.request({
            url: 'api/monitor/transaction/color/list',
            success: function(response){

                var object = Ext.decode(response.responseText);
                if(object.success == true) {

                    // 删除之前的样式
                    var ce = document.getElementById('transactionColorId');
                    if (ce) {
                        document.documentElement.removeChild(ce);
                    }

                    // 创建新的样式
                    var div = document.createElement('div');
                    div.id = "transactionColorId";
                    var styleStr = '<style>';

                    var listData = object.data;

                    // 主机返回码颜色
                    Ext.Array.each(listData,function(iter,index,dataSelf){
                        if (iter.hostRet) {
                            styleStr += '.x-grid-row.jsnx-grid-host-' + iter.hostRet + ' .x-grid-cell{background-color : ' + iter.backgroundColor + ';'+
                            'color : ' + iter.fontColor + '}';
                        }
                    });

                    // 本地返回码颜色(本地返回码颜色优先)
                    Ext.Array.each(listData,function(iter,index,dataSelf){
                        if (iter.localRet) {
                            styleStr += '.x-grid-row.jsnx-grid-local-' + iter.localRet + ' .x-grid-cell{background-color : ' + iter.localBackgroundColor + ';'+
                            'color : ' + iter.localFontColor + '}';
                        }

                    });

                    styleStr +='</style>';
                    div.innerHTML = styleStr;
                    document.documentElement.appendChild(div);
                }
            },
            failure : function(){
            }
        });

    },

    /**
     * 参数初始化
     */
    initUserParam : function() {
        
        this.listTran = new Array();
        this.list200Tran = new Array();
        
        this.initTransParam();
        
        var me = this;
        
        Ext.Ajax.request({
            url: 'api/msg/transaction/queryTransType',
            success: function(response){
                var object = Ext.decode(response.responseText);
                me.config.transType = object;
            }
        });

        Ext.Ajax.request({
            url: 'api/msg/transaction/queryHostRet',
            success: function(response){
                var object = Ext.decode(response.responseText);
                me.config.hostRet = object;
            }
        });
    },
    
    /**
     * 统计类数据
     */
    initTransParam : function() {
        
        this.tranTotal = 0; // 交易总数
        this.localTotal = 0; // 本行卡交易总数
        this.otherTotal = 0; // 他行卡交易总数　
        
        this.localWithdrawl = 0; // 本行卡取款成功数
        this.localDeposit = 0; // 本行卡存款数
        this.localTfr = 0; // 本行卡转帐数
        this.localQuery = 0; // 本行卡查询数
        
        this.creditWithdrawl = 0; // 贷记卡取款数
        this.creditDeposit = 0; // 贷记卡存款数
        this.creditQurey = 0; // 贷记卡查询数
        
        this.otherWithdrawl = 0; // 他行卡取款数
        this.otherTfr = 0; // 他行卡转帐
        this.otherQuery = 0; // 他行卡查询余额数
    },
    

    /**
     * 启动监控
     */
    startMonitor : function() {
    	var me = this;
    	var view = me.getEwayView();
    	
    	var grid = me.getNewTransactionGrid();
    	grid.down('button[action="start"]').setDisabled(true);
		
		grid.down('button[action="stop"]').setDisabled(false);
       		        
        var terminalId = view.down('textfield[name=terminalId]');
        
        var data = {
            terminalId : terminalId.getValue()
        };
        
        me.cometdConnect(data);
        me.startrAf();
        me.startTask();
        me.changeField(true);
    },

    /** 
     * 停止监控
     */
    stopMonitor : function() {

        this.getNewTransactionGrid().down('button[action="start"]').setDisabled(false);
        this.getNewTransactionGrid().down('button[action="stop"]').setDisabled(true);

        // 删除缓存中的交易数据
        this.listTran = [];

        // 停止执行
        this.config.ticking = false;

        // 停止监控后,开放条件
        this.changeField(false);

        this.stopTask();

        this.cometdDisconnect();
    },
    
    /**
     * 将fieldtext控件根据参数进行disabled
     */
    changeField : function(bool) {
        var view = this.getEwayView();
        
        
        var terminalId = view.down('textfield[name=terminalId]');
        var intervalTime = view.down('combobox[name=intervalTime]');
        
        terminalId.setDisabled(bool);
        intervalTime.setDisabled(bool);
        
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
            organizationId : ewayUser.getOrgId(), // 用户登录所属机构
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


    /**
     * 处理服务器推送过来的数据
     */
    onReceive : function(message) {
        var data = Ext.decode(message.data);

        this.tranTotal++;

        var cardType = data.cardType;
        var transType = data.transCode;
        
        if (cardType == 'LOCAL_BANK') {
            
            this.localTotal++;
            if (transType == 'CWD') { // 取款
                this.localWithdrawl++; 
                
            } else if (transType == 'DEP') { // 存款
                this.localDeposit++; 
                
            } else if (transType == 'INQ') { // 查询
               this.localQuery++; 
                
            } else if (transType == 'TFR') { // 转帐
              	this.localTfr++;
            } 
        } else if (cardType == 'LOCAL_CREDIT') {
            
            this.localTotal++;
            if (transType == 'CWD') {  // 取款
               this.creditWithdrawl++; 
                
            } else if (transType == 'DEP') { // 存款
               this.creditDeposit++;
                
            } else if (transType == 'INQ') { // 查询余额
                this.creditQurey++;
            } 
            
        } else if (cardType == 'OTHER_BANK') { // 他行卡
            
            this.otherTotal++;
            if (transType == 'CWD') {  // 取款
                this.otherWithdrawl++; 
                
            } else if (transType == 'TFR') { // 转帐
               this.otherTfr++;
               
            } else if (transType == 'INQ') { // 查询余额
               this.otherQuery++;
            } 
        }

        Ext.Array.each(this.config.hostRet.data,function(iter,index,dataSelf){
            if(data.hostRet == iter.hostRet){
                data.hostRetName = iter.name;
                // 找到符合条件的,返回false跳出所有循环
                return false;
            }
        });
        Ext.Array.each(this.config.transType.data,function(iter,index,dataSelf){
            if(data.transCode == iter.transCode) {
                data.transCode = iter.codeDesc;
                // 找到符合条件的,返回false跳出所有循环
                return false;
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
        var store = me.getNewTransactionGrid().getStore();

        // 始终执行
        me.config.ticking = true;

        // 更新交易情况
        function update() {
            var ar = me.list200Tran.splice(0, 1);
            Ext.Array.each(ar, function(iter, index, dataSelf) {
				var record = Ext.create('Eway.model.monitor.newTransaction.NewTransaction', iter);

                if (me.config.roll == 'DOWN') {
                    store.insert(0, record);
                } else {
                   store.add(record);
                    // 得到需要刷新的body区域id
                    if (!me.config.gridBodyId) {
                        me.config.gridBodyId = me.getNewTransactionGrid().body.dom.firstChild.id;
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

    clear : function() {
        var me = this;
        var store = me.getNewTransactionGrid().getStore();

        store.removeAll();
    },

    showMax : function(btn) {
        var viewportView = Ext.get('viewport_north');
        var ewayView = this.getEwayView();
        var workspace = ewayView.up('workspace');


        if(btn.hasCls('.showMax')) {

            btn.setText('全屏');

            // 当前已经是最大化
            btn.removeCls('.showMax');

            viewportView.show();
            workspace.getEl().down('div').show();
            workspace.setHeight($(window).height());
        } else {

            btn.setText('还原');

            // 当前是正常化
            btn.addCls('.showMax');

            viewportView.hide();
            workspace.getEl().down('div').hide();
            workspace.setHeight($(window).height());
        }
    },

    stopTask : function() {
        
        // 停止监控后同时停止对交易成功率及承兑率的统计
        if (this.fun_timeout) {
            window.clearTimeout(this.fun_timeout);
        }
        
    },
    
    startTask : function() {
        
        this.startChartTask();
      
    },
    
    startChartTask : function() {
        
        var chart, timeAxis, numericAxis;
        
        // 初始时都重置为0
        this.initTransParam();
        
        var view = this.getEwayView();
        
        chart = view.down('#chartCmp');
        numericAxis = chart.getAxes()[0];
        timeAxis = chart.getAxes()[1];
        
        chart.getStore().removeAll();
        
        var newValue = view.down('combobox[name=intervalTime]').getValue();
        
        var date = new Date();
        var intervalTime = 0;
        this.data = [];
        
        if (newValue == '2') {
            // "display" : "1分钟"
        	timeAxis.setStep([Ext.Date.MINUTE, 1]);
            timeAxis.setFromDate(date);
            timeAxis.setToDate(Ext.Date.add(date, Ext.Date.MINUTE, 2));
            
            intervalTime = 60;
            
        } else if (newValue == '3') {
            // "display" : "3分钟"
            timeAxis.setStep([Ext.Date.MINUTE, 3]);
            timeAxis.setFromDate(date);
            timeAxis.setToDate(Ext.Date.add(date, Ext.Date.MINUTE, 45));
            
            intervalTime = 180;
            
        } else if (newValue == '4') {
            // "display" : "5分钟"
        	timeAxis.setStep([Ext.Date.MINUTE, 5]);
            timeAxis.setFromDate(date);
            timeAxis.setToDate(Ext.Date.add(date, Ext.Date.MINUTE, 75));
            
            intervalTime = 300;
        }
        
        chart.markerIndex = 0;
        
        this.runTask(chart, timeAxis, numericAxis, intervalTime, date);
    },
    
    
    runTask : function(tChart, tTimeAxis, tNumericAxis, tIntervalTime, tDate) {
        var me = this;
//        try {
//            me.tpsStart(tChart, tTimeAxis, tNumericAxis, tIntervalTime, tDate);    
//        } catch(exception) {
//        }
//
//        try {
//            me.avgStart(tIntervalTime);  
//        } catch(exception) {
//        }
        me.tpsStart(tChart, tTimeAxis, tNumericAxis, tIntervalTime, tDate); 
        me.avgStart(tIntervalTime);  
        
        // 计算下一次执行时间
        tDate = Ext.Date.add(tDate, Ext.Date.SECOND, tIntervalTime);
        var cDate = Ext.Date.add(new Date(), Ext.Date.SECOND, tIntervalTime);
        var timeout = cDate.getTime() - tDate.getTime();
        this.fun_timeout = window.setTimeout(function() {
            if (!me.config.ticking) { // 是否需要继续执行
                return;
            }
            me.runTask(tChart, tTimeAxis, tNumericAxis, tIntervalTime, tDate);
            
        }, (tIntervalTime * 1000 - timeout));
    },
    
    
    
    // TPS图形刷新
    tpsStart : function(chart, timeAxis, numericAxis, intervalTime, date) {
        var newDate = date;
        var store = chart.getStore();
        
        var min = Math.min,
            max = Math.max,
            random = Math.random;
        
        var gs = {
            date:  newDate, // 时间
            trans_total_tps: min(250, this.avgValue(this.tranTotal, intervalTime)), // 总交易tps
            local_total_tps: min(250, this.avgValue(this.localTotal, intervalTime)), // 本行卡交易tps
            other_total_tps: min(250, this.avgValue(this.otherTotal, intervalTime)) // 他行卡交易tps
        }
        
//        this.index = (this.index || 1) + 1;
//        var gs = {
//            date:  new Date(), // 时间
//            trans_total_tps: this.index < 20 ? 140 : this.index < 40 ? 180 : this.index < 60 ? 245 : 90, // 总交易tps
//            local_total_tps: this.index < 20 ? 125 : this.index < 40 ? 170 : this.index < 60 ? 230 : 60, // 本行卡交易tps
//            other_total_tps: this.index < 20 ? 115 : this.index < 40 ? 160 : this.index < 60 ? 210 : 30 // 他行卡交易tps
//        }
        
        var len = this.data.length;
        if (len > 20) {
            this.data.splice(0, 1);
        }
        this.data.push(gs);
        
        var toDate = timeAxis.getToDate(),
            lastDate = gs.date,
            markerIndex = chart.markerIndex || 0;
        
        
        var tempLastDate = Ext.Date.add(lastDate, Ext.Date.SECOND, intervalTime);
        
        if (+toDate < +tempLastDate) {
            markerIndex = 1;
            timeAxis.setToDate(tempLastDate);
            timeAxis.setFromDate(Ext.Date.add(Ext.Date.clone(timeAxis.getFromDate()), Ext.Date.SECOND, intervalTime));
            chart.markerIndex = markerIndex;
        }
        
        
        var defaultNumberMax = 30; // 默认为30
        Ext.Array.each(this.data, function(data, index, countriesItSelf) {
            if (data.trans_total_tps > defaultNumberMax) {
                defaultNumberMax = data.trans_total_tps;
            }
        });
        
        var numberMax = defaultNumberMax - (defaultNumberMax % 10) + 10;
        var currentNum = numericAxis.maximum;
        if (currentNum != numberMax) {
            numericAxis.maximum = numberMax;
        }
        
        store.loadData(this.data);
    },
    
    // 平均值统计刷新
    avgStart : function(intervalTime) {
        var gridData = [];
        
        var formDate = new Date();
        var toDate = Ext.Date.add(formDate, Ext.Date.SECOND, -intervalTime)
        
        gridData.push({
            text: EwayLocale.monitor.business.newTransaction.time,
            successfly: Ext.Date.format( toDate, 'H:i' ) + '-' + Ext.Date.format( formDate, 'H:i' )
        });
        
        var tpsT = 0;
        if (this.tranTotal <= 0) {
        	tpsT =  '0 / 0';
        } else {
        	var val = ( this.tranTotal / intervalTime ).toFixed(0);
	        if (val <= 1 ) {
	            // 当时间段时有发生交易,但总tps又没有达到1，则默认tps为1笔
	            val = 1;
	        }
	        tpsT =  this.tranTotal + ' / ' + val;
        }
        
        gridData.push({
            text: EwayLocale.monitor.business.newTransaction.total,
            successfly: tpsT
         });
          
        gridData.push({
            text: EwayLocale.monitor.business.newTransaction.localW,
            successfly: this.localWithdrawl
         });
        
        gridData.push({
            text: EwayLocale.monitor.business.newTransaction.localD,
            successfly: this.localDeposit
         });
         
         gridData.push({
            text: EwayLocale.monitor.business.newTransaction.localT,
            successfly: this.localTfr
         });
        
        gridData.push({
            text: EwayLocale.monitor.business.newTransaction.localQ,
            successfly: this.localQuery
         });
        
        gridData.push({
            text: EwayLocale.monitor.business.newTransaction.creditW,
            successfly: this.creditWithdrawl
         });
        
        gridData.push({
            text: EwayLocale.monitor.business.newTransaction.creditD,
            successfly: this.creditDeposit
         });
        
        gridData.push({
            text: EwayLocale.monitor.business.newTransaction.creditQ,
            successfly: this.creditQurey
         });
        
        gridData.push({
            text: EwayLocale.monitor.business.newTransaction.otherW,
            successfly: this.otherWithdrawl
         });
         gridData.push({
            text: EwayLocale.monitor.business.newTransaction.otherT,
            successfly: this.otherTfr
         });
         gridData.push({
             text: EwayLocale.monitor.business.newTransaction.otherQ,
             successfly: this.otherQuery
          });
        
         var fiveGrid = this.getRightGrid();
         var fiveStore = fiveGrid.getStore();

         fiveStore.removeAll();
         fiveStore.loadData(gridData);
         
         // 重置为0
         this.initTransParam();
    },
    
    
    
    avgValue : function(form, to) {
        
        if (form <= 0) {
            return 0;
        }
        
        var val = (form / to).toFixed(0);
        if (val <= 1 ) {
            return 1;
        }
        
        return val;
    }
    
});
