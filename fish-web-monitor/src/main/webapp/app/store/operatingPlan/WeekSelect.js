
Ext.define('Eway.store.operatingPlan.WeekSelect',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Mon', 'display':EwayLocale.machine.serviceplan.Mon},
		{'value':'Tues', 'display':EwayLocale.machine.serviceplan.Tues},
		{'value':'Wed', 'display':EwayLocale.machine.serviceplan.Wed},
		{'value':'Thur', 'display':EwayLocale.machine.serviceplan.Thur},
		{'value':'Fri', 'display':EwayLocale.machine.serviceplan.Fri},
		{'value':'Sat', 'display':EwayLocale.machine.serviceplan.Sat},
		{'value':'Sun', 'display':EwayLocale.machine.serviceplan.Sun}
	]

});