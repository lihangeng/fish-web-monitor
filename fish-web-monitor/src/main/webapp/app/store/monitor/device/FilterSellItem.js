Ext.define('Eway.store.monitor.device.FilterSellItem',{

	extend : 'Ext.data.Store',
	model : 'Eway.model.monitor.device.FilterItem',
	
	data : [{
			name : '-全部-',
			value : '0'
		},{
			name : '自营',
			value : '1'
		},{
			name : '合作',
			value : '2'
		},{
			name : '外包',
			value : '3'
		}]
});