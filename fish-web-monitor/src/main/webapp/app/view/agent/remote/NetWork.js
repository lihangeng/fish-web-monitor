Ext.define("Eway.view.agent.remote.NetWork", {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.remote.NetWork',

	id : 'netWorkId',

	bodyStyle : 'padding: 10px 10px 30px 10px',



	defaults : {
		border : false
	},
	
	initComponent : function() {
		Ext.apply(this, {

			 bodyStyle:'padding:10px 10px 10px 10px',
			    defaults: {
			        anchor: '100%'
			    },
			    items:[{
			    	layout:'column',
			    	border :'false',
			    	items:[{
			    		title:'网络连接信息',
			    		columnWidth:.5,
		                border:false,
		                layout: 'anchor',
		                defaultType: 'textfield',
		                defaults:{
		                	anchor:'90%',
		                	readOnly:true,
		                	style : 'padding-top:10px'
		                },
		                items:[{
							fieldLabel : '连接速率',
							name : 'conenctRate'
						}, {
							fieldLabel : '接收到的字节数',
							name : 'receivedByte'
						} , {
							fieldLabel : '发动的字节数',
							name : 'sendByte'
						}]
			    	}]
			    }
			    ]
		});
		this.callParent(arguments);
	}
});