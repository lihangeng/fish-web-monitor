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
			    		title:Eway.locale.agent.remote.networkInfo,
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
							fieldLabel : Eway.locale.agent.remote.conenctRate,
							name : 'conenctRate'
						}, {
							fieldLabel : Eway.locale.agent.remote.receivedByte,
							name : 'receivedByte'
						} , {
							fieldLabel : Eway.locale.agent.remote.sendByte,
							name : 'sendByte'
						}]
			    	}]
			    }
			    ]
		});
		this.callParent(arguments);
	}
});