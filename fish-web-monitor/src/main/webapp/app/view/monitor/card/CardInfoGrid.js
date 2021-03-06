
Ext.define('Eway.view.monitor.card.CardInfoGrid', {
	alias: 'widget.card_CardInfoGrid',
	extend: 'Eway.view.base.Grid',

//	store: 'monitor.card.CardInfo',
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
		var store = Ext.create('Eway.store.monitor.card.CardInfo');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'cardAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'cardDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text : EwayLocale.monitor.business.card.exportData,
				glyph : 0xf1c3,
				action : 'export',
				code : 'cardExport',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				flex : 1
			}, {
				header : EwayLocale.commen.orgNameBelongs,
				dataIndex : 'subsidiaryorganName',
				flex : 1
			},
			/**
			 * 上海农商行暂不需要
			 */
			/*{
				header : '处理机构(机构号)',
				dataIndex : 'handOverOrgName',
				flex : 1
			},*/ {
				header : EwayLocale.monitor.business.transaction.card,
				dataIndex : 'accountNo',
				flex : 1
			},{
				header : EwayLocale.monitor.business.card.type,
				dataIndex : 'cardRetainType',
				flex : 1,
				renderer : function(value){
					if(value == 1){
						return EwayLocale.monitor.business.card.manual;
					}else if(value == 2){
						return EwayLocale.monitor.business.card.auto;
					}
				}
			},{
				header : EwayLocale.monitor.business.card.time,
				dataIndex : 'cardRetainTime',
				flex : 1
			},{
				header : EwayLocale.monitor.business.card.cardHolder,
				dataIndex : 'cardDistributionBank',
				flex : 1
			}/*,{
				header : EwayLocale.commen.state,
				dataIndex : 'status',
				renderer : function(value){
					if(value == 0){
						return EwayLocale.monitor.business.card.comboxStatus.wait;
					}else if(value == 1){
						return EwayLocale.monitor.business.card.comboxStatus.received;
					}else if(value == 2){
						return EwayLocale.monitor.business.card.comboxStatus.destroy;
					}else if(value == 3){
						return EwayLocale.monitor.business.card.comboxStatus.bringed;
					}
				},
				flex : 1
			}*/,{
				header : EwayLocale.monitor.business.card.reason,
				dataIndex : 'reason',
				flex : 1
			}
			/**
			 * 上海农商行暂不需要
			 */
			/*,{
				header : '处理人员',
				dataIndex : 'treatmentPeople',
				flex : 1
			},{
				header : '处理时间',
				dataIndex : 'treatmentTime',
				flex : 1
			},{
				header : '客户姓名',
				dataIndex : 'customerName',
				flex : 1
			},{
				header : '客户电话',
				dataIndex : 'customerPhone',
				flex : 1
			},{
				header : '客户证件号',
				dataIndex : 'customerPapers',
				flex : 1
			}*/],
				bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	onReload: function() {
		this.getStore().load();
	}
});