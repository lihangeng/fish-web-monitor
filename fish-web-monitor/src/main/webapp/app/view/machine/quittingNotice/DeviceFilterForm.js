
Ext.define('Eway.view.machine.quittingNotice.DeviceFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.quittingNotice_DeviceFilterForm',

	requires : [ 'Eway.view.common.OrgComboOrgTree','Eway.lib.Util' ],
	layout : 'column',
	initComponent: function() {
		Ext.apply(this, {

			items : [{
				columnWidth : .3,
				items : [{
					xtype : 'textfield',
					fieldLabel : '设备号',
					labelAlign : 'right',
					labelWidth : 50,
					width: 200,
					name : 'terminalId',
					vtype : 'terminalId',
					maxLength:20,
					msgTarget : 'side'
				},{
					columnWidth : .3,
					xtype : 'textfield',
					labelAlign : 'right',
					fieldLabel : '所属地址',
					labelWidth : 60,
					width: 200,
					name : 'address',
					msgTarget : 'side'
				}]
			},{
				columnWidth : .3,
				items : [{
					labelWidth : 60,
					labelAlign : 'right',
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '所属机构',
					emptyText : '--请选择--',
					name : 'orgName',
					hiddenValue : 'organization',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'organization'
				}]
			}]
		});

		this.callParent(arguments);
	}
});