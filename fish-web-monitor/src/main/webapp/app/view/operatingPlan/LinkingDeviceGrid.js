Ext.define('Eway.view.operatingPlan.LinkingDeviceGrid', {
	alias : 'widget.operatingPlan_linkingDeviceGrid',
	extend : 'Ext.grid.Panel',

	requires : [ 'Eway.lib.Util','Eway.view.operatingPlan.ImportLinkedMachine' ],

	border : false,
	autoFit : true,
	viewConfig : {
		loadMask : false, //该属性默认为true时,刷新会调用deselect方法
		forceFit : true,
		multiSelect:true
	},
	selModel:{selType:'checkboxmodel'},

	initComponent : function() {
		var gridStore = Ext.create('Eway.store.operatingPlan.LinkingDevice',{
			listeners: {
				load : function(store,records){
					var form = this.up('window').down("form[name=LinkingDeviceFilter]").getForm();
					var deviceIdsField = form.findField("deviceIds");
					var t = [];
					for(var i in records) {
						if(this.isExist(deviceIdsField.value, records[i].get("id"))){
							t.push(records[i]);
						}
					}
					if(t.length == 0){
						this.getSelectionModel().toggleUiHeader(false);
					}else{
						this.getSelectionModel().select(t);
					}
				},
				scope : this
			}
		});
		var sm = Ext.create('Ext.selection.CheckboxModel', {
			checkOnly : true,
			mode:'SIMPLE',
	        listeners: {
	        	select: function(sm, record, index) {
	        		var form = this.up('window').down("form[name=LinkingDeviceFilter]").getForm();
					var deviceIdsField = form.findField("deviceIds");
					if(!this.isExist(deviceIdsField.value,record.get("id"))){
						deviceIdsField.value = deviceIdsField.value + "," + record.get("id");
					}
	            },
	            deselect : function(sm, record, index) {
	            	var form = this.up('window').down("form[name=LinkingDeviceFilter]").getForm();
					var deviceIdsField = form.findField("deviceIds");
					var arr = this.toArray(deviceIdsField.value);
					Ext.Array.remove(arr,record.get("id").toString());
					var s = "";
					for(var i in arr){
						s = s + "," + arr[i];
					}
					deviceIdsField.setValue(s);
	            },
	            scope:this
	        }
	    });
		Ext.apply(this, {
			initRegion : true,
			store: gridStore,
			selModel : sm,
			tbar: [{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'->', {
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			}/*,{
			   text :'导入要关联的设备',
			   iconCls:'addBtn',
			   action:'importMachineCode'
			}*/, {
				text: EwayLocale.button.link,
				glyph : 0xf0c1,
//				iconCls :'connectBtn',
				action: 'link'
			}],
			columns : [ {
				header : EwayLocale.refs.terminalId,
				dataIndex : 'terminalId'
			}, {
				header : EwayLocale.refs.ip,
				dataIndex : 'ip'
			}, {
				header : EwayLocale.commen.orgNameBelongs,
				dataIndex : 'orgName'
			}, {
				header : EwayLocale.commen.devTypeName,
				dataIndex : 'devTypeName'
			}, {
				header : EwayLocale.commen.devVendorName,
				dataIndex : 'devVendorName'
			}, {
				header : EwayLocale.commen.devCatalogName,
				dataIndex : 'devCatalogName'
			}, {
				header : EwayLocale.commen.devStatus,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return EwayLocale.commen.comboxDevStatus.upOpen;
					}
					if (value == 2) {
						return EwayLocale.commen.comboxDevStatus.open;
					}
					if (value == 3) {
						return EwayLocale.commen.comboxDevStatus.stop;
					}
					if (value == 4) {
						return EwayLocale.commen.comboxDevStatus.scrapped;
					}
				}
			}, {
				header : EwayLocale.commen.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : EwayLocale.commen.cashboxLimit,
				dataIndex : 'cashboxLimit'
			}, {
				header : EwayLocale.commen.installDate,
				dataIndex : 'installDate'
			}, {
				header : EwayLocale.commen.address,
				dataIndex : 'address',
				flex : 1
			} ],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : gridStore,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	toArray : function(s){
		var d = [];
		if(!Ext.isEmpty(s)){
			d = s.substring(1).split(",");
		}
		return d;
	},
	isExist : function(deviceIds,id){
		var d = this.toArray(deviceIds);
		for(var i in d){
			if(d[i] == id){
				return true;
			}
		}
		return false;
	}
});