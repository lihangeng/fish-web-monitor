Ext.define('Eway.view.operatingPlan.GridForDevice', {
	alias : 'widget.operatingPlan_gridForDevice',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,

	initComponent : function() {
		var store = Ext.create('Eway.store.operatingPlan.OpenPlan');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.button.link,
				glyph : 0xf0c1,
				action : 'link'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true,
				getRowClass: function(record, index) {
					var planStateType = record.get('planStateType');
					if(planStateType == EwayLocale.commen.comboxStatus.close||planStateType == EwayLocale.commen.comboxStatus.pastDue){
						return 'user-online';
					}else if(planStateType == EwayLocale.commen.comboxStatus.pastDueSoon){
						return 'user-yellow';
					}
				}
			},
			columns : [ {
				header : EwayLocale.machine.serviceplan.name,
				width : 100,
				dataIndex : 'name'
			}, {
				header : EwayLocale.machine.serviceplan.state,
				width : 100,
				dataIndex : 'planType',
				renderer : function(value, metadata, record) {
					if (value == "DATE") {
						return EwayLocale.machine.serviceplan.date;
					} else if (value == "WEEK") {
						return EwayLocale.machine.serviceplan.week;
					}
				}
			}, {
				header : EwayLocale.machine.serviceplan.machineQuantity,
				width : 130,
				dataIndex : 'deviceCount'
			}, {
				header : EwayLocale.machine.serviceplan.state,
				width : 130,
				dataIndex : 'planStateType'
			}, {
				header : EwayLocale.machine.serviceplan.openDate,
				width : 120,
				dataIndex : 'startDate'
			}, {
				header : EwayLocale.machine.serviceplan.closeDate,
				width : 120,
				dataIndex : 'endDate'
			}, {
				header : EwayLocale.machine.serviceplan.createDateTime,
				dataIndex : 'createDateTime',
				width : 160,
				fiex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});