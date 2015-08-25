
Ext.define('Eway.controller.person.UserLog', {
	extend : 'Eway.controller.base.FishController',

	stores: ['person.user.UserLog'],

	models: ['person.user.UserLog'],

	views: ['Eway.view.person.user.ViewUserLog'],

	refs: [{
		ref: 'ewayView',
		selector: '#userLog',
		autoCreate: true,
		xtype: 'person_user_viewUserLog'
	}, {
		ref: 'grid',
		selector: 'userLog_grid'
	},{
		ref :'filterForm',
		selector:'userLogFilterForm'
	}],

	init: function() {
		this.control({
			'person_user_viewUserLog button[action=query]': {
				click: this.onQuery
			}
		});
		this.onQuery();
	},

	onQuery: function (){
		var viewUserLogWin = this.getEwayView();
		var form = viewUserLogWin.down('form').getForm();
		if(form.isValid() == true){
			var values = form.getValues();
			var grid = viewUserLogWin.down('userLog_grid')
			var store = grid.getStore();
			store.setUrlParamsByObject(values);
			store.loadPage(1);
		}else{
			Eway.alert('请正确输入.');
		}
	}
});