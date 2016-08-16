Ext.define('Eway.controller.report.baseReport.ETLJob', {
	extend : 'Eway.controller.base.FishController',

//	stores : ['Eway.store.report.baseReport.ETLJob'],
	stores : [],

//	models : ['Eway.model.report.baseReport.ETLJob'],
	models : [],


	views : [ 'report.baseReport.ETLJobView','report.baseReport.ETLCountView'],
//	views : [],

	refs:[ {
		ref: 'ewayView',
		selector: '#trans_JobView',
		autoCreate: true,
		xtype: 'trans_JobView',
		id: 'trans_JobView'
	},{
		ref: 'grid',
		selector: 'trans_JobGrid'
	},{
		ref :'filterForm',
		selector:'trans_JobFilter'
	},{
		ref :'countView',
		selector:'trans_CountView'
	}],

	init : function() {
		this.control({
			'#trans_JobView button[action=query]' : {
				click : this.onQuery
			},
			'#trans_JobView button[action=count]' : {
				click : this.onCount
			}
		});
	},

	onCount:function(){
		var view = Ext.create('Eway.view.report.baseReport.ETLCountView');
		view.down('button[action="confirm"]').on('click',this.onCountConfirm,this);
		view.show();
	},
	onCountConfirm:function(){
		var me = this ;
		var store = this.getGrid().getStore();
		var win=me.getCountView();
		win.close();
		Ext.Ajax.request({
			url : 'api/report/ETLJob/AllOpera',
			method : 'GET',
			success : function(response) {
				var object = Ext.decode(response.responseText);
				if (object.success) {
					Eway.alert(EwayLocale.batch.doAgainSu);
				} else {
					Eway.alert(EwayLocale.batch.doAgainFa);
				}
				store.loadPage(1);
			}
		});
	}

});