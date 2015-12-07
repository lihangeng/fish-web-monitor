Ext.define('Eway.view.atmLog.LogBackup',{

	extend : 'Eway.view.base.Panel',
	alias : 'widget.atmLog_LogBackup',
	
	requires : [
		'Eway.view.atmLog.LogBackupGrid',
		'Eway.view.atmLog.LogBackupFilterForm'
	],	
	title:{
	    	height :30
	      },
	    tools:[{			
		    	xtype : 'textfield',
				fieldLabel : EwayLocale.commen.terminalId,
				labelWidth : 80,
				name : 'terminalId'		    	
			},{				
					xtype : 'button',
					text : EwayLocale.button.search,
					glyph : 0xf002,
					action : 'query'
			}],
	    
	initComponent : function(){		
		Ext.apply(this,{
			items : [{
				region : 'center',
				xtype : 'atmLog_LogBackupGrid',
				height :572
			}]
		});
		this.callParent(arguments);
	}
	
});