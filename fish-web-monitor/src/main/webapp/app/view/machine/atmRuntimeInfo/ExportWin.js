
Ext.define('Eway.view.machine.atmRuntimeInfo.ExportWin',{
	extend : 'Ext.window.Window',
	alias : 'widget.machine_atmRuntimeInfo_ExportWin',

	requires : [],

	title : EwayLocale.machine.atmRuntimeInfo.exportName,
	modal : true,
	constrainHeader : true,

	initComponent : function(){
		/**
		 * 时间输入框Vtype验证
		 */
			Ext.apply(Ext.form.field.VTypes,{
			exportDateRange : function(val, field){
				var beginDate = null,//开始日期
					beginDateCmp = null,//开始日期组件
					endDate = null,//结束日期
					enddateCmp = null,//结束日期组件
					validStatus = true;//验证状态
					if(field.dateRange){
						//获取开始时间
						if(!Ext.isEmpty(field.dateRange.begin)){
							var win = Ext.ComponentQuery.query('machine_atmRuntimeInfo_ExportWin')[0];
							beginDateCmp = win.down('field[name="startDate"]');
							beginDate = beginDateCmp.getValue();
						}
						//获取结束时间
						if(!Ext.isEmpty(field.dateRange.end)){
							var win = Ext.ComponentQuery.query('machine_atmRuntimeInfo_ExportWin')[0];
							enddateCmp = win.down('field[name="endDate"]');
							endDate = enddateCmp.getValue();
						}
					}
					if(!Ext.isEmpty(beginDate) && !Ext.isEmpty(endDate)){
						validStatus = beginDate <= endDate;
					}
					return validStatus;
			},
			exportDateRangeText : EwayLocale.machine.atmRuntimeInfo.exportDateRangeText
		});
		Ext.apply(this,{
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults : {
					labelAlign : 'right',
					msgTarget : 'side'
				},
				items : [{
					xtype : 'displayfield',
					name : 'terminalId',
					fieldLabel : EwayLocale.machine.atmRuntimeInfo.terminalId
				},{
					xtype : 'displayfield',
					name : 'ip',
					fieldLabel : EwayLocale.machine.atmRuntimeInfo.terminalIp
				},{
					xtype : 'datefield',
					format : 'Y-m-d',
					name : 'startDate',
					fieldLabel : EwayLocale.machine.atmRuntimeInfo.startDate,
					allowBlank : false,
					eidtable:false,
					dateRange : {
						begin : 'beginDate',
						end : 'endDate'
						 },
						 vtype : 'exportDateRange',
						 msgTarget : 'side',
						 labelAlign : 'right',
						 listeners: {
						 	change : function(){
						 		var endDate = this.up('form').down('field[name="endDate"]');
						 		endDate.clearInvalid();
						 }
					}
				},{
					xtype : 'datefield',
					format : 'Y-m-d',
					name : 'endDate',
					fieldLabel : EwayLocale.machine.atmRuntimeInfo.endDate,
					allowBlank : false,
					editable : false,
					dateRange : {
						begin : 'beginDate',
						end : 'endDate'
					},
					vtype : 'exportDateRange',
					msgTarget : 'side',
					labelAlign : 'right',
					listeners : {
						change : function(){
							var beginDate = this.up('form').down('field[name="startDate"]');
							beginDate.clearInvalid();
						}
					}
				}/*,{
					xtype: 'displayfield',
					value:'<font color="red">包含开始时间和结束时间当天</font>'
				}*/
				],
				buttons : [{
					text : EwayLocale.button.exported,
					iconCls :'exportToExcel',
					action : 'exportFromDate'
				},{
					text : EwayLocale.back,
					iconCls :'returnBtn',
					handler : this.onOver
				}]
			}
		});
		this.callParent(arguments);
	},

	onOver : function(){
		this.up('window').close();
	}

});