Ext.define('Eway.view.parameter.template.AddedParamGrid', {
	alias : 'widget.param_addedParamGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	store : 'parameter.template.AddedParam',
	border : false,
	autoFit : true,
	id : 'addedParamGridId',

	viewConfig : {
		plugins : {
			ptype : 'gridviewdragdrop',
			dragGroup : 'addedParamGridId',
			dropGroup : 'paramGridId',
			enableDrop : true
		}
	},

	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			columns : [ {
				header : '参数名称',
				dataIndex : 'paramKey',
				storable : true
			}, {
				header : '参数值',
				dataIndex : 'paramValue',
				flex : 1
			}, {
				header : 'ID',
				dataIndex : 'id',
				hidden : true,
				storable : true
			} ],

			enableColumnMove : true
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().reload();
	}
});