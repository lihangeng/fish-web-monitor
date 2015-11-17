
Ext.define('Eway.view.atmLog.successAtmLogInfo.SuccessAtmLogInfoWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.atmLog_successAtmLogInfo_SuccessAtmLogInfoWin',
	
	requires: [
	           'Eway.view.atmLog.successAtmLogInfo.SuccessAtmLogInfoGrid'],
	           
	title: EwayLocale.atmLog.checkSucInfo,
	modal: true,
	resizable: false,
	constrainHeader: true,
	width: 700,
	height: 560,
	layout: 'fit',
	maximizable: true,
	
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				region : 'center',
				xtype : 'atmLog_successAtmLogInfo_SuccessAtmLogInfoGrid',
				anchor : '100%',
				layout: 'fit'
			}]
		});
		
		this.callParent(arguments);
	}
});