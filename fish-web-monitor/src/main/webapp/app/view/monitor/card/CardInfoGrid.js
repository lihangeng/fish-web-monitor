
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
				text:'查询',
				glyph : 0xf002,
				action:'query'
			},{
				text: '增加',
				glyph : 0xf067,
				action: 'add',
				code : 'cardAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'cardDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text : '导出',
				iconCls :'exportToExcel',
				action : 'export'
			}],
			columns : [{
				header : '设备号',
				dataIndex : 'terminalId',
				flex : 1
			}, {
				header : '所属机构',
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
				header : '卡号',
				dataIndex : 'accountNo',
				flex : 1
			},{
				header : '吞卡类型',
				dataIndex : 'cardRetainType',
				flex : 1,
				renderer : function(value){
					if(value == 1){
						return '手动添加';
					}else if(value == 2){
						return '自动添加';
					}
				}
			},{
				header : '吞卡时间',
				dataIndex : 'cardRetainTime',
				flex : 1
			},{
				header : '发卡行',
				dataIndex : 'cardDistributionBank',
				flex : 1
			},{
				header : '状态',
				dataIndex : 'status',
				renderer : function(value){
					if(value == 0){
						return '待领';
					}else if(value == 1){
						return '已领';
					}else if(value == 2){
						return '销毁';
					}else if(value == 3){
						return '调出';
					}
				},
				flex : 1
			},{
				header : '吞卡原因',
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