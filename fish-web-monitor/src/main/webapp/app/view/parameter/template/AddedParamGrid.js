Ext.define('Eway.view.parameter.template.AddedParamGrid', {
	alias : 'widget.param_addedParamGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	store : 'parameter.template.AddedParam',
	border : false,
	autoFit : true,

	viewConfig : {
		plugins : {
			ptype : 'gridviewdragdrop',
			dragGroup : 'addedParamGridId',
			dropGroup : 'paramGridId',
			enableDrop : true
		}
	},
	selModel:{selType:'checkboxmodel'},
	
	plugins : {
		ptype : 'cellediting',
		clicksToEdit: 1
	},

	initComponent : function() {
		//复选框选择模式  
		var checkboxSM = new Ext.create('Ext.selection.CheckboxModel', {
			checkOnly : false,
			singleSelect : false
		});
		
		Ext.apply(this, {
			initRegion : true,
			columns : [ {
				header : EwayLocale.param.template.paramName,
				dataIndex : 'paramName',
				storable : true
			}, {
				header : EwayLocale.param.template.paramValue,
				dataIndex : 'paramValue',
				flex : 1,
				editor:new Ext.form.TextField({  
	                allowBlank:false
	            })
			},{
				header : EwayLocale.param.template.paramType,
				dataIndex : 'paramType',
				flex : 1,
				renderer:function(value,metadata,record){
					if(value==1){
						return EwayLocale.param.element.integer;
					}else {
						return EwayLocale.param.element.character;
					}
				}
			},{
				header : EwayLocale.param.template.paramRights,
				dataIndex : 'paramRights',
				flex : 1,
				renderer: function(value,metadata,record){
					if(value == 2){
		                	 return EwayLocale.param.template.editable;
		             }else {
		                	 return EwayLocale.param.template.uneditable;
		             }
				}
			}, {
				header : 'ID',
				dataIndex : 'id',
				hidden : true,
				storable : true
			} ],
			sm: checkboxSM,
			enableColumnMove : true
		});
		

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().reload();
	}
});