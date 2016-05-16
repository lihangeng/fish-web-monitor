Ext.define('Eway.controller.parameter.classify.Classify', {
	extend : 'Eway.controller.base.FishController',

	stores : ['parameter.classify.Classify'],
	models : ['parameter.classify.Classify'],

	views : ['parameter.classify.View','Eway.view.parameter.classify.Form'],

	refs : [{
		ref : 'ewayView',
		selector : 'classify_View',
		autoCreate : true,
		xtype : 'classify_View'
	},{
		ref : 'classifyFilterForm',
		selector : 'classify_FilterForm'
	},{
		ref : 'classifyGrid',
		selector : 'classify_Grid'
	}],

	formConfig : {
		form : 'Eway.view.parameter.classify.Form',
		xtype : 'parameter_classify_form',
		width: 500,
		height: 280,
		title : EwayLocale.machine.param.paramClassify
	},

	init : function() {
		this.initBaseControl();
		this.control({
			'classify_View button[action=query]' : {
					click : this.onQuery
			},
			'classify_View button[action=add]' : {
					click : this.onAdd,
					scope : this
			},
			'classify_View button[action=update]' : {
					click : this.onUpdateWin
			},
			'classify_View button[action=remove]' : {
					click : this.onRemoveWin
			}
		});
	},
	
	onUpdateWin:function(){
		var grid=this.getClassifyGrid();
		var sm=grid.getSelectionModel();
		if(sm.getCount()==1){
			var record=sm.getLastSelected();
			if(record.get('id')==1){
				Eway.alert(EwayLocale.param.classify.notUpdate);
			}else{
				this.onUpdate();
			}
		}
	},
	onRemoveWin:function(){
		var grid=this.getClassifyGrid();
		var sm=grid.getSelectionModel();
		if(sm.getCount()==1){
			var record=sm.getLastSelected();
			if(record.get('id')==1){
				Eway.alert(EwayLocale.param.classify.notRemove);
			}else{
				this.onRemove();
			}
		}
	}
	
	
});