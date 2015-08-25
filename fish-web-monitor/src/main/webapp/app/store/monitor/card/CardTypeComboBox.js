
Ext.define('Eway.store.monitor.card.CardTypeComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:'身份证'}, 
	       {value:'2', display:'户口本'},
	       {value:'3', display:'驾驶执照'},
	       {value:'4', display:'护照'},
	       {value:'5', display:'军官证'},
	       {value:'6', display:'士兵证'},
	       {value:'7', display:'法人营业执照'},
	       {value:'8', display:'法人代码证'},
	       {value:'9', display:'税务登记证'}]
});
