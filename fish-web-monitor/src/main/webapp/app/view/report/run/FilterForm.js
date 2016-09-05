/**
 * 机构查询条件Form
 */
Ext.define('Eway.view.report.run.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.report_run_filterform',

	height: 'auto',
	layout : 'column',
	defaults : {
		border : false
	},

	initComponent: function() {
		Ext.apply(this, {
			items : [{ 
				xtype      : 'fieldcontainer',
	            fieldLabel : EwayLocale.report.run.reportType.title,
	            defaultType: 'radiofield',
	            defaults: {
	                flex: 1
	            },
				layout: {
				    type: 'hbox',
				    align: 'left'
				},
	            items: [
	                {
	                    boxLabel  : EwayLocale.report.run.reportType.weekReport,
	                    name      : 'reportType',
	                    inputValue: 'week',
	                    checked:true
	                }, {
	                    boxLabel  : EwayLocale.report.run.reportType.monthReport,
	                    name      : 'reportType',
	                    inputValue: 'month'
	                }
	            ]
				
			}]
		});

		this.callParent(arguments);
	}
});