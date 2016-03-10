Ext.define('Eway.view.monitor.newTransaction.Grid', {
    alias : 'widget.monitor_new_transaction_grid',
    extend : 'Eway.view.base.Grid',

    store : 'monitor.newTransaction.NewTransaction',
    border : false,
    
    requires : [ 'Eway.view.common.OrgComboOrgTree' ],
                 
    /**
     * 主机返回码不正常显示红色
     *
     * @type
     */
    viewConfig : {
        forceFit : true,
        enableTextSelection : true,
        getRowClass : function(record, index) {

            var hostRet = record.get('hostRet');
            var localRet = record.get('localRet');

            var result = '';
            if (hostRet) {
                result += 'jsnx-grid-host-' + hostRet;
            }

            if (localRet) {
                result += ' jsnx-grid-local-' + localRet;
            }

            return result;
        }
    },

    initComponent : function() {

        var interval = Ext.create('Ext.data.Store', {
            fields : [ 'value', 'display' ],
            data : [ {
                'value' : '2',
                'display' : '1 ' + EwayLocale.monitor.business.newTransaction.timeUnit
            }/*, {
                "value" : "3",
                "display" : "3分钟"
            }, {
                "value" : "4",
                "display" : "5分钟"
            }*/ ]
        });


        Ext.apply(this, {
            initRegion : true,
            tbar : [ {
                fieldLabel : EwayLocale.commen.terminalId,
                xtype : 'textfield',
                name : 'terminalId',
                labelWidth : 80,
                width : 150,
                msgTarget : 'side'
            }, {
                fieldLabel : EwayLocale.monitor.business.newTransaction.interval,
                xtype : 'combobox',
                store: interval,
                name : 'intervalTime',
                queryMode: 'local',
                displayField: 'display',
                valueField: 'value',
                value: '2',
                labelWidth : 120,
                width : 200,
                editable : false
            }/*, {
                xtype: 'radiogroup',
                fieldLabel: EwayLocale.monitor.business.transaction.transactionMonitor.scroll,
                labelWidth : 60,
                width : 170,
                items: [
                    {boxLabel: EwayLocale.monitor.business.transaction.transactionMonitor.up, name: 'rbAuto', inputValue: 'UP', checked: true},
                    {boxLabel: EwayLocale.monitor.business.transaction.transactionMonitor.down, name: 'rbAuto', inputValue: 'DOWN'}
                ]
            }*/, '->', {
               xtype : 'button',
				text : EwayLocale.monitor.business.transaction.transactionMonitor.begin,
				action : 'start',
				glyph : 0xf144,
				code:'newStart',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
            }, {
                xtype : 'button',
				text : EwayLocale.monitor.business.transaction.transactionMonitor.stop,
				glyph : 0xf04c,
				action : 'stop',
				disabled : true,
				code:'newStop',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
            }, {
                xtype : 'button',
                text : EwayLocale.monitor.business.transaction.transactionMonitor.clear,
                glyph : 0xf014,
                action : 'clear',
                code:'newClear',
                listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
            } ],
            columns : [ {
				header : EwayLocale.monitor.business.transaction.dateTime,
				width : 150,
				dataIndex : 'dateTime'
			}, {
				header : EwayLocale.monitor.business.transaction.transCode,
				width : 120,
				dataIndex : 'transCode'
			}, {
				header : EwayLocale.monitor.business.transaction.amt,
				dataIndex : 'amt'
			}, {
				header : EwayLocale.monitor.business.transaction.currency,
				width : 80,
				hidden : true,
				dataIndex : 'currency'
			}, {
				header : EwayLocale.commen.terminalId,
				width : 80,
				dataIndex : 'termId'
			}, {
				header : EwayLocale.monitor.business.transaction.transId,
				dataIndex : 'transId'
			}, {
				header : EwayLocale.monitor.business.transaction.debitAccount,
				width : 180,
				dataIndex : 'debitAccount'
			}, {
				header : EwayLocale.monitor.business.transaction.creditAccount,
				dataIndex : 'creditAccount',
				minWidth : 180
			}, {
				header : EwayLocale.monitor.business.transaction.localRet,
				dataIndex : 'localRet'
			}, {
				header : EwayLocale.monitor.business.transaction.hostRet,
				dataIndex : 'hostRet',
				flex :1
			} ]
        });

        this.callParent(arguments);
    }

});
