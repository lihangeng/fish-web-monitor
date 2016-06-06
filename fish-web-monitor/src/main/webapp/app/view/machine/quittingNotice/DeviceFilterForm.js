
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
					fieldLabel : EwayLocale.machine.atmGroup.terminalId,
					labelAlign : 'right',
					labelWidth : 50,
					width: 200,
					name : 'terminalId',
					msgTarget : 'side'
				},{
					columnWidth : .3,
					xtype : 'textfield',
					labelAlign : 'right',
					fieldLabel : EwayLocale.machine.quittingNotice.address,
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
					fieldLabel : EwayLocale.machine.atmGroup.orgName,
					emptyText : EwayLocale.combox.selects,
					name : 'orgName',
					hiddenValue : 'organization',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : Eway.user.getOrgType() != "" && Eway.user.getOrgType() == '0' ? true : false
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