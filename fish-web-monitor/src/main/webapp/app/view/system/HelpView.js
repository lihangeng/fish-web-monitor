Ext.define('Eway.view.system.HelpView',{

	extend : 'Eway.view.base.Panel',
	alias : 'widget.system_helpView',

	initComponent : function(){

		var store = new Ext.create('Ext.data.Store', {
	    	fields:['name', 'describe'],
		    data:{'items':[
		        {"name":"The user manual.doc", "describe":Eway.locale.system.guideUsers}
		    ]},
		    proxy: {
		        type: 'memory',
		        reader: {
		            type: 'json',
		            rootProperty : 'items'
		    	}
   			 }
		});

		Ext.apply(this,{
			layout : 'fit',
			title : Eway.locale.system.systemHelp,
			items : [{
				xtype : 'grid',
			    store: store,
			    columns: [
			        {header: Eway.locale.system.helpName,  dataIndex: 'name',width:150},
			        {header: Eway.locale.system.helpExpain, dataIndex: 'describe',width:400},
			        {
			        	header: Eway.locale.system.helpDownload,
						xtype:'actioncolumn',
						flex : 1,
						renderer: function(value,metadata,record){
			                   	metadata.tdAttr ='data-qtip="'+Eway.locale.system.clickDownload+'"';
						},
						items:[{
		                    icon:"./././resources/images/downfile.png",
		                    handler:function(grid,rowIndex,colIndex){
		                       var rec=grid.getStore().getAt(rowIndex);
		                       var requestName = rec.get('name');
		                       requestName = requestName.replace("&","%26");//将文件名含有&符号的用URL编码“%26”替换
		                       window.location.href = 'api/system/downloadFile?fileName=' + requestName ;
		                    }
		              	}]
			        }
			    ]
			}],
			listeners : {
				/*'render' : this.fillForm,
				scope : this*/
			}
		});
		this.callParent(arguments);
	}
})

