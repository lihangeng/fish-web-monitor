Ext.define('Eway.view.parameter.template.UpdateValue', {
	extend : 'Ext.window.Window',
	alias : 'widget.template_updateValue',

	requires : [ 'Eway.view.parameter.template.ParamValueGrid'],

	title : '更改参数值(直接修改)',
	modal : true,
	resizable : false,
	constrainHeader : true,
	layout : 'border',
	width : 800,
	height : 500,
	selModel: 'cellmodel',
	
	viewConfig: {
		
        plugins: [{
            ptype: 'cellediting',
            clicksToEdit: 1
            	
        }],
        
        renderTo: Ext.getBody(),
	},

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'panel',
				trackResetOnLoad : true,
				selectOnFocus : true,
				region : 'center',
				layout : 'border',
				items : [ {
//					title : '可添加参数',
					region : 'west',
					width : 800,
					xtype : 'param_paramValueGrid',
					templateId:this.templateId,
					margin : '0 5px 0 0',
					autoLoadStore : true
				}]
			} ],
			buttonAlign : 'center',
			buttons : [ {
				text : '保存',
				action : 'save'
			},  {
				text : '下发',
				action : 'issue'
			}, {
				text : '取消',
				handler : this.onOver
			}]
		});

		this.callParent(arguments);
	},

	onOver : function() {
		this.up('window').close();
	}
});