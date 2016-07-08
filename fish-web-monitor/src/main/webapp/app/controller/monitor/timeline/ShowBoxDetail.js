Ext.define('Eway.controller.monitor.timeline.ShowBoxDetail',{
	
	name : 'BoxDetail',
	
	statics : {
		showCashBox:function(){
			var win=Ext.create('Eway.view.parameter.paramMonitor.Form');
			win.show();
//			var cashBox = Ext.create('Eway.view.monitor.cashinit.Info');
//			cashBox.show();
//			alert('111sssss');
		},
		showSettleCash:function(){
			var win=Ext.create('Eway.view.parameter.paramMonitor.Form');
			win.show();
		}
	}
});