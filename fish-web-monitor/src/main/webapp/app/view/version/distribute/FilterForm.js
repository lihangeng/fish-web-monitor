Ext.define('Eway.view.version.distribute.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.distribute_filterForm',
	requires:['Eway.view.version.field.VersionTypeComboBoxAdd',
	          'Eway.view.common.OrgComboOrgTree'],
	height : 50,
	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			items : [{
				columnWidth : .5,
				items : [ {
					xtype:'field_versionTypeComboBoxAdd',
					fieldLabel : '软件分类',
					selectFirst:true,
					name:'versionType'
//				}]},{
//				columnWidth : .5,
//				items : [{
//					style : 'padding-top:0px',
//					xtype : 'hiddenfield',
//					name : 'orgId'
//				}, {
//					xtype : 'common_orgComboOrgTree',
//					fieldLabel : '所属机构',
//					emptyText : '--请选择--',
//					name : 'orgName',
//					hiddenValue : 'orgId',
//					editable : false,
//					filters : '{"type" : "0"}',
//					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				}]}
			 ]
		});
		this.callParent(arguments);
	}

});