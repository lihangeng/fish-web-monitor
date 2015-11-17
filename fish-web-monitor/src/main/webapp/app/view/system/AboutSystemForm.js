Ext.define('Eway.view.system.AboutSystemForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.system_form',
	initComponent : function(){
		Ext.apply(this, {
			width :'75%',
			height:500,
			title :EwayLocale.system.aboutSystem,
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
					html : '<p>'+EwayLocale.system.softwareName+'：<font size="5" face="Times"><b>'+EwayLocale.system.ATMV+'</b></font></p>'

							+ '<p>'+EwayLocale.system.softwareVersion+'：<font size="3" face="Times"><b>fish-web-base-1.1.0</b></font></p>'

							+ '<p>'+EwayLocale.system.innerVersion+'：<font size="3" face="Times"><b>fish-web-base-1.1.0 build $Rev: 19698 $</b></font></p>'

							+ '<p>'+EwayLocale.system.copyRight+'</p>'

							+ '<p>'+EwayLocale.system.introduction+'<font size="2" face="Times">'+EwayLocale.system.introductionA
							+ EwayLocale.system.introductionB
							+ EwayLocale.system.introductionC+'</font></p>'
				}]
			}]

		});
		this.callParent(arguments);
		}
});