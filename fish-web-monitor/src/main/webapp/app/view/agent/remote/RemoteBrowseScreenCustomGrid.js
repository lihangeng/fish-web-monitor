Ext.define('Eway.view.agent.remote.RemoteBrowseScreenCustomGrid', {
	extend: 'Eway.view.base.Grid',
	alias: 'widget.remote_remoteBrowseScreenCustomGrid',
	
	store: 'agent.RemoteBrowseScreenCustom',
	
	border: false,
	autoFit: true,
	columnLines: true, 
	viewConfig: {
		loadMask:false //为了解决me.loadMask.bindStore(store)错误的问题
	},
	
	initComponent: function(){
		Ext.apply(this, {
			initRegion : true,
			tbar : [/*{
				xtype : 'button',
				text : '客户前屏',
				action : 'custom',
				xtype : 'button'
			},{
				xtype : 'button',
				text : '管理后屏',
				action : 'admin',
				xtype : 'button'
			},{
				xtype : 'button',
				text : '广告屏',
				action : 'advertise',
				xtype : 'button'
			}*/{
				xtype : 'textfield',
				name : 'ip',
				hidden: true
			},{
				xtype : 'textfield',
				name : 'code',
				hidden : true
			}],
			columns : [/*{
				header : '屏幕类型',
				sortable : true,
				dataIndex : 'monitorType',
				flex: 1,
				renderer: function(value){
					if(value == 'CUSTOM'){
						return '客户前屏';
					}else if(value == 'ADVERTISE'){
						return '广告屏';						
					}else if(value == 'ADMIN'){
						return '管理屏'
					}
				}
			},*/{
				header: EwayLocale.agent.remote.screen.fileNameClient,
				sortable: true,
				dataIndex: 'fileNameClient',
				flex: 3
			},{
				header: EwayLocale.agent.remote.screenShotTime,
				sortable: true,
				dataIndex: 'screenShotTime',
				flex: 2
			},{
				header: EwayLocale.agent.remote.screen.manage,
				xtype:'actioncolumn',
				flex : 1,
				dataIndex : 'backupResult',
				items : [{
					icon : 'resources/images/down.gif',
					tooltip: EwayLocale.agent.remote.screen.loading,
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var value = record.data.allPath;
						/*var fileName = value.substring(13);
						var index = value.lastIndexOf('resources');
						var newValue = value.substring(index);
						var string = 'http://localhost:8085/fish-web/';
						var path = string + newValue;
						var body = Ext.getBody();*/
						
						var itemEl = grid.getEl();
						var iframe = itemEl.prev();
						if(iframe){
							Ext.core.Element.get(iframe).destroy();
						}
						iframe = Ext.core.DomHelper.createDom({
							tag : 'iframe',
							src : 'api/agent/screenshot/downloadScreen?path='+value,
							style : "display:none"
						});
						itemEl.insertSibling(iframe);
					},
					scope : this
				}]
			}]
		});
		this.callParent(arguments);
	}
});