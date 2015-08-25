
Ext.define('Eway.model.agent.RemoteBrowseFileSystem', {
	extend: 'Ext.data.Model',
	fields: ['diskName', 'path','name', 'type','createTime','lastTime','size'],
    proxy: {
        type: 'rest',
        url : 'api/agent/remoteBrowse/fileSystem',
        extraParams: {start:0,limit:200},
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});