Ext.define('Eway.view.parameter.template.Update', {
	extend : 'Ext.window.Window',
	alias : 'widget.template_update',

	requires : [ 'Eway.view.parameter.template.ParamGrid',
//		           'Eway.view.field.person.UserType',
			'Eway.view.parameter.template.AddedParamGrid' ],

	title : '更改参数模板（使用拖拽的方式）',
	modal : true,
	resizable : true,
	constrainHeader : true,
	layout : 'border',
	width : 800,
	height : 500,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
//				fieldLabel : '<font color="red">*</font> 用户类型',
//				xtype : 'field_userType',
//				region : 'north',
//				name:'userType',
//				allowBlank : false
//			},{
				xtype : 'panel',
				trackResetOnLoad : true,
				selectOnFocus : true,
				region : 'center',
				layout : 'border',
				items : [ {
					title : '可添加参数',
					region : 'west',
					width : 400,
					xtype : 'param_paramGrid',
					margin : '0 5px 0 0',
					autoLoadStore : true
				}, {
					title : '已添加的参数',
					region : 'center',
					xtype : 'param_addedParamGrid',
					autoLoadStore : true
				} ]
			} ],
			buttonAlign : 'center',
			fbar : [ {
				text : EwayLocale.button.back,
				// iconCls :'returnBtn',
				handler : this.onOver
			} ]
		});

		this.callParent(arguments);
	},

	onOver : function() {
		this.up('window').close();
	}
});