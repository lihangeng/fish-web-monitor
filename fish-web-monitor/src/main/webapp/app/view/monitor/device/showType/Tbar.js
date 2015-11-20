Ext.define('Eway.view.monitor.device.showType.Tbar', {
	alias : 'widget.monitor_device_showtype_tbar',
	extend : 'Ext.toolbar.Toolbar',

	initComponent : function() {
		
		var store = Ext.create('Eway.store.monitor.device.DeviceFilter');
		store.setUrlParamsByObject({
			userId : ewayUser.getId()
		});
		store.loadPage(1);
		
		Ext.apply(this, {
			items : [
				EwayLocale.commen.terminalId + ':',
				{xtype : 'textfield', width:100, action : 'deviceNumber'},
				{xtype : 'button', text : EwayLocale.button.search, action : 'query', glyph : 0xf002},
				'-',
				//{xtype : 'button', text : EwayLocale.button.pause, action : 'monitorOK', glyph : 0xf04c},
				//'-',
				//{text : EwayLocale.monitor.devMonitor.showWay, menu: [
				//                       {text: EwayLocale.monitor.devMonitor.comboxShowWay.summaryPattern, action : 'summaryPattern'},
				//                       {text: EwayLocale.monitor.devMonitor.comboxShowWay.matrixPattern, action : 'matrixPattern'},
				//                       {text: EwayLocale.monitor.devMonitor.comboxShowWay.maxIconPattern, action : 'maxIconPattern'},
				//                       {text: EwayLocale.monitor.devMonitor.comboxShowWay.listPattern, action : 'listPattern'},
				//                       {text: EwayLocale.monitor.devMonitor.comboxShowWay.boxPattern, action : 'boxPattern'}
				//                       ]},
				{text : EwayLocale.monitor.devMonitor.monitorState, action : 'monitorState'},
                {
                	xtype : 'combobox',
                	action : 'filterName',
                	hideLabel : false,
                	editable : false,
                	emptyText : EwayLocale.combox.select,
				    store: store,
				    queryMode: 'local',
				    displayField: 'filterName',
				    valueField: 'id'
                },
                '->',
                {text : EwayLocale.monitor.devMonitor.comboxShowWay.matrixPattern,glyph:0xf00a, cls : 'monitor_focus_color', action : 'matrixPattern'},
                {text : EwayLocale.monitor.devMonitor.comboxShowWay.maxIconPattern,glyph:0xf009, action : 'maxIconPattern'},
                {text : EwayLocale.monitor.devMonitor.comboxShowWay.listPattern,glyph:0xf0c9, action : 'listPattern'},
                {text : EwayLocale.monitor.devMonitor.comboxShowWay.boxPattern,glyph:0xf022,action : 'boxPattern'},
        	]
		});

		this.callParent(arguments);
	}
});