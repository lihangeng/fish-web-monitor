
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
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action:'update'
//				code : 'atmBrandUpdate',
//				listeners:{
//					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
//				}
			}],
			columns : [{
				header : Eway.locale.cases.caseFault.faultClassify,
				dataIndex : 'classifyName',
				width : 150
			}, {
				header : Eway.locale.cases.notifyMould.noticeType,
				dataIndex : 'notifyType',
				width : 80,
				renderer : function(value){
					if(value == 1){
						return Eway.locale.cases.notifyMould.createNotice;
					}else if(value == 2){
						return Eway.locale.cases.notifyMould.upgradeNotice;
					}else if(value == 3){
						return Eway.locale.cases.notifyMould.closeNotice;
					}
				}
			}, {
				header : Eway.locale.cases.caseFault.informWay,
				dataIndex : 'notifyWay',
				width : 80,
				renderer : function(value){
					if(value == 'SMS'){
						return Eway.locale.cases.caseFault.message;
					}else if(value == 'MAIL'){
						return Eway.locale.cases.caseFault.mail;
					}else if(value == 'BOTH'){
						return Eway.locale.cases.caseFault.messageAndMail;
					}
				}
			}, {
				header : Eway.locale.cases.notifyMould.noticeValue,
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