
Ext.define('Eway.view.machine.detail.basic.VersionInfo', {
	alias: 'widget.detail_versionInfo',
	extend: 'Eway.view.base.Grid',
	
	initComponent: function() {
		var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
	        groupHeaderTpl: '{name}'
    	});

    	var store = Ext.create("Eway.store.version.DeviceVersionHistory")
		Ext.apply(this, {
			features: [groupingFeature],

			store:store,
			columns : [{
				header:EwayLocale.refs.terminalId,
				dataIndex:'terminalId'
			},{
				header : EwayLocale.refs.ip,//'设备IP',
				dataIndex : 'ip',
				width:120
			},{
				header : EwayLocale.version.View.versionNo,//'版本号',
				dataIndex : 'versionNo',
				width:130
			},{
				header: EwayLocale.version.View.versionFile,//'版本文件',
				dataIndex:'fullName',
				width:150
			},{
				header : EwayLocale.version.task.downloadTime,//'下发时间',
				dataIndex : 'downTime',
				width:150
			},{
				header: EwayLocale.version.task.downloadResult,//'下发结果',
				dataIndex : 'status',
				width:150
			},{
				header: EwayLocale.version.View.remark,
				dataIndex : 'remark',
				flex:1
			}]
		});
		this.callParent(arguments);
	}
});