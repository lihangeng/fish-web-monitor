
Ext.define('Eway.view.person.person.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.person_filterform',
	
	requires: ['Eway.view.field.person.UserName',
	           'Eway.view.field.person.GenderFilter',
	           'Eway.view.field.person.Mobile',
	           'Eway.view.field.person.TypeFilter',
	           'Eway.view.field.person.StateFilter'
	           ],
	
	
	height: 80,
	layout : 'column',
	defaults : {
		border : false
	},
	
	/*title: '输入您要查询的条件：',*/
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .4,
				items : [{
					xtype : 'field.username',
					labelWidth: 50,
					maxLength : 20,
					msgTarget : 'side'
				}, {
					xtype : 'field.mobile',
					labelWidth: 50,
					regex: /^\d{8,11}$/,
					regexText:'手机电话号码只能输入8到11位数字‘0-9’！',
					msgTarget : 'side'
				}]
			}, {
				columnWidth : .4,
				items : [{
					xtype : 'field.genderFilter',
					labelWidth: 50,
					editable : false,
					value:'',
					msgTarget : 'side'
				}, {
					labelWidth: 50,
					xtype : 'field.stateFilter',
					editable : false,
					value:''
				}]
			}]
		});
		
		this.callParent(arguments);
	}
});