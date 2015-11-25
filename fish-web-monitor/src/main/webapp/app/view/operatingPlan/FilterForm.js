Ext.define('Eway.view.operatingPlan.FilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.operatingPlan_filterForm',
	
	requires : [ 'Eway.view.field.OpenPlan.PlanStateType'],
	
	layout : 'column',
	
	initComponent : function(){
		Ext.apply(this,{
			items : [{
				defaults : {
					labelAlign : 'left'
				},
				columnWidth : .30,
				xtype : 'form',
				items : [{
					xtype : 'textfield',
					fieldLabel : EwayLocale.machine.serviceplan.name,
					name : 'name'
				}]
			},{defaults : {
				labelAlign : 'left'
			},
				columnWidth : .35,
				items : [{
					xtype : 'datefield',
					fieldLabel : EwayLocale.machine.serviceplan.openDate,
					editable : false,
					name : 'startDate',
					format : 'Y-m-d',
					vtype : 'daterange',
					endDateField : 'endDate'
				}]
			},{
				defaults : {
					labelAlign : 'left'
				},
				columnWidth : .35,
				items : [{
					xtype : 'datefield',
					editable : false,
					fieldLabel : EwayLocale.machine.serviceplan.closeDate,
					name : 'endDate',
					format : 'Y-m-d',
					vtype : 'daterange',
					startDateField : 'startDate'
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});