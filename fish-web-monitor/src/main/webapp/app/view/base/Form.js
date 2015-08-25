Ext.define('Eway.view.base.Form', {
	extend: 'Ext.form.Panel',

	closable:false,
	trackResetOnLoad : true,
	selectOnFocus : true,
	labelAlign:'right',
	msgTarget : 'side',
	border : false,
	defaults: {
		anchor : '100%',
		labelWidth: 60,
		labelAlign: 'right',
		msgTarget : 'side'
	},
	bodyStyle: {
		padding:'10px'
	},

	getCusValues : function(){
		return this.getValues();
	},

	setCusValues : function(record){
		this.getForm().loadRecord(record);
	},

	loadCusRecord : function(record){
		this.getForm().loadRecord(record);
	},

	updateCusRecord : function(record){
		this.getForm().updateRecord(record);
	},

	findByType: function(xtype) {
		return this.query(xtype);
	}

});