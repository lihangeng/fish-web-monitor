Ext.define('Eway.view.monitor.charts.DonutCharts', {
    extend: 'Ext.Panel',
    alias: 'widget.pie-donut',
    requires:['Eway.view.monitor.charts.MonitorDeviceGrid'],
    width: 330,
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
            height: 220,
            store: this.myDataStore,
            insetPadding: 10,
            innerPadding: 20,
            legend: {
                docked: 'right',
                lazy:true
            },
            plugins: {
                ptype: 'chartitemevents',
                moveEvents: true,
                clickEvents: true,
                dbClickEvents: true
            },
            interactions: ['rotate', 'itemhighlight'],
            series: [{
                type: 'pie',
                showInLegend:false,
                angleField: this.getLabelField(),
                donut: 50,
                label: {
                    field: this.getAngleField(),
                    display: 'outside',
                    renderer: function(a,b,c,d,e) {
                        return me.myDataStore.data.items[e].get(me.getAngleField())+": "+me.myDataStore.data.items[e].get(me.getLabelField());
                    }
                },
                useDarkerStrokeColor:false,

                highlight: true,
                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    renderer: function(storeItem, item) {
                        this.setHtml(storeItem.get(me.getAngleField())+": "+storeItem.get(me.getLabelField()));
                    }
                }
            }],
            listeners:{
            	//点击表格进行查看当前版本详情对应的设备信息
            	itemclick:function( series, item, event, eOpts ){
            		var window = Ext.create("Ext.window.Window",{
            			title: me.getTitle()+"-"+item.record.get(me.getAngleField()),
            			width:500,
            			height:300,
            			modal : true,
            			maximized:true,
            			layout:'border',
            		    items: { 
            		    	region:'center',
            		        xtype: 'monitor_device_grid'
            		    }
            		});
            		window.show();
            		this.up("pie-donut").setZIndex(1000000);
            		var grid = window.down("monitor_device_grid");
            		var store = grid.getStore();
            		store.setBaseParam("args",item.record.get("filterStr"));
            		store.loadPage(1);
            	},
            	itemmouseout:function( series, item, event, eOpts ){
            		this.up("pie-donut").setZIndex(1000);
            	}
            }
        }];

        this.callParent();
    }
});