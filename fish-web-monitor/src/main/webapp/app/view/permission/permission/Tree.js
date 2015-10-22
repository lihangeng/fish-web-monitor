
Ext.define('Eway.view.permission.permission.Tree', {
	alias: 'widget.permission.tree',
	extend: 'Ext.tree.Panel',
	
	store: 'permission.PermissionTree',
	
	id:'permissionTreeId',
	
	useArrows: true,
	collapseFirst : true,
    columns: [{
        xtype: 'treecolumn', //this is so we know which column will show the tree
        text: Eway.locale.permission.permission.menuName,
        flex: 2,
        sortable: true,
        dataIndex: 'text'
    },{
        text: Eway.locale.permission.permission.menuDescription,
        flex: 2,
        sortable: true,
        dataIndex: 'text'
   }],

	
	initComponent: function() {		
/*        Ext.apply(this, {
        	checkModel: 'cascade' ,
        	rootVisible : false,
        	lines : true,
        	 useArrows: true,
        	 frame: true,
        	 title: 'Check Tree',
        	 autoScroll:true,
        	 dockedItems : [{
        		 xtype:'toolbar',
        		 items:[{
        			 text:'Get checked nodes',
        			 handler : function(){
        				 var records = this.getView().getChecked();
        				 names=[];
        				 
        				 Ext.Array.each(records,function(rec){
        					 names.push(rec.get('text'));
        				 });
        				 Ext.MessageBox.show({
        					 title:'Selected Nodes',
        					 msg: names.join('<br />'),
                             icon: Ext.MessageBox.INFO
        				 });
        			 },
        			 scope:this
        		 }]
        	 }]
		});*/
		
		this.callParent(arguments);
	}

});