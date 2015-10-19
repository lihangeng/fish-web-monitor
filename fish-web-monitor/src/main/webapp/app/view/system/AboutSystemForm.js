Ext.define('Eway.view.system.AboutSystemForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.system_form',
	initComponent : function(){
		Ext.apply(this, {
			width :'75%',
			height:500,
			title :Eway.locale.system.aboutSystem,
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
					html : '<p>'+Eway.locale.system.softwareName+'：<font size="5" face="Times"><b>'+Eway.locale.system.ATMV+'</b></font></p>'

							+ '<p>'+Eway.locale.system.softwareVersion+'：<font size="3" face="Times"><b>fish-web-base-1.1.0</b></font></p>'

							+ '<p>'+Eway.locale.system.innerVersion+'：<font size="3" face="Times"><b>fish-web-base-1.1.0 build $Rev: 19698 $</b></font></p>'

							+ '<p>'+Eway.locale.system.copyRight+'</p>'

							+ '<p>'+Eway.locale.system.introduction+'<font size="2" face="Times">'+Eway.locale.system.introductionA
							+ Eway.locale.system.introductionB
							+ Eway.locale.system.introductionC+'</font></p>'
				}]
			}]

		});
		this.callParent(arguments);
		}
});