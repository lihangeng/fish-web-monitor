
Ext.define('Eway.model.parameter.element.Element', {
	extend: 'Ext.data.Model',
	fields: ['id', 'paramName','paramValue','paramType','classifyId','paramClassify','versionNo','paramRights',
	         'paramBelongs','remark','createTime','lastModifyTime'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/element',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});