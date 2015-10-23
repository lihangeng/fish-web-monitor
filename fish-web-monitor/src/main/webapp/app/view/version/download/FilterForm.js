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
					vtype : 'terminalId',
					maxLength:20,
					fieldLabel:Eway.locale.refs.terminalId,//'设备编号',
					name:'terminalId'
				},{
					xtype : 'combo',
					fieldLabel : Eway.locale.version.task.updateResult,//'升级结果',
					name : 'updateResult',
					store : Ext.create('Ext.data.Store',{
						fields : ['value','name'],
						data : [
							{'value':'1','name':Eway.locale.tip.success},//'成功'},
							{'value':'0','name':Eway.locale.tip.fail}//'失败'}
						]
					}),
					queryMode : 'local',
					valueField : 'value',
					displayField : 'name',
					emptyText:Eway.locale.commen.all
				}]},{
					columnWidth: 0.3,
					items:[{
						xtype : 'combo',
						fieldLabel : Eway.locale.version.taskType,//'任务类型',
						name : 'taskType',
						store : Ext.create('Ext.data.Store',{
							fields : ['value','name'],
							data : [
								{'value':'0','name':Eway.locale.version.taskTypeAuto},//'自动升级'},
								{'value':'1','name':Eway.locale.version.taskTypeManual},//'手动升级'},
								{'value':'2','name':Eway.locale.version.taskTypeScheduler}//'计划作业'}
							]
						}),
						queryMode : 'local',
						valueField : 'value',
						displayField : 'name',
						emptyText:Eway.locale.commen.all
					},{
						xtype:'textfield',
						maxLength:20,
						fieldLabel:Eway.locale.version.batchTaskName,//'任务批次名称',
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
						fieldLabel : Eway.locale.version.View.versionNo,//'版本号',
						name : 'versionNo',
					   maxLength: 40,
					   vtype:'versionNo',
						maxLength: 20
					}]
				}
			]
		});
		this.callParent(arguments);
	}

});