
Ext.define('Eway.view.system.quartz.QuartzGrid', {
	alias: 'widget.quartz_QuartzGrid',
	extend: 'Eway.view.base.Grid',


	border : true,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.quartz.Quartz');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			},/*{
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'quartzAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'quartzDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},*/{
				text: EwayLocale.button.update,
				glyph : 0xf04c,
				action:'update',
				code : 'quartzUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '暂停',
				glyph : 0xf28b,
				action: 'pause',
				code : 'quartzPause',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '恢复',
				glyph : 0xf144,
				action: 'resume',
				code : 'quartzResume',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: '立即执行',
				glyph : 0xf04b,
				action: 'resume',
				code : 'quartzExecute',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : '任务名称',
				dataIndex : 'jobName',
				width : 200
			},/*{
				header : 'Job所属组',
				dataIndex : 'jobGroup',
				width : 140
			},{
				header : 'Trigger名称',
				dataIndex : 'triggerName',
				width : 140
			},{
				header : 'Trigger所属组',
				dataIndex : 'triggerGroup',
				width : 140
			},*/{
				header : '任务实现类',
				dataIndex : 'jobClassName',
				width : 400,
				hidden:true
			},{
				header : '任务描述',
				dataIndex : 'jobDescription',
				flex:1
			},{
				header : '任务执行规则',
				dataIndex : 'cronExpression',
				width : 140
			},{
				header : '任务当前状态',
				dataIndex : 'triggerState',
				width : 140
			},{
				header : '下次触发时间',
				dataIndex : 'nextFireTime',
				width : 140
			},{
				header : '上次触发时间',
				dataIndex : 'prevFireTime',
				width : 140
			},{
				header : '任务创建时间',
				dataIndex : 'startTime',
				width : 140
			}/*,{
				header : 'Job结束时间',
				dataIndex : 'endTime',
				width : 140
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