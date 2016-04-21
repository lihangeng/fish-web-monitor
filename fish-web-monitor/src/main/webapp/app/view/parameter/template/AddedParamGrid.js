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
				header : '参数名称',
				dataIndex : 'paramName',
				storable : true
			}, {
				header : '参数值',
				dataIndex : 'paramValue',
				flex : 1,
				editor:new Ext.form.TextField({  
	                allowBlank:false
	            })
			},{
				header : '参数权限',
				dataIndex : 'paramRights',
				flex : 1,
				renderer: function(value,metadata,record){
					if(value == 2){
		                	 return '不可编辑';
		             }else {
		                	 return '可以编辑';
		             }
				}
			},{
				header : "参数类型",
				dataIndex : 'paramType',
				flex : 1,
				renderer:function(value,metadata,record){
					if(value==1){
						return "数字";
					}else {
						return "字符串";
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