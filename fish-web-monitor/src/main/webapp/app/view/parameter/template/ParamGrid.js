Ext.define('Eway.view.parameter.template.ParamGrid', {
	alias : 'widget.param_paramGrid',
	extend : 'Eway.view.base.Grid',

	store : 'parameter.template.AddingParam',
	border : false,
	autoFit : true,

	viewConfig : {
		plugins : {
			ptype : 'gridviewdragdrop',
			dragGroup : 'paramGridId',
			dropGroup : 'addedParamGridId',
			enableDrop : true
		}
	},
	selModel:{selType:'checkboxmodel'},

	
	initComponent : function() {
		//复选框选择模式  
		var checkboxSM = new Ext.create('Ext.selection.CheckboxModel', {
			checkOnly : false,
			singleSelect : false
		});
		
		Ext.apply(this, {
			initRegion : true,
			frame: true,  
			columns : [
			    {
				header : '参数名称',
				dataIndex : 'paramName',
				flex : 1,
				storable : true
			}, {
				header : 'ID',
				dataIndex : 'id',
				hidden : true,
				storable : true
			} ],
		    stripeRows: true,  
		    sm: checkboxSM  

		});
		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});