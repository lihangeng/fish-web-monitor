Ext.define('Eway.view.cash.initRule.FilterForm',{
	extend: 'Eway.view.base.FilterForm',
	alias : 'widget.initRule_filterForm',
	requires : [ 'Eway.view.common.OrgComboOrgTree', 'Eway.view.field.device.DeviceAtmType',
	             'Eway.view.field.atmType.DeviceAtmVendorComboBox',
	             'Eway.view.field.atmType.DeviceAtmCatalogComboBox',
	             'Eway.lib.Util'],
	closable:false,
	layout : 'column',
	height: 'auto',
	initComponent : function(){
		var startUsingstate = Ext.create('Ext.data.Store', {
		    fields: ['value', 'name'],
		    data : [
		        {"value":"true", "name":EwayLocale.initRule.status.open},
		        {"value":"false", "name":EwayLocale.initRule.status.close}
		    ]
		});
		Ext.apply(this,{
			items: [ {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right'
				},
				items : [ {
					xtype : 'textfield',
					fieldLabel : EwayLocale.initRule.ruleName,
					name : 'name',
					allowBlank : true,
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right',
					labelWidth:160
				},
				items : [{
					xtype : 'combobox',
					fieldLabel : EwayLocale.initRule.startUsing,
					name:'startUsing',
					editable:false,
					emptyText : EwayLocale.combox.select,
				    store: startUsingstate,
				    queryMode: 'local',
				    displayField: 'name',
				    valueField: 'value'
				}]
			} ]
		});
		this.callParent(arguments);
	}
	
});