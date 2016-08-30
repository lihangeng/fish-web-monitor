Ext.define('Eway.view.cash.initPlan.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.initPlan_View',

	requires : [ 'Eway.view.cash.initPlan.Grid',
	 			'Eway.view.cash.initPlan.FilterForm','Eway.view.cash.initPlan.DetailGrid',
				'Eway.view.cash.initPlan.DetailFilterForm' ],

	title : EwayLocale.initPlan.title,//'加钞规则管理',

    layout: 'card',
	initComponent : function() {
		Ext.apply(this, {
			items:[{
				layout : 'border',
				xtype:'panel',
				itemId:'initPlan',
				items : [ {
						region : 'north',
						xtype : 'initPlan_filterForm'
					}, {
						region : 'center',
						xtype : 'initPlan_grid'
				} ]
				
			},
			{
				layout : 'border',
				xtype:'panel',
				itemId:'initDetailPlan',
				items : [ {
					region : 'north',
					xtype : 'initPlan_detailFilterForm'
					}, {
						region : 'center',
						xtype : 'initPlan_detailGrid'
				} ]
			}],
			listeners:{
				afterrender:function(_this,opt){
					_this.getLayout().setActiveItem("initDetailPlan");
				}
			}
		});

		this.callParent(arguments);
	}
});