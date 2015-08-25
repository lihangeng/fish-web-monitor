Ext.define('Eway.view.system.HelpView',{

	extend : 'Eway.view.base.Panel',
	alias : 'widget.system_helpView',

	initComponent : function(){

		var store = new Ext.create('Ext.data.Store', {
	    	fields:['name', 'describe'],
		    data:{'items':[
		        {"name":"The user manual.doc", "describe":"本手册指导用户操作本系统，更快的掌握系统的各项功能。"}
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
			title : '系统帮助',
			items : [{
				xtype : 'grid',
			    store: store,
			    columns: [
			        {header: '名称',  dataIndex: 'name',width:150},
			        {header: '说明', dataIndex: 'describe',width:400},
			        {
			        	header: '下载',
						xtype:'actioncolumn',
						flex : 1,
						renderer: function(value,metadata,record){
			                   	metadata.tdAttr ='data-qtip="'+"单击此处即可下载该文档"+'"';
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

