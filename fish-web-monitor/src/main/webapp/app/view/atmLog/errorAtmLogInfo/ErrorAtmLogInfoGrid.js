Ext.define('Eway.view.atmLog.errorAtmLogInfo.ErrorAtmLogInfoGrid',{

	extend : 'Eway.view.base.Grid',
	alias : 'widget.atmLog_errorAtmLogInfo_ErrorAtmLogInfoGrid',
	
	
	initComponent : function(){
		var me = this;
		var store = Ext.create('Eway.store.atmLog.ErrorAtmLogInfo');
//		store.loadPage(1);
		Ext.apply(this,{
			store : store,
			columns : [{
				header : 'id',
				dataIndex : 'id',
				width : 100,
				hidden : true
			},{
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				width : 100
			},{
				header : EwayLocale.atmLog.logDate,
				dataIndex : 'dateTime',
				width : 100
			},{
				header : EwayLocale.atmLog.backupResult,
				dataIndex : 'backupResult',
				width : 100,
				renderer : function(value,metaData,record,rowIndex,colIndex,store,view){
					if(value == 'SUCCESS'){
						return EwayLocale.atmLog.backupSuccess;
					}else{
						return EwayLocale.tip.fail;
					}
				}
			},{
				header : EwayLocale.atmLog.lastDoDate,
				dataIndex : 'lastDoDate',
				width : 160
			},{
				header : EwayLocale.atmLog.getLog,
				flex: 1,
				xtype:'actioncolumn',
				items:[{
                    icon:"././././resources/images/downfile.png",
                    iconCls:'<span class="floder-name-blackground">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>',
					getClass:function(v,metadata,r,rowIndex,colIndex,store){
						var rec=store.getAt(rowIndex);
						if(rec.data.type=="DIR"){
							return "actioncolumn-hidden";
	                       }else{
	                       		metadata.tdAttr ='data-qtip="'+EwayLocale.agent.remote.clickLoadFile+'"';
	                       }
					},
					scope : me,
                    handler : function(grid, rowIndex, colIndex) {
						var me = this;
						var rec = grid.getStore().getAt(rowIndex);
						var terminalId = rec.get('terminalId');
						var dateTime = rec.get('dateTime');
						if (rec.get('size') > 209715200) {
							Eway.alert(EwayLocale.agent.remote.loadFileSize);
						} else {
							var win = grid.up('window');
							var gridEl = grid.getEl();
							var mask = new Ext.LoadMask(grid, {msg : EwayLocale.agent.remote.nowLoadFile});
							Ext.MessageBox.show({
								title : EwayLocale.tip.remind,
								msg : EwayLocale.agent.remote.judgeLoad,
								modal : true,
								fn : function(id) {
									mask.show();
									if (id == 'yes') {
										var flag = true;
										me.download(flag, terminalId,dateTime,mask, gridEl)
									} else if (id == 'no') {
										var flag = false;
										me.download(flag, terminalId,dateTime,mask, gridEl)
									} else {
										mask.hide();
									}
								},
								buttons : Ext.Msg.YESNOCANCEL
							}).setWidth(260);
						}
					}
                  }],
                  align:"center",
                  code : 'remoteDownFile',
  				  listeners:{
  					'afterrender': Eway.lib.ButtonUtils.onButtonBeforeRender
  				  }
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
			
				store : store,
				displayInfo : true
			}),
			tbar : ['->',{
				xtype : 'button',
				text : EwayLocale.button.exported,
				glyph : 0xf1c3,
				action : 'export'
			}]
		});
		this.callParent(arguments);
	},


download : function(flag, terminalId, dateTime, mask, gridEl) {
	Ext.Ajax.request({
		method : 'POST',
		url : 'api/machine/atmLog/fileDownError?date=' + new Date(),
		timeout : 120000,
		params : {
			flag : flag,
			terminalId : terminalId,
			dateTime : dateTime
		},
		success : function(response) {
			var object = Ext.decode(response.responseText);
			if (object.success == true) {
				mask.hide();
				var iframe = gridEl.prev();
				var fileName = object.fileName.replace("&", "%26");// 将文件名含有&符号的用URL编码“%26”替换
				if (iframe) {
					Ext.core.Element.get(iframe).destroy();
				}
				iframe = Ext.core.DomHelper.createDom({
							tag : 'iframe',
							src : 'api/machine/atmLog/downloadFile?deviceId='
									+ terminalId + '&dateTime=' + dateTime,
							style : "display:none"
						});
				gridEl.insertSibling(iframe);
			} else {
				mask.hide();
				Eway.alert(object.errorMsg);
			}
		},
		failure : function() {
			mask.hide();
			Eway.alert(EwayLocale.agent.remote.loadFailure);
		}
	});
}	
});