Ext.define('Eway.store.monitor.device.FilterIngItem',{

	extend : 'Ext.data.Store',
	model : 'Eway.model.monitor.device.FilterItem',
	
	data : [{
			name : '-全部-',
			value : '0'
		},{
			name : '在行自助银行',
			value : '1'
		},{
			name : '离行自助银行',
			value : '2'
		},{
			name : '单机离行自助服务',
			value : '3'
		}]
});