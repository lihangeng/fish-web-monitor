
Ext.define('Eway.store.case.NotifyType',{

	extend: 'Ext.data.Store',
	
	fields : ['value','name'],
	data : [
		{'value':'1', 'name':'创建通知'},
		{'value':'2', 'name':'升级通知'},
		{'value':'3', 'name':'关闭通知'}
	]

});