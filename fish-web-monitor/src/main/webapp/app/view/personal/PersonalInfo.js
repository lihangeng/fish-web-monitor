Ext.define('Eway.view.personal.PersonalInfo', {
	extend : 'Ext.form.Panel',
	alias : 'widget.personalInfo',
	
	layout: {
        type: 'anchor'
	},
	
	bodyStyle : 'padding: 15px 30px 10px 30px',
	
	initComponent: function() {
		Ext.apply(this,{
			items : [ {
				xtype : 'fieldset',
				title : EwayLocale.personal.baseInfo,
				collapsible : true,
				defaults : {
					border : false,
					width: 500,
					labelWidth: 100,
					labelAlign: 'right',
					msgTarget : 'side',
					xtype: 'textfield',
					readOnly: true
				},
				items: [{
					fieldLabel : EwayLocale.personal.accountNum,
					value:ewayUser.getCode()
				},{
					fieldLabel : EwayLocale.commen.name,
					value:ewayUser.getName()
				},{
					fieldLabel : EwayLocale.commen.orgNameBelongs,
					name : 'organizationName'
				},{
					fieldLabel : EwayLocale.commen.mobile,
					name : 'phone'
				},{
					fieldLabel : EwayLocale.commen.email,
					name : 'email'
				},{
					fieldLabel : EwayLocale.commen.jobNum,
					name : 'jobNum'
				},{
					fieldLabel : EwayLocale.commen.remark,
					name : 'remark'
				}]
			}/*,{
				xtype : 'fieldset',
				title : '角色权限',
				collapsible : true,
				items: [{
					xtype: 'grid',
					store : 
					columns : [{
						header : '角色名称',
						dataIndex : 'role'
					}]
				}]
			}*/]
		});
		
		this.callParent(arguments);
	}
});