Ext.define('Eway.view.version.download.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.version_download_filterForm',
	
	requires : ['Eway.view.version.field.VersionTypeComboBox'],
	height : 80,
	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			items : [{
				columnWidth : 0.3,
				items : [{
					xtype:'textfield',
					fieldLabel:EwayLocale.refs.terminalId,//'设备编号',
					name:'terminalId'
				},{
					xtype : 'combo',
					fieldLabel : EwayLocale.version.task.updateResult,//'升级结果',
					name : 'updateResult',
					editable:false,
					store : Ext.create('Ext.data.Store',{
						fields : ['value','name'],
						data : [
							{'value':'1','name':EwayLocale.version.task.updateResultSuccess},//'成功'},
							{'value':'0','name':EwayLocale.version.task.updateResultFailed}//'失败'}
						]
					}),
					queryMode : 'local',
					valueField : 'value',
					displayField : 'name',
					emptyText:EwayLocale.commen.all
				}]},{
					columnWidth: 0.3,
					items:[{
						xtype : 'combo',
						fieldLabel : EwayLocale.version.taskType,//'任务类型',
						name : 'taskType',
						editable:false,
						store : Ext.create('Ext.data.Store',{
							fields : ['value','name'],
							data : [
								{'value':'0','name':EwayLocale.version.taskTypeAuto},//'自动升级'},
								{'value':'1','name':EwayLocale.version.taskTypeManual},//'手动升级'},
								{'value':'2','name':EwayLocale.version.taskTypeScheduler}//'计划作业'}
							]
						}),
						queryMode : 'local',
						valueField : 'value',
						displayField : 'name',
						emptyText:EwayLocale.commen.all
					},{
						xtype:'textfield',
						fieldLabel:EwayLocale.version.task.jobBatchName,//'任务批次名称',
						name:'jobName'
					}]
				},
				{
					columnWidth: 0.3,
					items:[{
						xtype:'field_versionTypeComboBox',
						store: Ext.create('Ext.data.Store',{
							fields: ['id', 'typeName'],
							proxy: {
						        type: 'rest',
						        url : 'api/version/versionType/searchCombo',
						        reader: {
						            type: 'json',
						            rootProperty : 'data'
						        }
						    },
						    autoLoad : false						
						})
					},{
						xtype : 'textfield',
						fieldLabel : EwayLocale.version.View.versionNo,//'版本号',
						name : 'versionNo',
					   maxLength: 40,
					   vtype:'versionNo'
					}]
				}
			]
		});
		this.callParent(arguments);
	}

});