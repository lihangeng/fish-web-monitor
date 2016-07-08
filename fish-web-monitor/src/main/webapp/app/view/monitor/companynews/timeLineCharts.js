Ext.define('Eway.view.monitor.companynews.timeLineCharts', {
	alias : 'widget.monitor_companynews_timeLineCharts',
	extend: 'Ext.Panel',
    xtype: 'column-stacked',

	requires : [ 'Eway.lib.Util','Ext.chart.theme.Muted' ],
	border : true,
	closable : false ,	
//	autoScroll : true,
    initComponent: function() {
    	var me = this;
        Ext.apply(this, {
        items : [{
                xtype: 'cartesian',
                reference: 'chart',
                autoScroll : true,
//                width: '500%',
                theme: {
                    type: 'muted'
                },
                legend: {
                    docked: 'right'
                },
                interactions: {
                    type: 'itemedit',
                    tooltip: {
                        renderer: 'onEditTipRender'
                    }
                },
                store: {type: 'news'},
                height : 300,
                style: 'background: #fff',
                padding: '0 0 0 0',
                insetPadding: {
                    top: 45,
                    left: 15,
                    right: 15,
                    bottom: 15
                },
                animation: Ext.isIE8 ? false : {
                    easing: 'backOut',
                    duration: 5
                },
                shadow: false,
                sprites: [{
                    type  : 'text',
                    font  : '14px Helvetica',
                    fontStyle:'oblique',
                    width : 100,
                    height: 30,
                    x : 20, //the sprite x position
                    y : 25  //the sprite y position
                }],
                axes: [{
                    type: 'numeric',
                    position: 'left',
                    minimum: 0,
                    adjustByMajorUnit: true,
                    fields: ['title'],
                    renderer : function(axis, label, layoutContext) {
						return layoutContext.renderer(label);
					},
                    grid: true
                }, {
                    type: 'category',
                    position: 'bottom',
                    fields: ['paragraph'],
                    grid: true,
                    label: {
                        rotate: {
                            degrees: -45
                        }
                    }
                }],
                series: [{
                    type: 'bar',
                    title: [ "加钞金额",
							"清机总金额"],
                    xField: 'paragraph',
                    yField: [ 'time','group'],
                    stacked: false,
                    style: {
                        opacity: 0.80,
                        width:5
                    },
                    label : {
						field : [ 'time','group'],
						display : 'insideEnd'
					},
                    tooltip: {
                        renderer: function (tooltip, record, item) {
                            var fieldIndex = Ext.Array.indexOf(item.series.getYField(), item.field),
                            brand = item.series.getTitle()[fieldIndex];
                            tooltip.setHtml(brand + ' on ' +
                                    record.get('time') + ': ' +
                                    record.get(item.field) );
                        }
                    }
                }]
            }]
        }) ;

        this.callParent();
    },

	onReload : function() {
		this.getStore().load();
	},
	/**
	 * Custom function used for column renderer
	 *
	 * @param {Object}
	 *            val
	 */
	pctChange : function(val) {
		if (val >= 20) {
			return '<span style="color:green;">' + val + '%</span>';
		} else if (val < 20) {
			return '<span style="color:red;">' + val + '%</span>';
		}
		return val;
	}
});