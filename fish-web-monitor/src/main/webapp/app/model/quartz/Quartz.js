
Ext.define('Eway.model.quartz.Quartz', {
	extend: 'Ext.data.Model',
	fields: ['jobName','jobGroup','triggerName','triggerGroup','jobClassName','jobDescription','cronExpression','triggerState',
	         'nextFireTime','prevFireTime','startTime','endTime'],
    proxy: {
        type: 'rest',
        url : 'api/system/quartz',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});