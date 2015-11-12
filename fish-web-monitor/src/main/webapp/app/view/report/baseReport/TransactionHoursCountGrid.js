Ext.define('Eway.view.report.baseReport.TransactionHoursCountGrid', {
	alias : 'widget.baseReport_TransactionHoursCountGrid',
	extend: 'Eway.view.base.Panel',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	closable : false ,
    initComponent: function() {
        var store = Ext.create('Eway.store.report.baseReport.TransactionHoursCount');
//        store.load() ;
        Ext.apply(this, {
        tbar : [ '->', {
			text : Eway.locale.button.search,
			glyph : 0xf002,
			action : 'query'
		}
		/*, {
			text : Eway.locale.report.openrate.device.importStat,
			glyph : 0xf1c3,
			action : 'importStat'
		}*/
		 ],
        items : [{
            xtype: 'cartesian',
            width: '100%',
            height: 450,
           interactions: {
                type: 'itemhighlight',
                zoomOnPanGesture: false
            },
            store: store,
            insetPadding: 40,
            innerPadding: {
                left: 40,
                right: 40
            },
            sprites: [{
                type: 'text',
                text: Eway.locale.index.dailyFaultPic,
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
//                maximum: 48,
                renderer: function (v, layoutContext) {
                    return layoutContext.renderer(v);
                }
            }, {
                type: 'category',
                fields: 'month',
                position: 'bottom',
                grid: true
                /*renderer: function (v) {
                    return v.substring(4);
                },
                label: {
                    rotate: {
                        degrees: -45
                    }
                }*/
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
                        this.setHtml(storeItem.get('month') + Eway.locale.report.baseReport.tradeCount + storeItem.get('data1'));
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