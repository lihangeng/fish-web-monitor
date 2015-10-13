
Ext.define('Eway.view.advert.AddAnnoucement', {
	extend : 'Ext.window.Window',
	alias : 'widget.advert_addAnnoucement',

	requires : [ 'Eway.view.advert.field.AnnoucementFieldSet'],

	title : Eway.locale.advert.addAnnoucementTitle,//'增加公告信息',
	modal : true,
	resizable : false,
	constrainHeader : true,
	width : 600,
	height: 500,
	maxHeight : 800,
	autoScroll : true,

	initComponent : function() {
		Ext.apply(this, {
			dockedItems: [{
		        xtype: 'toolbar',
		        dock: 'top',
//		        ui: 'footer',
		        items: [{
					text : Eway.locale.button.save,//'保存',
					action : 'confirm',
					id : "savaAdvert",
					iconCls:'db-save'
				}/*, {
					text : '重置',
					handler : this.onReset
				}, {
					text : '返回',
					handler : this.onOver
				}*//*,"->",{
					text : '再增加一个广告资源',
					action : 'addMore'
				}*/]
		    }],
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
			            title: Eway.locale.advert.annoucementBasicInfo,//'公告基本信息',
			            defaultType: 'combobox',
			            collapsed: false,
			            height:80,
			            layout: 'anchor',
			            defaults: {
			                anchor: '100%'
			            },
			            items :[{
			            	xtype: 'displayfield',
			            	fieldLabel:  Eway.locale.advert.type,//'广告类型',
			            	name : 'advertType',
			            	value: Eway.locale.advert.annoucementInfoAdvert//'公告'
			            },{
					    	xtype: 'container',
					        msgTarget: 'under',
					        layout:'hbox',
					        defaultType: 'combobox',
					        items:[{
				                fieldLabel: Eway.locale.advert.downType,//'下发方式',
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
				                fieldLabel:  Eway.locale.advert.advertValidity,//'广告有效期',
				                displayField: 'display',
				                store: Ext.StoreMgr.lookup("advert.AdvertValidity"),
				                queryMode: 'local',
				                valueField : 'value',
				                name:'advertValidity',
				                editable : false,
				                value:'ALWAYS',
				                width:250
				            }]
			            }]
				},{
					xtype:'field_annoucementFieldSet',
					title: Eway.locale.advert.annoucementAdvertResConfig//'公告资源配置'
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