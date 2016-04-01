Ext.define('Eway.view.parameter.devParameter.ParamGrid', {
	alias : 'widget.parameter_devParameter_paramGrid',
	extend : 'Eway.view.base.Grid',

	border : false,
	autoFit : true,

//    verticalScroller:{  
//    	        xtype:'paginggridscroller'  
//    	  },  

	initComponent : function() {
		var store = Ext.create('Eway.store.parameter.devParameter.ParamInfo',{
	        groupField: 'paramBelongs'
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
				action : 'info'
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
				flex:1
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true,
			})
		});
		
		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});