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
				title : '基本信息',
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
					fieldLabel : '账号',
					value:ewayUser.getCode()
				},{
					fieldLabel : Eway.locale.commen.name,
					value:ewayUser.getName()
				},{
					fieldLabel : Eway.locale.commen.orgNameBelongs,
					name : 'organizationName'
				},{
					fieldLabel : Eway.locale.commen.mobile,
					name : 'phone'
				},{
					fieldLabel : Eway.locale.commen.email,
					name : 'mail'
				},{
					fieldLabel : Eway.locale.commen.jobNum,
					name : 'jobNum'
				},{
					fieldLabel : Eway.locale.commen.remark,
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