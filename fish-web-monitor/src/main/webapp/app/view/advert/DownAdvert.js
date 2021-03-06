
Ext.define('Eway.view.advert.DownAdvert', {
	extend : 'Ext.window.Window',
	alias : 'widget.advert_downAdvert',
	requires : ['Eway.view.version.field.JobType','Ext.ux.form.DateTimeField',
	            'Eway.view.version.download.MultiSelectableDeviceGrid',
	            'Eway.view.version.download.LinkedDeviceGrid'],

	title : EwayLocale.advert.downloadAdvertConfig,//'下发广告配置',
	modal : true,
	resizable : true,
	constrainHeader : true,
    maximizable: true,
    minWidth: 1100,
    minHeight: 400,
	width: 1100,
    height: 500,
	layout: 'border',

	initComponent : function() {
		Ext.apply(this,{
			items : [ {
				region : 'south',
				buttonAlign:'center',
				buttons :[{
					text : EwayLocale.button.save,//'保存',
					action : 'confirm',
					iconCls:'db-save'
				}]
			},{
				region : 'north',
				xtype:'form',
				bodyStyle : 'padding: 5px 0px 0px 5px',
			    height: 110,
				trackResetOnLoad : true,
				layout: {
		            type: 'vbox',
		            align: 'stretch'  // Child items are stretched to full width
		        },
		        fieldDefaults : {
					labelWidth : 80,
					labelAlign : 'right',
					msgTarget : 'side',
					grow : false,
					border : false
				},
				items : [{
					xtype: 'textfield',
					fieldLabel:EwayLocale.version.View.remark,
					readOnly:true,
					name :'jobName',
					height : 40,
					maxWidth:600,
					maxLength: 40
				},{
					xtype: 'container',
                    layout: 'hbox',
                    items: [{
    					xtype: 'hidden',
    					fieldLabel : EwayLocale.version.downloadVersionId,//'下发版本ID',
    					name: 'versionId'
    				},{
    					xtype: 'displayfield',
    					fieldLabel : EwayLocale.advert.versionType,//'软件分类',
    					name: 'versionType',
    					width : 200
    				},{
    					xtype: 'displayfield',
    					fieldLabel : EwayLocale.version.View.versionNo,//'版本号',
    					name: 'versionNo',
    					width : 260
    				},{
    					xtype: 'displayfield',
    					fieldLabel : EwayLocale.advert.advertVersionFile,//'版本文件',
    					name: 'serverPath'
    				}]
				},{

//					{jobName: "2次", versionId: "2", taskType: "MANUAL", deviceIds: ",1"}
//					{versionId: "4", jobPriority: "GENERAL", jobType: "MANUAL", desc: "", deviceIds: ",1"}
					xtype: 'container',
                    layout: 'hbox',
                    items: [{
//                    	xtype: 'combobox',
//                    	fieldLabel: EwayLocale.advert.jobPriority,//'作业优先级',
//		                store: Ext.StoreMgr.lookup("version.JobPriority"),
//		                queryMode: 'local',
//		                valueField : 'value',
//		                displayField: 'display',
//		                value:'GENERAL',
//		                name:'jobPriority',
//		                editable : false,
//		                width : 200
//		            },{
                    	xtype: 'combobox',
                    	fieldLabel: EwayLocale.advert.jobType,//'作业类型',
		                store: Ext.StoreMgr.lookup("version.JobType"),
		                queryMode: 'local',
		                valueField : 'value',
		                displayField: 'display',
		                value:'MANUAL',
		                name:'jobType',
		                editable : false,
		                width : 245
		            }, {
			        	xtype:'datetimefield',
			        	fieldLabel:EwayLocale.version.planTime,//'计划执行时间',
			        	name:'planTime',
			        	disabled: true,
			        	editable: false,
			            format: 'Y-m-d H:i:s',
			            padding: '0 0 0 30',
			            labelWidth:130,
		                width : 325,
			            minValue: new Date(),
			            maxValue:Ext.Date.add(Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d') + " 23:59:59","Y-m-d H:i:s"), Ext.Date.DAY, 7)
					},{
						 xtype: 'radiogroup',
						 fieldLabel: EwayLocale.version.download.selectAllDevice,
				            labelWidth:100,
					        // Arrange radio buttons into two columns, distributed vertically
					        columns: 2,
					        vertical: true,
					        items: [
					            { boxLabel: EwayLocale.commen.yes, name: 'selectAll', inputValue: 'true' },
					            { boxLabel: EwayLocale.commen.no, name: 'selectAll', inputValue: 'false', checked: true}
					        ]
					}]
				},{
					xtype: 'hidden',
					name: 'deviceIds',
					value:''
			}]
			},{
				region : 'center',
				xtype: 'tabpanel',
				padding: '1px 0px 0px 0px',
				items : [{
						xtype:'version_download_multiselectableDeviceGrid',
						title: EwayLocale.version.selectableDevice,//'可以下发的设备',
						minHeight:260,
				        flex : 1
					},{
						xtype: 'version_download_linkedDeviceGrid',
						title: EwayLocale.version.linkedDevice,//'已选择的设备',
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