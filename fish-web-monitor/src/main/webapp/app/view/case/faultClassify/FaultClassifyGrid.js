
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
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action:'update'
//				code : 'atmBrandUpdate',
//				listeners:{
//					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
//				}
			}],
			columns : [{
				header : Eway.locale.cases.faultClassify.faultClassifyName,
				dataIndex : 'classifyName',
				width : 140
			}, {
				header : Eway.locale.cases.faultClassify.faultresponsorType,
				dataIndex : 'responsorType',
				width : 110,
				renderer : function(value){
					if(value == 1){
						return Eway.locale.commen.comboxType.machineManager;
					}else if(value == 2){
						return Eway.locale.cases.faultClassify.maintain;
					}else if(value == 3){
						return Eway.locale.cases.faultClassify.manageAndMaintain;
					}
				}
			}, {
				header : Eway.locale.cases.faultClassify.upGradeTimes,
				dataIndex : 'upgrade',
				width : 90
			},{
				header : Eway.locale.cases.caseFault.notifyRepeatTimes,
				dataIndex : 'notifyTimes',
				width : 90
			},{
				header : Eway.locale.cases.faultClassify.faultInformWay,
				dataIndex : 'notifyWay',
				width : 90,
				renderer : function(value){
					if(value == 'SMS'){
						return Eway.locale.cases.caseFault.message;
					}else if(value == 'MAIL'){
						return Eway.locale.cases.caseFault.mail;
					}else if(value == 'BOTH'){
						return Eway.locale.cases.caseFault.messageAndMail;
					}else if(value == 'NONE'){
						return Eway.locale.cases.caseFault.none;
					}
				}
			}, {
				header : Eway.locale.cases.faultClassify.faultCloseInterval,
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