Ext.define('Eway.view.version.versionType.FilterForm',{
	extend: 'Eway.view.base.FilterForm',
	alias : 'widget.versionType_filterForm',
	
	closable:false,
	layout : 'column',
	height: 40,
	initComponent : function(){
		Ext.apply(this,{
			items: [{
				columnWidth:.3,
				items : [{
					xtype : 'textfield',
					fieldLabel : EwayLocale.version.View.versionTypeCode,
					maxLength: 64,
					regex : /^[a-zA-Z0-9_-]{1,30}$/,//'软件分类编码',
					name : 'typeName'
				}]
			},{
				columnWidth:.3,
				items : [{
					xtype : 'textfield',
					fieldLabel : EwayLocale.version.View.versionTypeName,//'软件分类名称',
					maxLength: 42,
					name : 'desc'
				}]
			}]
		});
		this.callParent(arguments);
	}
	
});