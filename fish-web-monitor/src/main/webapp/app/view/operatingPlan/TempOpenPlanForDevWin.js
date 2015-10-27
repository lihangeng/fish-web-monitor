Ext.define("Eway.view.operatingPlan.TempOpenPlanForDevWin",{
   alias:'widget.temp_openPlan_DevWin',
   extend:'Ext.window.Window',
   modal: true,
   resizable: false,
   maximizable: true,
   constrainHeader: true,
   requires: ['Eway.view.operatingPlan.TempOpenPlanForDevGrid'],
   width: 700,
   height: 500,
   layout: 'fit',
   initComponent: function() {
		Ext.apply(this, {
			items : [{
				padding : 1,
				xtype : 'temp_planofdevice_grid'
			}]
		});
		this.callParent(arguments);
	}
});