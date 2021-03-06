Ext.define('Eway.view.bsAdvert.BsWaitTab', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.advert_bs_waitTab',
	requires : ['Eway.view.bsAdvert.BsAdvertImgView'],
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
				    	blankText :EwayLocale.bsAdvert.uploadResourceBlank,
						buttonText: EwayLocale.bsAdvert.addMorePic,
						padding:'2px -60px 0 0',
						regex : (/\.jpg$|\.gif$/i),
						regexText:EwayLocale.bsAdvert.uploadRegText
			    	}]
				},{
					region:'center',
					xtype:'bsadvertimgview',
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
				    	blankText :EwayLocale.bsAdvert.uploadResourceBlank,
						buttonText: EwayLocale.bsAdvert.addMorePic,
						padding:'2px -60px 0 0',
						regex : (/\.jpg$|\.gif$/i),
						regexText:EwayLocale.bsAdvert.uploadRegText
			    	}]
				},{
					region:'center',
					xtype:'bsadvertimgview',
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
				    	blankText :EwayLocale.bsAdvert.uploadResourceBlank,
						buttonText: EwayLocale.bsAdvert.addMorePic,
						padding:'2px -60px 0 0',
						regex :(/\.jpg$|\.gif$/i),
						regexText:EwayLocale.bsAdvert.uploadRegText
			    	}]
				},{
					region:'center',
					xtype:'bsadvertimgview',
					name:'600'
				}]
			}]


		});
		this.callParent(arguments);
	}
});