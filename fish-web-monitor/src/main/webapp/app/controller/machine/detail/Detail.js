Ext.define('Eway.controller.machine.detail.Detail', {
	extend : 'Eway.controller.base.FishController',
	views : [ 'machine.detail.View' ],
	refs : [ {
		ref : 'ewayView',
		selector : '#detail',
		autoCreate : true,
		xtype : 'detail.view',
		id : 'deviceDetail'
	}],


	init : function() {
		this.control({
			'#deviceDetail displayfield[name=deviceBasicInfo]' : {
				click : this.onBasicInfo
			},
			'#deviceDetail displayfield[name=deviceRunInfo]' : {
				click : this.onRunInfo
			}
		});
	},
	onBasicInfo:function(){
		var view = this.getEwayView();
		view.getLayout().setActiveItem("detail_basicInfo");
	},
	onRunInfo:function(){
		var view = this.getEwayView();
		view.getLayout().setActiveItem("detail_runInfo");
		
	}
});
