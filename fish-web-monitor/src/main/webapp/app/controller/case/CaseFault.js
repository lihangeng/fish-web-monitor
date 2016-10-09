
Ext.define('Eway.controller.case.CaseFault', {
	extend: 'Ext.app.Controller',

	stores: ['case.Fault','case.DevMod','case.FaultStatus','case.FaultClassify'],

	models: ['case.Fault','case.FaultClassify'],

	views: ['case.caseFault.FaultView'],

	refs: [{
		ref: 'ewayView',
		selector: '#fault',
		autoCreate: true,
		xtype: 'case_faultView',
		id: 'fault'
	}, {
		ref: 'grid',
		selector: 'caseFault_faultGrid'
	},{
		ref :'filterForm',
		selector:'caseFault_filterForm'
	}],

	init: function() {
		this.control({
			'caseFault_faultGrid button[action=query]': {
				click: this.onQuery
			},
			'caseFault_faultGrid button[action=export]': {
				click: this.onExport
			},
			'caseFault_faultGrid' : {
				cellclick : this.onCellClik
			}
		});
		this.onQuery();
	},

	/**
	 * 根据条件查询
	 */
	onQuery: function(){
		var store = this.getEwayView().down('caseFault_faultGrid').getStore();
		var data = this.getFilterForm().getForm().getValues();//得到所有的查询条件的值
		store.setUrlParamsByObject(data);
		store.loadPage(1);
	},

	onExport : function(){
		var form = this.getFilterForm().getForm();
		var bool = form.isValid();
		if(bool == false){
			return false;
		}
		var data = form.getValues();
		var params = "";
		for(var key in data){
			if(!Ext.isEmpty(data[key])){
				params += '&'+ key + "=" + data[key];
			}
		}
		var columns = this.getGrid().getColumns();
        var headerName = new Array();
	    var colIndex = new Array();
	    var colWidth = new Array();
	    Ext.Array.forEach(columns,function(item,index,opt){
	    	if(item.cellWidth){
	    		headerName.push(item.text);
				colWidth.push(item.cellWidth);
				if(item.dataIndex=="devMod"){
					colIndex.push("devModName");
				}else if(item.dataIndex=="faultStatus"){
					colIndex.push("faultStatusName");
				}else if(item.dataIndex=="faultCloseType"){
					colIndex.push("closeTypeName");
				}else{
					colIndex.push(item.dataIndex);
				}
	    	}
		 },this);
	    params+="&gridInfoHeaderNames="+headerName+"&gridInfoColIndexs="+colIndex+"&gridInfoColWidths="+colWidth;
		window.location.href = 'api/case/caseFault/export?_dc='+params;

	},
	onCellClik : function (grid, td, cellIndex, record, tr, rowIndex, e, eOpts) {//当单击超链接的时候
		var me = this ;
		if(cellIndex==9){
			var faultStatus = record.get('faultStatus') ;
			if(faultStatus=="OPEN"){
				record.set('faultStatus', 'CLOSED');
				record.save({
					success : function(record, operation) {
						Eway.alert(EwayLocale.updateSuccess);
						me.onQuery() ;
					},
					failure : function(record, operation) {
						Eway.alert(operation.getError());
						record.set('notifyTimes', notifyTimes);
						record.set('notifyWay', notifyWay);
						record.set('resolveHour', resolveHour);
						record.set('responsorType', responsorType);
						record.set('upgrade', upgrade);
					}
				});
			}
		}
	}

});