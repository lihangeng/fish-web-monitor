Ext.define('Eway.view.operatingPlan.FilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.operatingPlan_filterForm',
	
	requires : [ 'Eway.view.field.OpenPlan.PlanStateType'],
	
	layout : 'column',
	
	initComponent : function(){
		Ext.apply(this,{
			items : [{
				defaults : {
					labelAlign : 'left',
					labelWidth : 60
				},
				columnWidth : .30,
				xtype : 'form',
				items : [{
					xtype : 'textfield',
					fieldLabel : EwayLocale.report.serviceplan.name,
					name : 'name'
				}]
			},{defaults : {
				labelAlign : 'left',
				labelWidth : 90
			},
				columnWidth : .35,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : EwayLocale.report.serviceplan.openDate,
					editable : true,
					name : 'startDate',
					format : 'Y-m-d'
				}]
			},{
				defaults : {
					labelAlign : 'left',
					labelWidth : 90
				},
				columnWidth : .35,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					editable : true,
					fieldLabel : EwayLocale.report.serviceplan.closeDate,
					name : 'endDate',
					format : 'Y-m-d'
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});