Ext.define('Ext.ux.form.MonthComBox', {
	extend:'Ext.form.field.ComboBox',
	alias:'widget.MonthComBox',
	
	name:'dateMonth',
	hiddenName:'dateMonth',
	msgTarget : 'side',
	store :  Ext.create('Ext.data.Store', {
	     model : Ext.define('dateMonth', {
				     extend: 'Ext.data.Model'
				 }),
	     data : [
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -1),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -1),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -2),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -2),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -3),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -3),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -4),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -4),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -5),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -5),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -6),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -6),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -7),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -7),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -8),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -8),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -9),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -9),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -10),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -10),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -11),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -11),'Y-m')},
               {value:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -12),'Ym'),display:Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -12),'Y-m')},
	     ]
	}),
	listeners:{
		beforerender : function() {
			var monthField = this.up('form').getForm().findField("monthLimit") ;
			if(monthField!=null){
				var monthLimit = monthField.getValue();
				var str = "[" ;
				for(var i=1;i<=monthLimit;i++){
					str += "{value:'" + Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -i),'Ym')  + "',display:'" + Ext.Date.format( Ext.Date.add(new Date(), Ext.Date.MONTH, -i),'Y-m')+ "'}," ;
				}
				str += "]" ;
			
				this.store.clearData() ;
				this.store.setData(Ext.decode(str)) ;
			}
			
		}	
	},
	 valueField : 'value',
	 displayField : 'display',
	 queryMode : 'local',
	 editable : false,
	 emptyText : EwayLocale.combox.select
});