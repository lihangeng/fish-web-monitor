Ext.define('Eway.view.index.StatusDonutCharts', {
    extend: 'Ext.Panel',
    alias: 'widget.statusDonutCharts',
    config:{
            angleField:'displayName',
            labelField:'numberInfo'
    },
    title : {
    	text : Eway.locale.index.devStatusDisPic,
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
			    		panel.down("polar series[type='pie']").updateColors(colors);
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
		    			console.log(temp);
		    			if(temp){
		    				temp.childNodes[0].style.backgroundColor=item.data.color;
		    			}
		    		},this);
		    		me.down("polar series[type='pie']").updateColors(colors);
		    	}
		    }
		});

        this.callParent();
    }
});