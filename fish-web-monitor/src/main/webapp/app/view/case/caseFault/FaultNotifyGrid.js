
Ext.define('Eway.view.case.caseFault.FaultNotifyGrid', {
	alias: 'widget.caseFault_faultNotifyGrid',
	extend: 'Eway.view.base.Grid',

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.case.Notify');
		Ext.apply(this,{
			initRegion : true,
			store : store,
			columns : [{
				header : EwayLocale.cases.caseFault.createTime,
				dataIndex : 'createTime',
				flex : 1
			}, {
				xtype:'actioncolumn',
				header : EwayLocale.cases.caseFault.informContent,
				dataIndex : 'id',
				items : [{
					icon : 'resources/images/icon_email.gif',
					tooltip: EwayLocale.cases.caseFault.checkDetails,
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var result = record.get('id');
						if(result != null){
							return 'changeCursor';
						}
						else {
							return 'hiddenComp';
						}
					},
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var content = record.get('content');

						var aboutInfoWin = Ext.create('Ext.window.Window', {
						    height:150,
						    width: 500,
						    title : EwayLocale.cases.caseFault.messageContentDetail,
						    layout: 'border',
						    items: [{
							    	xtype : 'form',
							    	border : 0 ,
							    	region:'center',
							    	defaults: {
										width: 400,
										labelWidth: 100,
										labelAlign: 'center',
										msgTarget : 'center'
									},
							    	items : [{
							    		fieldLabel : EwayLocale.cases.caseFault.informContent,
										xtype : 'displayfield',
										name : 'content',
										value :content,
										allowBlank : false
									}]
								}]
						});
						aboutInfoWin.show();
					},
					scope : this
				}],
			}, {
				header : EwayLocale.cases.caseFault.informWay,
				dataIndex : 'notifyWay',
				renderer: function(value,metadata,record){
					if(value=="SMS"){
	                 	 return EwayLocale.cases.caseFault.message;
	                }
					else if(value=="MAIL"){
						 return EwayLocale.cases.caseFault.mail;
	                }
					else if(value=="BOTH"){
						return EwayLocale.cases.caseFault.messageAndMail;
					}
				},
				flex : 1
			}, {
				header : EwayLocale.cases.caseFault.informMobile,
				dataIndex : 'mobile',
				flex : 1
			}, {
				header : 'E-Mail',
				dataIndex : 'mail',
				flex : 1
			}, {
				header : EwayLocale.cases.caseFault.notifyTimes,
				dataIndex : 'notifyTimes',
				flex : 1
			}, {
				header : EwayLocale.cases.caseFault.sendTimes,
				dataIndex : 'sendTimes',
				flex : 1
			}, {
				header : EwayLocale.cases.caseFault.sendInterval,
				dataIndex : 'sendInterval',
				flex : 1
			}, {
				header : EwayLocale.cases.caseFault.sendTime,
				dataIndex : 'sendTime',
				flex : 1
			}, {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});