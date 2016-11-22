 Ext.define('Eway.view.report.baseReport.DeviceTypeCountJReportFilter', {
         extend : 'Eway.view.base.FilterForm',
         alias : 'widget.baseReport_DeviceTypeCountJReportFilter',
 
         requires : ['Eway.view.common.OrgComboOrgTree',
                                 'Eway.view.field.card.DeviceAtmVendorComboBox',
                                 'Eway.view.field.card.DeviceTypeComboBox',
                                 'Eway.view.field.person.OrganizationLevel'],
                         
         height : 70,
         layout : 'column',
         hideLabel : false,
         initComponent : function() {
                 var levelStore = Ext.create('Eway.store.person.organization.OrganizationLevelDict');
                 Ext.apply(this, {
                         items : [{
                                                 columnWidth : .3,
                                                 items : [{
                                                                         style : 'padding-top:0px',
                                                                         xtype : 'hiddenfield',
                                                                         name : 'orgId'
                                                                 }, {
                                                                         //只带出银行机构
                                                                         xtype : 'common_orgComboOrgTree',
                                                                         fieldLabel : EwayLocale.commen.orgNameBelongs,
                                                                         labelAlign : 'right',
                                                                         emptyText : EwayLocale.combox.select,
                                                                         name : 'orgName',
                                                                         hiddenValue : 'orgId',
                                                                         editable : false,
                                                                         filters : '{"type" : "0"}',
                                                                         rootVisible : Eway.user.getOrgType() != "" && Eway.user.getOrgType() == '0' ? true : false
                                                                 },{
                                                                         xtype : 'field.organizationLevel',
                                                                         name : 'orgLevel',
                                                                         store : levelStore,
                                                                         emptyText : EwayLocale.combox.select
                                                                 }]
                                         },{
                                                 columnWidth : .3,
                                                 items : [{
                                                         xtype : 'field_card_DeviceAtmVendorComboBox',
                                                         labelAlign : 'right'
                                                 }]
                                         
                                         },{
                                                 columnWidth : .3,
                                                 items : [{
                                                                         xtype : 'field_card_DeviceTypeComboBox',
                                                                         labelAlign : 'right'
                                                 }]
                                         }]
                 });
 
                 this.callParent(arguments);
         }
 });
