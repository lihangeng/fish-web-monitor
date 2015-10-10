Ext.define('Eway.view.version.autoUpdate.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.versionupdate_filterForm',
	requires:['Eway.view.version.field.VersionTypeComboBoxAdd',
	          'Eway.view.common.OrgComboOrgTree', 'Eway.view.field.device.DeviceAtmType'],
	height : 100,
	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			items : [{
				columnWidth : .3,
				items : [ {
					xtype:'field_versionTypeComboBoxAdd',
					fieldLabel : '软件分类',
					name:'versionType'
				},{
				   xtype: 'textfield',
			       fieldLabel : '版本号',
				   name : 'versionNo',
				   allowBlank: false,
				   maxLength: 40,
				   vtype:'versionNo'
				},{
					xtype : 'combo',
					fieldLabel : '任务状态',
					name : 'taskStatus',
	                store: "version.TaskStatus",
	                queryMode: 'local',
	                valueField : 'value',
	                displayField: 'display',
	                emptyText : Eway.locale.commen.all,
	                editable : false
				}]},{
				columnWidth : .3,
				items : [{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : Eway.locale.commen.orgNameBelongs,
					emptyText : '--请选择--',
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				},{
					xtype:'field_device_deviceatmtype',
					fieldLabel : Eway.locale.commen.devTypeName,
					name: 'atmTypeId',
					editable  : false,
					store: 'machine.DeviceAtmType',
					emptyText : '--请选择--',
					mode : 'local',
					valueField : 'id',
					displayField : 'name'
				}]},{
				columnWidth : .4,
				items : [{
					xtype : 'textfield',
					fieldLabel : Eway.locale.commen.terminalId,
					name : 'terminalId',
					vtype : 'terminalId',
					allowBlank : true,
					maxLength:20,
					msgTarget : 'side'
				},{
					xtype : 'textfield',
					fieldLabel : '设备IP',
					name : 'deviceIp',
					regex : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,
					regexText : '请输入正确的IP地址',
					msgTarget : 'side'
				}]}
			 ]
		});
		this.callParent(arguments);
	}

});