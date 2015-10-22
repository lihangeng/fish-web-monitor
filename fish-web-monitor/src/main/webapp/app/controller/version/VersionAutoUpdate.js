Ext.define('Eway.controller.version.VersionAutoUpdate', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'version.VersionAutoUpdate', 'version.TaskStatus',
			'machine.DeviceAtmType', 'version.ComboVersionType' ],
	models : [ 'version.VersionAutoUpdate' ],
	views : [ 'version.autoUpdate.View', 'version.autoUpdate.FilterForm' ],

	refs : [ {
		ref : 'ewayView',
		selector : 'versionAutoUpdateView',
		autoCreate : true,
		xtype : 'versionAutoUpdateView'
	}, {
		ref : 'grid',
		selector : 'versionupdate_grid'
	}, {
		ref : 'filterForm',
		selector : 'versionupdate_filterForm'
	} ],

	init : function() {
		this.control({
			'versionAutoUpdateView button[action=query]' : {
				click : this.onQuery
			}
		});

	},

	onQuery : function() {
		var view = this.getEwayView();
		var form = view.down('form').getForm();
		var bool = form.isValid();
		if (bool == false) {// 查询输入验证 Eway.locale.tip.search.warn
			Eway.alert( Eway.locale.tip.search.warn);//"查询条件存在错误项.");
			return;
		}
		var store = this.getGrid().getStore();
		var data = this.getFilterForm().getForm().getValues();// 得到所有的查询条件的值
		store.setUrlParamsByObject(data);
		store.loadPage(1);
	}
});
