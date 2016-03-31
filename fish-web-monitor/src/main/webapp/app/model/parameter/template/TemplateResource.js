
Ext.define('Eway.model.parameter.template.TemplateResource', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'templateId' ,'paramName','paramValue','paramBelongs'],
	         
    proxy: {
        type: 'rest',
        url : 'api/parameter/template/resource',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        wirter : {
        	type : 'json'
        }
    },
    
    
    convertToTempResource: function(){
    	var me = this;
    	
		return Ext.create("Eway.model.parameter.template.TemplateResource",{
				id:Ext.isNumeric(me.data.id)?me.data.id:0,
				templateId: me.data.templateId,
				paramName:me.data.paramName,
				paramValue:me.data.paramValue,
				paramBelongs : me.paramBelongs
			});
    }
    
    
    
    belongsTo : 'TemplateDetail'
});