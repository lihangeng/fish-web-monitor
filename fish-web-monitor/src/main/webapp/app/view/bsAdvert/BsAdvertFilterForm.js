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
											//'广告归属组'
											fieldLabel : EwayLocale.bsAdvert.advertGroup
										},{
											xtype : 'field_bsadvert_advertStatus',
											name:'bsAdvertStatus',
											//'广告状态'
											fieldLabel : EwayLocale.bsAdvert.advertStatus
										} ]
									},
									{
										columnWidth : .5,
										items : [ {
											xtype : 'textfield',
											name:'advertName',
											labelWidth : 120,
											//'广告名'
											fieldLabel : EwayLocale.bsAdvert.advertName
										},{
											xtype : 'fieldcontainer',
											labelWidth : 120,
											fieldLabel : EwayLocale.bsAdvert.createdTimeStart,
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
														value : EwayLocale.bsAdvert.createdTimeStop
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