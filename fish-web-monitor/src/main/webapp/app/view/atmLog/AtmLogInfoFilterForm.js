Ext.define('Eway.view.atmLog.AtmLogInfoFilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.atmLog_AtmLogInfoFilterForm',
	
	layout : 'column',
	
	requires : ['Eway.view.common.OrgComboOrgTree'],
	
	initComponent : function(){
		Ext.apply(this,{
			
			items : [{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : EwayLocale.atmLog.logDate,
					name : 'backupDate',
					editable : false,
					format : 'Y-m-d'
				}]
			},{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.commen.orgNameBelongs,
					labelAlign : 'right',
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
					}]
			}]
		});
		this.callParent(arguments);	
	}
	
});