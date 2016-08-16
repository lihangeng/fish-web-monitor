Ext.define('Eway.view.report.baseReport.ETLJobFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.trans_JobFilter',

	requires : ['Eway.lib.Util','Ext.ux.form.DateTimeField'],
	height : 40,
	layout : 'column',
	hideLabel : false,
	initComponent : function() {
		var me = this;
		Ext.apply(Ext.form.field.VTypes, {
			operDateRange : function(val, field) {
				var beginDate = null, // 开始日期
				beginDateCmp = null, // 开始日期组件
				endDate = null, // 结束日期
				enddateCmp = null, // 结束日期组件
				validStatus = true;// 验证状态
				var filterForm = Ext.ComponentQuery.query('trans_JobFilter')[0];
				beginDateCmp = filterForm.down('field[name="startTime"]');
				beginDate = beginDateCmp.getValue();
				enddateCmp = filterForm.down('field[name="endTime"]');
				endDate = enddateCmp.getValue();
				if (!Ext.isEmpty(beginDate) && !Ext.isEmpty(endDate)) {
					validStatus = beginDate <= endDate;
				}
				return validStatus;
			},
			operDateRangeText : EwayLocale.batch.errorMsg
				
		});
		Ext.apply(this, {
			items : [ {
			    xtype: 'container',
                layout: 'hbox',
				width:1000,
                margin: '0 5 0 0',
				items:[
				{
					fieldLabel: EwayLocale.batch.startTime,
					labelWidth : 100,
					width:285,
					margin: '0 5 0 5',
		        	xtype : 'datetimefield',
					name : 'startTime',
					format : 'Y-m-d H:i:s',
					msgTarget : 'side',
					editable: true,
					vtype : 'operDateRange'
                }, {
                    fieldLabel: EwayLocale.batch.endTime,
                    labelWidth : 100,
					width:285,
                    margin: '0 5 0 5',
		        	xtype : 'datetimefield',
					name : 'endTime',
					format : 'Y-m-d H:i:s',
					editable: true,
					msgTarget : 'side',
					vtype : 'operDateRange'
                }]
			} ]
	});

		this.callParent(arguments);
	}
});