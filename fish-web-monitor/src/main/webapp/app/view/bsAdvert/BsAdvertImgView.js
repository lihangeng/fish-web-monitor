Ext.define('Eway.view.bsAdvert.BsAdvertImgView', {
    extend: 'Ext.view.View',
    alias : 'widget.bsadvertimgview',
    requires: ['Ext.data.Store','Eway.model.bsAdvert.BsAdvertResource'],

    tpl: [
        '<tpl for=".">',
            '<div class="thumb-wrap">',
             	'<div class="remove_adevert_res"><img src="resources/images/delete.gif" title="'+EwayLocale.advert.deleteAdvertResource+'"></div>',
                '<div class="thumb">',
                    '<img src="{path}" style="cursor:pointer" title="{displayName}">',
                '</div>',
                '<span>{shortName:htmlEncode}</span>',
            '</div>',
        '</tpl>'
    ],

	autoScroll : true,
	// 解决IE7,8下不出现滚动条问题,由于extjs会对ie7,8特殊处理
	// autoScroll属性会作用在其他div上,所以需要直接写css来显示滚动
	style : 'overflow:auto;',
	frame : true,

    itemSelector: 'div.thumb-wrap',
    multiSelect: false,
    singleSelect: true,
    trackOver: true,
    cls: 'advert-image-view',
   	overItemCls: 'advert-item-over',
   	selectedItemCls :'advert-item-selected',
//   	itemSelector: 'div.moveimg',  
    prepareData: function(data) {
            Ext.apply(data, {
                shortName: Ext.util.Format.ellipsis(data.displayName, 23)
            });
            return data;
        },
    initComponent: function() {
        this.store = Ext.create('Ext.data.Store', {
            autoLoad: false,
            model:'Eway.model.bsAdvert.BsAdvertResource',
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
	    }
	    ,
	    render:function (v) {  
	    	var me = this;
	    	var dragPicWidth = 148;
	    	var dragPicHeight = 108;
	    	var defaultIndex = 0;
	    	var srcTop = 0;
	    	var srcLeft = 0;
	        v.dragZone = Ext.create('Ext.dd.DragZone', v.getEl(), {  
	            getDragData: function(e) {  
	                var sourceEl = e.getTarget(v.itemSelector, 10), d;  
	                if (sourceEl) {  
	                	srcLeft = sourceEl.offsetLeft;
	                	srcTop = sourceEl.offsetTop;
//	                	dragPicWidth = sourceEl.offsetWidth;
//	                	dragPicHeight = sourceEl.offsetHeight;
	                    d = sourceEl.cloneNode(true);  
	                    d.id = Ext.id();
	                    d.childNodes[1].childNodes[0].style.width=dragPicWidth+"px";
	                   	d.childNodes[1].childNodes[0].style.height=dragPicHeight+"px";
	                    return {  
	                        sourceEl: sourceEl,  
	                        repairXY:Ext.fly(sourceEl).getXY(),  
	                        ddel: d,
	                        patientData: v.getRecord(sourceEl).data  
	                    };  
	                }  
	            },  
	      
	            getRepairXY: function() {  
	                return this.dragData.repairXY;  
	            }  
	        });  
	          
	        var panel = this;  
	        panel.dropZone = Ext.create('Ext.dd.DropZone', v.el, {  
	            getTargetFromEvent: function(e) {
	            	v.dragZone.setDelta(100,60);
	            	
	                return e.getTarget('div');  
	            },  
	      
	            onNodeEnter : function(target, dd, e, data){  
	            },  
	      
	            onNodeOut : function(target, dd, e, data){ 
	            },  
	            onNodeOver : function(target, dd, e, data){  
	            	var movTarget = Ext.get(target);
	                var proto = Ext.dd.DropZone.prototype;  
	                return proto.dropAllowed;  
	            },
	            onNodeDrop : function(target, dd, e, data){
	            	
	            	var disY=v.dragZone.lastPageY-v.dragZone.startPageY;
	            	var disX=v.dragZone.lastPageX-v.dragZone.startPageX;
	            	var hMult=disY/dragPicHeight;
	            	var wMult=disX/dragPicWidth;
	            	var rows;
	            	var cols;
	            	if(hMult<=0){
	            		rows = 0;
	            	}
	            	else{
	            		rows = Math.round(hMult)
	            	}
	            	if(wMult<=0){
	            		cols = 0;
	            	}
	            	else{
	            		cols=Math.round(wMult)
	            	}
	            	defaultIndex = rows*3+cols;
	            	var moveIndex=0,counter=0;
	            	Ext.Array.forEach(panel.store.data.items,function(item,index,opt){
	            		if(item.data==data.patientData){
	            			moveIndex= counter;
	            		}
	            		counter++;
	            	});
	            	defaultIndex>=moveIndex?defaultIndex++:defaultIndex;
	                var name = data.patientData.name;  

//                    panel.store.remove(data.patientData);    
	                var comFlag = Ext.getCmp(data.patientData.id);  
	      
//	                    panel.store.add(data.patientData);  
	                     panel.store.insert(defaultIndex,data.patientData);     
	      
	                return true;  
	            }  
	        });  
	      
	    }
    },
    removeAdvertRes : function(){
    	if(selectedRecord != null){
    		var me = this;
    		if('0'!=selectedRecord.get('id')&&Ext.isNumeric(selectedRecord.get('id'))){
	    		Ext.Ajax.request({
					url :"api/bsadvert/advert/deleteResource",
					params: {
						id: selectedRecord.get('id')
				    },
					success: function(response){
						var obj = Ext.decode(response.responseText);
				        if(obj.success==true){
				    		me.removeStoreData(selectedRecord);
				        }
				        else{
							Eway.alert(obj.errorMsg);
				        }
				    },
				    failure:function(){
				    	Eway.alert("删除失败！");
				    }
				});
    		}
    		else{
    			me.removeStoreData(selectedRecord);
    		}
    	}
    }

});