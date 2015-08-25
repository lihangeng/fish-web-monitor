
Ext.define('Eway.model.agent.RemoteBrowseDisk', {
	extend: 'Ext.data.Model',
	fields: ['id', 'name', 'fileSys','totalSize', 'freeSize','path'],
    proxy: {
        type: 'rest',
        url : 'api/agent/remoteBrowse',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});