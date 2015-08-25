Ext.define('Eway.view.case.caseFault.CaseNotifyWin', {
	alias: 'widget.caseFault_caseNotifyWin',
	extend: 'Ext.window.Window',
	
	modal: true,
	resizable: false,
	maximizable: true,
	constrainHeader: true,
	
	requires: ['Eway.view.case.caseFault.FaultNotifyGrid'],
	
	width: 800,
	height: 600,
	layout: 'fit',
	
	initComponent: function() {
		Ext.apply(this, {
			title : '故障相关短信',
			items : [{
				padding : 1,
				xtype : 'caseFault_faultNotifyGrid'
			}]
		});
		this.callParent(arguments);
	}
	
});