
Ext.define('Eway.view.machine.quittingNotice.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.quittingNotice_add',

	requires: ['Eway.view.field.quittingNotice.StopType',
			   'Ext.ux.form.DateTimeField',
			   'Eway.view.field.quittingNotice.DevStatus',
	           'Eway.view.field.quittingNotice.DeviceCode',
	           'Eway.lib.Util'],

	title: Eway.locale.machine.quittingNotice.addCloseMsg,
	modal: true,
	resizable: false,
	constrainHeader: true,

	initComponent: function() {
		Ext.apply(Ext.form.field.VTypes,{
			dateRange : function(val, field){
				var beginDate = null,//开始日期
					beginDateCmp = null,//开始日期组件
					endDate = null,//结束日期
					enddateCmp = null,//结束日期组件
					validStatus = true;//验证状态
					if(field.dateRange){
						//获取开始时间
						if(!Ext.isEmpty(field.dateRange.begin)){
							beginDateCmp = Ext.getCmp(field.dateRange.begin);
							beginDate = beginDateCmp.getValue();
						}
						//获取结束时间
						if(!Ext.isEmpty(field.dateRange.end)){
							endDateCmp = Ext.getCmp(field.dateRange.end);
							endDate = endDateCmp.getValue();
						}
					}
					if(!Ext.isEmpty(beginDate) && !Ext.isEmpty(endDate)){
						validStatus = beginDate < endDate;
					}
					return validStatus;
			},
			dateRangeText : Eway.locale.machine.quittingNotice.dateRangeText
		});
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 400,
					labelWidth: 80,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmGroup.terminalId,
					xtype : 'field_deviceCode',
					regexText : Eway.locale.vtype.bankOrgCode,
					maxLength : 20,
					blankText:Eway.locale.machine.quittingNotice.click,
					allowBlank : false
				},{
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.quittingNotice.stopTime,
					xtype : 'datetimefield',
					name : 'stopTime',
					editable : false,
					format : 'Y-m-d H:i:s',
					dateRange : {begin : 'stopTimeId', end : 'openTimeId'},
					vtype : 'dateRange',
					id : 'stopTimeId',
					allowBlank : false,
					listeners : {
						change : function(){
							var openTime = this.up('form').down('field[name="openTime"]');
							openTime.clearInvalid();
						}
					}
				},{
					fieldLabel : Eway.locale.machine.quittingNotice.openTime,
					xtype : 'datetimefield',
					name : 'openTime',
					editable : false,
					format : 'Y-m-d H:i:s',
					vtype : 'dateRange',
					dateRange : {begin : 'stopTimeId', end : 'openTimeId'},
					id : 'openTimeId',
					listeners : {
						change : function(){
							var stopTime = this.up('form').down('field[name="stopTime"]');
							stopTime.clearInvalid();
						}
					}
				},{
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.quittingNotice.currentStatus,
					xtype : 'field_devStatus',
					allowBlank : false,
					value : 'DISABLED',
					editable : false
				},{
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.quittingNotice.closeType,
					xtype : 'field_stopType',
					allowBlank : false,
					value : '1',
					editable : false
				},{
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.quittingNotice.responsibilityName,
					xtype : 'textfield',
					maxLength :20,
					name : 'responsibilityName',
					allowBlank : false
				},{
					fieldLabel : Eway.locale.machine.quittingNotice.stopReason,
					xtype : 'textarea',
					name : 'stopReason',
					maxLength :60,
					autoScroll : true
				/*},{
					fieldLabel : '<font color="red">*</font> 设置时间',
					xtype : 'datetimefield',
					name : 'setTime',
					format : 'Y-m-d H:i:s',
					allowBlank : false*/
				}],
				buttonAlign : 'center',
				buttons: [{
					text: Eway.locale.button.confirm,
					action: 'confirm'
				}, {
					text: Eway.locale.button.reset,
					handler: this.onReset,
					hidden : true
				}, {
					text: Eway.locale.button.back,
					handler: this.onOver
				}]
			}
		});

		this.callParent(arguments);
	},

	onReset: function() {
		this.up('form').getForm().reset();
	},

	onOver: function() {
		this.up('window').close();
	}
});