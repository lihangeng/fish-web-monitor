Ext.define('Eway.view.operatingPlan.FilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.operatingPlan_filterForm',
	
	requires : [ 'Eway.view.field.OpenPlan.PlanStateType'],
	
	layout : 'column',
	
	initComponent : function(){
		Ext.apply(this,{
			items : [{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					xtype : 'textfield',
					fieldLabel : Eway.locale.report.openplan.name,
					name : 'name'
				}]
			},{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : Eway.locale.report.openplan.openDate,
					editable : true,
					name : 'startDate',
					format : 'Y-m-d'
				}]
			},{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					editable : true,
					fieldLabel : Eway.locale.report.openplan.closeDate,
					name : 'endDate',
					format : 'Y-m-d'
				}]
			}
//			,{
//				labelWidth : .25,
//				xtype : 'form',
//				items : [{
//					xtype : 'field_openPlan_planStateType',
//					fieldLabel : '方案状态',
//					name : 'planStateType'
//				}]
//			}
			]
		});
		this.callParent(arguments);	
	}
	
});