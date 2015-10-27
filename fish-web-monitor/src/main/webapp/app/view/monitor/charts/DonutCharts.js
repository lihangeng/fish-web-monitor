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
                colors:['#8ca640',
                        '#974144',
                        '#4091ba',
                        '#8e658e',
                        '#3b8d8b',
                        '#b86465',
                        '#d2af69',
                        '#6e8852',
                        '#3dcc7e',
                        '#a6bed1',
                        '#cbaa4b',
                        '#998baa'],
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
                		var grid = me.up("panel").up("panel").down("monitor_device_grid");
                		grid.setTitle(me.getTitle()+"-"+item.record.get(me.getAngleField()));
                		var store = grid.getStore();
                		store.setBaseParam("args",item.record.get("filterStr"));
                		store.loadPage(1);
                	}
                }
            }]
        }];

        this.callParent();
    }
});