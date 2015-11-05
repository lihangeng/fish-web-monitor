
Ext.define('Eway.view.operatingPlan.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.operatingPlan_update',

	requires: ['Eway.lib.Util'],

	title: Eway.locale.report.plan.changePlan,
	modal : true,
	constrainHeader : true,
	width : 810,
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
					fieldLabel : '<font color="red">*</font>'+Eway.locale.report.openplan.name,
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
							fieldLabel : '<font color="red">*</font>'+Eway.locale.report.plan.startDate,
							xtype : 'datefield',
							name : 'startDate',							
							format : 'Y-m-d',
							allowBlank : false,
							editable : false,
							minValue : Ext.Date.format(new Date(), 'Y-m-d'),
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
							fieldLabel : '<font color="red">*</font>'+Eway.locale.report.plan.endDate,
							xtype : 'datefield',
							name : 'endDate',
							format : 'Y-m-d',
							allowBlank : false,
							editable : false,
							vtype : 'daterange',
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
							fieldLabel : '<font color="red">*</font>'+Eway.locale.report.openplan.state,
							xtype : 'field_planState',
							name : 'planState',
							value :'Normal',
							allowBlank : false
							} ,{
							fieldLabel : '<font color="red">*</font>'+Eway.locale.report.plan.type,
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : Eway.locale.report.openplan.week,
								name : 'planType',
								checked : true,
								inputValue : 'WEEK'
							}, {
								boxLabel : Eway.locale.report.openplan.date,
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
						title : Eway.locale.tip.planWeek,
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
										fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.device.person.openClose,
										xtype : 'radiogroup',
										name : 'openClose',
										allowBlank : false,
										columns: 3,
										items : [ {
											boxLabel : Eway.locale.machine.device.person.Open,
											name : 'openClose',
											checked : true,
											width:50,
											inputValue : 'Open'
										}, {
											boxLabel : Eway.locale.machine.device.person.Close,
											width:50,
											name : 'openClose',
											inputValue : 'Close'
										}]
									},{
										xtype : 'fieldcontainer',
										fieldLabel : Eway.locale.system.startDate,
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
											value : Eway.locale.advert.hourDisplay

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
											value : Eway.locale.advert.minuteDisplay
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
											value : Eway.locale.advert.secondeDisplay
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
										fieldLabel : '<font color="red">*</font>'+Eway.locale.report.openplan.week,
										xtype : 'checkboxgroup',
										allowBlank : false,
										itemId: 'checkboxgroupId',
										labelWidth:65,
										columns: 7,
										msgTarget:'qtip',
										blankText :Eway.locale.report.openplan.chooseOne,
							            items : [
						                    {boxLabel: Eway.locale.report.openplan.Mon, name: 'Mon', checked: true},
						                    {boxLabel: Eway.locale.report.openplan.Tues, name: 'Tues'},
						                    {boxLabel: Eway.locale.report.openplan.Wed, name: 'Wed'},
						                    {boxLabel: Eway.locale.report.openplan.Thur, name: 'Thur'},
						                    {boxLabel: Eway.locale.report.openplan.Fri, name: 'Fri'},
						                    {boxLabel: Eway.locale.report.openplan.Sat, name: 'Sat'},
						                    {boxLabel: Eway.locale.report.openplan.Sun, name: 'Sun'}
							              ]
									}, {
										xtype : 'fieldcontainer',
										fieldLabel : Eway.locale.advert.endTime,
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
											value : Eway.locale.advert.hourDisplay
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
											value : Eway.locale.advert.minuteDisplay
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
											value : Eway.locale.advert.secondeDisplay
										} ]
									} ]
								} ],
								fbar: [{
									xtype:'displayfield',
									value:'<b>'+Eway.locale.report.openplan.lanDetailWeek+'</b>'
								},'->',{
									text: Eway.locale.card.add,
									glyph : 0xf067,
									action: 'add'
								},{
									text: Eway.locale.card.dell,
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
						title : Eway.locale.tip.planDate,
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
										fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.device.person.openClose,
										xtype : 'radiogroup',
										name : 'openClose',
										allowBlank : false,
										items : [ {
											boxLabel : Eway.locale.machine.device.person.Open,
											name : 'openClose',
											checked : true,
											inputValue : 'Open'
										}, {
											boxLabel : Eway.locale.machine.device.person.Close,
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
										fieldLabel : Eway.locale.system.startDate,
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
											value : Eway.locale.advert.hourDisplay
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
											value : Eway.locale.advert.minuteDisplay
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
											value : Eway.locale.advert.secondeDisplay
										} ]
									}, {
										xtype : 'fieldcontainer',
										fieldLabel : Eway.locale.commen.endDataTime,
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
											value : Eway.locale.advert.hourDisplay
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
											value : Eway.locale.advert.minuteDisplay
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
											value : Eway.locale.advert.secondeDisplay
										} ]
									} ]
								} ],
								fbar: [{
										xtype:'displayfield',
										value:'<b>' + Eway.locale.report.openplan.planDetailDay + '</b>'
									},'->',{
									text: Eway.locale.card.add,
									glyph : 0xf067,
									action: 'add'
								},{
									text: Eway.locale.card.dell,
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
					text: Eway.locale.button.confirm,
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