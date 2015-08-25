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
					fieldLabel : '姓名',
					value:ewayUser.getName()
				},{
					fieldLabel : '所属机构',
					name : 'organizationName'
				},{
					fieldLabel : '手机号码',
					name : 'phone'
				},{
					fieldLabel : '邮箱',
					name : 'mail'
				},{
					fieldLabel : '工号',
					name : 'jobNum'
				},{
					fieldLabel : '备注',
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