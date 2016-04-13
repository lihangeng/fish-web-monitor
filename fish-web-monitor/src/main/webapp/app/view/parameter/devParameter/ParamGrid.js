Ext.define('Eway.view.parameter.devParameter.ParamGrid', {
	alias : 'widget.parameter_devParameter_paramGrid',
	extend : 'Eway.view.base.Grid',

	border : false,
	autoFit : true,
	selModel: 'cellmodel',
    plugins: {
        ptype: 'cellediting',
        listeners : {
               'beforeEdit' : function(_this) {
                       if (_this.clicksToEdit == 1) {
                               return false;
                       }
               }
       },
        clicksToEdit: 1
    },
	initComponent : function() {
		var store = Ext.create('Eway.store.parameter.devParameter.ParamInfo',{
	        groupField: 'paramClassify'
		});
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			features: [{
	            ftype: 'grouping'
	        }],
			tbar : [ '->', {
				text : '查询',
				glyph : 0xf002,
				action : 'query'
			},{
				text : '更改',
				glyph : 0xf129,
				action : 'update'
			},{
				text : '保存',
				//glyph : 0xf002,
				action : 'save'
			}],
			columns : [ {
				header : '参数名称',
				dataIndex : 'paramName',
				width:'35%'
			}, {
				header : '参数值',
				dataIndex : 'paramValue',
				flex:1,
				editor: {
					xtype:'textfield',
					allowBlank:false
				}
			}]
		});
		
		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});