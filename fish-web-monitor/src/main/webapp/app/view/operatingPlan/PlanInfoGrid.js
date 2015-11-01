
Ext.define('Eway.view.operatingPlan.PlanInfoGrid', {
	alias: 'widget.planInfo_grid',
	extend: 'Eway.view.base.Grid',
	
	requires: ['Eway.lib.Util'],
	
	border : false,
	
	initComponent: function() {
		var store = Ext.create('Eway.store.operatingPlan.OpenPlanDetail');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : '星期',
				flex : 1,
				dataIndex : 'week',
				renderer: function(value,metadata,record){
					if(value=="Mon"){
	                 	 return "星期一";
	                }
					else if(value=="Tues"){
						 return "星期二";
	                }
					else if(value=="Wed"){
						return "星期三";
					}
					else if(value=="Thur"){
						return "星期四";
					}
					else if(value=="Fri"){
						return "星期五";
					}
					else if(value=="Sat"){
						return "星期六";
					}
					else if(value=="Sun"){
						return "星期日";
					}
				}
			},{
				header : '开机/关机',
				flex : 1,
				dataIndex : 'openClose',
				renderer: function(value,metadata,record){
					if(value=="Open"){
	                 	 return "开机";
	                }
					else if(value=="Close"){
						 return "关机";
	                }
				}
			},{
				header : '开始时间',
				flex : 1,
				dataIndex : 'startTime'
			},{
				header : '结束时间',
				flex : 1,
				dataIndex : 'endTime'
			}]
		});
		
		this.callParent(arguments);
	}
});