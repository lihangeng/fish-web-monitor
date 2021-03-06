
Ext.define('Eway.view.advert.AddText', {
	extend : 'Ext.window.Window',
	alias : 'widget.advert_addText',

	requires : [ 'Eway.view.advert.field.TextResourceFieldSet'],

	title : EwayLocale.advert.addTextTitle,
	modal : true,
	resizable : false,
	constrainHeader : true,
	width : 600,
	height: 445,
	maxHeight : 800,
	autoScroll : true,

	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 40px 10px',
				trackResetOnLoad : true,
				fieldDefaults : {
					labelAlign : 'right',
					msgTarget : 'side',
					grow : false
				},
				items : [{
					 	xtype:'fieldset',
			            checkboxToggle:false,
			            title: EwayLocale.advert.advertBasicInfo,
			            defaultType: 'combobox',
			            collapsed: false,
			            layout: 'anchor',
			            height:80,
			            defaults: {
			                anchor: '100%'
			            },
			            items :[{
			            	xtype: 'displayfield',
			            	fieldLabel: EwayLocale.advert.type,
			            	name : 'advertType',
			            	value: EwayLocale.advert.advertTypeText
			            },{
					    	xtype: 'container',
					        msgTarget: 'under',
					        layout:'hbox',
					        defaultType: 'combobox',
					        items:[{
				                fieldLabel: EwayLocale.advert.downType,
				                displayField: 'display',
				                store: Ext.StoreMgr.lookup("advert.AdvertDownMethod"),
				                queryMode: 'local',
				                valueField : 'value',
				                value:'COVER',
				                name:'advertDownMethod',
				                editable : false,
				                forceSelection: true,//强制选择一个,不起作用
				                width:250
				            },{
				                fieldLabel: EwayLocale.advert.validity,
				                displayField: 'display',
				                store: Ext.StoreMgr.lookup("advert.AdvertValidity"),
				                queryMode: 'local',
				                valueField : 'value',
				                name:'advertValidity',
				                hidden:true,
				                editable : false,
				                value:'ALWAYS',
				                width:250
				            }]
			            }]
				},{
					xtype:'field_textResourceFieldSet',
					title: EwayLocale.advert.textAdvertResConfig,
					height:218
				}],
				buttonAlign : 'center',
				buttons : [ {
					text : EwayLocale.cases.confirm,
					id:'savaAdvert',
					action : 'confirm'
				}, {
					text : EwayLocale.cases.cancel,
					handler : this.onOver
				}]
			}
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