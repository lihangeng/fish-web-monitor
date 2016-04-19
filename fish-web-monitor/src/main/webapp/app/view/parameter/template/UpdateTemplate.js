Ext.define('Eway.view.parameter.template.UpdateTemplate', {
	extend: 'Ext.window.Window',
	alias: 'widget.template_updateTemplate',
	
	requires: ['Eway.view.parameter.template.ParamGrid','Eway.view.parameter.template.AddedParamGrid'],
	
	title:'更改参数模板',
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
					width: 700,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [ {
					fieldLabel : '<font color="red">*</font>模板名称',
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					msgTarget : 'side',
					allowBlank : false
					},{
				    xtype : 'textarea',
				    fieldLabel : '模板备注',
				    name : 'remark',
				    autoScroll : true,
					maxLength :30,
					msgTarget : 'side',
					allowBlank : true
					},{
					xtype : 'panel',
					trackResetOnLoad : true,
					selectOnFocus : true,
					layout:'column',
					height: 350,
					width : 700,
					region : 'center',
					layout : 'border',
					autoScroll : true,
					items : [ {
						title : '<font color="black" >可添加参数(使用拖拽的方式)</font>',
						width : 220,
						height: 350,
						region : 'west',
						margin : '0 10px 0 0',
						xtype : 'param_paramGrid',
						autoLoadStore : true
					}, {
						title : '<font color="black">已添加的参数(可编辑的参数可以直接修改)</font>',
						width : 470,
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