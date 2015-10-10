
Ext.define('Eway.view.person.user.UserLogFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.userLogFilterForm',

	requires: ['Ext.ux.form.DateTimeField'],

	layout : 'column',
	height : 40,
	defaults : {
		border : false
	},

	initComponent: function() {
		var me = this;

		Ext.apply(Ext.form.field.VTypes, {
			userLogDateRange : function(val, field) {
				var beginDate = null, // 开始日期
				endDate = null, // 结束日期
				validStatus = true;// 验证状态
				if (field.dateRange) {
					// 获取开始时间
					if (!Ext.isEmpty(field.dateRange.begin)) {
						var filterForm = Ext.ComponentQuery.query('userLogFilterForm')[0];
						var beginDateCmp = filterForm.down('field[name="operTimeStart"]');
						beginDate = beginDateCmp.getValue();

					}
					// 获取结束时间
					if (!Ext.isEmpty(field.dateRange.end)) {
						var filterForm = Ext.ComponentQuery.query('userLogFilterForm')[0];
						var enddateCmp = filterForm.down('field[name="operTimeEnd"]');
						endDate = enddateCmp.getValue();
					}
				}
				if (!Ext.isEmpty(beginDate) && !Ext.isEmpty(endDate)) {
					validStatus = beginDate <= endDate;
				}
				return validStatus;
			},
			userLogDateRangeText : Eway.locale.vtype.endDateGtBenginDate
		});

		Ext.apply(this, {
			items : [{
				columnWidth : .5,
				items : [{
					fieldLabel: Eway.locale.tip.operateDate.operateDateBegin,
		        	xtype : 'datetimefield',
					name : 'operTimeStart',
					format : 'Y-m-d H:i:s',
					msgTarget : 'side',
					editable: false,
					value:Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d') + " 00:00:00","Y-m-d H:i:s"),
					dateRange : {
						 begin : 'operTimeStart',
						 end : 'operTimeEnd'
					 },
					 vtype : 'userLogDateRange',
					 msgTarget : 'side',
					 labelAlign : 'right',
					 listeners: {
					 	change : function(){
					 		var endDate = this.up('form').down('field[name="operTimeEnd"]');
					 		endDate.clearInvalid();
					 	}
					 }
				}]
			},
			{
				columnWidth : .5,
				items : [{
					fieldLabel: Eway.locale.tip.operateDate.operateDateEnd,
		        	xtype : 'datetimefield',
					name : 'operTimeEnd',
					format : 'Y-m-d H:i:s',
					editable: false,
					value:Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d') + " 23:59:59","Y-m-d H:i:s"),
					dateRange : {
						begin : 'operTimeStart',
						end : 'operTimeEnd'
					},
					vtype : 'userLogDateRange',
					msgTarget : 'side',
					labelAlign : 'right',
					listeners : {
						change : function(){
							var beginDate = this.up('form').down('field[name="operTimeStart"]');
							beginDate.clearInvalid();
						}
					}

				}]
			}]
		});

		this.callParent(arguments);
	}
});