Ext.define('Eway.view.parameter.devParameter.ParamGrid', {
	alias : 'widget.parameter_devParameter_paramGrid',
	extend : 'Eway.view.base.Grid',

	border : false,
	autoFit : true,
	selModel: 'cellmodel',
    plugins: {
        ptype: 'cellediting',
        listeners : {
               'beforeEdit' : function(_this, context, eOpts) {
                       if (_this.clicksToEdit == 2) {
                               return false;
                       }
               },
               'validateedit':function( _editor, context, eOpts ){
            	   var argsType = context.record.get("eleParamType");
            	   var regex=/(^([0-9]\d{0,17})$)/;
            	   if(argsType == '1'){
            		   if(!(context.value.match(regex))){
            			   Eway.alert(EwayLocale.param.element.paramName+context.record.get("paramValue")+EwayLocale.param.deviceParam.notMatch);
//                		   _editor.startEditByPosition({
//                			    row: context.rowIdx,
//                			    column: context.colIdx
//                			});
                		   return false;
                	   }
            	   }
//            	   _editor.completeEdit(true);
            	   return true;
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
				action : 'query',
				code : 'devParamQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text : EwayLocale.button.save,
				// glyph : 0xf002,
				action : 'save',
				code : 'devParamSave',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [ {
				header : EwayLocale.param.element.paramName,
				dataIndex : 'paramName',
				width:110,
				sortable:true
			}, {
				header : EwayLocale.param.element.paramValue,
				dataIndex : 'paramValue',
				editor: {
					xtype:'textfield',
					allowBlank:false
				},
				width:130,
				sortable:true
			},{
				header : EwayLocale.param.element.paramRights,
				dataIndex:'eleParamRights',
				width:130,
				sortable:true,
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
				sortable:true,
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
				dataIndex:'eleModifyTime',
				sortable:true,
				flex:1
			}]
		});
		
		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});