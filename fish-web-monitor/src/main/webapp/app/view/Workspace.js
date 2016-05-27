Ext.define('Eway.view.Workspace', {
    alias: 'widget.workspace',
	extend: 'Ext.tab.Panel',
	id:'workspace',

	border: false,
	autoDestroy: false,
//firefox下不起作用，
	requires:['Ext.ux.TabCloseMenu'],
	plugins:['tabclosemenu'],
	
	//F5刷新，未完全实现
//	requires:['Eway.view.index.Index'],
//	initComponent : function() {
//		Ext.apply(this, {
//			items : [{
//					xtype:'appindex'
//				}]
//			}
//		);
//		
//		this.callParent(arguments);
//	},

	activeEwayView: function(comp) {
		var compId = comp.getId();
		var c = this.getComponent(compId);
		if(!c) {
			this.add(comp);
		}
		else {
			var currentView = this.getActiveTab();
			if(currentView.id == c.id){
				return;
			}
		}
		this.setActiveTab(comp);
	}
});