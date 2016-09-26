Ext.define('Eway.view.system.quartz.Form',{

	extend : 'Eway.view.base.Form',
	alias : 'widget.system_quartz_form',

	requires: ['Eway.view.field.quartz.JobCron','Eway.view.field.quartz.JobName','Eway.view.field.quartz.JobGroup'],
	defaults: {
					labelWidth: 120,
					labelAlign: 'right',
					msgTarget : 'side',
					width: 400
				},
	initComponent : function(){
		Ext.apply(this,{
			defaults: {
				anchor : '90%',
				labelWidth: 120,
				labelAlign: 'right',
				msgTarget : 'side'
			},
			items: [{
					fieldLabel: EwayLocale.quartz.jobName,
					xtype : 'quartz_jobName',
					maxLength : 128,
					allowBlank: false,
					editable:false
			},{
					fieldLabel: '<font color="red">*</font>'+ EwayLocale.quartz.cronExpression,
					xtype : 'quartz_jobCron',
					maxLength : 128,
					allowBlank: false
			}/*,{
				fieldLabel: '<font color="red">*</font>'+ 'job group',
				xtype : 'quartz_jobGroup',
				maxLength : 30,
				allowBlank: false
		}*/]
		});
		this.callParent(arguments);
	}

});