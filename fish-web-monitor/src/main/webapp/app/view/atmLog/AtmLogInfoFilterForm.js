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
					fieldLabel : '日志日期',
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
					fieldLabel : '所属机构 ',
					labelAlign : 'right',
					emptyText : '--请选择--',
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