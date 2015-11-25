Ext.define('Eway.view.Viewport', {
	extend : 'Ext.container.Viewport',

	requires : [ 'Eway.view.Header', 'Eway.view.Workspace','Ext.layout.container.Border' ],

	layout : 'border',

	initComponent : function() {
		var store = Ext.create('Ext.data.TreeStore', {
		    proxy: {
		        type: 'ajax',
		        url : 'api/login/mymenu/'+ewayUser.getId(),
		        reader: {
			        type: 'json',
			        rootProperty: 'data'
			    }
		    },
		    autoLoad: true
		});
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'appheader'
			}, {
				region : 'west',
				width : 205,
				title : EwayLocale.myTable,
				id:'appmenu',
				header : true,
				bodyStyle: 'opacity:0.8',
				xtype : 'treepanel',
				border : true,
				rootVisible : false,
				lines : false,
				bufferedRenderer : false,
				store : store,
				collapsible : true,
				collapseMode : 'header',
				split : false
			}, {
				region : 'center',
				xtype : 'workspace'
			} ]
		});
		this.callParent(arguments);
	}

});
