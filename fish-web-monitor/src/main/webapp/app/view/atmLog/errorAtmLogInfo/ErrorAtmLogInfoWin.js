
Ext.define('Eway.view.atmLog.errorAtmLogInfo.ErrorAtmLogInfoWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.atmLog_errorAtmLogInfo_ErrorAtmLogInfoWin',
	
	requires: [
	           'Eway.view.atmLog.errorAtmLogInfo.ErrorAtmLogInfoGrid'],
	           
	title: EwayLocale.atmLog.checkFailInfo,
	modal: true,
	resizable: false,
	constrainHeader: true,
	width: 700,
	height: 560,
	layout: 'fit',
	maximizable: true,
	
	initComponent: function() {
		Ext.apply(this, {
			items : [/*{
				region : 'north',
				xtype : 'atmLog_errorAtmLogInfo_ErrorAtmLogInfoFilterForm',
				anchor : '100%'
			},*/{
				region : 'center',
				xtype : 'atmLog_errorAtmLogInfo_ErrorAtmLogInfoGrid',
				anchor : '100%',
				layout: 'fit'
			}]
		});
		
		this.callParent(arguments);
	}
});