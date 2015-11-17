
Ext.define('Eway.store.operatingPlan.WeekSelect',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Mon', 'display':EwayLocale.report.openplan.Mon},
		{'value':'Tues', 'display':EwayLocale.report.openplan.Tues},
		{'value':'Wed', 'display':EwayLocale.report.openplan.Wed},
		{'value':'Thur', 'display':EwayLocale.report.openplan.Thur},
		{'value':'Fri', 'display':EwayLocale.report.openplan.Fri},
		{'value':'Sat', 'display':EwayLocale.report.openplan.Sat},
		{'value':'Sun', 'display':EwayLocale.report.openplan.Sun}
	]

});