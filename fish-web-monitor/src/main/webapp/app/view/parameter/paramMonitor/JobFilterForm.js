Ext.define('Eway.view.parameter.paramMonitor.JobFilterForm',{
	extend :'Eway.view.base.FilterForm',
	alias : 'widget.parameter_paramMonitor_JobFilterForm',
	height :50,
	
	initComponent : function(){
		Ext.apply(Ext.form.field.VTypes, {
			cardInfoDateRange : function(val, field) {
				var startTime = null, // 开始日期
				startTimeCmp = null, // 开始日期组件
				finishTime = null, // 结束日期
				finishTimeCmp = null, // 结束日期组件
				validStatus = true;// 验证状态
				if (field.dateRange) {
					// 获取开始时间
					if (!Ext.isEmpty(field.dateRange.begin)) {
						var filterForm = Ext.ComponentQuery
								.query('parameter_paramMonitor_JobFilterForm')[0];
						startTimeCmp = filterForm
								.down('field[name="startTime"]');
						startTime = startTimeCmp.getValue();

					}
					// 获取结束时间
					if (!Ext.isEmpty(field.dateRange.end)) {
						var filterForm = Ext.ComponentQuery
								.query('parameter_paramMonitor_JobFilterForm')[0];
						finishTimeCmp = filterForm
								.down('field[name="finishTime"]');
						finishTime = finishTimeCmp.getValue();
					}
				}
				if (!Ext.isEmpty(startTime)
						&& !Ext.isEmpty(finishTime)) {
					validStatus = startTime <= finishTime;
				}
				return validStatus;
			},
			cardInfoDateRangeText : EwayLocale.tip.dateReSelect
		});
		Ext.apply(this,{
			layout :'column',
			msgTarget : 'side',
			items :[{
				columnWidth : .3,
				items:[{
					xtype : 'datefield',
					editable : false,
					format : 'Y-m-d',
					fieldLabel : EwayLocale.commen.startDataTime,
					name:'startTime',
					dateRange : {
						begin : 'startTime',
						end : 'finishTime'
					},
					vtype : 'cardInfoDateRange',
					msgTarget : 'side',
					labelAlign : 'right'
				}]	
			},{
				columnWidth:.3,
				items:[{
					xtype : 'datefield',
					name : 'finishTime',
					fieldLabel : EwayLocale.commen.endDataTime,
					format:'Y-m-d',
					editable:false,
					dateRange : {
						begin : 'startTime',
						end : 'finishTime'
					},
					vtype : 'cardInfoDateRange',
					msgTarget : 'side',
					labelAlign : 'right'
				}]
			}/*,{
				columnWidth:.3,
				items:[{
					xtype:'textfield',
					fieldLabel:'发布者',
					labelAlign:'right',
					name:'publisher'
				}]
			}*/]
		});
		this.callParent(arguments);
	}
});