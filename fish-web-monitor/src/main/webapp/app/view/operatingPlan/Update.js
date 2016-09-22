
Ext.define('Eway.view.operatingPlan.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.operatingPlan_update',

	requires: ['Eway.lib.Util'],

	title: EwayLocale.machine.plan.changePlan,
	modal : true,
	constrainHeader : true,
	width : 1000,
	layout : 'fit',
	maximizable : true,
	resizable : false,
	autoScroll : true,
	requires : ['Eway.lib.Util','Eway.view.operatingPlan.PlanInfoGrid','Eway.view.operatingPlan.WeekSelect','Eway.view.operatingPlan.PlanState'],
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding:10px 10px 0px 10px',
				defaults : {
					anchor : '100%',
					labelAlign:'right'
				},
				items : [  {
					fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.serviceplan.name,
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					anchor : '90%',
					allowBlank : false
				},{
					layout : 'column',
					border : false,
					items : [ {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '85%',
							style : 'padding-top:10px',
							labelAlign:'right'
						},
						items : [ {
							fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.plan.startDate,
							xtype : 'datefield',
							name : 'startDate',							
							format : 'Y-m-d',
							allowBlank : false,
							editable : false,
							msgTarget:'side',
			        		maxValue : Ext.Date.format(Ext.Date.add(new Date(), Ext.Date.YEAR, 2),'Y-m-d'),
							value:Ext.Date.format(new Date(), 'Y-m-d'),
							vtype : 'daterange',
							endDateField : 'endDate',
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
						},{
							fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.plan.endDate,
							xtype : 'datefield',
							name : 'endDate',
							format : 'Y-m-d',
							allowBlank : false,						
							editable : false,
							vtype : 'daterange',
							msgTarget:'side',
							minValue : Ext.Date.format(new Date(), 'Y-m-d'),
							startDateField : 'startDate',
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
						} ]
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '80%',
							style : 'padding-top:10px',
							labelAlign:'right'
						},
						items : [{
							fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.serviceplan.state,
							xtype : 'field_planState',
							name : 'planState',
							value :'Normal',
							allowBlank : false
							} ,{
							fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.plan.type,
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : EwayLocale.machine.serviceplan.week,
								name : 'planType',
								checked : true,
								inputValue : 'WEEK'
							}, {
								boxLabel : EwayLocale.machine.serviceplan.date,
								name : 'planType',
								inputValue : 'DATE'
							}]
						} ]
					} ]
				}, {
					xtype : 'panel',
					layout : 'card',
					activeItem: 1,
					height : 350,
					itemId: 'cardPanelId',
					items : [ {
						title : EwayLocale.tip.planWeek,
						items : [ {
							xtype : 'form',
							height : 330,
							itemId : 'weekFormId',
							bodyStyle : 'padding:10px 10px 10px 10px',
							defaults : {
								anchor : '100%'
							},
							items : [ {
								layout : 'column',
								border : false,
								items : [ {
									columnWidth : .5,
									border : false,
									layout : 'anchor',
									defaultType : 'textfield',
									defaults : {
										anchor : '100%',
										style : 'padding-top:10px'
									},
									items : [ {
										fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.device.person.openClose,
										xtype : 'radiogroup',
										labelWidth :120,
										name : 'openClose',
										allowBlank : false,
										columns: 3,
										items : [ {
											boxLabel : EwayLocale.machine.device.person.Open,
											name : 'openClose',
											checked : true,
											width:50,
											inputValue : 'Open'
										}, {
											boxLabel : EwayLocale.machine.device.person.Close,
											width:50,
											name : 'openClose',
											inputValue : 'Close'
										}]
									},{
										xtype : 'fieldcontainer',
										fieldLabel : EwayLocale.system.startDate,
										layout : 'hbox',
										defaults : {
											hideLabel : true
										},
										items : [ {
											xtype : 'combobox',
											fieldLabel : 'hour',
											displayField : 'display',
											store : 'Hour',
											queryMode : 'local',
											valueField : 'value',
											value : '00',
											editable : false,
											name : 'startTimeHour',
											width :  75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.hourDisplay

										}, {
											xtype : 'combobox',
											fieldLabel : 'minute',
											displayField : 'display',
											store : 'Minute',
											queryMode : 'local',
											valueField : 'value',
											value : '00',
											editable : false,
											name : 'startTimeMinute',
											width :  75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.minuteDisplay
										}, {
											xtype : 'combobox',
											fieldLabel : 'second',
											displayField : 'display',
											store : 'Minute',
											queryMode : 'local',
											valueField : 'value',
											value : '00',
											editable : false,
											name : 'startTimeSecond',
											width :  75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.secondeDisplay
										} ]
									}]
								}, {
									columnWidth : .5,
									border : false,
									layout : 'anchor',
									defaults : {
										anchor : '90%',
										xtype : 'textfield',
										style : 'padding:10px 0px 0px 0px'
									},
									items : [ {
										fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.serviceplan.week,
										xtype : 'checkboxgroup',
										allowBlank : true,
										itemId: 'checkboxgroupId',
										labelWidth:65,
										columns: 7,
										msgTarget:'qtip',
										//blankText :EwayLocale.machine.serviceplan.chooseOne,
							            items : [
						                    {boxLabel: EwayLocale.machine.serviceplan.Mon, name: 'Mon', checked: true},
						                    {boxLabel: EwayLocale.machine.serviceplan.Tues, name: 'Tues'},
						                    {boxLabel: EwayLocale.machine.serviceplan.Wed, name: 'Wed'},
						                    {boxLabel: EwayLocale.machine.serviceplan.Thur, name: 'Thur'},
						                    {boxLabel: EwayLocale.machine.serviceplan.Fri, name: 'Fri'},
						                    {boxLabel: EwayLocale.machine.serviceplan.Sat, name: 'Sat'},
						                    {boxLabel: EwayLocale.machine.serviceplan.Sun, name: 'Sun'}
							              ]
									}, {
										xtype : 'fieldcontainer',
										fieldLabel : EwayLocale.advert.endTime,
										layout : 'hbox',
										labelWidth:65,
										defaults : {
											hideLabel : true
										},
										items : [ {
											xtype : 'combobox',
											fieldLabel : 'hour',
											displayField : 'display',
											store : 'Hour',
											queryMode : 'local',
											valueField : 'value',
											value : '23',
											editable : false,
											name : 'endTimeHour',
											width : 75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.hourDisplay
										}, {
											xtype : 'combobox',
											fieldLabel : 'minute',
											displayField : 'display',
											store : 'Minute',
											queryMode : 'local',
											valueField : 'value',
											value : '59',
											editable : false,
											name : 'endTimeMinute',
											width : 75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.minuteDisplay
										}, {
											xtype : 'combobox',
											fieldLabel : 'second',
											displayField : 'display',
											store : 'Minute',
											queryMode : 'local',
											valueField : 'value',
											value : '59',
											editable : false,
											name : 'endTimeSecond',
											width : 75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.secondeDisplay
										} ]
									} ]
								} ],
								fbar: [{
									xtype:'displayfield',
									value:'<b>'+EwayLocale.machine.serviceplan.lanDetailWeek+'</b>'
								},'->',{
									text: EwayLocale.card.add,
									glyph : 0xf067,
									action: 'add'
								},{
									text: EwayLocale.card.dell,
									glyph : 0xf014,
									action: 'remove'
								}]
							} ,{
								header:false,
								itemId : 'weekPlanInfoID',
								height : 180,
								width : 760,
								autoScroll : true,
								xtype : 'planInfo_grid'
							}]
						} ]
					}, {
						title : EwayLocale.tip.planDate,
						items : [ {
							xtype : 'form',
							height : 330,
							itemId : 'dateFormId',
							/*bodyStyle : 'padding:10px 10px 10px 10px',*/
							defaults : {
								anchor : '100%'
							},
							items : [ {
								layout : 'column',
								border : false,
								items : [ {
									columnWidth : .4,
									border : false,
									layout : 'anchor',
									defaultType : 'textfield',
									defaults : {
										anchor : '90%',
										style : 'padding:10px 10px 0px 10px'
									},
									items : [ {
										fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.device.person.openClose,
										xtype : 'radiogroup',
										name : 'openClose',
										allowBlank : false,
										items : [ {
											boxLabel : EwayLocale.machine.device.person.Open,
											name : 'openClose',
											checked : true,
											inputValue : 'Open'
										}, {
											boxLabel : EwayLocale.machine.device.person.Close,
											name : 'openClose',
											inputValue : 'Close'
										}]
									}]
								}, {
									columnWidth : .6,
									border : false,
									layout : 'anchor',
									defaults : {
										anchor : '90%',
										xtype : 'textfield',
										style : 'padding-top:10px'
									},
									items : [ {
										xtype : 'fieldcontainer',
										fieldLabel : EwayLocale.system.startDate,
										layout : 'hbox',
										defaults : {
											hideLabel : true
										},
										items : [ {
											xtype : 'combobox',
											fieldLabel : 'hour',
											displayField : 'display',
											store : 'Hour',
											queryMode : 'local',
											valueField : 'value',
											value : '00',
											editable : false,
											name : 'startTimeHour',
											width : 75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.hourDisplay
										}, {
											xtype : 'combobox',
											fieldLabel : 'minute',
											displayField : 'display',
											store : 'Minute',
											queryMode : 'local',
											valueField : 'value',
											value : '00',
											editable : false,
											name : 'startTimeMinute',
											width : 75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.minuteDisplay
										}, {
											xtype : 'combobox',
											fieldLabel : 'second',
											displayField : 'display',
											store : 'Minute',
											queryMode : 'local',
											valueField : 'value',
											value : '00',
											editable : false,
											name : 'startTimeSecond',
											width : 75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.secondeDisplay
										} ]
									}, {
										xtype : 'fieldcontainer',
										fieldLabel : EwayLocale.commen.endDataTime,
										layout : 'hbox',
										defaults : {
											hideLabel : true
										},
										items : [ {
											xtype : 'combobox',
											fieldLabel : 'hour',
											displayField : 'display',
											store : 'Hour',
											queryMode : 'local',
											valueField : 'value',
											value : '23',
											editable : false,
											name : 'endTimeHour',
											width : 75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.hourDisplay
										}, {
											xtype : 'combobox',
											fieldLabel : 'minute',
											displayField : 'display',
											store : 'Minute',
											queryMode : 'local',
											valueField : 'value',
											value : '59',
											editable : false,
											name : 'endTimeMinute',
											width : 75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.minuteDisplay
										}, {
											xtype : 'combobox',
											fieldLabel : 'second',
											displayField : 'display',
											store : 'Minute',
											queryMode : 'local',
											valueField : 'value',
											value : '59',
											editable : false,
											name : 'endTimeSecond',
											width : 75
										}, {
											xtype : 'displayfield',
											value : EwayLocale.advert.secondeDisplay
										} ]
									} ]
								} ],
								fbar: [{
										xtype:'displayfield',
										value:'<b>' + EwayLocale.machine.serviceplan.planDetailDay + '</b>'
									},'->',{
									text: EwayLocale.card.add,
									glyph : 0xf067,
									action: 'add'
								},{
									text: EwayLocale.card.dell,
									glyph : 0xf014,
									action: 'remove'
								}]
							} ,{
								itemId : 'datePlanInfoID',
								height : 180,
								width:760,
								autoScroll : true,
								xtype : 'planInfo_grid',
								listeners : {
									beforerender :{
										fn: function(grid){
											grid.columns[0].hidden=true;
										}
									}
								}
							}]
						} ]
					} ]
				} ],
				buttonAlign : 'center',
				fbar: [{
					text: EwayLocale.button.confirm,
					action: 'confirm',
					iconCls:'db-save'
				}]
			}
		});
		this.callParent(arguments);
	},
	onOver : function() {
		this.up('window').close();
	}
});