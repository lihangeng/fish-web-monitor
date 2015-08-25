Ext.define('Eway.view.thread.ThreadWin', {

	extend : 'Ext.window.Window',

	alias : 'widget.thread_ThreadWin',

	width : 650,
	height : 350,
	maximizable : true,
	modal : true,
	border : false,
	title:'系统运维',
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
				title : '日志备份线程信息',
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
						fieldLabel : '每日备份主线程状态',
						name : 'daybackThreadState',
						renderer:function(value,record){
							if(value=='NEW'){
								return '<font color="#45b97c">'+value+'<font>'+'（至今尚未启动的线程的状态。）';
							}else if(value=='RUNNABLE'){
								return '<font color="#1d953f">'+value+'<font>'+'（可运行线程的线程状态。）';
							}else if(value=='BLOCKED'){
								return '<font color="#ae6642">'+value+'<font>'+'（受阻塞并且正在等待监视器锁的某一线程的线程状态。）';
							}else if(value=='WAITING'){
								return '<font color="#f47920">'+value+'<font>'+'（某一等待线程的线程状态。）';
							}else if(value=='TIMED_WAITING'){
								return '<font color="#ffd400">'+value+'<font>'+'（具有指定等待时间的某一等待线程的线程状态。）';
							}else if(value=='TERMINATED'){
								return '<font color="#d71345">'+value+'<font>'+'（已终止线程的线程状态。）';
							}
						}
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : '失败任务备份主线程状态',
						name : 'redoBackTreadState',
						renderer:function(value,record){
							if(value=='NEW'){
								return '<font color="#45b97c">'+value+'<font>'+'（至今尚未启动的线程的状态。）';
							}else if(value=='RUNNABLE'){
								return '<font color="#1d953f">'+value+'<font>'+'（可运行线程的线程状态。）';
							}else if(value=='BLOCKED'){
								return '<font color="#ae6642">'+value+'<font>'+'（受阻塞并且正在等待监视器锁的某一线程的线程状态。）';
							}else if(value=='WAITING'){
								return '<font color="#f47920">'+value+'<font>'+'（某一等待线程的线程状态。）';
							}else if(value=='TIMED_WAITING'){
								return '<font color="#ffd400">'+value+'<font>'+'（具有指定等待时间的某一等待线程的线程状态。）';
							}else if(value=='TERMINATED'){
								return '<font color="#d71345">'+value+'<font>'+'（已终止线程的线程状态。）';
							}
						}
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : '并发执行任务数',
						name : 'activeRuners'
					} ]
				} ]
			}, {
				xtype : 'fieldset',
				title : '下发版本线程信息',
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
						fieldLabel : '版本下发作业状态',
						name : 'jobManagerState',
						renderer:function(value,record){
							if(value=='NEW'){
								return '<font color="#45b97c">'+value+'<font>'+'（至今尚未启动的线程的状态。）';
							}else if(value=='RUNNABLE'){
								return '<font color="#1d953f">'+value+'<font>'+'（可运行线程的线程状态。）';
							}else if(value=='BLOCKED'){
								return '<font color="#ae6642">'+value+'<font>'+'（受阻塞并且正在等待监视器锁的某一线程的线程状态。）';
							}else if(value=='WAITING'){
								return '<font color="#f47920">'+value+'<font>'+'（某一等待线程的线程状态。）';
							}else if(value=='TIMED_WAITING'){
								return '<font color="#ffd400">'+value+'<font>'+'（具有指定等待时间的某一等待线程的线程状态。）';
							}else if(value=='TERMINATED'){
								return '<font color="#d71345">'+value+'<font>'+'（已终止线程的线程状态。）';
							}
						}
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : '版本下发任务执行状态',
						name : 'taskMangerState',
						renderer:function(value,record){
							if(value=='NEW'){
								return '<font color="#45b97c">'+value+'<font>'+'（至今尚未启动的线程的状态。）';
							}else if(value=='RUNNABLE'){
								return '<font color="#1d953f">'+value+'<font>'+'（可运行线程的线程状态。）';
							}else if(value=='BLOCKED'){
								return '<font color="#ae6642">'+value+'<font>'+'（受阻塞并且正在等待监视器锁的某一线程的线程状态。）';
							}else if(value=='WAITING'){
								return '<font color="#f47920">'+value+'<font>'+'（某一等待线程的线程状态。）';
							}else if(value=='TIMED_WAITING'){
								return '<font color="#ffd400">'+value+'<font>'+'（具有指定等待时间的某一等待线程的线程状态。）';
							}else if(value=='TERMINATED'){
								return '<font color="#d71345">'+value+'<font>'+'（已终止线程的线程状态。）';
							}
						}
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : '最大作业队列数',
						name : 'maxJobCount'
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : '队列中作业数',
						name : 'jobQueueCount'
					} ]
				}, {
					defaultType : 'displayfield',
					items : [ {
						fieldLabel : '正在执行任务数',
						name : 'activeTaskCount'
					} ]
				} ]
			} ]
		} ];
		this.callParent(arguments);
	}

});