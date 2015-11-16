Ext.define('Eway.view.base.Window',{
	extend : 'Ext.window.Window',
	alias : 'widget.base_window',
	modal : true,
	resizable : false,
	constrainHeader : true,
//	height : 400,
	width : 500,
//	maximizable : true,
	config : {
		action : undefined
	},

	initComponent : function(){
		Ext.apply(this,{
			buttonAlign : 'center',
			buttons : [{
				xtype : 'button',
				text : Eway.locale.button.confirm,
				action : 'confirm'
			},{
				xtype : 'button',
				text : Eway.locale.button.reset,
				handler : this.onReset,
				hidden : true,
				scope : this
			},{
				xtype : 'button',
				text : Eway.locale.button.cancle,
				handler : this.onOver,
				scope : this
			}]
		});
		this.callParent(arguments);
	},

	getForm : function(){
		return this.down('form');
	},

	onOver : function(){
		this.close();
	},

	onReset : function(){
		this.getForm().getForm().reset();
	}

});