Ext.define('Eway.model.permission.Permission', {
	extend : 'Ext.data.Model',
	idProperty : 'id',
	fields : [ 'id', 'text', 'desc','leaf','expanded']
	
//	belongsTo: 'Role'

});
