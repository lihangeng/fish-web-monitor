
Ext.define('Eway.model.report.plan.PlanDevice', {
	extend: 'Ext.data.Model',
	fields: ['id','planId', 'deviceId'],
    proxy: {
        type: 'rest',
        url : 'api/report/plan/link',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});
