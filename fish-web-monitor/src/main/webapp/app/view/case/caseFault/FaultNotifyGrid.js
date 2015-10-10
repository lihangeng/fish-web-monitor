
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
				header : '创建时间',
				dataIndex : 'createTime',
				flex : 1
			}, {
				xtype:'actioncolumn',
				header : '通知内容',
				dataIndex : 'id',
				items : [{
					icon : 'resources/images/detail.png',
					tooltip: '查看详情',
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
						    title : '短信内容详情',
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
							    		fieldLabel : '通知内容',
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
				header : '通知方式',
				dataIndex : 'notifyWay',
				renderer: function(value,metadata,record){
					if(value=="SMS"){
	                 	 return "短信";
	                }
					else if(value=="MAIL"){
						 return "邮件";
	                }
					else if(value=="BOTH"){
						return "短信和邮件";
					}
				},
				flex : 1
			}, {
				header : '通知手机号',
				dataIndex : 'mobile',
				flex : 1
			}, {
				header : 'E-Mail',
				dataIndex : 'mail',
				flex : 1
			}, {
				header : '通知次数',
				dataIndex : 'notifyTimes',
				flex : 1
			}, {
				header : '发送次数',
				dataIndex : 'sendTimes',
				flex : 1
			}, {
				header : '发送时间间隔',
				dataIndex : 'sendInterval',
				flex : 1
			}, {
				header : '发送时间',
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