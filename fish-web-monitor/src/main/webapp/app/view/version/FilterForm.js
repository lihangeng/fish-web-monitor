Ext.define('Eway.view.version.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.version_filterForm',

	height : 50,
	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			items : [{
				columnWidth : .3,
				items : [{
					xtype : 'textfield',
					fieldLabel : '版本号',
					name : 'versionNo',
					maxLength: 20,
					labelWidth:60
				}]},{
				columnWidth : .3,
				items : [{
					xtype : 'combo',
					fieldLabel : '版本状态',
					name : 'versionStatus',
	                store: Ext.StoreMgr.lookup("version.VersionStatus"),
	                queryMode: 'local',
	                valueField : 'value',
	                displayField: 'display',
	                emptyText : Eway.locale.commen.all,
	                editable : false,
					labelWidth:60
				}]},{
				columnWidth : .4,
				items : [{
					xtype : 'combo',
					fieldLabel : '允许自动更新',
					name : 'autoUpdate',
					store: Ext.StoreMgr.lookup("version.AutoUpdate"),
	                queryMode: 'local',
	                valueField : 'value',
	                displayField: 'display',
	                emptyText : Eway.locale.commen.all,
	                editable : false,
					labelWidth:100
				}]}
			 ]
		});
		this.callParent(arguments);
	}

});