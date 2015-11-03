
Ext.define('Eway.view.machine.device.person.DevicePlanInfoGrid', {
	alias: 'widget.device_planInfo_grid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.operatingPlan.OpenPlanDetailForDevice');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			autoScroll: true,
			height: 200,
			columns : [{
				header : Eway.locale.machine.device.person.week,
				flex : 1,
				dataIndex : 'week',
				renderer: function(value,metadata,record){
					if(value=="Mon"){
	                 	 return Eway.locale.machine.device.person.Mon;
	                }
					else if(value=="Tues"){
						 return Eway.locale.machine.device.person.Tues;
	                }
					else if(value=="Wed"){
						return Eway.locale.machine.device.person.Wed;
					}
					else if(value=="Thur"){
						return Eway.locale.machine.device.person.Thur;
					}
					else if(value=="Fri"){
						return Eway.locale.machine.device.person.Fri;
					}
					else if(value=="Sat"){
						return Eway.locale.machine.device.person.Sat;
					}
					else if(value=="Sun"){
						return Eway.locale.machine.device.person.Sun;
					}
				}
			},{
				header : Eway.locale.machine.device.person.openClose,
				flex : 1,
				dataIndex : 'openClose',
				renderer: function(value,metadata,record){
					if(value=="Open"){
	                 	 return Eway.locale.machine.device.person.Open;
	                }
					else if(value=="Close"){
						 return Eway.locale.machine.device.person.Close;
	                }
				}
			},{
				header :Eway.locale.commen.startDataTime,
				flex : 1,
				dataIndex : 'startTime'
			},{
				header : Eway.locale.commen.endDataTime,
				flex : 1,
				dataIndex : 'endTime'
			}]
		});
		
		this.callParent(arguments);
	}
});