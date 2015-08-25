
Ext.define('Eway.view.machine.quittingNotice.QuittingNoticeGrid', {
	alias: 'widget.quittingNotice_quittingNoticeGrid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	autoFit:true,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.machine.quittingNotice.QuittingNotice');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar: ['->',{
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}, {
				text: '增加',
				glyph : 0xf067,
				action: 'add',
				code : 'quittingNoticeAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '更改',
				glyph : 0xf040,
				action: 'update',
				code : 'quittingNoticeUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: '删除',
				glyph : 0xf014,
				action: 'remove',
				code : 'quittingNoticeDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : '设备编号',
				dataIndex : 'deviceCode',
				flex : 1
			}, {
				header : '停机时间',
				dataIndex : 'stopTime',
				flex : 1/*,
				renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')*/
			}, {
				header : '恢复时间',
				dataIndex : 'openTime',
				flex : 1/*,
				renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')*/
			},{
				header : '当前状态',
				dataIndex : 'devStatus',
				renderer: function(value,metadata,record){  
                   if(value=='DISABLED'){
                	   return "停用";
                   }else if(value=='OPENING'){
                	   return "开通";
                   }
				},
				flex : 1
			},{
				header : '停机类型',
				dataIndex : 'stopType',
				renderer: function(value,metadata,record){  
                   if(value==0){
                	   return "放假";
                   }else if(value==1){
                	   return "装修";
                   }else if(value==2){
                	   return "停电";
                   }else if(value==3){
                	   return "设备故障未修复";
                   }else{
                	   return "其它";
                   }
				},
				flex : 1
			},{
				header : '停机原因',
				dataIndex : 'stopReason',
				flex : 1
			},{
				header : '设置时间',
				dataIndex : 'setTime',
				flex : 1/*,
				renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')*/
			//	format : 'Y-m-d H:i:s'
			},{
				header : '停机责任人',
				dataIndex : 'responsibilityName',
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
		this.getStore().load();
	}
});