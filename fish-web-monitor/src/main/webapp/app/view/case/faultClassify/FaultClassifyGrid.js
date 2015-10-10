
Ext.define('Eway.view.case.faultClassify.FaultClassifyGrid', {
	alias: 'widget.faultClassify_FaultClassifyGrid',
	extend: 'Eway.view.base.Grid',

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.case.FaultClassify');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->', {
				text: '更改',
				glyph : 0xf040,
				action:'update'
//				code : 'atmBrandUpdate',
//				listeners:{
//					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
//				}
			}],
			columns : [{
				header : '故障分类名称',
				dataIndex : 'classifyName',
				width : 140
			}, {
				header : '故障责任人类型',
				dataIndex : 'responsorType',
				width : 110,
				renderer : function(value){
					if(value == 1){
						return Eway.locale.commen.comboxType.machineManager;
					}else if(value == 2){
						return '维护员';
					}else if(value == 3){
						return '管机员和维护员 ';
					}
				}
			}, {
				header : '最高升级次数',
				dataIndex : 'upgrade',
				width : 90
			},{
				header : '重复通知次数',
				dataIndex : 'notifyTimes',
				width : 90
			},{
				header : '故障通知方式',
				dataIndex : 'notifyWay',
				width : 90,
				renderer : function(value){
					if(value == 'SMS'){
						return "短信";
					}else if(value == 'MAIL'){
						return "邮件";
					}else if(value == 'BOTH'){
						return "短信和邮件";
					}
				}
			}, {
				header : '故障规定关闭时间间隔（单位:小时）',
				dataIndex : 'resolveHour',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},
	onReload : function(){
		this.getStore().load();
	}
});