Ext.define('Eway.view.agent.remote.RemoteCheckInfoWin', {

	extend : 'Ext.window.Window',
	
	alias : 'widget.remote_RemoteCheckInfoWin',
	
	width : 550,
	height : 350,
	//maximizable : true,
	modal : true,
	border : false,
	title:EwayLocale.agent.remote.ATMExamination,
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
	    		text: EwayLocale.agent.remote.checkATM 
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
					title : EwayLocale.agent.remote.ATMExamInfo,
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
							fieldLabel : EwayLocale.agent.remote.cpuIdle,
							name : 'cpuIdle'
						} ]
					}, {
						defaultType : 'displayfield',
						items : [ {
							fieldLabel : EwayLocale.agent.remote.memoryIdle,
							name : 'memoryIdle'
						} ]
					}, {
						defaultType : 'displayfield',
						items : [ {
							fieldLabel : EwayLocale.agent.remote.hardDiskIdle,
							name : 'hardDiskIdle'
						} ]
					} ]
				} ]
	    	}]
		} ];
		this.callParent(arguments);
	}
	
});