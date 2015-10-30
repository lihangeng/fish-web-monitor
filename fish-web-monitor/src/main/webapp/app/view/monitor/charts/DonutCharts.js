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
                docked: 'right',
                lazy:true
            },
            interactions: ['rotate', 'itemhighlight'],
            series: [{
                type: 'pie',
                showInLegend:true,
                angleField: this.getLabelField(),
                donut: 50,
                label: {
                    field: this.getAngleField(),
                    display: 'outside'
                },
                useDarkerStrokeColor:false,

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