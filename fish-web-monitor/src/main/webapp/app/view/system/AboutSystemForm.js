Ext.define('Eway.view.system.AboutSystemForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.system_form',
	initComponent : function(){
		Ext.apply(this, {
			width :'75%',
			height:500,
			title :'关于系统',
			header: false,
			layout:'fit',
			items : [{
				layout : 'border',
				region : 'center',
				border : false,
				xtype : 'panel',
				items : [{
					region : 'north',
					height : 100,
					border : false,
					xtype : 'panel',
					html : '<br>'
							+ '<br>'
							+ '<p><img src ="././resources/images/logo.jpg"></p>'
				}, {
					region : 'center',
					xtype : 'panel',
					border : false,
					html : '<p>软件名称：<font size="5" face="Times"><b>金融自助设备集中监控系统</b></font></p>'

							+ '<p>软件版本：<font size="3" face="Times"><b>fish-web-base-1.1.0</b></font></p>'

							+ '<p>内部版本号：<font size="3" face="Times"><b>fish-web-base-1.1.0 build $Rev: 19698 $</b></font></p>'

							+ '<p>版权信息：&copy;深圳市怡化电脑有限公司 版权所有</p>'

							+ '<p>系统简介：<font size="2" face="Times">本系统是监控系统的基础功能有ATM信息管理、'
							+ '自动化版本分发管理、 ATM设备监控等功能。通过这些功能，各大银行可以集中管理ATM设备信息  监视 远程的ATM，对远程ATM机器上的软件升'
							+ '级和软件维护，方便了各大银 行对自助设备进行高效的管理和维护。</font></p>'
				}]
			}]

		});
		this.callParent(arguments);
		}
});