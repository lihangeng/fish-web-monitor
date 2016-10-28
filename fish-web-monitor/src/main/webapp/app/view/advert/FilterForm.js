Ext.define('Eway.view.advert.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.advert_filterForm',

	requires : [ 'Ext.ux.form.DateTimeField' ],
	height : 65,
	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			layout : 'column',
			items : [ {
				columnWidth : .3,
				items : [ {
					xtype : 'combobox',
					fieldLabel : EwayLocale.advert.type,
					displayField : 'display',
					store : Ext.StoreMgr.lookup("advert.AdvertType"),
					queryMode : 'local',
					valueField : 'value',
					name : 'advertType',
					labelWidth:80,
					editable : false,
					width : 250
				} ]
			}, {


				columnWidth : .6,
				items : [ {
							xtype : 'fieldcontainer',
							labelWidth : 300,
							fieldLabel : EwayLocale.advert.createdTimeStart,
							labelAlign : 'right',
							layout : 'hbox',
							defaults : {
								hideLabel : true
							},
							items : [{
								fieldLabel : 'startDateTime',
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'createdTimeStart',
								vtype : 'daterange',
								width : 120,
								editable:false,
								endDateField : 'createdTimeEnd',
								listeners : {
									blur : {
							            fn: function(This, options){
							            	var value = this.getValue();
							            	if (!value) {
							            		var endField = this.up('form').getForm().findField(this.endDateField);
							            		endField.setMinValue(null);
							            	}
							            }
									}
								}
							},
//							 dateRange : {
//							 begin : 'createdTimeStart',
//							 end : 'createdTimeEnd'
//							 },
							{
								xtype : 'label',
								text : EwayLocale.advert.createdTimeStop
							}, {
								fieldLabel : 'endDateTime',
								width : 120,
								xtype : 'datefield',
								format : 'Y-m-d',
								name : 'createdTimeEnd',
								vtype : 'daterange',
								editable:false,
								startDateField : 'createdTimeStart',
								listeners : {
									blur : {
							            fn: function(This, options){
							            	var value = this.getValue();
							            	if (!value) {
							            		var startField = this.up('form').getForm().findField(this.startDateField);
							            		startField.setMaxValue(null);
							            	}
							            }
									}
								}
							}]
						}]
			
			} ]
		});
		this.callParent(arguments);
	}

});