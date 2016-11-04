Ext.define('Eway.view.machine.detail.basic.OtherInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_otherInfo',

	tools: [ {
        itemId: 'refreshOtherInfo',
        type: 'refresh',
        tooltip: EwayLocale.button.refresh
    }],
	requires : [  ],
	title : EwayLocale.deviceInfo.otherInfo,
    closable:false,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype:'form',
				defaults : {
					labelAlign : 'left',
					xtype : 'displayfield',
					labelWidth : 150,
					readOnly : true,
					width : '50%'
				},
			    layout: {
			        type: 'column',
			    },
				items : [ {
					columnWidth : .33,
					fieldLabel : EwayLocale.boxInfo.cashboxInLimit,
					name : 'maxAlarm',
					minHeight : 20
				},{
					columnWidth : .33,
					fieldLabel : EwayLocale.boxInfo.cashboxOutLimit,
					name : 'minAlarm',
					minHeight : 20
				},{
					columnWidth : .33,
					fieldLabel : EwayLocale.deviceInfo.boxInitAmt,
					name : 'boxInitCount',
					minHeight : 20
				},{
					columnWidth : .33,
					fieldLabel : EwayLocale.deviceInfo.boxCurrentAmt,
					name : 'boxCurrentCount',
					minHeight : 20
				},{
					columnWidth : .33,
					fieldLabel : EwayLocale.deviceInfo.deviceSwallowCardCount,
					name : 'retainCardCount'
				}]
			}]
		});

		this.callParent(arguments);
	}
   
});
