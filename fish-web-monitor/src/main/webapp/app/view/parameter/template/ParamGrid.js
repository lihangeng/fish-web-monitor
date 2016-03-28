Ext.define('Eway.view.parameter.template.ParamGrid', {
	alias : 'widget.param_paramGrid',
	extend : 'Eway.view.base.Grid',

	store : 'parameter.template.AddingParam',
	border : false,
	autoFit : true,
	id : 'paramGridId',

	viewConfig : {
		plugins : {
			ptype : 'gridviewdragdrop',
			dragGroup : 'paramGridId',
			dropGroup : 'addedParamGridId',
			enableDrop : true
		}
	},


	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			columns : [ {
				header : '参数名称',
				dataIndex : 'name',
				storable : true
			}, {
				header : '参数值',
				dataIndex : 'description',
				flex : 1
			}, {
				header : 'ID',
				dataIndex : 'id',
				hidden : true,
				storable : true
			} ]

		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});