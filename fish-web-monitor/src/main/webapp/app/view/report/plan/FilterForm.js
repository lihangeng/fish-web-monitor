Ext.define('Eway.view.report.plan.FilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.plan_filterForm',
	
	layout : 'column',
	
	initComponent : function(){
		Ext.apply(this,{
			items : [{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					xtype : 'textfield',
					fieldLabel : EwayLocale.report.plan.name,
					name : 'name'
				}]
			},{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : EwayLocale.report.plan.startDate,
					name : 'startDate',
					format : 'Y-m-d'
				}]
			},{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : EwayLocale.report.plan.endDate,
					name : 'endDate',
					format : 'Y-m-d'
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});