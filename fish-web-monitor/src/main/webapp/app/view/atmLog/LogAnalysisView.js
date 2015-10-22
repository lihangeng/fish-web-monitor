Ext.define('Eway.view.atmLog.LogAnalysisView',{
	alias : 'widget.atmLog_LogAnalysisView',
	extend : 'Eway.view.base.Panel',
	requires: ['Eway.view.atmLog.LogAnalysisForm'],
	title :Eway.locale.atmLog.busLogAnalysis,
	layout:'border',
	header:false,
	border:false,
	initComponent: function() {
		Ext.apply(this, {
			items:[
				{
				  region : 'center',
				  xtype : 'LogAnalysisregister_form'
			    }
			]
		});
		this.callParent(arguments);
	}

})

