Ext.define('Eway.view.agent.remote.MergeDownLoadFileListGrid', {
	alias : 'widget.remote_mergeDownLoadFileGrid',
	extend: 'Eway.view.base.Grid',
  	requires: ['Eway.lib.Util'],

  	border : false,
	autoFit:true,
	initComponent : function() {
	var gridStore = Ext.create('Eway.store.agent.MergeDownLoadFileList');
	/*	var gridStore = Ext.create('Ext.data.JsonStore', {
            fields: ['name', 'type','path','size','lastTime' ],
            data: [
                { name: 'a.txt', type:'',path: 'C:',size:100,lastTime:'2015-1-2' },
                { name: 'b.txt', type:'',path: 'C:',size:100,lastTime:'2015-1-2' },
                { name: 'c.txt', type:'',path: 'C:',size:100,lastTime:'2015-1-2' }
            ]*/
    //    });
		Ext.apply(this, {
	        initRegion : true,
			autoScroll: true,
			store: gridStore,
			viewConfig : {
				loadMask   : {
       			 	msg : EwayLocale.agent.remote.loadData
        		}
			},
			tbar: [{
				text: EwayLocale.agent.remote.removeFile,
				iconCls:'refresh-btn-custom',
				action:'removeFile',
				xtype : 'button'
			},{
				text:EwayLocale.agent.remote.mergeDownLoad,
				iconCls:'up-btn-custom',
				action:'mergeDownLoad',
				xtype : 'button'
			}],			
			columns : [{
				header : EwayLocale.agent.remote.name,
				sortable : true,
				renderer: this.iconBackground,
				width : 260,
				dataIndex : 'name',
				renderer: function (value, meta, record) {
					meta.tdAttr = 'data-qtip="'+ value+ '"';
					return value;
	            }

			}, {
				header : EwayLocale.commen.type,
				dataIndex : 'type',
				hidden: true,
				sortable : true
			},{
				header : EwayLocale.agent.remote.path,
				dataIndex : 'path',
				width : 180,			
				sortable : true
			},{
				header : EwayLocale.agent.remote.size,
				dataIndex : 'size',
				renderer: function(value,metadata,record){
					if(record.data.type=="DIR"){
	                 	   return "";;
	                   }else{
	                   	metadata.tdAttr ='data-qtip="'+EwayLocale.agent.remote.fileSize + value+" B"+'"';
	                   	if(value>1024*1024*1024){
	                   		return (value/(1024*1024*1024)).toFixed(2)+" GB";
	                   	}else if(value>1024*1024){
	                   		return (value/(1024*1024)).toFixed(1)+" MB";
	                   	}else if(value>1024){
	                   		return Math.ceil(value/1024)+" KB";
	                   	}
	                	    return value+" B";
	                   }
				},
				sortable : true
			},{
				header : EwayLocale.agent.remote.lastTime,
				dataIndex : 'lastTime',
				width : 160,
				sortable : true
			}]
		});

		this.callParent(arguments);
	}
});