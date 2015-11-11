Ext.define('Eway.view.operatingPlan.TempOpenPlanForDevGrid',{
	alias:'widget.temp_planofdevice_grid',
    extend:'Eway.view.base.Grid',
    requires:['Eway.lib.Util'],
    border: false,
    initComponent:function(){
    	var store = Ext.create('Eway.store.operatingPlan.TempOpenPlanForDevice');
    	Ext.apply(this,{
    		store : store,
    		initRegion : true,
    		tbar : ['->',{
    			text : Eway.locale.tip.business.device.detail,
    			iconCls : 'detailBtn',
    			action:'tempDevQueryDetail'
    		},{
    			text : Eway.locale.button.link,
    			iconCls : 'connectBtn',
    			action : 'tempDevLink'
    		},{
    			text : Eway.locale.button.unlink,
    			iconCls:'deleteBtn',
    			action :'tempDevUnlink'
    		}],
    		viewConfig : {
    			forceFit : true,
    			stripeRows : true
    		},
    		columns : [{
    			header :Eway.locale.agent.remote.loading,
    			width :100,
    			dataIndex :'name'
    		},{
    			header:Eway.locale.commen.type,
    			width : 100,
    			dataIndex : 'planType',
    			renderer: function( value, metadata, record){
    				if(value == "DATE"){
    					return Eway.locale.commen.dayTime;
    				}else if(value == "WEEK")
    				{
    					return Eway.locale.machine.device.person.week;
    				}
    			}
    		},{
    			header :Eway.locale.report.openplan.state,
    			width:80,
    			dataIndex :'palnStateType',
    		},{
    			header :Eway.locale.report.plan.startDate,
    			width : 100,
    			dataIndex : 'startDate'
    		},{
    			header : Eway.locale.report.plan.endDate,
    			width : 100,
    			dataIndex :'endDate'
    		},{
				header : Eway.locale.version.View.remark,
				dataIndex : 'desc',
				flex : 1
			}, {
				header : Eway.locale.version.View.versionTime,
				dataIndex : 'createDateTime',
				width : 160,
				fiex : 1
			}]
    	});
    	this.callParent(arguments);
    }
});