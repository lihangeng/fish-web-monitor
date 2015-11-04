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
				Eway.locale.commen.terminalId + ':',
				{xtype : 'textfield', width:100, action : 'deviceNumber'},
				{xtype : 'button', text : Eway.locale.button.search, action : 'query', glyph : 0xf002},
				'-',
				//{xtype : 'button', text : Eway.locale.button.pause, action : 'monitorOK', glyph : 0xf04c},
				//'-',
				//{text : Eway.locale.monitor.devMonitor.showWay, menu: [
				//                       {text: Eway.locale.monitor.devMonitor.comboxShowWay.summaryPattern, action : 'summaryPattern'},
				//                       {text: Eway.locale.monitor.devMonitor.comboxShowWay.matrixPattern, action : 'matrixPattern'},
				//                       {text: Eway.locale.monitor.devMonitor.comboxShowWay.maxIconPattern, action : 'maxIconPattern'},
				//                       {text: Eway.locale.monitor.devMonitor.comboxShowWay.listPattern, action : 'listPattern'},
				//                       {text: Eway.locale.monitor.devMonitor.comboxShowWay.boxPattern, action : 'boxPattern'}
				//                       ]},
				{text : Eway.locale.monitor.devMonitor.monitorState, action : 'monitorState'},
                {
                	xtype : 'combobox',
                	action : 'filterName',
                	hideLabel : false,
                	editable : false,
                	emptyText : Eway.locale.combox.select,
				    store: store,
				    queryMode: 'local',
				    displayField: 'filterName',
				    valueField: 'id'
                },
                '->',
                {text : Eway.locale.monitor.devMonitor.comboxShowWay.matrixPattern, action : 'matrixPattern'},
                {text : Eway.locale.monitor.devMonitor.comboxShowWay.maxIconPattern, action : 'maxIconPattern'},
                {text : Eway.locale.monitor.devMonitor.comboxShowWay.listPattern, action : 'listPattern'},
                {text : Eway.locale.monitor.devMonitor.comboxShowWay.boxPattern, action : 'boxPattern'},
        	]
		});

		this.callParent(arguments);
	}
});