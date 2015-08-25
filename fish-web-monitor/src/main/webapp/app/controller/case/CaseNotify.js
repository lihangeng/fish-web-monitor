
Ext.define('Eway.controller.case.CaseNotify', {
	extend: 'Ext.app.Controller',

	stores: ['case.Notify','case.NotifyWay'],

	models: ['case.Notify'],

	views: ['case.caseNotify.NotifyView'],

	refs: [{
		ref: 'ewayView',
		selector: '#notify',
		autoCreate: true,
		xtype: 'case_notifyView',
		id: 'notify'
	}, {
		ref: 'grid',
		selector: 'caseNotify_notifyGrid'
	},{
		ref :'filterForm',
		selector:'caseNotify_filterForm'
	}],

	init: function() {
		this.control({
			'caseNotify_notifyGrid button[action=query]': {
				click: this.onQuery
			},
			'caseNotify_notifyGrid pagingtoolbar':{
				beforechange : this.onReflesh
			}
		});
		this.onQuery();
	},

	/**
	 * 根据条件查询
	 */
	onQuery: function(){
		var store = this.getEwayView().down('caseNotify_notifyGrid').getStore();
		var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值
		store.setUrlParamsByObject(data);
		store.loadPage(1);
	},

	/**
	 *  刷新
	 */

	onReflesh: function(){
		var store = this.getEwayView().down('caseNotify_notifyGrid').getStore();
		var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值
		store.setUrlParamsByObject(data);
	}

});