
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
				text: Eway.locale.button.search,
				glyph : 0xf002,
				action: 'query'
			}, {
				text: Eway.locale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'quittingNoticeAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.update,
				glyph : 0xf040,
				action: 'update',
				code : 'quittingNoticeUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text: Eway.locale.button.remove,
				glyph : 0xf014,
				action: 'remove',
				code : 'quittingNoticeDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header : Eway.locale.machine.atmGroup.terminalId,
				dataIndex : 'deviceCode',
				flex : 1
			}, {
				header : Eway.locale.machine.quittingNotice.stopTime,
				dataIndex : 'stopTime',
				flex : 1/*,
				renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')*/
			}, {
				header : Eway.locale.machine.quittingNotice.openTime,
				dataIndex : 'openTime',
				flex : 1/*,
				renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')*/
			},{
				header : Eway.locale.machine.quittingNotice.currentStatus,
				dataIndex : 'devStatus',
				renderer: function(value,metadata,record){  
                   if(value=='DISABLED'){
                	   return Eway.locale.machine.atmGroup.comboxStatus.close;
                   }else if(value=='OPENING'){
                	   return Eway.locale.machine.atmGroup.comboxStatus.open;
                   }
				},
				flex : 1
			},{
				header : Eway.locale.machine.quittingNotice.stopType,
				dataIndex : 'stopType',
				renderer: function(value,metadata,record){  
                   if(value==0){
                	   return Eway.locale.machine.quittingNotice.comboxStopType.recess;
                   }else if(value==1){
                	   return Eway.locale.machine.quittingNotice.comboxStopType.fit;
                   }else if(value==2){
                	   return Eway.locale.machine.quittingNotice.comboxStopType.power;
                   }else if(value==3){
                	   return Eway.locale.machine.quittingNotice.comboxStopType.devFailue;
                   }else{
                	   return Eway.locale.machine.quittingNotice.comboxStopType.other;
                   }
				},
				flex : 1
			},{
				header : Eway.locale.machine.quittingNotice.stopReason,
				dataIndex : 'stopReason',
				flex : 1
			},{
				header : Eway.locale.machine.quittingNotice.setTime,
				dataIndex : 'setTime',
				flex : 1/*,
				renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')*/
			//	format : 'Y-m-d H:i:s'
			},{
				header : Eway.locale.machine.quittingNotice.responsibilityName,
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