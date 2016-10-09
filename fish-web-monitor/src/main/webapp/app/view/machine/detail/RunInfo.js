Ext.define('Eway.view.machine.detail.RunInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_runInfo',

	requires : [  ],
	height:800,
	closable:false,
	width:1024,
	title : '设备运行信息',
	layout: 'responsivecolumn',
    scrollable : 'y',

	isLoad : false,

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype: 'panel',
		    	responsiveCls: 'big-50 small-100',
	            title: '交易信息',
	            html:'交易信息'
			},{
				xtype: 'panel',
		    	responsiveCls: 'big-50 small-100',
	            title: '故障信息',
	            html:'故障信息'
			},{
				xtype: 'panel',
		    	responsiveCls: 'big-50 small-100',
	            title: '吞卡信息',
	            html:'吞卡信息'
			},{
				xtype: 'panel',
		    	responsiveCls: 'big-50 small-100',
	            title: '日志备份',
	            html:'日志备份'
			},{
				xtype: 'panel',
		    	responsiveCls: 'big-50 small-100',
	            title: '清机加钞',
	            html:'清机加钞'
			}],

			listeners : {
				activate : function(panel) {}
			}
		});

		this.callParent(arguments);
	}
});
