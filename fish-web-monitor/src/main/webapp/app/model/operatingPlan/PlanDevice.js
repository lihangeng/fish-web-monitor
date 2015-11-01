
Ext.define('Eway.model.operatingPlan.PlanDevice', {
	extend: 'Ext.data.Model',
	fields: ['id','planId', 'deviceId'],
    proxy: {
        type: 'rest',
        timeout : 60000,
        url : 'api/plan/link',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});
