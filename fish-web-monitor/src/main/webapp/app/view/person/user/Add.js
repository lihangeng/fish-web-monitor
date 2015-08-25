
Ext.define('Eway.view.person.user.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.user_add',

	requires: ['Eway.view.field.person.Code',
	           'Eway.view.field.person.UserState',
	           'Eway.view.field.person.Gender',
	           'Eway.view.field.person.UserName',
	           'Eway.view.person.user.Role'],

	title: '增加用户信息',
	modal: true,
	resizable: false,
	constrainHeader: true,
	layout: 'border',
	width: 700,
	height: 450,
	initComponent: function() {
		Ext.apply(this, {
			items :[ {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				region: 'north',
        		height: 100,
				defaults: {
					labelWidth: 60,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
			        xtype: 'fieldcontainer',
			        fieldLabel: '<font color="red">*</font>姓名',
			        combineErrors: true,
			        layout: 'hbox',
			        defaults: {
			            hideLabel: true
			        },
			        items: [{
						xtype : 'field.username',
						fieldLabel : '<font color="red">*</font> 姓名',
						emptyText:"请点击查询，选择人员",
						allowBlank : false,
						readOnly : true
					},{
				        xtype: 'button',
						glyph : 0xf002,
				        name: 'personAddButton',
				        width: 30,
				        margin: '0 0 0 5'
					}]
				},{
					fieldLabel : '<font color="red">*</font> 用户名',
					xtype : 'field.code',
					regex: /^[a-zA-Z0-9][a-zA-Z0-9-_\.]{0,19}$/,
					regexText:'只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！',
					allowBlank : false
				},{
				    xtype : 'field.userState',
				    fieldLabel : '<font color="red">*</font> 状态',
				    allowBlank : false,
				    value : '0',
				    hidden : true,
					editable : false
				},{
					xtype : 'field.gender',
					allowBlank : false,
					editable : false,
					readOnly: true,
					hidden : true
				},{
					xtype : 'textfield',
					name : 'userGuid',
					hidden : true
				}]
			},{
				title:'角色赋予',
				xtype: 'user_role',
				region: 'center',
				autoLoadStore : false
			}],
			buttonAlign : 'center',
			buttons: [{
					text: '确认',
					action: 'confirm'
				}, {
					text: '取消',
					handler: this.onOver
			}]
		});

		this.callParent(arguments);
	},

	onOver: function() {
		this.up('window').close();
	}
});