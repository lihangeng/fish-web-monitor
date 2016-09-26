Ext.define('Eway.view.thread.ThreadWin', {

	extend : 'Ext.window.Window',

	alias : 'widget.thread_ThreadWin',

	width : 650,
	height : 350,
	maximizable : true,
	modal : true,
	border : false,
	title : EwayLocale.thread.title ,
	layout : 'fit',
	constrain : true,
	constrainHeader : true,
	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			autoScroll : true,
			fieldDefaults : {
				labelAlign : 'left',
				labelWidth : 150,
				msgTarget : 'qtip'
			},
			items : [ {
				xtype : 'fieldset',
				title : EwayLocale.thread.backThreadManage,
				collapsible : true,
				defaults : {
					border : false,
					bodyStyle: {
						'background-color':'#dfe8f5'
					}
				},
				items : [ {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : EwayLocale.thread.daybackThreadState,
						name : 'daybackThreadState',
						renderer:function(value,record){
							if(value=='NEW'){
								return '<font color="#45b97c">'+value+'<font>'+'（'+EwayLocale.thread.noFiringThreadState+'）';
							}else if(value=='RUNNABLE'){
								return '<font color="#1d953f">'+value+'<font>'+'（'+EwayLocale.thread.workingThreadState+'）';
							}else if(value=='BLOCKED'){
								return '<font color="#ae6642">'+value+'<font>'+'（'+EwayLocale.thread.blockThreadState+'）';
							}else if(value=='WAITING'){
								return '<font color="#f47920">'+value+'<font>'+'（'+EwayLocale.thread.waitThreadState+'）';
							}else if(value=='TIMED_WAITING'){
								return '<font color="#ffd400">'+value+'<font>'+'（'+EwayLocale.thread.specifyTimeThreadState+'）';
							}else if(value=='TERMINATED'){
								return '<font color="#d71345">'+value+'<font>'+'（'+EwayLocale.thread.endThreadState+'）';
							}
						}
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : EwayLocale.thread.redoBackTreadState,
						name : 'redoBackTreadState',
						renderer:function(value,record){
							if(value=='NEW'){
								return '<font color="#45b97c">'+value+'<font>'+'（'+EwayLocale.thread.noFiringThreadState+'）';
							}else if(value=='RUNNABLE'){
								return '<font color="#1d953f">'+value+'<font>'+'（'+EwayLocale.thread.workingThreadState+'）';
							}else if(value=='BLOCKED'){
								return '<font color="#ae6642">'+value+'<font>'+'（'+EwayLocale.thread.blockThreadState+'）';
							}else if(value=='WAITING'){
								return '<font color="#f47920">'+value+'<font>'+'（'+EwayLocale.thread.waitThreadState+'）';
							}else if(value=='TIMED_WAITING'){
								return '<font color="#ffd400">'+value+'<font>'+'（'+EwayLocale.thread.specifyTimeThreadState+'）';
							}else if(value=='TERMINATED'){
								return '<font color="#d71345">'+value+'<font>'+'（'+EwayLocale.thread.endThreadState+'）';
							}
						}
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : EwayLocale.thread.activeRuners,
						name : 'activeRuners'
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : EwayLocale.thread.grantVersion,
				collapsible : true,
//				height : 120,
				defaults : {
					border : false,
					bodyStyle: {
						'background-color':'#dfe8f5'
					}
				},
				items : [ {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : EwayLocale.thread.jobManagerState,
						name : 'jobManagerState',
						renderer:function(value,record){
							if(value=='NEW'){
								return '<font color="#45b97c">'+value+'<font>'+'（'+EwayLocale.thread.noFiringThreadState+'）';
							}else if(value=='RUNNABLE'){
								return '<font color="#1d953f">'+value+'<font>'+'（'+EwayLocale.thread.workingThreadState+'）';
							}else if(value=='BLOCKED'){
								return '<font color="#ae6642">'+value+'<font>'+'（'+EwayLocale.thread.blockThreadState+'）';
							}else if(value=='WAITING'){
								return '<font color="#f47920">'+value+'<font>'+'（'+EwayLocale.thread.waitThreadState+'）';
							}else if(value=='TIMED_WAITING'){
								return '<font color="#ffd400">'+value+'<font>'+'（'+EwayLocale.thread.specifyTimeThreadState+'）';
							}else if(value=='TERMINATED'){
								return '<font color="#d71345">'+value+'<font>'+'（'+EwayLocale.thread.endThreadState+'）';
							}
						}
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : EwayLocale.thread.taskMangerState,
						name : 'taskMangerState',
						renderer:function(value,record){
							if(value=='NEW'){
								return '<font color="#45b97c">'+value+'<font>'+'（'+EwayLocale.thread.noFiringThreadState+'）';
							}else if(value=='RUNNABLE'){
								return '<font color="#1d953f">'+value+'<font>'+'（'+EwayLocale.thread.workingThreadState+'）';
							}else if(value=='BLOCKED'){
								return '<font color="#ae6642">'+value+'<font>'+'（'+EwayLocale.thread.blockThreadState+'）';
							}else if(value=='WAITING'){
								return '<font color="#f47920">'+value+'<font>'+'（'+EwayLocale.thread.waitThreadState+'）';
							}else if(value=='TIMED_WAITING'){
								return '<font color="#ffd400">'+value+'<font>'+'（'+EwayLocale.thread.specifyTimeThreadState+'）';
							}else if(value=='TERMINATED'){
								return '<font color="#d71345">'+value+'<font>'+'（'+EwayLocale.thread.endThreadState+'）';
							}
						}
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : EwayLocale.thread.maxJobCount,
						name : 'maxJobCount'
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : EwayLocale.thread.jobQueueCount,
						name : 'jobQueueCount'
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : EwayLocale.thread.activeTaskCount,
						name : 'activeTaskCount'
					} ]
				} ]
			} ]
		} ];
		this.callParent(arguments);
	}

});