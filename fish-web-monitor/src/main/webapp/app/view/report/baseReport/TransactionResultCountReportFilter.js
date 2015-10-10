Ext.define('Eway.view.report.baseReport.TransactionResultCountReportFilter', {
			extend : 'Eway.view.base.FilterForm',
			alias : 'widget.baseReport_TransactionResultCountReportFilter',

			requires : ['Eway.view.field.card.StartDate',
					'Eway.view.field.card.EndDate',
					'Eway.view.common.OrgComboOrgTree',
					'Ext.form.field.Date',
		            'Eway.lib.Util',
					'Eway.view.field.monitor.TransTypeComboBox'],

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
												.query('baseReport_TransactionResultCountReportFilter')[0];
										beginDateCmp = filterForm
												.down('field[name="startData"]');
										beginDate = beginDateCmp.getValue();

									}
									// 获取结束时间
									if (!Ext.isEmpty(field.dateRange.end)) {
										var filterForm = Ext.ComponentQuery
												.query('baseReport_TransactionResultCountReportFilter')[0];
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
							cardInfoDateRangeText : '开始时间不能大于结束日期,请重新选择'
						});
				Ext.apply(this, {
							items : [{
								columnWidth : .3,
								items : [{
											style : 'padding-top:0px',
											xtype : 'hiddenfield',
											name : 'orgId'
										}, {
											// 只带出银行机构
											xtype : 'common_orgComboOrgTree',
											fieldLabel : Eway.locale.commen.orgNameBelongs,
											labelAlign : 'right',
											emptyText : '--请选择--',
											name : 'orgName',
											hiddenValue : 'orgId',
											editable : false,
											filters : '{"type" : "0"}',
											rootVisible : ewayUser.getOrgType() != ""
													&& ewayUser.getOrgType() == '0'
													? true
													: false
										}, {
											fieldLabel : Eway.locale.commen.terminalId,
											labelAlign : 'right',
											xtype : 'textfield',
											name : 'terminalId',
											regex : /^\w[\w-_\.]{0,19}$/,
											regexText : '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’，只能以字母或数字开头。',
											msgTarget : 'side',
											hidden:true
										}, {
											xtype : 'monitor_TransTypeComboBox',
											//	value : 'DEP',
												labelAlign : 'right'
											}]
							}, {
								columnWidth : .3,
								items : [{
									xtype : 'datefield',
									fieldLabel : '开始时间',
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
								}]
							}, {

								columnWidth : .4,
								items : [{
									xtype : 'datefield',
									fieldLabel : '结束时间',
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
								}]
							}]
						});

				this.callParent(arguments);
			}
		});