
Ext.define('Eway.view.monitor.card.CardActionFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.card_CardActionFilterForm',


	requires : ['Eway.view.field.card.InOutComboBox',
				'Eway.view.field.card.StartDate',
				'Eway.view.field.card.EndDate',
				'Eway.view.field.card.AccountNo',
				'Eway.view.field.card.TerminalId',
				'Eway.view.field.card.DeviceAtmVendorComboBox',
				'Eway.view.field.card.DeviceTypeComboBox',
				'Eway.view.common.OrgComboOrgTree',
				'Eway.view.field.card.ActionStatus',
				'Ext.ux.form.DateTimeField',
	             'Eway.lib.Util'],
	height : 120,
	layout : 'column',
	hideLabel : false,
	hideLabel:false,
	initComponent: function() {
		Ext.apply(Ext.form.field.VTypes,{
			cardActionDateRange : function(val, field){
				var beginDate = null,//开始日期
					beginDateCmp = null,//开始日期组件
					endDate = null,//结束日期
					enddateCmp = null,//结束日期组件
					validStatus = true;//验证状态
					if(field.dateRange){
						//获取开始时间
						if(!Ext.isEmpty(field.dateRange.begin)){
							var filterForm = Ext.ComponentQuery.query('card_CardActionFilterForm')[0];
							beginDateCmp = filterForm.down('field[name="startData"]');
							beginDate = beginDateCmp.getValue();
						}
						//获取结束时间
						if(!Ext.isEmpty(field.dateRange.end)){
							var filterForm = Ext.ComponentQuery.query('card_CardActionFilterForm')[0];
							enddateCmp = filterForm.down('field[name="endData"]');
							endDate = enddateCmp.getValue();
						}
					}
					if(!Ext.isEmpty(beginDate) && !Ext.isEmpty(endDate)){
						validStatus = beginDate <= endDate;
					}
					return validStatus;
			},
			cardActionDateRangeText : EwayLocale.monitor.business.card.beginEndDate
		});
		Ext.apply(this, {
			items : [
			{
				columnWidth : .3,
				items : [{
							style : 'padding-top:0px',
							xtype : 'hiddenfield',
							name : 'orgId'
						}, {
/*							xtype : 'common_orgComboOrgTree',
							fieldLabel : EwayLocale.commen.orgNameBelongs,
							emptyText : '--请选择--',
							name : 'orgName',
							hiddenValue : 'orgId',
							editable : false,
							labelAlign : 'right'*/

							//只带出银行机构
							xtype : 'common_orgComboOrgTree',
							fieldLabel : EwayLocale.monitor.business.card.orgBelongs,
							labelAlign : 'right',
							emptyText : EwayLocale.combox.select,
							name : 'orgName',
							hiddenValue : 'orgId',
							editable : false,
							filters : '{"type" : "0"}',
							rootVisible : Eway.user.getOrgType() != "" && Eway.user.getOrgType() == '0' ? true : false
						}, {
							xtype : 'card_InOutComboBox',
							labelAlign : 'right',
							editable:false
						},{
							xtype : 'card_AccountNo',
							msgTarget : 'side',
							labelAlign : 'right'
						}]
				}, {
				columnWidth : .3,
				items : [{
							xtype : 'field_card_DeviceAtmVendorComboBox',
							labelAlign : 'right'
					}, {
							xtype: 'datetimefield',
							fieldLabel : EwayLocale.monitor.business.card.beginTime,
							editable : false,
							name: 'startData',
							format: 'Y-m-d H:i:s',
					 		dateRange : {
							begin : 'startData',
							end : 'endDate'
							 },
							 vtype : 'cardActionDateRange',
							 msgTarget : 'side',
							 labelAlign : 'right',
							 listeners: {
							 	change : function(){
							 		var endDate = this.up('form').down('field[name="endData"]');
							 		endDate.clearInvalid();
							 	}
							 }
						},{
							xtype : 'card_TerminalId',
							labelAlign : 'right'
						}]

					}, {

				columnWidth : .4,
				items : [{
							xtype : 'field_card_DeviceTypeComboBox',
							labelAlign : 'right'
						}, {
							xtype: 'datetimefield',
							fieldLabel : EwayLocale.monitor.business.card.endTime,
							editable : false,
							format: 'Y-m-d H:i:s',
							name: 'endData',
							dateRange : {
								begin : 'startData',
								end : 'endDate'
							},
							vtype : 'cardActionDateRange',
							msgTarget : 'side',
							labelAlign : 'right',
							listeners : {
								change : function(){
									var beginDate = this.up('form').down('field[name="startData"]');
									beginDate.clearInvalid();
								}
							}
						},{
							xtype: 'card_ActionStatus',
							labelAlign : 'right',
							editable:false
						}]
					}
			]
		});

		this.callParent(arguments);
	}
});