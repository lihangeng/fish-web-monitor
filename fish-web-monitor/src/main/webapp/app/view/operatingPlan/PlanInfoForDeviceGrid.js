
Ext.define('Eway.view.operatingPlan.PlanInfoForDeviceGrid', {
	alias: 'widget.planInfo_device_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	border : false,
	initComponent: function() {
		var store = Ext.create('Eway.store.operatingPlan.OpenPlanForDevice');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar : [ '->',  {
				text : EwayLocale.tip.business.device.detail,
				glyph : 0xf129,
				iconCls : 'detailBtn',
				action : 'deviceQueryDetail'
			}, {
				text:EwayLocale.button.link,
				glyph : 0xf0c1,
				action: 'deviceLink'
			}, {
				text:EwayLocale.button.unlink,
				glyph : 0xf014,
				action: 'deviceUnlink'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ {
				header : EwayLocale.machine.plan.name,
				width : 100,
				dataIndex : 'name'
			}, {
				header : EwayLocale.machine.plan.type,
				width : 80,
				dataIndex : 'planType',
				renderer : function(value, metadata, record) {
					if (value == "DATE") {
						return EwayLocale.machine.serviceplan.date;
					} else if (value == "WEEK") {
						return EwayLocale.machine.serviceplan.week;
					}
				}
			}, {
				header : EwayLocale.machine.serviceplan.state,
				width : 80,
				dataIndex : 'planStateType'
			}, {
				header : EwayLocale.machine.serviceplan.openDate,
				width : 100,
				dataIndex : 'startDate'
			}, {
				header : EwayLocale.machine.serviceplan.closeDate,
				width : 100,
				dataIndex : 'endDate'
			}, /*{
				header : EwayLocale.version.View.remark,
				dataIndex : 'desc',
				flex : 1
			},*/ {
				header :EwayLocale.machine.serviceplan.createDateTime,
				dataIndex : 'createDateTime',
				flex : 1
			}]
		});

		this.callParent(arguments);
	}
});