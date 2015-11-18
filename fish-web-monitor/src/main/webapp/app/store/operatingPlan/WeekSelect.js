
Ext.define('Eway.store.operatingPlan.WeekSelect',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Mon', 'display':EwayLocale.report.serviceplan.Mon},
		{'value':'Tues', 'display':EwayLocale.report.serviceplan.Tues},
		{'value':'Wed', 'display':EwayLocale.report.serviceplan.Wed},
		{'value':'Thur', 'display':EwayLocale.report.serviceplan.Thur},
		{'value':'Fri', 'display':EwayLocale.report.serviceplan.Fri},
		{'value':'Sat', 'display':EwayLocale.report.serviceplan.Sat},
		{'value':'Sun', 'display':EwayLocale.report.serviceplan.Sun}
	]

});