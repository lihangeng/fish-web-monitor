Ext.define('Eway.view.version.download.monitor.JobFilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.version_download_monitor_jobfilterForm',
	
	requires : ['Eway.view.version.field.VersionTypeComboBox'],
//	height : 80,
	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			items : [{
				columnWidth : 0.5,
				items : [{
					xtype : 'textfield',
					fieldLabel : EwayLocale.version.View.versionNo,//'版本号',
					name : 'versionNo',
					maxLength: 20
				}]},{
					columnWidth : 0.5,
					items : [{
					xtype:'field_versionTypeComboBox',
					store: Ext.create('Ext.data.Store',{
						fields: ['id', 'typeName'],
						proxy: {
					        type: 'rest',
					        url : 'api/version/versionType/searchCombo',
					        reader: {
					            type: 'json',
					            rootProperty : 'data'
					        }
					    },
					    autoLoad : false						
					})
				}]}
				]
		});
		this.callParent(arguments);
	}

});