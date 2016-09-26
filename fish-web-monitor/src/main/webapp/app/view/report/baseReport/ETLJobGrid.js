Ext.define('Eway.view.report.baseReport.ETLJobGrid', {
	extend: 'Eway.view.base.Grid',
	alias : 'widget.trans_JobGrid',
	requires : ['Eway.lib.Util'],

	scrollable :true,
	closable : false,
	initComponent: function() {
		var store = Ext.create('Eway.store.report.baseReport.ETLJob');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: ['->',{
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query',
				code:'ETLJobQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.batch.check,
				glyph : 0xf133,
				action: 'count',
				code:'ETLJobCount',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [{
				header :EwayLocale.batch.jobName,
				dataIndex : 'jobName',
				width : 180
			},{
				header : EwayLocale.batch.startTime,
				dataIndex : 'startTime',
				width : 180
			},{
				header : EwayLocale.batch.endTime,
				dataIndex : 'endTime',
				width : 180
			},{
				header : EwayLocale.batch.tradeTime,
				dataIndex : 'tradeTime',
				width : 180
			},{
				header : EwayLocale.batch.operaResult,
				dataIndex : 'operaResult',
				width : 150
			},{
				header : EwayLocale.batch.again,
				xtype:'actioncolumn',
				width : 150,
				items:[{
                    icon:"././././resources/images/yihua-exploer/refresh.gif",
                    getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var result = record.get('operaResult'); 
						if(result == 'COMPLETED'){
							return 'hiddenComp';
						}else{
							return 'changeCursor';
						}
					},
                    handler:function(grid,rowIndex,colIndex){
                       var rec=grid.getStore().getAt(rowIndex);
                       var tradeTime = rec.get('tradeTime');
                       var id = rec.get('id');
                       var jobName = rec.get('jobName');
					   Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.batch.doAgain,callBack);
					    function callBack(button){
					    	if(button=='yes'){
					    		Ext.Ajax.request({
			               			method : 'POST',
			               			url : 'api/report/ETLJob/operaAgain',
			               			params :{
			               				id:id,
			               				jobName:jobName,
			               				tradeTime: tradeTime
			               			},
			               			success : function(response){
			               				var object = Ext.decode(response.responseText);
			               				grid.getStore().loadPage(1);
			               				Eway.alert(EwayLocale.batch.doAgainSu);
			               			},
			               			failure : function(){
			               				Eway.alert(EwayLocale.batch.doAgainFa);
			               			}
		               			});
					    	}
					    }
					}
				}]
			
			},{
				header : EwayLocale.batch.errorPrompt,
				xtype:'actioncolumn',
				width : 150,
				items:[{
                    icon:"././././resources/images/down.gif",
                    getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var result = record.get('operaResult'); 
						if(result == 'COMPLETED'){
							return 'hiddenComp';
						}else{
							return 'changeCursor';
						}
					},
                    handler:function(grid,rowIndex,colIndex){
                       var rec=grid.getStore().getAt(rowIndex);
                       var id = rec.get('id');
					   Ext.MessageBox.confirm(EwayLocale.tip.tips,EwayLocale.tip.achieveTips,callBack);
					    function callBack(button){
					    	if(button=='yes'){
					    		Ext.Ajax.request({
			               			method : 'POST',
			               			url : 'api/report/ETLJob/getErrorMsg',
			               			params :{
			               				id:id
			               			},
			               			success : function(response){
			               				var fileName='BatchErrorMsg';
			               				var object = Ext.decode(response.responseText);
			        			        if(object.success){
			        			        	var url = encodeURI('api/report/downloadFile?path='+ object.data + '&reportTitle=' + fileName);
			        						var iframe = document.getElementById('downloadFileFromWeb');
			        						iframe.src = url;
			        			        }else{
			        			        	Eway.alert(EwayLocale.vtype.exportRepError);
			        			        }
			               			},
			               			failure : function(){
//			               				Eway.alert('');
			               			}
		               			});
					    	}
					    }
					}
				}]
			
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	}
});
