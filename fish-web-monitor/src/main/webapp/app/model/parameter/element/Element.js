
Ext.define('Eway.model.parameter.element.Element', {
	extend: 'Ext.data.Model',
	fields: ['id', 'paramName','paramValue','paramType','classifyId','paramClassify','paramRights',
	         'paramBelongsId','paramBelongsName','remark','createTime','lastModifyTime','paramTimestamp'],
    proxy: {
        type: 'rest',
        url : 'api/parameter/element',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});