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
					fieldLabel : '名称',
					name : 'name'
				}]
			},{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : '有效开始时间',
					name : 'startDate',
					format : 'Y-m-d'
				}]
			},{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : '有效结束时间',
					name : 'endDate',
					format : 'Y-m-d'
				}]
			}]
		});
		this.callParent(arguments);	
	}
	
});