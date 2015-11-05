Ext.define('Eway.view.monitor.device.filterManager.FilterConfig',{
	extend : 'Ext.form.Panel',
	alias : 'widget.monitor_device_filtermanager_filterconfig',
	
	border : false,
	
	action : 'monitor_device_filtermanager_filterconfig',
	
	bodyStyle : 'padding: 10px 5px 5px 5px',
	
	layout : 'column',
	
	requires : [ 'Eway.view.common.OrgComboOrgTree' ],
	
	initComponent : function() {
		var FilterBrandItem = Ext.create('Eway.store.monitor.device.FilterBrandItem',{});
		var FilterClassifyItem = Ext.create('Eway.store.monitor.device.FilterClassifyItem',{});
		var FilterIngItem = Ext.create('Eway.store.monitor.device.FilterIngItem',{}); // 本地资源
		var FilterSellItem = Ext.create('Eway.store.monitor.device.FilterSellItem',{}); // 本地资源
		var atmGroupStore = Ext.create("Eway.store.monitor.device.AtmGroup");
		
		FilterBrandItem.load();
		FilterClassifyItem.load();
		atmGroupStore.load();
		
		Ext.apply(this, {
			items : [ {
				columnWidth : .5,
				items : [ {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'id'
				}, {
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>'+Eway.locale.monitor.devMonitor.filterManager.filterForm.filterName,
					name : 'filterName',
					allowBlank : false,
					maxLength : 20
				}, {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : Eway.locale.machine.atmGroup.orgName,
					emptyText : Eway.locale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
				}, {
					fieldLabel : Eway.locale.commen.devVendorName,
					xtype : 'combo',
					displayField : 'name',
					name : 'brandItem',
					store : FilterBrandItem,
					queryMode : 'local',
					typeAhead : false,
					valueField : 'value',
					value : '0',
					editable : false,
					listeners : {
//						change : this.cascade
					}
				}, {
					fieldLabel : Eway.locale.commen.devTypeName,
					name : 'classifyItem',
					xtype : 'combo',
					displayField : 'name',
					store : FilterClassifyItem,
					queryMode : 'local',
					typeAhead : false,
					editable : false,
					valueField : 'value',
					value : '0'
				} ]
			}, {
				columnWidth : .5,
				items : [ {
					fieldLabel : Eway.locale.commen.seviceMode,
					xtype : 'combo',
					displayField : 'name',
					name : 'sellItem',
					store : FilterSellItem,
					queryMode : 'local',
					typeAhead : false,
					valueField : 'value',
					value : '0',
					editable : false
				}, {
					fieldLabel : Eway.locale.commen.insideOutside,
					xtype : 'combo',
					displayField : 'name',
					name : 'ingItem',
					store : FilterIngItem,
					queryMode : 'local',
					typeAhead : false,
					valueField : 'value',
					value : '0',
					editable : false
				}, {
					fieldLabel : Eway.locale.monitor.devMonitor.atmGroup,
					xtype : 'combo',
					displayField : 'name',
					name : 'atmGroup',
					store : atmGroupStore,
					queryMode : 'local',
					typeAhead : false,
					valueField : 'value',
					value : '0',
					editable : false
				} ]
			} ]
		});
		
		this.callParent(arguments);
	},
	
	cascade : function(field,newValue,oldValue) {
		var classifyItem = field.nextSibling('combo[name="classifyItem"]');
		classifyItem.reset();
		var store = classifyItem.getStore();
		store.load({
			params : {
				brand : newValue
			}
		});
	}
});