 Ext.define('Eway.view.report.baseReport.DeviceTypeCountJReportView', {
         extend : 'Eway.view.base.Panel',
         alias : 'widget.baseReport_DeviceTypeCountJReportView',
 
         requires : [ 'Eway.view.report.baseReport.DeviceTypeCountJReportGrid',
                         'Eway.view.report.baseReport.DeviceTypeCountJReportFilter' ],
 
         title : EwayLocale.report.baseReport.devBrandRep,
         layout : 'border',
 
         initComponent : function() {
                 Ext.apply(this, {
                         items : [ {
                                 region : 'north',
                                 xtype : 'baseReport_DeviceTypeCountJReportFilter'
                         }, {
                                 region : 'center',
                                 xtype : 'baseReport_DeviceTypeCountJReportGrid'
                         } ],
                         listeners : {
                                 activate : function(panel) {
                                         if (!panel.isLoad) {
                                                 // 第一次进来不需要重新加载信息
                                                 panel.isLoad = true;
                                                 return;
                                         } 
                                         
                                         // 刷新设备型号信息
                                         panel.down('baseReport_DeviceTypeCountJReportFilter').down(
                                                         'field_card_DeviceTypeComboBox').getStore().load();
 
                                         // 刷新设备品牌信息
                                         panel.down('baseReport_DeviceTypeCountJReportFilter').down(
                                                         'field_card_DeviceAtmVendorComboBox').getStore()
                                                         .load();
 
                                         var orgTrees = panel.down(
                                                         'baseReport_DeviceTypeCountJReportFilter').query(
                                                         'common_orgComboOrgTree');
                                         Ext.Array.each(orgTrees, function() {
                                                 // 刷新维护商和所属机构信息
                                                 this.reflesh();
                                         });
                                 }
                         }
                 });
 
                 this.callParent(arguments);
         }
 });