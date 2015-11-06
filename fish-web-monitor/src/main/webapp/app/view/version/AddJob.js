
Ext.define('Eway.view.version.AddJob', {
	extend : 'Ext.window.Window',
	alias : 'widget.version_addJob',
	requires : ['Eway.view.version.field.JobType','Ext.ux.form.DateTimeField',
	            'Eway.view.version.download.SelectableDeviceGrid',
	            'Eway.view.version.download.LinkedDeviceGrid'],

	title : Eway.locale.version.addJobTitle,//'配置作业信息',
	modal : true,
	resizable : true,
	constrainHeader : true,
    maximizable: true,
    minWidth: 600,
    minHeight: 400,
    width: 930,
    height: 500,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this,{
			items : [ {
				region : 'south',
				buttonAlign:'center',
				buttons :[{
					text : Eway.locale.button.save,//'保存',
					action : 'confirm',
					iconCls:'db-save'
				}]
//			}]
			},{
				region : 'north',
				xtype:'form',
				bodyStyle : 'padding: 5px 0px 5px 5px',
				trackResetOnLoad : true,
				layout: {
		            type: 'vbox',
		            align: 'stretch'  // Child items are stretched to full width
		        },
		        fieldDefaults : {
					labelWidth : 100,
					labelAlign : 'right',
					msgTarget : 'side',
					grow : false,
					border : false
				},
				items : [{
					xtype: 'textfield',
					fieldLabel:Eway.locale.version.View.versionDesc,//版本描述",
					name :'jobName',
					maxLength: 128,
					allowBlank:false,
					readOnly:true,
					emptyText:Eway.locale.version.batchTaskNameEmpty//'例如:****需求第1批次升级'
			 },{
					xtype: 'container',
                    layout: 'hbox',
                    items: [{
    					xtype: 'hidden',
    					fieldLabel : Eway.locale.version.downloadVersionId,//'下发版本ID',
    					name: 'versionId'
    				},{
    					xtype: 'displayfield',
    					fieldLabel : Eway.locale.version.View.versionTypeName,//'软件分类',
    					name: 'versionType',
    					width : 350
    				},{
    					xtype: 'displayfield',
    					fieldLabel : Eway.locale.version.View.versionNo,//'版本号',
    					name: 'versionNo',
    					width : 200
    				},{
    					xtype: 'displayfield',
    					fieldLabel : Eway.locale.version.View.versionFile,//'版本文件',
    					name: 'serverPath'
    				}]
				},{
					xtype: 'container',
                    layout: 'hbox',
                    items: [/*{
                    	xtype: 'combobox',
                    	fieldLabel: '作业优先级',
		                store: Ext.StoreMgr.lookup("version.JobPriority"),
		                queryMode: 'local',
		                valueField : 'value',
		                displayField: 'display',
		                value:'GENERAL',
		                name:'jobPriority',
		                editable : false,
		                canClear : false,
		                width : 200
		            },*/{
                    	xtype: 'combobox',
                    	fieldLabel: Eway.locale.version.taskType,//'任务类型',
		                store: Ext.StoreMgr.lookup("version.JobType"),
		                queryMode: 'local',
		                valueField : 'value',
		                displayField: 'display',
		                value:'MANUAL',
		                name:'taskType',
		                padding: '0 0 0 30',
		                canClear : false,
		                editable : false,
		                width : 260
		            }, {
			        	xtype:'datetimefield',
			        	fieldLabel:Eway.locale.version.planTime,//'计划执行时间',
			        	name:'planTime',
			        	disabled: true,
			        	editable: false,
			        	canClear : false,
		                padding: '0 0 0 30',
			            format: 'Y-m-d H:i:s',
			            minValue: new Date(),
			            maxValue:Ext.Date.add(Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d') + " 23:59:59","Y-m-d H:i:s"), Ext.Date.DAY, 7)
					},{
						 xtype: 'radiogroup',
						 fieldLabel: Eway.locale.version.download.selectAllDevice,
					        // Arrange radio buttons into two columns, distributed vertically
					        columns: 2,
					        vertical: true,
					        items: [
					            { boxLabel: Eway.locale.commen.yes, name: 'allDevice', inputValue: 'true' },
					            { boxLabel: Eway.locale.commen.no, name: 'allDevice', inputValue: 'false', checked: true}
					        ]
					}]
				}/*,{
					xtype: 'container',
                    layout: 'hbox',
                    items: [ {
			        	xtype:'datefield',
			        	fieldLabel:'部署开始时间',
			        	name:'deployStartDate',
			        	editable: false,
			            format: 'Y-m-d',
			            width: 245,
			            allowBlank : false,
			            canClear : false,
		                minValue: new Date(),
			            value: new Date(),
			            margin :'2 0 0 0'
					},{
			        	xtype:'datefield',
			        	fieldLabel:'部署结束时间',
			        	name:'deployEndDate',
			        	editable: false,
			            format: 'Y-m-d',
			            width: 200,
			            canClear : false,
		                padding: '0 0 0 50',
			            minValue: new Date(),
			            margin :'2 0 0 0'
					}]
				}*/,{
					xtype: 'hidden',
					name: 'deviceIds',
					value:''
			}]
			},{
				region : 'center',
				xtype: 'tabpanel',
				padding: '1px 0px 0px 0px',
				items : [{
						xtype:'version_download_selectableDeviceGrid',
						title: Eway.locale.version.selectableDevice,//'可以下发的设备',
						minHeight:260,
				        flex : 1
					},{
						xtype: 'version_download_linkedDeviceGrid',
						title: Eway.locale.version.linkedDevice,//'已选择的设备',
						minHeight:260,
				        flex : 1
					}
				]
			}]
		});
		this.callParent(arguments);
	},

	onOver : function() {
		this.up('window').close();
	}

});