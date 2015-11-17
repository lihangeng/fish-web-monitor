
Ext.define('Eway.view.advert.AddWait', {
	extend : 'Ext.window.Window',
	alias : 'widget.advert_addWait',

	requires : ['Eway.view.advert.WaitTab','Eway.view.advert.AdvertResourceConfigForm'],

	title : EwayLocale.advert.addIdleTitle,
	modal : true,
	resizable :  true,
	constrainHeader : true,
	maximizable: true,
	height: document.body.clientHeight >= 600 ? 600 : document.body.clientHeight,
	width : 1024,
	minWidth: 600,
    minHeight: 400,
	autoScroll : true,
	layout:'border',

	initComponent : function() {
		Ext.apply(this, {
			items: [{
						region : 'south',
						padding: '1px 0px 0px 0px',
						dockedItems : [{
						buttonAlign : 'center',
						buttons : [{
								text : EwayLocale.cases.confirm,
								action : 'confirm'
							},{
								text : EwayLocale.cases.cancel,
								handler : this.onOver
							}, {
					        	text : EwayLocale.advert.idleAdvertUpTipsInfo,
					        	xtype:'tbtext'
					        }]
					}]
					},{
						region:'north',
						xtype : 'form',
						bodyStyle : 'padding: 10px 10px 10px 10px',
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
				            	value: EwayLocale.advert.idleAdvertInfo
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
					                forceSelection: true//强制选择一个,不起作用
					            },{
					                fieldLabel: EwayLocale.advert.validity,
					                displayField: 'display',
					                store: Ext.StoreMgr.lookup("advert.AdvertValidity"),
					                queryMode: 'local',
					                valueField : 'value',
					                name:'advertValidity',
					                editable : false,
					                value:'ALWAYS'
					         	}]
				        	 }]
				         }]
					}
					,{
						region:'center',
						xtype:'advert_waitTab',
						autoScroll:true,
						plain: true,
						/*tabPosition :'left',*/
						padding: '2px 0px 0px 0px'
				},{
					region:'east',
					xtype:'advert_resourceConfigForm',
					padding: '2px 0px 0px 2px'
				}
			]
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