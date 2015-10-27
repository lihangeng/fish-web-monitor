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
								'<li><img class="left" src="{[this.getRunPath(values.run)]}"  ' +
									'onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.comboxStatus.runStatus,\'{runStatus}\');"/></li>',
								'<li><img class="left" src="{[this.getModulePath(values.mod)]}" ' +
									'onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.comboxStatus.modStatus,\'{modStatus}\');"/></li>',
								'<li><img class="left" src="{[this.getBoxPath(values.box)]}"  ' +
									'onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.comboxStatus.boxStatus,\'{boxStatus}\');"/></li>',
								'<li><img class="left" src="{[this.getNetPath(values.net)]}"  ' +
									'onmouseover="javascript:Eway.lib.FunctionUtils.toolTip(this,Eway.locale.monitor.devMonitor.comboxStatus.netStatus,\'{netStatus}\');"/></li>',
							'</ul>',
						'</div>',
					'<span class="x-editable">{code}({org})</span>',
				'</div>',
			'</tpl>',
			'<div class="x-clear"></div>',{
				getRunPath : function(status){

					var path = "resources/images/monitor/"+(me.screen == "" ? "" :me.screen +"/");
					if(status == 'Healthy'){//正常服务
						path += 'run_HEALTHY.png';
					}
					else if(status == 'Initial'){//初始化
						path += 'run_Initial.png';
					}
					else if(status == 'SubHealth'){//半功能服务
						path += 'SubHealth.png';
					}
					else if(status == 'Customer'){//客户交易
						path += 'run_Customer.png';
					}
					else if(status == 'Maintain'){//维护
						path += 'run_Maintain.png';
					}
					else if(status == 'Vdm'){//厂商模式
						path += 'run_Vdm.png';
					}
					else if(status == 'Halt'){//关机
						path += 'run_Halt.png';
					}
					else if(status == 'ReBoot'){//重启
						path += 'run_ReBoot.png';
					}
					else if(status == 'StopAtmp'){//P端通讯故障
						path += 'run_StopAtmp.png';
					}
					else if(status == 'StopManmade'){//人工报停
						path += 'run_StopManmade.png';
					}
					else if(status == 'StopMod'){//暂停服务-模块故障
						path += 'run_StopMod.png';
					}
					else if(status == 'StopUnCashIn'){//暂停服务-未加钞
						path += 'run_StopUnCashIn.png';
					}
					else if(status == 'StopUnKnown'){//暂停服务-未知原因
						path += 'run_StopUnKnown.png';
					}
					else {
						path += 'run_UNKNOWN.png';
					}
					return path;
				},
				getModulePath : function(status){
					var path = "resources/images/monitor/"+(me.screen == "" ? "" :me.screen +"/");
					if(status == 'Healthy'){
						path += 'module_HEALTHY.png';
					}
					else if(status == 'Warning'){
						path += 'module_WARNING.png'
					}
					else if(status == 'Fatal'){
						path += 'module_FATAL.png';
					}
					else if(status == 'Nodevice'){
						path += 'module_Nodevice.png';
					}
					else {
						path += 'module_UNKNOWN.png';
					}
					return path;
				},
				getBoxPath : function(status){
					var path = "resources/images/monitor/"+(me.screen == "" ? "" :me.screen +"/");
					if(status == 'Healthy'){ //取款钞满正常
						path += 'box_HEALTHY.png';
					}else if(status == 'Full'){ 			//存款入钞满
						path += 'box_FULL.png';
					}
					else if (status == 'Low'){     //钞少
						path += 'box_LOW.png';
					}
					else if (status == 'Empty'){    //钞空
						path += 'box_EMPTY.png';
					}
					else if(status == 'High'){      //存款入钞将满
						path += 'box_High.png';
					}
					else if(status == 'Fatal'){     //钞箱故障
						path += 'box_Fatal.png';
					}
					else {
						path += 'box_UNKNOWN.png';  //钞箱未知
					}
					return path;
				},
				getNetPath : function(status){
					var path = "resources/images/monitor/"+(me.screen == "" ? "" :me.screen +"/");
					if(status == 'Healthy'){  //网络正常
						path += 'net_HEALTHY.png';
					}
					else if(status == 'Warning'){ //网络不稳定
						path += 'net_WARNING.png';
					}
					else if(status == 'Fatal'){ //网络故障
						path += 'net_FATAL.png';
					}
					else { //未知
						path += 'net_UNKNOWN.png';
					}
					return path;
				}
			}
		);
		
		var dataViewStore = Ext.create('Eway.store.monitor.device.DataView',{});
		// dataViewStore.loadPage(1);
		Ext.apply(this, {
			autoScroll : true,
			// 解决IE7,8下不出现滚动条问题,由于extjs会对ie7,8特殊处理
			// autoScroll属性会作用在其他div上,所以需要直接写css来显示滚动
			style : 'overflow:auto;',
			frame : true,
			store : dataViewStore,
			tpl : monitorTpl,
			multiSelect : false,
			trackOver : true,
			overItemCls : 'x-item-over',
			itemSelector : 'div.thumb-wrap',
			emptyText : Eway.locale.monitor.devMonitor.noData,
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