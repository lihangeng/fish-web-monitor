Ext.define('Eway.view.version.download.SelectableDeviceWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.selectableDeviceWindow',
	requires : ['Eway.view.version.download.SelectableDeviceGrid'],

	title : Eway.locale.version.task.chooseTitleDevice,//'选择设备',
	modal : true,
	resizable : false,
	constrainHeader : true,
	width : 500,
	initComponent : function(){
		Ext.apply(this,{
//			html:'aaaaa',
			items:[{
				xtype:'version_download_selectableDeviceGrid'
			}],
			buttons:[{
				text : Eway.locale.button.confirm,//'确定',
				action :'confirm',
				handler: this.onChoose
			},{
				text: Eway.locale.version.task.closeWindow,//'关闭窗口',
				handler : this.onOver
			}]
		});
		this.callParent(arguments);
	},
	onOver : function() {
		this.up('window').close();
	},
	onChoose: function(){
		var addWin = Ext.ComponentQuery.query("version_download_add")[0];
		var linkedGrid = addWin.down("version_download_linkedDeviceGrid");
//		var linkedGrid = Ext.ComponentQuery.query("version_download_linkedDeviceGrid")[0];
		var selectableGrid = this.up("window").down("version_download_selectableDeviceGrid");
		var sm = selectableGrid.getSelectionModel();
		var rows = sm.getSelection();
		
		
	}
});
	