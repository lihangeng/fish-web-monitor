Ext.define('Ext.ux.form.MonthComBox', {
	extend:'Ext.form.field.ComboBox',
	alias:'widget.monthcombox',
	config:{
		monthLimit:0,
		displayCurrentMonth:false
	},
	name:'dateMonth',
	hiddenName:'dateMonth',
	msgTarget : 'side',
	store :  Ext.create('Ext.data.Store', {
	     model : Ext.define('dateMonth', {
				     extend: 'Ext.data.Model'
				 }),
	     data : [
	             
	     ]
	}),
	listeners:{
		beforerender : function(text) {
			var clearTip = text.getTrigger("clear");
			if(undefined==clearTip){
				return;
			}else{
				clearTip.hide();
			}
			
			var str = "[" ;
			var i=this.displayCurrentMonth?0:1;
			var monthLimits=this.displayCurrentMonth?this.monthLimit:(this.monthLimit+1);
			for(;i<monthLimits;i++){
				str += "{value:'" + Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -i),'Ym')  + "',display:'" + Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -i),'Y-m')+ "'}," ;
			}
			str += "]" ;
		    
			this.store.clearData() ;
			this.store.setData(Ext.decode(str)) ;
			this.setValue(this.store.getAt(0));
		}	
	},
	 valueField : 'value',
	 displayField : 'display',
	 canClear: false,
	 queryMode : 'local',
	 editable : false,
	 emptyText : EwayLocale.combox.select
});