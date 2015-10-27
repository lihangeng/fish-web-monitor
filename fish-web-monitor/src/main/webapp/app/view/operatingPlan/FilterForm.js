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
					fieldLabel : '名称',
					name : 'name'
				}]
			},{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : '有效开始时间',
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
					fieldLabel : '有效结束时间',
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