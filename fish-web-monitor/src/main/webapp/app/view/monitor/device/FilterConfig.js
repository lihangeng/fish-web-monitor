Ext.define('Eway.view.monitor.device.FilterConfig',{
	extend : 'Ext.form.Panel',
	alias : 'widget.monitor_device_filterConfig',
	
	buttonAlign : 'center',
	border : false,
	action : 'monitor_device_filterConfig',
	bodyStyle : 'padding: 10px 5px 5px 5px',
	initComponent : function(){
		var FilterBrandItem = Ext.create('Eway.store.monitor.device.FilterBrandItem',{});
		FilterBrandItem.load();
		var FilterClassifyItem = Ext.create('Eway.store.monitor.device.FilterClassifyItem',{});
		FilterClassifyItem.load();
		var FilterIngItem = Ext.create('Eway.store.monitor.device.FilterIngItem',{});
		var FilterSellItem = Ext.create('Eway.store.monitor.device.FilterSellItem',{});
		var atmGroupStore = Ext.create("Eway.store.monitor.device.AtmGroup");
		atmGroupStore.load();
		this.items = [{
			fieldLabel : '设备品牌',
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
				change : this.cascade
			}
		},{
			fieldLabel : '设备型号',
			name : 'classifyItem',
			xtype : 'combo',
			displayField : 'name',
			store : FilterClassifyItem,
			queryMode : 'local',
			typeAhead : false,
			editable : false,
			valueField : 'value',
			value : '0'
		},{
			fieldLabel : '经营方式',
			xtype : 'combo',
			displayField : 'name',
			name : 'sellItem',
			store : FilterSellItem,
			queryMode : 'local',
			typeAhead : false,
			valueField : 'value',
			value : '0',
			editable : false
		},{
			fieldLabel : '在行标志',
			xtype : 'combo',
			displayField : 'name',
			name : 'ingItem',
			store : FilterIngItem,
			queryMode : 'local',
			typeAhead : false,
			valueField : 'value',
			value : '0',
			editable : false
		},{
			fieldLabel : '分组',
			xtype : 'combo',
			displayField : 'name',
			name : 'atmGroup',
			store : atmGroupStore,
			queryMode : 'local',
			typeAhead : false,
			valueField : 'value',
			value : '0',
			editable : false
		}];
		this.callParent(arguments);
	},
	
	cascade : function(field,newValue,oldValue){
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