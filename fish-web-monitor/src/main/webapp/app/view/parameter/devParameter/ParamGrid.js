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
	            ftype: 'grouping',
	            groupHeaderTpl: '{name}'
	        }],
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			},{
				text : EwayLocale.button.update,
				glyph : 0xf129,
				action : 'update'
			},{
				text : EwayLocale.button.save,
				//glyph : 0xf002,
				action : 'save'
			}],
			columns : [ {
				header : EwayLocale.param.element.paramName,
				dataIndex : 'paramName',
				width:'35%'
			}, {
				header : EwayLocale.param.element.paramValue,
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