Ext.define('Eway.view.person.person.LinkingDeviceFilter', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.person_linkingDeviceFilter',
	height : 60,
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right'
			},
			items : [ {
				columnWidth : .5,
				xtype : 'textfield',
				fieldLabel : '设备号',
				name : 'terminalId',
				regex : /^\w+[\w-\.]*$/,
				regexText : '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。',
				maxLength : 20
			} ]
		});
		this.callParent(arguments);
	}
});