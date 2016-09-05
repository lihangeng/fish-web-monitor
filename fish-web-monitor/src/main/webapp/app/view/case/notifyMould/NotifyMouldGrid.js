
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
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'messageUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.cases.caseFault.faultClassify,
				dataIndex : 'classifyName',
				width : 150
			}, {
				header : EwayLocale.cases.notifyMould.noticeType,
				dataIndex : 'notifyType',
				width : 80,
				renderer : function(value){
					if(value == 1){
						return EwayLocale.cases.notifyMould.createNotice;
					}else if(value == 2){
						return EwayLocale.cases.notifyMould.upgradeNotice;
					}else if(value == 3){
						return EwayLocale.cases.notifyMould.closeNotice;
					}
				}
			}, {
				header : EwayLocale.cases.caseFault.informWay,
				dataIndex : 'notifyWay',
				width : 80,
				renderer : function(value){
					if(value == 'SMS'){
						return EwayLocale.cases.caseFault.message;
					}else if(value == 'MAIL'){
						return EwayLocale.cases.caseFault.mail;
					}else if(value == 'BOTH'){
						return EwayLocale.cases.caseFault.messageAndMail;
					}else if(value == 'NONE'){
						return EwayLocale.cases.caseFault.none;
					}
				}
			}, {
				header : EwayLocale.cases.notifyMould.noticeValue,
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