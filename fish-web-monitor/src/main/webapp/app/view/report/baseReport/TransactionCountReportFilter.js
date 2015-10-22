Ext.define('Eway.view.report.baseReport.TransactionCountReportFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.baseReport_TransactionCountReportFilter',

	requires : [ 'Eway.view.field.card.StartDate',
			'Eway.view.field.card.EndDate', 'Eway.view.common.OrgComboOrgTree',
			'Ext.ux.form.DateTimeField',
            'Eway.lib.Util',
			'Eway.view.field.monitor.TransTypeComboBox',
			'Eway.view.field.person.OrganizationLevel','Eway.view.field.report.DeviceComboBox'],

	height : 80,
	layout : 'column',
	hideLabel : false,
	initComponent : function() {
		Ext.apply(Ext.form.field.VTypes, {
			cardInfoDateRange : function(val, field) {
				var beginDate = null, // 开始日期
				beginDateCmp = null, // 开始日期组件
				endDate = null, // 结束日期
				enddateCmp = null, // 结束日期组件
				validStatus = true;// 验证状态
				if (field.dateRange) {
					// 获取开始时间
					if (!Ext.isEmpty(field.dateRange.begin)) {
						var filterForm = Ext.ComponentQuery
								.query('baseReport_TransactionCountReportFilter')[0];
						beginDateCmp = filterForm
								.down('field[name="startData"]');
						beginDate = beginDateCmp.getValue();

					}
					// 获取结束时间
					if (!Ext.isEmpty(field.dateRange.end)) {
						var filterForm = Ext.ComponentQuery
								.query('baseReport_TransactionCountReportFilter')[0];
						enddateCmp = filterForm
								.down('field[name="endData"]');
						endDate = enddateCmp.getValue();
					}
				}
				if (!Ext.isEmpty(beginDate)
						&& !Ext.isEmpty(endDate)) {
					validStatus = beginDate <= endDate;
				}
				return validStatus;
			},
			cardInfoDateRangeText : Eway.locale.tip.dateReSelect,
		});
		var levelStore = Ext.create('Eway.store.person.organization.OrganizationLevelDict');
		Ext.apply(this, {
			items : [ {
				columnWidth : .3,
				items : [ {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				}, {
					border : false,
					defaults : {
						border : false,
						bodyStyle: {
							'background-color':'#dfe8f5'
						}
					},
					items : [ {
						xtype : 'common_orgComboOrgTree',
						fieldLabel : Eway.locale.commen.orgNameBelongs,
						labelAlign : 'right',
						emptyText : Eway.locale.combox.select,
						name : 'orgName',
						hiddenValue : 'orgId',
						editable : false,
						labelWidth : 80,
						filters : '{"type" : "0"}',
						rootVisible : ewayUser.getOrgType() != ""
								&& ewayUser.getOrgType() == '0'
								? true
								: false
					}, {
						columnWidth : .29,
						xtype : 'checkboxfield',
						fieldLabel : Eway.locale.report.baseReport.dependDev,
						labelWidth : 44,
						name : 'isDevice',
						value : true,
						hidden:true
					} ]

				},{
					xtype : 'datefield',
					labelWidth : 80,
					fieldLabel : Eway.locale.commen.startDataTime,
					name : 'startData',
					format : 'Y-m-d',
					editable : false,
					value : Ext.Date.add(new Date(), Ext.Date.DAY, -2),
					dateRange : {
						begin : 'beginDate',
						end : 'endDate'
					},
					vtype : 'cardInfoDateRange',
					msgTarget : 'side',
					labelAlign : 'right',
					listeners : {
						change : function() {
							var endDate = this
									.up('form')
									.down('field[name="endData"]');
							endDate.clearInvalid();
						}
					}
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					xtype : 'field.organizationLevel',
					name : 'orgLevel',
					store : levelStore,
					emptyText : Eway.locale.combox.select,
					labelWidth : 80
				},{
					xtype : 'datefield',
					fieldLabel : Eway.locale.commen.endDataTime,
					labelWidth : 80,
					name : 'endData',
					format : 'Y-m-d',
					editable : false,
					value : new Date(),
					dateRange : {
						begin : 'beginDate',
						end : 'endDate'
					},
					vtype : 'cardInfoDateRange',
					msgTarget : 'side',
					labelAlign : 'right',
					listeners : {
						change : function() {
							var beginDate = this
									.up('form')
									.down('field[name="startData"]');
							beginDate.clearInvalid();
						}
					}
				} ]

			}, {
				columnWidth : .4,
				items : [ {
					xtype : 'monitor_TransTypeComboBox',
					labelAlign : 'right',
					labelWidth : 80
				},{
					xtype : 'report_DevicComboBox',
					labelAlign : 'right',
					labelWidth : 80,
					emptyText : Eway.locale.combox.select,
					labelWidth : 80
				}]
			}]
		});
		this.callParent(arguments);
	}
});