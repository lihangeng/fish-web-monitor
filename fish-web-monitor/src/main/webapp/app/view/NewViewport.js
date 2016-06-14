Ext.define('Eway.view.NewViewport', {
	extend : 'Ext.container.Viewport',

	requires : [ 'Eway.view.Header', 'Eway.view.Workspace','Ext.layout.container.Border' ],

	layout : 'border',

	initComponent : function() {
		var store = Ext.create('Ext.data.TreeStore', {
		    proxy: {
		        type: 'ajax',
		        url : 'api/login/mymenu/'+Eway.user.getId(),
		        reader: {
			        type: 'json',
			        rootProperty: 'data'
			    }
		    },
		    fields : [ 'id','code','text','leaf','iconCls'],
		    defaultRootId :'0',
		    autoLoad: true
		});
		Ext.apply(this, {
			items : [/* {
				region : 'north',
				xtype : 'appheader'
			}, *//*{
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
			}*/,{
				region : 'west',
				xtype:'container',
				width:205,
				style : 'background-color:#32404e;',
				layout: {
			        type: 'vbox',
			        align: 'stretch'
			    },
				items:[/*{
					 xtype : 'component',
			         html : EwayLocale.ATMV,
			         height:48,
			         style : 'font-size : 18px; margin:0px;color:#fff;background-color: #89bae6;'
				},*/{
					xtype:'treelist',
					ui:'nav',
					singleExpand:true,
					expanderOnly:false,
					expanderFirst:true,
					store:store
				}]
				}, {
				region : 'center',
				xtype : 'workspace'
			} ]
		});
		this.callParent(arguments);
	}

});
