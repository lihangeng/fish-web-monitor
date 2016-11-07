Ext.define('Eway.view.monitor.newTransaction.Chart', {
    alias : 'widget.monitor_new_transaction_chart',
    extend : 'Ext.panel.Panel',

    border : false,

    initComponent : function() {
    
    
        var seriesConfig = function(field, title, lineColor) {
            return {
                type: 'line',
                smooth: false,
                axis: ['left', 'bottom'],
                xField: 'date',
                yField: field,
                title: title,
                tips: {
                    trackMouse: true,
                    width: 50,
                    height: 24,
                    renderer: function(storeItem, item) {
                    	storeItem.setTitle(' ' + item.get(field));
                    }
                }
            }
        }
    
        var store = Ext.create('Eway.store.monitor.newTransaction.Chart');
        var date = new Date();
        Ext.apply(this, {
            layout: 'fit',
            items: [ {
                xtype: 'cartesian',
                style: 'background:#fff',
                itemId: 'chartCmp',
                store: store,
                shadow: true,
                animate: true,
//                layout: 'border',
//                legend: {
//	                docked: 'right'
//	            },
                axes: [ {
                    type: 'numeric',
                    minimum: 0,
                    position: 'left',
                    fields: [ 'trans_total_tps', 'local_total_tps', 'other_total_tps' ]
                }, {
                    type: 'time',
                    position: 'bottom',
                    fields: 'date',
                    dateFormat: 'H:i',
                    
                    step: [Ext.Date.MINUTE, 1],    //时间轴，坐标点，步进距离  
                    constrain: true,
                    fromDate: date,
                    toDate: Ext.Date.add(date, Ext.Date.MINUTE, 15),
                    grid: true
                } ],
                series: [
                     seriesConfig('trans_total_tps', '总交易tps', '#115fa6'),
                     seriesConfig('local_total_tps', '本行卡交易tps', '#94ae0a'),
                     seriesConfig('other_total_tps', '他行卡交易tps', '#a61120')
                ]
            } ]
        });
        
        this.callParent(arguments);
    }
});
