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
				text: '查询',
				iconCls :'queryBtn',
				action: 'query'
			},{
			   text :'导入要关联的设备',
			   iconCls:'addBtn',
			   action:'importMachineCode'
			}, {
				text: '关联',
				iconCls :'connectBtn',
				action: 'link'
			}],
			columns : [ {
				header : '编号',
				dataIndex : 'terminalId'
			}, {
				header : '网络地址',
				dataIndex : 'ip'
			}, {
				header : '所属机构',
				dataIndex : 'orgName'
			}, {
				header : '设备型号',
				dataIndex : 'devTypeName'
			}, {
				header : '设备品牌',
				dataIndex : 'devVendorName'
			}, {
				header : '设备类型',
				dataIndex : 'devCatalogName'
			}, {
				header : '设备状态',
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return "开通";
					}
					if (value == 2) {
						return "停用";
					}
				}
			}, {
				header : '设备维护商',
				dataIndex : 'devServiceName'
			}, {
				header : '钱箱报警金额(单位：张数)',
				dataIndex : 'cashboxLimit'
			}, {
				header : '安装日期',
				dataIndex : 'installDate'
			}, {
				header : '地址',
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