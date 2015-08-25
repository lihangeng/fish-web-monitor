
Ext.define('Eway.model.SoftAndHardwareInfo', {
	extend: 'Ext.data.Model',
	fields: ['memory', 
	         'system', 
	         'systemPatchVersion',
	         'hardDisk',
	         'systemVersion',
	         'cpu'
	         ],
	         proxy: {
	     		//idProperty : 'id',
	             type: 'ajax',
//	             url : 'api/machine/devicedetail',
	             url : 'resources/data/machine/softAndHardwareInfo.json',
	             reader: {
	                 type: 'json',
	                 rootProperty: 'data'
	             }

	         }
   
});