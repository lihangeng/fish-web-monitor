
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
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'faultClassifyUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.cases.faultClassify.faultClassifyName,
				dataIndex : 'classifyName',
				flex : 1
			}, {
				header : EwayLocale.cases.faultClassify.faultresponsorType,
				dataIndex : 'responsorType',
				width : 160,
				renderer : function(value){
					if(value == 1){
						return EwayLocale.commen.comboxType.machineManager;
					}else if(value == 2){
						return EwayLocale.cases.faultClassify.maintain;
					}else if(value == 3){
						return EwayLocale.cases.faultClassify.manageAndMaintain;
					}
				}
			}, {
				header : EwayLocale.cases.faultClassify.upGradeTimes,
				dataIndex : 'upgrade',
				width : 150
			},{
				header : EwayLocale.cases.caseFault.notifyRepeatTimes,
				dataIndex : 'notifyTimes',
				width : 100
			},{
				header : EwayLocale.cases.faultClassify.faultInformWay,
				dataIndex : 'notifyWay',
				width : 100,
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
				header : EwayLocale.cases.faultClassify.faultCloseInterval,
				dataIndex : 'resolveHour',
				width : 240
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