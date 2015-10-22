
Ext.define('Eway.view.monitor.card.CardDestoryGrid', {
	alias: 'widget.card_CardDestoryGrid',
	extend: 'Eway.view.base.Grid',
	
	border : false,
	
	/**
	 * 如果吞卡超过7天则显示为红色
	 * @type 
	 */
	viewConfig: {
		forceFit: true,
		getRowClass: function(record, index) {
			var progress = record.get('cardRetainTime');
			var d = new Date(Date.parse(progress.replace(/-/g, "/")));
			/*var progressTime = Ext.util.Format.date(d, 'Y-m-d');
			var nowTime = Ext.util.Format.date(new Date(), 'Y-m-d');*/
			var start = new Date(d);
			var end = new Date();
			var elapsed = Math.round((end.getTime()-start.getTime())/86400000);
			var cardStatus = record.get('status');
			if(elapsed > 7 && cardStatus == '0'){
				return 'user-online';
			}
			else{
				return '';
			}
		}
	},
	
	initComponent: function() {
		var store = Ext.create('Eway.store.monitor.card.CardDestroy');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:Eway.locale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: Eway.locale.monitor.business.card.destroyCard,
				iconCls :'cardDetoryBtn',
				action: 'destory',
				code : 'cardDestory',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : Eway.locale.commen.terminalId,
				dataIndex : 'terminalId',
				flex : 1
			}, {
				header : Eway.locale.commen.orgNameBelongs,
				dataIndex : 'subsidiaryorganName',
				flex : 1
			}, {
				header : Eway.locale.monitor.business.card.processOrg,
				dataIndex : 'handOverOrgName',
				flex : 1
			}, {
				header : Eway.locale.monitor.business.transaction.card,
				dataIndex : 'accountNo',
				flex : 1
			},{
				header : Eway.locale.monitor.business.card.type,
				dataIndex : 'cardRetainType',
				flex : 1,
				renderer : function(value){
					if(value == 1){
						return Eway.locale.monitor.business.card.manual;
					}else if(value == 2){
						return Eway.locale.monitor.business.card.auto;
					}
				}
			},{
				header : Eway.locale.monitor.business.card.time,
				dataIndex : 'cardRetainTime',
				flex : 1
			},{
				header : Eway.locale.monitor.business.card.cardHolder,
				dataIndex : 'cardDistributionBank',
				flex : 1
			},{
				header : Eway.locale.commen.state,
				dataIndex : 'status',
				renderer : function(value){
					if(value == 0){
						return Eway.locale.monitor.business.card.comboxStatus.wait;
					}else if(value == 1){
						return Eway.locale.monitor.business.card.comboxStatus.received;
					}else if(value == 2){
						return Eway.locale.monitor.business.card.comboxStatus.destroy;
					}else if(value == 3){
						return Eway.locale.monitor.business.card.comboxStatus.bringed;
					}
				},
				flex : 1
			},{
				header : Eway.locale.monitor.business.card.reason,
				dataIndex : 'reason',
				flex : 1
			},{
				header : Eway.locale.monitor.business.card.treatmentPeople,
				dataIndex : 'treatmentPeople',
				flex : 1
			},{
				header : Eway.locale.monitor.business.card.treatmentTime,
				dataIndex : 'treatmentTime',
				flex : 1
			},{
				header : Eway.locale.monitor.business.card.customerName,
				dataIndex : 'customerName',
				flex : 1
			},{
				header : Eway.locale.monitor.business.card.customerPhone,
				dataIndex : 'customerPhone',
				flex : 1
			},{
				header : Eway.locale.monitor.business.card.customerPapers,
				dataIndex : 'customerPapers',
				flex : 1
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		
		this.callParent(arguments);
	},
	
	onReload: function() {
		this.getStore().load({
			url: 'api/monitor/retainCard/findCardByStatus'
		});
	}
});