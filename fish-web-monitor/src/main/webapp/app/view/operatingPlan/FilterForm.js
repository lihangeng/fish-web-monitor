Ext.define('Eway.view.operatingPlan.FilterForm', {

	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.operatingPlan_filterForm',

	requires : [ 'Eway.view.field.OpenPlan.PlanStateType' ],

	layout : 'column',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				defaults : {
					labelAlign : 'left'
				},
				columnWidth : .30,
				xtype : 'form',
				items : [ {
					xtype : 'textfield',
					labelWidth:80,
					fieldLabel : EwayLocale.machine.serviceplan.name,
					name : 'name'
				} ]
			}, {
				defaults : {
					labelAlign : 'left'
				},
				columnWidth : .35,
				items : [ {
					xtype : 'datefield',
					fieldLabel : EwayLocale.machine.plan.startDate,
					editable : false,
					name : 'startDate',
					format : 'Y-m-d',
					vtype : 'daterange',
					endDateField : 'endDate'
				} ]
			}, {
				defaults : {
					labelAlign : 'left'
				},
				columnWidth : .35,
				items : [ {
					xtype : 'datefield',
					editable : false,
					fieldLabel : EwayLocale.machine.plan.endDate,
					name : 'endDate',
					format : 'Y-m-d',
					vtype : 'daterange',
					startDateField : 'startDate'
				} ]
			} ]
		});
		this.callParent(arguments);
	}

});