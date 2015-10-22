
Ext.define('Eway.view.version.download.Add', {
	extend : 'Ext.window.Window',
	alias : 'widget.version_download_add',
	requires : ['Eway.view.version.field.JobType',
	            'Eway.view.version.download.SelectableDeviceGrid'],

	title : Eway.locale.version.addJobTitle,//'配置作业信息',
	modal : true,
	resizable : true,
	constrainHeader : true,
    maximizable: true,
    minWidth: 600,
    minHeight: 400,
	width : 900,
	height : 630,
	autoScroll : true,
	layout: 'fit',
	
	initComponent : function() {
		Ext.apply(this, {
			dockedItems : [{ 
				xtype: 'toolbar',
				dock: 'bottom',
				ui: 'footer',
				layout: {
				    pack: 'center'
				},  
				defaults:{
					margins:'0 5 0 0',
					xtype:'button',
					height: 20
				},
				items :[{
					text : Eway.locale.button.save,//'保存',
					action : 'confirm',
//					scale: 'medium',
					width:70
				},/*"->",*/ {
					text : Eway.locale.button.reset,//'重置',
					handler : this.onReset
				}, {
					text : Eway.locale.button.back,//'返回',
					handler : this.onOver
				}]
			}],
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 5px 5px 5px 5px',
//				trackResetOnLoad : true,
				layout: {
		            type: 'vbox',
		            align: 'stretch'  // Child items are stretched to full width
		        },
		        fieldDefaults : {
					labelWidth : 80,
					labelAlign : 'right',
					msgTarget : 'qtip',
					grow : false,
					border : false
				},
				items : [{
					xtype: 'container',
                    layout: 'hbox',
                    items: [{
//    					xtype:'field_versionComboBox'
    					xtype: 'combobox',
    					fieldLabel : Eway.locale.version.downloadVersionId,//'下发版本',
    					name: 'versionId',
    					editable  : false,
    					store: Ext.create('Ext.data.Store',{
    						fields: ['id', 'displayName'],
    						proxy: {
    					        type: 'rest',
    					        url : 'api/version/version/combo',
    					        reader: {
    					            type: 'json',
    					            rootProperty : 'data'
    					        }
    					    },
    					    autoLoad : false						
    					}),
    					emptyText : Eway.locale.version.View.downloadVersionNameEmpty,//'请选择下发的版本',
    					mode : 'remote',
    					triggerAction: 'all',
    					valueField : 'id',
    					displayField : 'displayName',
    					listConfig: {
    					        getInnerTpl: function() {
    					            return '<div data-qtip="{displayName}">{displayName}</div>';
    					        }
    					},
    					pageSize: 20,
    					width: 400,
    					allowBank: false
    				},{
                    	xtype: 'combobox',
                    	fieldLabel: Eway.locale.advert.jobPriority,//'作业优先级',
		                store: Ext.StoreMgr.lookup("version.JobPriority"),
		                queryMode: 'local',
		                valueField : 'value',
		                displayField: 'display',
		                value:'GENERAL',
		                name:'jobPriority',
		                editable : false,
		                width : 300
		            }]
				},{
					xtype: 'container',
                    layout: 'hbox',
                    items: [{
                    	xtype: 'combobox',
                    	fieldLabel: Eway.locale.advert.jobType,//'作业类型',
		                store: Ext.StoreMgr.lookup("version.JobType"),
		                queryMode: 'local',
		                valueField : 'value',
		                displayField: 'display',
		                value:'MANUAL',
		                name:'jobType',
		                editable : false,
		                width : 400
		            }, {
			        	xtype:'datefield',
			        	fieldLabel:Eway.locale.version.task.actionTime,//'执行时间',
			        	name:'planTime',
			        	disabled: true,
			        	editable: true,
			            format: 'Y-m-d H:i:s',
			            width: 300
					}]
				},{
					xtype: 'textarea',
					fieldLabel:Eway.locale.version.View.remark,
					name :'desc',
					height : 40,
					maxWidth:700
			 },{
					xtype: 'hidden',
					name: 'deviceIds'
			},{
				xtype: 'tabpanel',
		        activeTab: 0,
		        minHeight:440,
		        height:440,
		        flex : 1,
				items :[{
					xtype:'version_download_selectableDeviceGrid',
					title: Eway.locale.version.task.selectDownloadDevice//'选择下发的设备'
				}]
			}]
		  }//end with items
		});
		this.callParent(arguments);
	},

	onReset : function() {
		this.up('window').down('form').getForm().reset();
	},

	onOver : function() {
		this.up('window').close();
	}

});