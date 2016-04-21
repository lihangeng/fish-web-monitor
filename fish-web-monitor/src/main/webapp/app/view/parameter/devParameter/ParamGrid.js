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
                       if (_this.clicksToEdit == 2) {
                               return false;
                       }
               }
       },
        clicksToEdit: 2
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
				text : EwayLocale.button.save,
				// glyph : 0xf002,
				action : 'save'
			}],
			columns : [ {
				header : EwayLocale.param.element.paramName,
				dataIndex : 'paramName',
				width:110
			}, {
				header : EwayLocale.param.element.paramValue,
				dataIndex : 'paramValue',
				editor: {
					xtype:'textfield',
					allowBlank:false
				},
				width:130
			},{
				header : EwayLocale.param.element.paramRights,
				dataIndex:'eleParamRights',
				width:130,
				renderer:function(value,metadata,record){
					if(value==1){
						return EwayLocale.param.element.editable;
					}
					if(value==2){
						return EwayLocale.param.element.uneditable;
					}
				}
			},{
				header:EwayLocale.param.element.paramType,
				dataIndex:'eleParamType',
				width:110,
				renderer:function(value,metadata,record){
					if(value==1){
						return EwayLocale.param.element.integer;
					}
					if(value==2){
						return EwayLocale.param.element.character;
					}
				}
			},{
				header:EwayLocale.param.element.lastModifyTime,
				dataIndex:'eleModifyTiem',
				flex:1
			}]
		});
		
		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});