Ext.define('Eway.view.parameter.template.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.template_add',
	
	requires: ['Eway.view.parameter.template.ParamGrid','Eway.view.parameter.template.AddedParamGrid','Eway.view.field.paramElement.ParamBelongs'],
	
	title:'新增',
	modal: true,
	resizable: false,
	constrainHeader: true,
	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 30px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 600,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '<font color="red">*</font>模板名称',
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					msgTarget : 'side',
					allowBlank : false
				}, {
					fieldLabel : '<font color="red">*</font>归属系统',
					xtype : 'field_paramElement_ParamBelongs',
					name : 'paramBelongsId',
					value: 1,
					editable : false,
					allowBlank : false
				},{
				    xtype : 'textarea',
				    fieldLabel : '模板备注',
				    name : 'remark',
				    autoScroll : true,
					maxLength :30,
					msgTarget : 'side',
					allowBlank : true
				}, {
					xtype : 'panel',
					trackResetOnLoad : true,
					selectOnFocus : true,
					layout:'column',
					height: 350,
					width : 600,
					autoScroll : true,
					items : [ {
						title : '可添加参数',
//						region : 'west',
						width : 300,
						height: 350,
						xtype : 'param_paramGrid',
						autoLoadStore : true
					}, {
						title : '已添加的参数',
//						region : 'east',
						width : 300,
						height: 350,
						xtype : 'param_addedParamGrid',
						autoLoadStore : true
					} ]
				} ],
				buttonAlign : 'center',
				buttons: [{
					text: EwayLocale.button.confirm,
					action: 'add'
				},{
					text: EwayLocale.button.reset,
					handler: this.onReset,
					hidden : true
				},{
					text: EwayLocale.button.cancle,
					handler: this.onOver
				}]
			}
		});
		this.callParent(arguments);
	},
	
	onReset: function() {
		this.up('form').getForm().reset();
	},
	
	onOver: function() {
		this.up('window').close();
	}
});