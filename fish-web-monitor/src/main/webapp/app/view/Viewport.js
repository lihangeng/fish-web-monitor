Ext.define('Eway.view.Viewport', {
	extend : 'Ext.container.Viewport',

	requires : [ 'Eway.view.Header', 'Eway.view.Workspace','Ext.layout.container.Border' ],

	layout : 'border',
	config:{
		treepanelStore:'',
		otherStore:''
	},
	initComponent : function() {
		var me = this;
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
				store : me.getTreepanelStore(),
				collapsible : true,
				collapseMode : 'header',
				split : false,
				singleExpand:true
			}, {
				region : 'center',
				xtype : 'workspace'
			} ]
		});
		this.callParent(arguments);
	}

});
