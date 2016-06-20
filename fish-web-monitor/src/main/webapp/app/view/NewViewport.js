Ext.define('Eway.view.NewViewport', {
	extend : 'Ext.container.Viewport',

	requires : [ 'Eway.view.Header', 'Eway.view.Workspace','Eway.view.MainContainerWrap' ],

	layout: {
        type: 'vbox',
        align: 'stretch'
	},
	
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
			items:[{
				xtype:'appheader',
				height:36
			},{
				xtype: 'maincontainerwrap',
				flex: 1,
				items:[{
						xtype:'treelist',
						ui:'nav',
						id:'appmenu',
						width:205,
						singleExpand:true,
						expanderOnly:false,
						expanderFirst:true,
						store:store
					},{
						xtype : 'workspace',
						flex: 1
					}]
			}]
		});
		this.callParent(arguments);
	}

});
