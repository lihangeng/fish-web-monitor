Ext.define('Eway.view.monitor.device.showType.DataViewGrid', {
	alias : 'widget.monitor_device_showtype_dataviewgrid',
	extend : 'Ext.view.View',
	border : false,
	
	requires : [
		'Eway.lib.FunctionUtils',
    	'Ext.ux.BoxReorderer',
    	'Ext.ux.DataView.Animated'
	],
	
	screen : "",
	
	initComponent : function() {
		
		var me = this;
		
		var monitorTpl = new Ext.XTemplate(
			'<tpl for=".">',
				'<div class="thumb-wrap" >',
						'<div class="thumb">',
							'<ul>',
								'<li><div class="monitor-div-run-{run}" onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,EwayLocale.monitor.devMonitor.comboxStatus.runStatus,\'{runStatus}\');"/></li>',
								'<li><div class="monitor-div-mod-{mod}" onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,EwayLocale.monitor.devMonitor.comboxStatus.modStatus,\'{modStatus}\');"/></li>',
								'<li><div class="monitor-div-box-{box}" onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,EwayLocale.monitor.devMonitor.comboxStatus.boxStatus,\'{boxStatus}\');"/></li>',
								'<li><div class="monitor-div-net-{net}" onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,EwayLocale.monitor.devMonitor.comboxStatus.netStatus,\'{netStatus}\');"/></li>',
							'</ul>',
						'</div>',
					'<span class="x-editable">{code}({org})</span>',
				'</div>',
			'</tpl>',
			'<div class="x-clear"></div>'
		);
		
//		var dataViewStore = Ext.create('Eway.store.monitor.device.DataView',{});
		// dataViewStore.loadPage(1);
		Ext.apply(this, {
			autoScroll : true,
			// 解决IE7,8下不出现滚动条问题,由于extjs会对ie7,8特殊处理
			// autoScroll属性会作用在其他div上,所以需要直接写css来显示滚动
			style : 'overflow:auto;',
			frame : true,
//			store : dataViewStore,
			tpl : monitorTpl,
			multiSelect : false,
			trackOver : true,
			overItemCls : 'x-item-over',
			itemSelector : 'div.thumb-wrap',
			emptyText : EwayLocale.monitor.devMonitor.noData,
			loadData : function(store){
				var myStore = this.getStore();
				myStore.removeAll();
				var records = [];
				store.each(function(record){
					records[records.length] = record;
				});
				myStore.loadRecords(records);
			}
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});