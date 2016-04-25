Ext.define('Eway.view.parameter.template.AdvertImgView', {
    extend: 'Ext.view.View',
    alias : 'widget.templateDetailView',
    requires: ['Ext.data.Store','Eway.model.parameter.template.TemplateResource'],

    tpl: [
        '<tpl for=".">',
            '<div class="thumb-wrap">',
             	'<div class="remove_adevert_res"><img src="resources/images/delete.gif" title="'+EwayLocale.advert.deleteAdvertResource+'"></div>',
                '<div class="thumb">',
                    '<img src="{path}" title="{displayName}">',
                '</div>',
                '<span>{shortName:htmlEncode}</span>',
            '</div>',
        '</tpl>'
    ],

	autoScroll : true,

	style : 'overflow:auto;',
	frame : true,
 
    itemSelector: 'div.thumb-wrap',
    multiSelect: false,
    singleSelect: true,
    trackOver: true,
    cls: 'advert-image-view',
   	overItemCls: 'advert-item-over',
   	selectedItemCls :'advert-item-selected',

    prepareData: function(data) {
            Ext.apply(data, {
                shortName: Ext.util.Format.ellipsis(data.displayName, 23)
            });
            return data;
        },

    initComponent: function() {
        this.store = Ext.create('Ext.data.Store', {
            autoLoad: false,
            model:'Eway.model.parameter.template.TemplateResource',
            autoSync: false
        });

        this.callParent();
    },

    updateStoreData : function(record){
    	this.store.add(record);
    },

    removeStoreData : function(record){
    	this.store.remove(record);
    },

    selectedRecord : null,

    listeners: {
	    itemmouseenter : function(me, record, itemHtml, index, e){
	    	var winEl = Ext.get(itemHtml);
		    var imgHtml = winEl.down('div.remove_adevert_res');
		    imgHtml.down('img').on("click",this.removeAdvertRes,this,{single:true});
		    selectedRecord = record;
		    imgHtml.show();
	    },
	    itemmouseleave :function(me, record, itemHtml, index, e){
	    	var winEl = Ext.get(itemHtml);
		    var imgHtml = winEl.down('div.remove_adevert_res');
		    selectedRecord = null;
		    imgHtml.hide();
	    }//,
//	    itemclick : function(me,record,itemHtml,index,e){
//	    	return false;
//	    }
    },
    removeAdvertRes : function(){
    	if(selectedRecord != null){
    		this.removeStoreData(selectedRecord);
    	}
    }

});