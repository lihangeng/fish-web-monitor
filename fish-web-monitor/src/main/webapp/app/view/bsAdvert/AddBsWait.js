Ext.define('Eway.view.bsAdvert.AddBsWait', {
	extend : 'Ext.window.Window',
	alias : 'widget.advert_add_bsWait',

	requires : [ 'Eway.view.bsAdvert.BsWaitTab',
			'Eway.view.bsAdvert.field.AdvertGroupComBox',
			'Eway.view.bsAdvert.BsAdvertResourceConfigForm' ],

	title : EwayLocale.advert.addIdleTitle,
	modal : true,
	resizable : true,
	constrainHeader : true,
	maximizable : true,
	height : document.body.clientHeight >= 600 ? 600
			: document.body.clientHeight,
	width : 1024,
	minWidth : 600,
	minHeight : 400,
	autoScroll : true,
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'south',
				padding : '1px 0px 0px 0px',
				dockedItems : [ {
					buttonAlign : 'center',
					buttons : [ {
						text : EwayLocale.cases.confirm,
						action : 'confirm'
					}, {
						text : EwayLocale.cases.cancel,
						handler : this.onOver
					}, {
						text : EwayLocale.advert.idleAdvertUpTipsInfo,
						xtype : 'tbtext'
					} ]
				} ]
			}, {
				region : 'north',
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 10px 10px',
				trackResetOnLoad : true,
				fieldDefaults : {
					labelAlign : 'right',
					msgTarget : 'side',
					grow : false
				},
				items : [ {
					xtype : 'fieldset',
					checkboxToggle : false,
					title : EwayLocale.advert.advertBasicInfo,
					collapsed : false,
					items : [ {
					layout : 'column',
					height : 80,
					defaults : {
						anchor : '95%',
						style : 'padding-top:5px',
						labelAlign : 'right'
					},
					items : [ {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '95%',
							style : 'padding-top:5px',
							labelAlign : 'right'
						},
						items : [ {
							xtype : 'field_advert_advertGroup',
							allowBlank : false,
							//广告组
							fieldLabel : '<font color="red">*</font>'+EwayLocale.bsAdvert.advertGroup
						}, {
							xtype : 'displayfield',
							fieldLabel : EwayLocale.advert.type,
							name : 'advertType',
							value : EwayLocale.advert.idleAdvertInfo
						}, {
							xtype : 'hidden',
							name : 'id'
						} ]
					}, {

						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '95%',
							style : 'padding-top:5px',
							labelAlign : 'right'
						},
						items : [ {
							xtype : 'textfield',
							name : 'advertName',
							maxLength : 20,
							allowBlank : false,
							//广告名称
							fieldLabel : '<font color="red">*</font>'+EwayLocale.bsAdvert.advertName
						} ]
					} ]
					} ]
				} ]
			}, {
				region : 'center',
				xtype : 'advert_bs_waitTab',
				autoScroll : true,
				plain : true,
				padding : '2px 0px 0px 0px'
			}, {
				region : 'east',
				xtype : 'advert_bs_resourceConfigForm',
				padding : '2px 0px 0px 2px'
			} ]
		});
		this.callParent(arguments);
	},

	onReset : function() {
		this.up("window").down('form').getForm().reset();
	},

	onOver : function() {
		this.up('window').close();
	}
});