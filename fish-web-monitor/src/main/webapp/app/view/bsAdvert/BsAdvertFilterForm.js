Ext.define('Eway.view.bsAdvert.BsAdvertFilterForm',
				{
					extend : 'Eway.view.base.FilterForm',
					alias : 'widget.bs_advert_filterForm',
					store:[ 'Eway.store.bsAdvert.BsAdvertStatus'],
					requires : [ 'Ext.ux.form.DateTimeField',
					             'Eway.view.bsAdvert.field.BsAdvertStatusComBox'],
					height : 70,
					initComponent : function() {
						var me = this;
						Ext.apply(this,
						{
							layout : 'column',
							items : [
									{
										columnWidth : .5,
										items : [ {
											xtype : 'field_advert_advertGroup',
											name:'groupId',
											fieldLabel : '广告归属组'
										},{
											xtype : 'field_bsadvert_advertStatus',
											name:'bsAdvertStatus',
											fieldLabel : '广告状态'
										} ]
									},
									{
										columnWidth : .5,
										items : [ {
											xtype : 'textfield',
											name:'advertName',
											labelWidth : 120,
											fieldLabel : '广告名'
										},{
											xtype : 'fieldcontainer',
											labelWidth : 120,
											fieldLabel : EwayLocale.advert.createdTimeStart,
											layout : 'hbox',
											defaults : {
												hideLabel : true
											},
								items : [{
											fieldLabel : 'startDateTime',
											xtype : 'datefield',
											format : 'Y-m-d',
											name : 'updateTimeStart',
											vtype : 'daterange',
											width : 120,
											editable : false,
											endDateField : 'updateTimeEnd',
											listeners : {
												blur : {
													fn : function(This,options) {
														var value = this.getValue();
														if (!value) {
															var endField = this.up('form').getForm().findField(this.endDateField);
															endField.setMinValue(null);
														}
													}
												}
											}
										},
										{
											xtype : 'displayfield',
											value : EwayLocale.advert.createdTimeStop
										},
										{
											fieldLabel : 'endDateTime',
											width : 120,
											xtype : 'datefield',
											format : 'Y-m-d',
											name : 'updateTimeEnd',
											vtype : 'daterange',
											editable : false,
											startDateField : 'updateTimeStart',
											listeners : {
												blur : {
													fn : function(This,options) {
														var value = this.getValue();
														if (!value) {
															var startField = this.up('form').getForm().findField(this.startDateField);
															startField.setMaxValue(null);
														}
													}
												}
											}
										} ]
									} ]
								}]
						});
						this.callParent(arguments);
					}

				});