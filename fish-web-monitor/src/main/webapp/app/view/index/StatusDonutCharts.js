Ext.define('Eway.view.index.StatusDonutCharts', {
    extend: 'Ext.Panel',
    alias: 'widget.statusDonutCharts',
    requires:['Eway.store.monitor.charts.DonutChartsSummary'],
    config:{
            angleField:'displayName',
            labelField:'numberInfo'
    },
    title : {
    	text : EwayLocale.index.devStatusDisPic,
    	height:24
    },
    
    tools:[{
	    type:'refresh',
	    handler: function(event, toolEl, panelHeader) {
	    	var panel = this.up('statusDonutCharts');
	     	panel.down('polar').getStore().load({
			 	scope: this,
			    callback: function(records, operation, success) {
			    	if(success){
			    		var colors = new Array();
			    		Ext.Array.forEach(records,function(item,index,allItems){
			    			colors.push(item.data.color);	
			    			panel.down("polar").legend.all.elements[index].childNodes[0].style.backgroundColor=item.data.color;
			    		},this);
			    		panel.down("polar series[type='pie']").setColors(colors);
			    	}
			    }
    		});
	    }
	}],

    initComponent: function() {
        var me = this;

        me.myDataStore = Ext.create("Eway.store.monitor.charts.DonutChartsSummary");

        me.items = [{
            xtype: 'polar',
            width: '100%',
            height: 260,
            plugins: {
                ptype: 'chartitemevents',
                moveEvents: true,
                clickEvents: true,
                dbClickEvents: true
            },
            store: this.myDataStore,
            insetPadding: 10,
            innerPadding: 10,
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
                    renderer: function(storeItem, item) {
                    	return "";
                    }
                },
                useDarkerStrokeColor:false,

                highlight: true,
                tooltip: {
                    trackMouse: true,
                    style: 'background: #fff',
                    renderer: function(storeItem, item) {
                    	storeItem.setHtml(item.get(me.getAngleField())+ ': ' +item.get(me.getLabelField())  );
                    }
                }
            }]
        }];
        
        me.myDataStore.load({
		 	scope: this,
		    callback: function(records, operation, success) {
		    	if(success){
		    		var colors = new Array();
		    		Ext.Array.forEach(records,function(item,index,allItems){
		    			colors.push(item.data.color);
		    			var temp = me.down("polar").legend.all.elements[index];
		    			if(temp){
		    				temp.childNodes[0].style.backgroundColor=item.data.color;
		    			}
		    		},this);
		    		me.down("polar series[type='pie']").setColors(colors);
		    	}
		    }
		});

        this.callParent();
    }
});