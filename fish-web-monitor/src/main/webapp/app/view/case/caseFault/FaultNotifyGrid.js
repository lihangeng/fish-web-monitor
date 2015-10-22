
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
				header : Eway.locale.cases.caseFault.createTime,
				dataIndex : 'createTime',
				flex : 1
			}, {
				xtype:'actioncolumn',
				header : Eway.locale.cases.caseFault.informContent,
				dataIndex : 'id',
				items : [{
					icon : 'resources/images/detail.png',
					tooltip: Eway.locale.cases.caseFault.checkDetails,
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
						    title : Eway.locale.cases.caseFault.messageContentDetail,
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
							    		fieldLabel : Eway.locale.cases.caseFault.informContent,
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
				header : Eway.locale.cases.caseFault.informWay,
				dataIndex : 'notifyWay',
				renderer: function(value,metadata,record){
					if(value=="SMS"){
	                 	 return Eway.locale.cases.caseFault.message;
	                }
					else if(value=="MAIL"){
						 return Eway.locale.cases.caseFault.mail;
	                }
					else if(value=="BOTH"){
						return Eway.locale.cases.caseFault.messageAndMail;
					}
				},
				flex : 1
			}, {
				header : Eway.locale.cases.caseFault.informMobile,
				dataIndex : 'mobile',
				flex : 1
			}, {
				header : 'E-Mail',
				dataIndex : 'mail',
				flex : 1
			}, {
				header : Eway.locale.cases.caseFault.notifyTimes,
				dataIndex : 'notifyTimes',
				flex : 1
			}, {
				header : Eway.locale.cases.caseFault.sendTimes,
				dataIndex : 'sendTimes',
				flex : 1
			}, {
				header : Eway.locale.cases.caseFault.sendInterval,
				dataIndex : 'sendInterval',
				flex : 1
			}, {
				header : Eway.locale.cases.caseFault.sendTime,
				dataIndex : 'sendTime',
				flex : 1
			}, {
				header : Eway.locale.commen.terminalId,
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