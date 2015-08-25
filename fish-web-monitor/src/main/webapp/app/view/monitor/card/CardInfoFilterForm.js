Ext.define('Eway.view.monitor.card.CardInfoFilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.card_cardInfoFilterForm',

	requires : [
			'Eway.view.field.card.CardStatusComboBox',
			'Eway.view.field.card.InOutComboBox',
			'Eway.view.field.card.StartDate', 'Eway.view.field.card.EndDate',
			'Eway.view.field.card.AccountNo',
			'Eway.view.field.card.TerminalId',
			'Eway.view.field.card.DeviceAtmVendorComboBox',
			'Eway.view.field.card.DeviceTypeComboBox',
			'Eway.view.common.OrgComboOrgTree',
			'Ext.ux.form.DateTimeField',
            'Eway.lib.Util'],
	height : 120,
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
						var filterForm = Ext.ComponentQuery.query('card_cardInfoFilterForm')[0];
						beginDateCmp = filterForm.down('field[name="startData"]');
						beginDate = beginDateCmp.getValue();

					}
					// 获取结束时间
					if (!Ext.isEmpty(field.dateRange.end)) {
						var filterForm = Ext.ComponentQuery.query('card_cardInfoFilterForm')[0];
						enddateCmp = filterForm.down('field[name="endData"]');
						endDate = enddateCmp.getValue();
					}
				}
				if (!Ext.isEmpty(beginDate) && !Ext.isEmpty(endDate)) {
					validStatus = beginDate <= endDate;
				}
				return validStatus;
			},
			cardInfoDateRangeText : '吞卡起始时间不能大于吞卡截止日期,请重新选择'
		});
		Ext.apply(this, {
			items : [{
						columnWidth : .3,
						items : [{
									style : 'padding-top:0px',
									xtype : 'hiddenfield',
									name : 'orgId'
								}, {
/*									xtype : 'common_orgComboOrgTree',
									fieldLabel : '所属机构',
									emptyText : '--请选择--',
									name : 'orgName',
									hiddenValue : 'orgId',
									editable : false,
									labelAlign : 'right'*/

									//只带出银行机构
									xtype : 'common_orgComboOrgTree',
									fieldLabel : '所属机构',
									labelAlign : 'right',
									emptyText : '--请选择--',
									name : 'orgName',
									hiddenValue : 'orgId',
									editable : false,
									filters : '{"type" : "0"}',
									rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
								},{
									xtype : 'card_InOutComboBox',
									labelAlign : 'right',
									editable:false
								},{
									xtype : 'card_AccountNo',
									regex: /^\d{13,19}$/,
									regexText: '只能输入数字,13-19位',
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
									fieldLabel : '开始时间',
									editable:false,
									name: 'startData',
									format: 'Y-m-d H:i:s',
									 dateRange : {
									 begin : 'startData',
									 end : 'endDate'
									 },
									 vtype : 'cardInfoDateRange',
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
									maxLength:20,
									labelAlign : 'right'
								}]

					}, {

						columnWidth : .4,
						items : [{
									xtype : 'field_card_DeviceTypeComboBox',
									labelAlign : 'right'
								}, {
									xtype: 'datetimefield',
									fieldLabel : '结束时间',
									editable:false,
									name: 'endData',
									format: 'Y-m-d H:i:s',
									dateRange : {
										begin : 'startData',
										end : 'endDate'
									},
									vtype : 'cardInfoDateRange',
									msgTarget : 'side',
									labelAlign : 'right',
									listeners : {
										change : function(){
											var beginDate = this.up('form').down('field[name="startData"]');
											beginDate.clearInvalid();
										}
									}
								},{
									xtype : 'card_cardStatusComboBox',
									labelAlign : 'right',
									editable:false
								}]
					}]
		});

		this.callParent(arguments);
	}
});