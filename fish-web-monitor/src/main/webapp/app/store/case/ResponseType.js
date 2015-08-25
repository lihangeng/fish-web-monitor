
Ext.define('Eway.store.case.ResponseType',{

	extend: 'Ext.data.Store',
	
	fields : ['value','name'],
	data : [
		{'value':'1', 'name':'管机员'},
		{'value':'2', 'name':'维护员'},
		{'value':'3', 'name':'管机员与维护员'}
	]

});