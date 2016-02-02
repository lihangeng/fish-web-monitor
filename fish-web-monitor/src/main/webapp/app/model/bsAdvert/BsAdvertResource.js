Ext.define('Eway.model.bsAdvert.BsAdvertResource',{
	extend : 'Ext.data.Model',
    fields: [{name:'id',type:'string'},'fileName', 'originalFileName','displayName','path',
    		'playTime','beginDate','endDate',/*'beginTime','endTime',*/
    		'beginHour','beginMinute','beginSecond','endHour','endMinute','endSecond','screen'],
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
		wirter : {
			type : 'json'
		}
    },

    getBeginTime: function(){
    	return this.get('beginHour')+":"+this.get('beginMinute') +":"+this.get("beginSecond");
    },

    setBeginTime: function(){

    },

    getEndTime : function(){
		return this.get('endHour')+":"+this.get('endMinute') +":"+this.get("endSecond");
    },

    setEndTime : function(){

    },

    getBeginDate: function(){
    	return Ext.Date.format(this.data.beginDate,"Y-m-d");
    },

    getEndDate : function(){
    	if(!Ext.isEmpty(this.data.endDate)){
    		return Ext.Date.format(this.data.endDate,'Y-m-d');
    	}
    },

    convertToAdvertResource: function(){
    	var me = this;
		return Ext.create("Eway.model.bsAdvert.BsAdvertResource",{
				playTime: me.data.playTime,
				beginDate:me.data.beginDate,
				endDate:me.data.endDate,
				beginTime : me.getBeginTime(),
				endTime : me.getEndTime(),
				content: me.data.fileName,
				screen:"SCREEN_"+me.data.screen
			});
    }

});