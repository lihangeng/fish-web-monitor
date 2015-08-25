Ext.define('Eway.view.base.Window',{
	extend : 'Ext.window.Window',
	alias : 'widget.base_window',
	modal : true,
	resizable : false,
	constrainHeader : true,
//	height : 400,
	width : 500,
	maximizable : true,
	config : {
		action : undefined
	},

	initComponent : function(){
		Ext.apply(this,{
			buttonAlign : 'center',
			buttons : [{
				xtype : 'button',
				text : '确认',
				action : 'confirm'
			},{
				xtype : 'button',
				text : '重置',
				handler : this.onReset,
				hidden : true,
				scope : this
			},{
				xtype : 'button',
				text : '取消',
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