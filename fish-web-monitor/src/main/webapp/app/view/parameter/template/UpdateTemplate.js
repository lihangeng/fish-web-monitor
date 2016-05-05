Ext.define('Eway.view.parameter.template.UpdateTemplate', {
	extend: 'Ext.window.Window',
	alias: 'widget.template_updateTemplate',

	requires: ['Eway.view.parameter.template.ParamGrid',
	           'Eway.view.parameter.template.AddedParamGrid',
	           'Eway.view.field.paramElement.ParamBelongsRadioGroup'],

	title:EwayLocale.param.template.updateTitle,
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
					width: 900,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [ {
					fieldLabel : '<font color="red">*</font>'+EwayLocale.param.template.templateName,
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					msgTarget : 'side',
					allowBlank : false
					},{
				    xtype : 'textarea',
				    fieldLabel : EwayLocale.param.template.templateRemark,
				    name : 'remark',
				    autoScroll : true,
					maxLength :30,
					msgTarget : 'side',
					allowBlank : true
					}, {
						xtype:'field_paramElement_ParamBelongsRadioGroup',
						labelWidth: 90,
						width:300,
//						value: 1,
						editable : false,
						name : 'paramBelongsId',
						fieldLabel : EwayLocale.param.element.paramBelongs,
						msgTarget : 'side',
//						labelAlign : 'right',
						checked:true

					},{
					xtype : 'panel',
					trackResetOnLoad : true,
					selectOnFocus : true,
					layout:'column',
					height: 350,
					width : 900,
					region : 'center',
					layout : 'border',
//					autoScroll : true,
					items : [ {
						title : EwayLocale.param.template.paramGridTitle,
						width : 300,
						height: 350,
						region : 'west',
						margin : '0 10px 0 0',
						xtype : 'param_paramGrid',
						autoLoadStore : true
					}, {
						title : EwayLocale.param.template.addedParamGridTitle,
						width : 600,
						height: 350,
						region : 'center',
						xtype : 'param_addedParamGrid',
						listeners:{
							        beforeedit:function(o){
									var grid = o.grid;
									var sm = grid.getSelectionModel();
									var record = sm.getLastSelected();
									var paramRights = record.data.paramRights;
							            if( paramRights =='2'){
							                return false;
							            }
							        }
							    },
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