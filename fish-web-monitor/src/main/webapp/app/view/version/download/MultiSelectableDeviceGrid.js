Ext.define('Eway.view.version.download.MultiSelectableDeviceGrid', {
	alias : 'widget.version_download_multiselectableDeviceGrid',
	extend : 'Ext.grid.Panel',

	requires : [ 'Eway.lib.Util','Eway.model.version.SelectableDevice','Ext.selection.CheckboxModel',
				 'Eway.view.field.device.DeviceAtmType','Eway.view.common.OrgComboOrgTree'],
	viewConfig : {
		forceFit : true,
		loadMask : false,
		getRowClass: function(record, rowIndex, rowParams, store){
//			if(record.get('selectable') ==  false){
//				return "x-item-disabled";
//			}
        	return "";
   		}
	},

	initComponent : function() {
		var gridStore = Ext.create('Eway.store.version.SelectableDevice',{
			listeners: {
				load : function(store,records){
					var form = this.up('window').down('form').getForm();
					var deviceIdsField = form.findField("deviceIds");
					var t = [];
					for(var i in records){
						if(this.isExist(deviceIdsField.value,records[i].get("id"))){
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
			},
			//获得能够实际选择的条数
			getRealCount: function(){
				var models = this.getRange();
				var i = 0;
				for(var j in models){
					var record = models[j];
					if(record.get('selectable') ==  false){
					}else{
						i ++ ;
					}
				}
				return i;
			}
		});
		var sm = Ext.create('Ext.selection.CheckboxModel',{
			checkOnly: true,//只保留checkbox的选择能力，row选择失效
			listeners:{
				beforeselect : function(me,record,rowIndex){
					if(!this.isRowOK(record)){
						return false;
					}else{
						var form = this.up('window').down('form').getForm();
						var deviceIdsField = form.findField("deviceIds");
						var deviceId = record.get("id");
						if(!this.isExist(deviceIdsField.value,deviceId)){
							deviceIdsField.value = deviceIdsField.value + "," + deviceId;
							//
							var linkedGrid =  this.up('window').down('version_download_linkedDeviceGrid');
							var linkedStore = linkedGrid.getStore();
							linkedStore.add(record);
							linkedGrid.setTitle(Eway.locale.version.selectDeviceInfo0 + linkedStore.getCount() + Eway.locale.version.selectDeviceInfo1);
							
//							linkedGrid.setTitle("已选择的设备(<font color='red'>" + linkedStore.getCount() + "</font>)台");
						}
						return true;
					}
				},
				deselect: function(me,record,rowIndex){
					var form = this.up('window').down('form').getForm();
					var deviceIdsField = form.findField("deviceIds");
					var deviceId = record.get("id");
					this.updateDeviceIdValue(deviceIdsField,deviceId);
					//
					var linkedGrid =  this.up('window').down('version_download_linkedDeviceGrid');
					var linkedStore = linkedGrid.getStore();
					linkedStore.remove(linkedStore.getById(deviceId));
					linkedGrid.setTitle(Eway.locale.version.selectDeviceInfo0 + linkedStore.getCount() + Eway.locale.version.selectDeviceInfo1);
//					linkedGrid.setTitle("已选择的设备(<font color='red'>" + linkedStore.getCount() + "</font>)台");
					//
					return true;
				},
				scope:this
			}
		});
		Ext.apply(this, {
			initRegion : true,
			store: gridStore,
			selModel : sm,
			multiSelect : true,
			tbar:[{
				xtype:'textfield',
				fieldLabel:'IP',
				name:'ip',
				labelSeparator:'',
				labelWidth : 10,
				width: 120,
				vtype:'ip'
			}, {
				xtype:'textfield',
				vtype : 'terminalId',
				maxLength:20,
				fieldLabel:Eway.locale.refs.terminalId,//'设备编号',
				name:'terminalId',
				labelSeparator:'',
				labelWidth : 60,
				width: 140
			},{
				style : 'padding-top:0px',
				xtype : 'hiddenfield',
				name : 'orgId'
			}, {
				xtype : 'common_orgComboOrgTree',
				fieldLabel : Eway.locale.refs.orgName,
				emptyText : Eway.locale.combox.select,//'--请选择--',
				name : 'orgName',
				hiddenValue : 'orgId',
				editable : false,
				labelWidth : 60,
				labelSeparator:'',
				width: 200,
				filters : '{"type" : "0"}',
				parentXtype:'toolbar',
				rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false,
				onTreeItemClick : function(view,record){
					this.setValue(record.get('text'));
					this.up('grid').down("hiddenfield[name=orgId]").setValue(record.get('id'));
					this.collapse();
				},
				clearValue : function(){
					this.setValue('');
					this.up('grid').down("hiddenfield[name=orgId]").setValue('');
				}
			},{
				xtype:'field_device_deviceatmtype',
				fieldLabel :  Eway.locale.refs.devType,
				name: 'atmTypeId',
				editable  : false,
				store: 'machine.DeviceAtmType',
				emptyText : Eway.locale.combox.select,//'--请选择--',
				mode : 'local',
				triggerAction: 'all',
				valueField : 'id',
				displayField : 'name',
				labelWidth : 60,
				labelSeparator:'',
				width: 200
			},{
				action :'queryDownDevice',
				glyph : 0xf002,
				tooltip:Eway.locale.version.task.queryByFilter//'根据条件查找'
			},
			"->",
			{
				xtype:"combobox",
				fieldLabel: Eway.locale.version.task.displayNumPerPage,//'每页显示条数',
				store:Ext.create('Ext.data.Store', {
				    fields: ['value', 'name'],
				    data : [
				        {"value":"30", "name":"30"},
				        {"value":"40", "name":"40"},
				        {"value":"50", "name":"50"},
				        {"value":"100", "name":"100"},
				        {"value":"200", "name":"200"}
				    ]
				}),
				name:'pageSize',
				value:'30',
				queryMode: 'local',
                valueField : 'value',
                displayField: 'name',
                editable : false,
                width: 165,
                labelSeparator :''
			}],
			columns : [/* Ext.create('Ext.grid.RowNumberer'),
			{
				header : 'ID',
				dataIndex : 'id',
				width:50
			}, */{
				header : Eway.locale.refs.terminalId,//'设备编号',
				sortable : true,
				dataIndex : 'code',
				width:80
			}, {
				header : Eway.locale.refs.ip,//'IP地址',
				dataIndex : 'ip',
				sortable : true,
				width: 110
			},{
				header : Eway.locale.refs.orgName,
				dataIndex : 'orgName',
				sortable : true
			}, {
				header: Eway.locale.refs.devType,
				dataIndex : 'deviceType',
				sortable : true
			},{
				header: Eway.locale.version.View.nowVersionNo,//'当前版本',
				dataIndex : 'deviceVersion',
				sortable: true
			} ,{
				header: Eway.locale.version.task.targetVersionNo,//'目标版本',
				dataIndex : 'targetVersion',
				sortable: true
			} ,{
				header: Eway.locale.version.task.downloadStatus,//'下发状态',
				dataIndex : 'taskStatus',
				sortable: true
			} ,{
				header: Eway.locale.version.task.downloadResult,//'下发结果',
				dataIndex : 'reason',
				flex : 1
			}],
			dockedItems : [{ // 分页栏
				xtype : 'pagingtoolbar',
				store : gridStore, // same store GridPanel is using
				dock : 'bottom',
				displayInfo : true,
            	emptyMsg: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无记录'
			}]
		});

		this.callParent(arguments);
	},

	updateDeviceIdValue: function(deviceIdsField,deviceId){
		var arr = this.toArray(deviceIdsField.value);
		Ext.Array.remove(arr,deviceId.toString());
		var s = "";
		for(var i in arr){
			s = s + "," + arr[i];
		}
		deviceIdsField.setValue(s);
	},

	//s = ",1,2"
	toArray : function(s){
		var d = [];
		if(!Ext.isEmpty(s)){
			d = s.substring(1).split(",");
		}
		return d;
	},

	isRowOK : function(record){
		if(record.get('selectable') ==  false){
			return false;
		}
		return true;
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