
Ext.define('Eway.view.advert.AddTrans', {
	extend : 'Ext.window.Window',
	alias : 'widget.advert_addTrans',

	requires : [ 'Eway.view.advert.field.TransResourceFieldSet'],

	title : '增加交易页面广告信息',
	modal : true,
	resizable : false,
	constrainHeader : true,
	width : 600,
	height: document.body.clientHeight >= 700 ? 700 : document.body.clientHeight,
	autoScroll : true,

	initComponent : function() {
		Ext.apply(this, {
			dockedItems: [{
		        xtype: 'toolbar',
		        dock: 'top',
		        items: [{
					text : '保存',
					action : 'confirm',
					id : "savaAdvert",
					iconCls:'db-save'
				}/*, {
					text : '重置',
					handler : this.onReset
				}, {
					text : '返回',
					handler : this.onOver
				}*/,"->",{
					text : '再增加一个广告资源',
					action : 'addMore',
					iconCls:'addBtn'
				}]
		    }],
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 10px 10px',
				trackResetOnLoad : true,
				fieldDefaults : {
					labelWidth : 90,
					labelAlign : 'right',
					msgTarget : 'side'
				},
				items : [{
					 	xtype:'fieldset',
			            checkboxToggle:false,
			            title: '广告基本信息',
			            defaultType: 'combobox',
			            collapsed: false,
			            layout: 'anchor',
			            height:80,
			            defaults: {
			                anchor: '100%'
			            },
			            items :[{
			            	xtype: 'displayfield',
			            	fieldLabel: '广告类型',
			            	name : 'advertType',
			            	value: '交易页面广告'
			            },{
					    	xtype: 'container',
					        msgTarget: 'under',
					        layout:'hbox',
					        defaultType: 'combobox',
					        items:[{
					                fieldLabel: '下发方式',
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
					                fieldLabel: '广告有效期',
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
					xtype:'field_transResourceFieldSet',
					title: '交易页面广告资源配置'
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