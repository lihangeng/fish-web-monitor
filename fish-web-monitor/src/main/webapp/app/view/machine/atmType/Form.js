Ext.define('Eway.view.machine.atmType.Form',{

	extend : 'Eway.view.base.Form',
	alias : 'widget.machine_atmType_form',

	requires: ['Eway.view.field.atmType.Dev_catalog',
	           'Eway.view.field.atmType.Dev_vendor',
	           'Eway.view.field.atmType.Name',
	           'Eway.view.field.atmType.No',
	           'Eway.view.field.atmType.Spec',
	           'Eway.view.field.atmType.Watt',
	           'Eway.view.field.atmType.Weight',
	           'Eway.view.field.atmType.CashtypeComboBox',
	           'Eway.view.field.atmType.DeviceAtmVendorComboBox',
	           'Eway.view.field.atmType.DeviceAtmCatalogComboBox',
	           'Eway.view.field.atmType.DeviceAtmVendorForTypeComboBox'],

	 initComponent : function(){
	 	Ext.apply(this,{
	 		defaults: {
				anchor : '100%',
				labelWidth: 120,
				labelAlign: 'right',
				msgTarget : 'side'
			},
	 		items: [{
				fieldLabel: '<font color="red">*</font> '+EwayLocale.machine.atmType.name,
				xtype : 'field_atmType_name',
				maxLength : 20,
				maxLengthText : EwayLocale.vtype.maxLength20,
				msgTarget : 'side',
				allowBlank : false
			},{
				fieldLabel: '<font color="red">*</font> '+EwayLocale.machine.atmType.devVendorName,
				xtype : 'field_atmType_DeviceAtmVendorForTypeComboBox',
				value : 1,
				allowBlank : false,
				editable : false
			},{
				fieldLabel: '<font color="red">*</font> '+EwayLocale.machine.atmType.devCatalogName,
				xtype : 'field_atmType_DeviceAtmCatalogComboBox',
				value : 2,
				allowBlank : false,
				editable : false
			},{
				fieldLabel: EwayLocale.machine.atmType.cashtype,
				xtype : 'field_atmType_cashtypeComboBox',
				value: '1',
				allowBlank : false,
				editable : false
			},
			{
				xtype: 'checkboxgroup',
	            fieldLabel: EwayLocale.machine.atmType.modules,//'该类型包含的设备模块',
	            labelWith:120,
	            columns: 3,
	            loader : {//使用自定义的加载方式
					autoLoad: false,
					url : 'api/machine/atmType/atmModule',
					params : {
						atmTypeId : 0//this.up('form').getForm().findField('id').getValue()
					},
					renderer:	function(loader, response, active){
						var success = true,
		                    target = loader.getTarget(),
		                    items = [];
		                try {
		                	var text = Ext.decode(response.responseText);
		                	if(Ext.isString(text.data)){
		                		items = Ext.decode(text.data);//解析从后台返回的菜单列表是字符串的情况
		                	}else{
		                		items = text.data;
		                	}
		                } catch (e) {
		                    success = false;
		                }

		                if (success) {
		                    if (active.removeAll) {
		                        target.removeAll();
		                    }
		                    target.add(items);
		                }
		                return success;
					}
				}

			}
		]
	 	});
	 	this.callParent(arguments);
	 }
});