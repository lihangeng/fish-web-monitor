
Ext.define('Eway.store.operatingPlan.WeekSelect',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Mon', 'display':Eway.locale.report.openplan.Mon},
		{'value':'Tues', 'display':Eway.locale.report.openplan.Tues},
		{'value':'Wed', 'display':Eway.locale.report.openplan.Wed},
		{'value':'Thur', 'display':Eway.locale.report.openplan.Thur},
		{'value':'Fri', 'display':Eway.locale.report.openplan.Fri},
		{'value':'Sat', 'display':Eway.locale.report.openplan.Sat},
		{'value':'Sun', 'display':Eway.locale.report.openplan.Sun}
	]

});