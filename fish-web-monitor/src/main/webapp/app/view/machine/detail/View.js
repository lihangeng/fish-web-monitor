Ext.define('Eway.view.machine.detail.View', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.detail.view',
	requires : [ 'Eway.view.machine.detail.BasicInfo',
	             'Eway.view.machine.detail.RunInfo'],

	title : '设备信息明细',
	layout : 'fit',
	tabPosition: 'bottom',
	isLoad : false,
	closable:true,
	initComponent : function() {
		Ext.apply(this, {
			items:[{
				name : 'deviceBasicInfo',
				xtype : 'detail_basicInfo',
				autoScroll :true,
				title : '设备基本信息'
				//设备的具体信息，维护人员，银行人员,硬件状态，版本信息，钞箱预警
//							设备硬件模块状态，钞箱预警信息配置，设备的基础信息维护人员管机员，设备版本信息，
			},{
				name : 'deviceRunInfo',
				xtype : 'detail_runInfo',
				autoScroll :true,
				title : '设备运行信息'
				//交易，日志，故障,清机加钞,吞卡
//							交易信息，应用日志(10条)，最近的设备故障信息（10条），清机加钞信息，设备吞卡信息（10条）
			}]
		});

		this.callParent(arguments);
	}
});
