Ext.define('Eway.controller.bsAdvert.BsAdvertGroup', {
	extend : 'Ext.app.Controller',

	stores : [ 'bsAdvert.BsAdvertGroup'],
	models : [ 'bsAdvert.BsAdvertGroup' ],
	views : [ 'bsAdvert.BsAdvertGroupView','bsAdvert.BsAdvertGroupFilterForm','bsAdvert.BsAdvertGroupGrid'],

	refs : [ {
		ref : 'ewayView',//必须,固定值为ewayView
		selector : '#bsadvert', //这里的比较特殊，是对@@处的选择, 在其他地方没有任何定义
		autoCreate : true,//必须
		xtype : 'bs_advert_group_view',//必须
		id : 'bsadvert'//@@
	}, {
		ref : 'grid',
		selector : 'bs_advert_group_grid'//ComponentQuery的query规则
	},{
		ref : 'filterForm',
		selector: 'bs_advert_group_filterform'
	}],

	init : function() {
		 this.control({
			'#bsadvert button[action=query]' : {
				click : this.onQuery
			},
			'#bsadvert button[action=add]' : {
				click : this.onAdd
			}
		});
	},

	//查询
	onQuery : function(){
		var view = this.getEwayView();
		var form = view.down('bs_advert_group_filterform').getForm();
		if(!form.isValid()){
			Eway.alert(EwayLocale.tip.search.warn);
			return ;
		}
		var values = form.getValues();
		var store = view.down('bs_advert_group_grid').getStore();
		store.setUrlParamsByObject(values);
		store.loadPage(1);
	},
	onAdd:function(){
		
	}

});
