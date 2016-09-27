
Ext.define('Eway.view.person.user.UserLogFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.userLogFilterForm',

	requires: ['Ext.ux.form.DateTimeField'],

	layout : 'column',
	height : 40,
	defaults : {
		border : false
	},

	initComponent: function() {
		var me = this;
		Ext.apply(this, {
			items : [{
				columnWidth : .5,
				items : [{
					fieldLabel: EwayLocale.tip.operateDate.operateDateBegin,
		        	xtype : 'datetimefield',
					name : 'operTimeStart',
					format : 'Y-m-d H:i:s',
					msgTarget : 'side',
					labelWidth:170,
					editable: false,
					value:Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d') + " 00:00:00","Y-m-d H:i:s"),
					 msgTarget : 'side',
					 labelAlign : 'right',
					 listeners: {
					 	change : function(){
					 		var endDate = this.up('form').down('field[name="operTimeEnd"]');
					 		endDate.setMinValue(this.getValue());
					 	},
						render:function(){
							var endDate = this.up('form').down('field[name="operTimeEnd"]');

							this.setMaxValue(endDate.getValue());
						}
					 }
				}]
			},
			{
				columnWidth : .5,
				items : [{
					fieldLabel: EwayLocale.tip.operateDate.operateDateEnd,
		        	xtype : 'datetimefield',
					name : 'operTimeEnd',
					labelWidth:140,
					format : 'Y-m-d H:i:s',
					editable: false,
					value:Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d') + " 23:59:59","Y-m-d H:i:s"),
					msgTarget : 'side',
					labelAlign : 'right',
					listeners : {
						change : function(){
							var beginDate = this.up('form').down('field[name="operTimeStart"]');
							beginDate.setMaxValue(this.getValue());
						},
						render:function(){
							var beginDate = this.up('form').down('field[name="operTimeStart"]');
							this.setMinValue(beginDate.getValue());
						}
					}

				}]
			}]
		});

		this.callParent(arguments);
	}
});