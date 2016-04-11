Ext.define('Eway.view.parameter.template.UpdateTemplate', {
	extend: 'Ext.window.Window',
	alias: 'widget.template_updateTemplate',
	
	requires: ['Eway.view.parameter.template.ParamGrid','Eway.view.parameter.template.AddedParamGrid'],
	
	title:'修改',
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
				items: [ {
					xtype : 'panel',
					trackResetOnLoad : true,
					selectOnFocus : true,
					layout:'column',
					height: 350,
					width : 600,
					autoScroll : true,
					items : [ {
						title : '可添加参数',
						width : 200,
						height: 350,
						xtype : 'param_paramGrid',
						autoLoadStore : true
					}, {
						title : '已添加的参数',
						width : 400,
						height: 350,
						xtype : 'param_addedParamGrid',
						autoLoadStore : true
					} ]
				} ],
				buttonAlign : 'center',
				buttons: [{
					text: EwayLocale.button.confirm,
					action: 'confirm'
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