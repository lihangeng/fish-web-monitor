Ext.define('Eway.view.advert.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.advert_filterForm',

	requires : [ 'Ext.ux.form.DateTimeField' ],
	height : 35,
	initComponent : function() {
//		Ext.apply(Ext.form.field.VTypes, {
//			cardInfoDateRange : function(val, field) {
//				var beginDate = null, // 开始日期
//				beginDateCmp = null, // 开始日期组件
//				endDate = null, // 结束日期
//				enddateCmp = null, // 结束日期组件
//				validStatus = true;// 验证状态
//				if (field.dateRange) {
//					// 获取开始时间
//					if (!Ext.isEmpty(field.dateRange.begin)) {
//						var filterForm = Ext.ComponentQuery.query('advert_filterForm')[0];
//						beginDateCmp = filterForm.down('field[name="createdTimeStart"]');
//						beginDate = beginDateCmp.getValue();
//
//					}
//					// 获取结束时间
//					if (!Ext.isEmpty(field.dateRange.end)) {
//						var filterForm = Ext.ComponentQuery.query('advert_filterForm')[0];
//						enddateCmp = filterForm.down('field[name="createdTimeEnd"]');
//						endDate = enddateCmp.getValue();
//					}
//				}
//				if (!Ext.isEmpty(beginDate) && !Ext.isEmpty(endDate)) {
//					validStatus = beginDate <= endDate;
//				}
//				return validStatus;
//			},
//			cardInfoDateRangeText : EwayLocale.tip.dateReSelect
//		});
		var me = this;
		Ext.apply(this, {
			layout : 'column',
			items : [ {
				columnWidth : .3,
				items : [ {
					xtype : 'combobox',
					fieldLabel : EwayLocale.advert.type,
					displayField : 'display',
					store : Ext.StoreMgr.lookup("advert.AdvertType"),
					queryMode : 'local',
					valueField : 'value',
					name : 'advertType',
					labelWidth:80,
					editable : false,
					width : 320
				} ]
			}, {
				columnWidth : .33,
				items : [ {
					fieldLabel : EwayLocale.advert.createdTimeStart,
					labelWidth:150,
					xtype : 'datefield',
					name : 'createdTimeStart',
					editable : false,
					format : 'Y-m-d',
					width : 335,
//					value : Ext.Date.add(new Date(), Ext.Date.DAY, -2),
//					 dateRange : {
//					 begin : 'createdTimeStart',
//					 end : 'createdTimeEnd'
//					 },
					vtype : 'daterange',
					endDateField : 'createdTimeEnd',
					 msgTarget : 'side',
					 labelAlign : 'right',
					 listeners: {
							blur : {
					            fn: function(This, options){
					            	var value = this.getValue();
					            	if (!value) {
					            		var endField = this.up('form').getForm().findField(this.endDateField);
					            		endField.setMinValue(null);
					            	}
					            }
							}
						}
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					fieldLabel : EwayLocale.advert.createdTimeStop,
					xtype : 'datefield',
					name : 'createdTimeEnd',
					labelWidth:130,
					vtype : 'daterange',
					editable:false,
					startDateField : 'createdTimeStart',
					format : 'Y-m-d',
					width : 310,
//					value:Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d') + " 23:59:59","Y-m-d H:i:s"),
//					dateRange : {
//						begin : 'createdTimeStart',
//						end : 'createdTimeEnd'
//					},
//					vtype : 'cardInfoDateRange',
					msgTarget : 'side',
					labelAlign : 'right',
					listeners : {
						blur : {
				            fn: function(This, options){
				            	var value = this.getValue();
				            	if (!value) {
				            		var startField = this.up('form').getForm().findField(this.startDateField);
				            		startField.setMaxValue(null);
				            	}
				            }
						}
					}
				} ]
			} ]
		});
		this.callParent(arguments);
	}

});