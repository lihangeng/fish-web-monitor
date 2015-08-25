Ext.define('Eway.view.personal.PersonalSettings', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.personalSettings',

	requires : ['Eway.view.personal.PersonalSettingsModel','Eway.view.personal.PersonalSettingsController',
	            'Eway.view.personal.UpdatePwd','Eway.view.personal.PersonalInfo'],
	
	controller : 'personalSettings',
	// MVVM架构的控制器的名称，会在当前路径中根据‘personalSettings’ + Controller 来确定文件名
	// 这个我没找到其他任何可以自动加载PersonalSettingsController.js的依据，只能作上面的判断了
	viewModel : {
		type : 'personalSettings'
		// MVVM架构的viewModel的类型，会在当前路径中根据‘personalSettings’ + Model 来确定文件名
	},

	title : '个人设置',
	layout : 'border',
	closable:true,
	initComponent : function() {
		Ext.apply(this, { 
			items : [ {
					region: 'center',
					xtype: 'tabpanel',
					height: '50%',
					plain:true,
					tabPosition : 'bottom',
					headerPosition: 'bottom',
					split: true,
					items:	[{
						title: '个人信息',
						xtype:'personalInfo',
						listeners: {
							beforerender : 'onBeforeRender',
							scope : 'controller'
						}
					},{
						title: '修改密码',
						xtype:'updatePwd'
					}]
				} ]
		});

		this.callParent(arguments);
	}
});