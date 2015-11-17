Ext.define('Eway.view.person.user.PersonWin', {
	alias : 'widget.user_personWin',
	extend : 'Ext.window.Window',

	modal : true,
	resizable : false,
	maximizable : true,
	constrainHeader : true,

	requires : [ 'Eway.view.person.user.PersonGrid',
			'Eway.view.person.user.PersonUserFilterForm' ],

	title : EwayLocale.person.user.userListTitle,
	width : 750,
	height : 400,
	// layout : 'fit',
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				region : 'north',
				xtype : 'person_user_personUserFilterForm'
			}, {
				region : 'center',
				xtype : 'user_personGrid'
			} ]
		});
		this.callParent(arguments);
	}

});