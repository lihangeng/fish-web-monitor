Ext.define('Eway.view.machine.detail.run.CaseFaultInfo', {
    extend: 'Ext.Panel',
    xtype: 'faultTrend',
    requires:['Eway.store.machine.detail.FaultTrend',
              'Eway.view.case.caseFault.FaultView',
              'Eway.view.case.caseFault.FaultGrid',
              'Eway.view.case.caseFault.FilterForm'],

    title : {
    	text :EwayLocale.index.dailyFaultPic,
    	height:24
    },

	tools:[{
	    type:'refresh',
	    tooltip: EwayLocale.button.refresh,
	    handler: function(event, toolEl, panelHeader) {
	     	this.up('faultTrend').down('cartesian').getStore().load();
	    }
	}],

    initComponent: function() {
        var me = this;
        me.myDataStore = Ext.create('Eway.store.machine.detail.FaultTrend');
        me.items = [{
            xtype: 'cartesian',
            width: '100%',
            height: 260,
            plugins: {
                ptype: 'chartitemevents',
                moveEvents: true,
                clickEvents: true,
                dbClickEvents: true
            },
            interactions: {
                type: 'itemhighlight',
                zoomOnPanGesture: false
            },
            store: this.myDataStore,
            insetPadding: 40,
            innerPadding: {
                left: 40,
                right: 40,
                top : 30
            },
            sprites: [{
                type: 'text',
                text: EwayLocale.index.dailyFaultPic,
                fontSize: 10,
                x: 230,
                y: 485
            }],
            axes: [{
                type: 'numeric',
                fields: 'data1',
                position: 'left',
                grid: true,
                minimum: 0,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }, {
                type: 'category',
                fields: 'month',
                position: 'bottom',
                grid: true,
                renderer: function (axis, label, layoutContext) {
                    return layoutContext.renderer(label);
                }
            }],
            series: [{
                type: 'line',
                xField: 'month',
                yField: 'data1',
                style: {
                    lineWidth: 4
                },
                marker: {
                    radius: 4
                },
                label: {
                    field: 'data1',
                    display: 'over'
                },
                highlight: {
                    fillStyle: '#000',
                    radius: 5,
                    lineWidth: 2,
                    strokeStyle: '#fff'
                },
                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    showDelay: 0,
                    dismissDelay: 0,
                    hideDelay: 0,
                    renderer: function(storeItem, item) {
                    	storeItem.setHtml(item.get('month') + EwayLocale.index.faultAmount + item.get('data1'));
                    }
                }
            }],
            listeners:{
            	//点击表格进行查看当前版本详情对应的设备信息
            	itemclick:function( series, item, event, eOpts ){
            		var window = Ext.create("Ext.window.Window",{
            			width:500,
            			height:300,
            			modal : true,
            			maximized:true,
            			layout:'border',
            		    items: { 
            		    	region:'center',
            		        xtype: 'case_faultView'
            		    }
            		});
            		window.show();
//            		this.up("pie-donut").setZIndex(1000000);
            		var grid = window.down("caseFault_faultGrid");
            		var store = grid.getStore();
//            		store.setBaseParam("args",item.record.get("filterStr"));
            		store.loadPage(1);
            	},
            	itemmouseout:function( series, item, event, eOpts ){
//            		this.up("pie-donut").setZIndex(1000);
            	}
            }
        }];

        me.callParent();
    }
});