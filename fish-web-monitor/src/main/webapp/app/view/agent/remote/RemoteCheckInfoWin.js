Ext.define('Eway.view.agent.remote.RemoteCheckInfoWin', {

	extend : 'Ext.window.Window',
	
	alias : 'widget.remote_RemoteCheckInfoWin',
	
	width : 550,
	height : 350,
	//maximizable : true,
	modal : true,
	border : false,
	title:'ATM体检',
	layout : 'border',
	constrain : true,
	constrainHeader : true,
	initComponent : function() {
		var me = this;
		this.items = [{
			region:'west',
	    	width: 50,
	    	border:false,
	    	xtype:'panel'
	    },{
	    	region:'east',
	    	width: 50,
	    	border:false,
	    	xtype:'panel'
	    },{
	    	region:'north',
	    	height: 30,
	    	border:false,
	    	xtype:'panel',
	    	bodyStyle: {
				'background-color':'#dfe8f5'
			},
	    	rbar: [{
	    		iconCls:'checkATM-btn-custom',
	    		xtype: 'button',
	    		action : 'checkATM',
	    		text: '重新体检' 
	    	}],
	    	html:'<br>'
		},{
			region: 'center',
			layout: 'border',
	    	border:false,
	    	xtype:'panel',
	    	items :[{
	    		region:'north',
				height : 50,
				xtype:'panel',
				id : 'checkATMPointhtml'
	    	},{
	    		region: 'center',
	    		xtype : 'form',
				autoScroll : true,
				fieldDefaults : {
					labelAlign : 'left',
					labelWidth : 100,
					msgTarget : 'qtip'
				},
				items : [ {
					xtype : 'fieldset',
					title : 'ATM体检详情',
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
							fieldLabel : 'CPU空闲率',
							name : 'cpuIdle'
						} ]
					}, {
						defaultType : 'displayfield',
						items : [ {
							fieldLabel : '内存空闲率',
							name : 'memoryIdle'
						} ]
					}, {
						defaultType : 'displayfield',
						items : [ {
							fieldLabel : '硬盘空闲率',
							name : 'hardDiskIdle'
						} ]
					} ]
				} ]
	    	}]
		} ];
		this.callParent(arguments);
	}
	
});