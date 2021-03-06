Ext.define('Eway.view.report.baseReport.DeviceTypeCountReportGrid', {
	extend : 'Ext.pivot.Grid',
	xtype : 'outline-pivot-grid',
	alias : 'widget.baseReport_DeviceTypeCountReportGrid',
	requires : [ 'Eway.store.report.baseReport.DeviceVendorCountReport',
			'Ext.pivot.plugin.Exporter' ,'Ext.exporter.file.excel.Style'],
	//	forceFit : true,
	rowSubTotalsPosition : 'last',
	colSubTotalsPosition : 'last',
	rowGrandTotalsPosition : 'first',
	colGrandTotalsPosition : 'first',
	collapsible : true,
	store : {
		type : 'deviceVendorCount'
	},
	plugins : [ {
		ptype : 'pivotexporter',
		pluginId : 'exporter'
	} ],
	selModel : {
		type : 'rowmodel'
	},
    /**
     * @cfg {String} textTotalTpl Configure the template for the group total. (i.e. '{name} ({rows.length} items)')
     * @cfg {String}           textTotalTpl.groupField         The field name being grouped by.
     * @cfg {String}           textTotalTpl.name               Group name
     * @cfg {Ext.data.Model[]} textTotalTpl.rows               An array containing the child records for the group being rendered.
     */
	 textTotalTpl:               '{name}'+EwayLocale.report.pivot.common.sumSub,

    /**
     * @cfg {String} textGrandTotalTpl Configure the template for the grand total.
     * textGrandTotalTpl:          'Grand total',
     */
    
	 textGrandTotalTpl:          EwayLocale.report.pivot.common.totalSum,
	tbar : {
		itemPosition : 1, // after title before collapse tool
		items : [
				'->',
				{
					xtype : 'button',
					text : EwayLocale.button.search,
					glyph : 0xf002,
					action : 'query'
				},
				{
					xtype : 'button',
					glyph : 0xf1c3,
					text : EwayLocale.button.exportXLS,
					handler : function() {
						var exports = this.up('baseReport_DeviceTypeCountReportGrid');
						exports.saveDocumentAs({
									type: 'excel',
									title : EwayLocale.report.baseReport.devBrandRep,
									showSummary:true,
									fileName : EwayLocale.report.baseReport.devBrandRep+'.xls'
						});
					}
				} ]
	},

	// Set layout type to "outline". If this config is missing then the default layout is "outline"
	viewLayoutType : 'outline',

	// Set this to false if multiple dimensions are configured on leftAxis and
	// you want to automatically expand the row groups when calculations are ready.
	startRowGroupsCollapsed : false,
	startColGroupsCollapsed:true,

	// Configure the aggregate dimensions. Multiple dimensions are supported.
	aggregate : [ {
		dataIndex : 'deviceCount',
		header : EwayLocale.report.pivot.common.totalSum,
		aggregator : 'sum',
		align:'center',
		width : 220
	} ],

	// Configure the left axis dimensions that will be used to generate the grid rows
	leftAxis : [{
		dataIndex : 'orgName',
		header : EwayLocale.person.bankOrg.name,
		width: 320
	}],
	/**
	 * Configure the top axis dimensions that will be used to generate the columns.
	 * When columns are generated the aggregate dimensions are also used. If multiple aggregation dimensions
	 * are defined then each top axis result will have in the end a column header with children
	 * columns for each aggregate dimension defined.
	 */
	topAxis : [  {
	dataIndex : 'vendorName',
	header : 'vendorName',
	renderer:function(v){
		return v;
	}
},{
	dataIndex : 'devTypeName',
	header : 'devTypeName'
} ],

	initComponent : function() {
		var me = this;
//		me.width = me.profiles.width;
		me.callParent();
	}
});