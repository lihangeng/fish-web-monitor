
Ext.define('Eway.view.case.notifyMould.NotifyMouldGrid', {
	alias: 'widget.notifyMould_NotifyMouldGrid',
	extend: 'Eway.view.base.Grid',

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.case.NotifyMould');
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
				header : '故障分类',
				dataIndex : 'classifyName',
				width : 150
			}, {
				header : '通知类型',
				dataIndex : 'notifyType',
				width : 80,
				renderer : function(value){
					if(value == 1){
						return "创建通知";
					}else if(value == 2){
						return "升级通知";
					}else if(value == 3){
						return "关闭通知"
					}
				}
			}, {
				header : '通知方式',
				dataIndex : 'notifyWay',
				width : 80,
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
				header : '通知参数',
				dataIndex : 'notifySet',
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