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
					fieldLabel:'设备编号',
					name:'terminalId'
				},{
					xtype : 'combo',
					fieldLabel : '升级结果',
					name : 'updateResult',
					store : Ext.create('Ext.data.Store',{
						fields : ['value','name'],
						data : [
							{'value':'1','name':'成功'},
							{'value':'0','name':'失败'}
						]
					}),
					queryMode : 'local',
					valueField : 'value',
					displayField : 'name',
					emptyText:'全部'
				}]},{
					columnWidth: 0.3,
					items:[{
						xtype : 'combo',
						fieldLabel : '任务类型',
						name : 'taskType',
						store : Ext.create('Ext.data.Store',{
							fields : ['value','name'],
							data : [
								{'value':'0','name':'自动升级'},
								{'value':'1','name':'手动升级'},
								{'value':'2','name':'计划作业'}
							]
						}),
						queryMode : 'local',
						valueField : 'value',
						displayField : 'name',
						emptyText:'全部'
					},{
						xtype:'textfield',
						fieldLabel:'任务批次名称',
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
						fieldLabel : '版本号',
						name : 'versionNo',
						maxLength: 20
					}]
				}
			]
		});
		this.callParent(arguments);
	}

});