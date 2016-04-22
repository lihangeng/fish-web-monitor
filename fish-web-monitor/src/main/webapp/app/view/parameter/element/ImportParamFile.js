Ext.define('Eway.view.parameter.element.ImportParamFile', {
	extend : 'Ext.window.Window',
	alias : 'widget.element_ImportParamFile',

	title : EwayLocale.param.element.import,
	modal : true,
	resizable : false,
	constrainHeader : true,

	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults : {
					width : 400,
					labelWidth : 100,
					labelAlign : 'right',
					msgTarget : 'side'
				},
				items : [
                  {
					xtype : 'filefield',
					buttonText : EwayLocale.combox.explorer,
					fieldLabel : EwayLocale.param.element.importFile,
					emptyText : EwayLocale.param.element.importEmptyText,
					width : 400,
					allowBlank : false,
					name : 'file',
				} ],
				buttonAlign : 'center',
				buttons : [ {
					text : EwayLocale.cases.confirm,
					// iconCls : 'sureBtn',
					action : 'import'
				}, {
					text : EwayLocale.cases.cancel,
					// iconCls : 'returnBtn',
					handler : this.onOver
				} ]
			}
		});

		this.callParent(arguments);
	},

	onReset : function() {
		this.up('form').getForm().reset();
	},

	onOver : function() {
		this.up('window').close();
	}
});