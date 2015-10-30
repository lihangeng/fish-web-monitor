
Ext.define('Eway.store.operatingPlan.WeekSelect',{

	extend: 'Ext.data.Store',
	
	fields : ['value','display'],
	data : [
		{'value':'Mon', 'display':'一'},
		{'value':'Tues', 'display':'二'},
		{'value':'Wed', 'display':'三'},
		{'value':'Thur', 'display':'四'},
		{'value':'Fri', 'display':'五'},
		{'value':'Sat', 'display':'六'},
		{'value':'Sun', 'display':'日'}
	]

});