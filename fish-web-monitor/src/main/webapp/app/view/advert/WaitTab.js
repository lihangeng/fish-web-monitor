Ext.define('Eway.view.advert.WaitTab', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.advert_waitTab',
	requires : ['Eway.view.advert.AdvertImgView'],
	initComponent : function() {
		Ext.apply(this, {
			defaults:{
				xtype: 'panel'
			},
			items:[{
				title:'1024',
				layout: 'border',
				items:[{
					region:'north',
					xtype:'form',
					border:false,
					layout:'hbox',
		        	items:[{
		        		xtype:'displayfield',
		        		name:'tip',
		        		hideLabel:true
		        	},{xtype: 'tbfill'},{
				    	xtype: 'filefield',
						buttonOnly:true,
						name: 'file',
						allowBlank: false,
				    	blankText :EwayLocale.advert.uploadResourceBlank,
						buttonText: EwayLocale.advert.addMorePic,
						padding:'2px 5px 0 0',
						regex : /[\.(jpg)|\.(gif)]$/i,
						regexText:EwayLocale.advert.uploadRegText
			    	}]
				},{
					region:'center',
					xtype:'advertimgview',
					name:'1024'
				}]
			},{
				title:'800',
				layout: 'border',
				items:[{
					region:'north',
					xtype:'form',
					border:false,
					layout:'hbox',
		        	items:[{
		        		xtype:'displayfield',
		        		name:'tip',
		        		hideLabel:true
		        	},{xtype: 'tbfill'},{
				    	xtype: 'filefield',
						buttonOnly:true,
						name: 'file',
						allowBlank: false,
				    	blankText :EwayLocale.advert.uploadResourceBlank,
						buttonText: EwayLocale.advert.addMorePic,
						padding:'2px 5px 0 0',
						regex : /[\.(jpg)|\.(gif)]$/i,
						regexText:EwayLocale.advert.uploadRegText
			    	}]
				},{
					region:'center',
					xtype:'advertimgview',
					name:'800'
				}]
			},{
				title:'600',
				layout: 'border',
				items:[{
					region:'north',
					xtype:'form',
					border:false,
					layout:'hbox',
		        	items:[{
		        		xtype:'displayfield',
		        		name:'tip',
		        		hideLabel:true
		        	},{xtype: 'tbfill'},{
				    	xtype: 'filefield',
						buttonOnly:true,
						name: 'file',
						allowBlank: false,
				    	blankText :EwayLocale.advert.uploadResourceBlank,
						buttonText: EwayLocale.advert.addMorePic,
						padding:'2px 5px 0 0',
						regex : /[\.(jpg)|\.(gif)]$/i,
						regexText:EwayLocale.advert.uploadRegText
			    	}]
				},{
					region:'center',
					xtype:'advertimgview',
					name:'600'
				}]
			}]


		});
		this.callParent(arguments);
	}
});