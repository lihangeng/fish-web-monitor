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
    			text : EwayLocale.tip.business.device.detail,
    			iconCls : 'detailBtn',
    			action:'tempDevQueryDetail'
    		},{
    			text : EwayLocale.button.link,
    			iconCls : 'connectBtn',
    			action : 'tempDevLink'
    		},{
    			text : EwayLocale.button.unlink,
    			iconCls:'deleteBtn',
    			action :'tempDevUnlink'
    		}],
    		viewConfig : {
    			forceFit : true,
    			stripeRows : true
    		},
    		columns : [{
    			header :EwayLocale.agent.remote.loading,
    			width :100,
    			dataIndex :'name'
    		},{
    			header:EwayLocale.commen.type,
    			width : 100,
    			dataIndex : 'planType',
    			renderer: function( value, metadata, record){
    				if(value == "DATE"){
    					return EwayLocale.commen.dayTime;
    				}else if(value == "WEEK")
    				{
    					return EwayLocale.machine.device.person.week;
    				}
    			}
    		},{
    			header :EwayLocale.machine.serviceplan.state,
    			width:80,
    			dataIndex :'palnStateType'
    		},{
    			header :EwayLocale.machine.plan.startDate,
    			width : 100,
    			dataIndex : 'startDate'
    		},{
    			header : EwayLocale.machine.plan.endDate,
    			width : 100,
    			dataIndex :'endDate'
    		},{
				header : EwayLocale.version.View.remark,
				dataIndex : 'desc',
				flex : 1
			}, {
				header : EwayLocale.version.View.versionTime,
				dataIndex : 'createDateTime',
				width : 160,
				fiex : 1
			}]
    	});
    	this.callParent(arguments);
    }
});