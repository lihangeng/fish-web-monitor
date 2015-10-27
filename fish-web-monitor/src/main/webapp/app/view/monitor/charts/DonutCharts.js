Ext.define('Eway.view.monitor.charts.DonutCharts', {
    extend: 'Ext.Panel',
    alias: 'widget.pie-donut',
    width: 300,
    config:{
            angleField:'displayName',
            labelField:'numberInfo',
            stores:''
    },
    initComponent: function() {
        var me = this;

        me.myDataStore = this.getStores();

        me.items = [{
            xtype: 'polar',
            width: '100%',
            plugins: {
                ptype: 'chartitemevents',
                moveEvents: true,
                clickEvents: true,
                dbClickEvents: true
            },
            height: 340,
            store: this.myDataStore,
            insetPadding: 50,
            innerPadding: 20,
            legend: {
                docked: 'right'
            },
            interactions: ['rotate', 'itemhighlight'],
            series: [{
                type: 'pie',
                angleField: this.getLabelField(),
                donut: 50,
                label: {
                    field: this.getAngleField(),
                    display: 'outside'
                },
                highlight: true,
                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    renderer: function(storeItem, item) {
                        this.setHtml(storeItem.get(me.getLabelField()) + ': ' + storeItem.get(me.getAngleField()));
                    }
                },
                listeners:{
                	//点击表格进行查看当前版本详情对应的设备信息
                	itemclick:function( series, item, event, eOpts ){
//                		Ext.scroll.Scroller.create({
//                		    element: 'myElementId'
//                		});
                		var win = Ext.create('Eway.view.monitor.charts.MonitorDeviceDetailWindow', {
                		    title: me.getTitle()+"-"+item.record.get(me.getAngleField())
                		});
                		win.show();
                		var store = win.down("monitor_device_grid").getStore();
                		store.setBaseParam("args",item.record.get("filterStr"));
                		store.load();
                	}
                }
            }]
        }];

        this.callParent();
    }
});