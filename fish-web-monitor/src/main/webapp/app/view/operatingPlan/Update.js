
Ext.define('Eway.view.operatingPlan.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.operatingPlan_update',

	requires: ['Eway.lib.Util'],

	title: Eway.locale.report.plan.changePlan,
	modal: true,
	constrainHeader : true,
	width : 800,
	height : 620,
	// maxHeight : 600,.
	layout : 'fit',
	maximizable : true,
	resizable : false,
	autoScroll : true,
	bodyStyle : 'padding: 10px 10px 30px 10px',
	requires : ['Eway.lib.Util','Eway.view.operatingPlan.PlanInfoGrid','Eway.view.operatingPlan.WeekSelect','Eway.view.operatingPlan.PlanState'],
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding:10px 10px 10px 10px',
				height : 550,
				defaults : {
					anchor : '100%'
				},
				items : [ {
					layout : 'column',
					border : 'false',
					items : [ {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							style : 'padding-top:10px'
						},
						items : [ {
							fieldLabel : '<font color="red">*</font> '+Eway.locale.agent.remote.loading,
							xtype : 'textfield',
							name : 'name',
							maxLength : 30,
							allowBlank : false
						},{
							xtype : 'textarea',
						    fieldLabel : Eway.locale.version.View.remark,
						    name : 'desc',
						    autoScroll : true,
							maxLength :30,
							allowBlank : true
						} ]
					}, {
						columnWidth : .5,
						border : false,
						layout : 'anchor',
						defaults : {
							anchor : '90%',
							style : 'padding-top:10px'
						},
						items : [ {
							fieldLabel : '<font color="red">*</font>'+Eway.locale.report.plan.startDate,
							xtype : 'datefield',
							name : 'startDate',
							format : 'Y-m-d',
							allowBlank : false,
							editable : false,
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
						},{
							fieldLabel : '<font color="red">*</font>'+Eway.locale.commen.type,
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : Eway.locale.report.openplan.week,
								name : 'planType',
								checked : true,
								inputValue : 'WEEK'
							}, {
								boxLabel : Eway.locale.report.openplan.dates,
								name : 'planType',
								inputValue : 'DATE'
							}]
						},{
							fieldLabel : '<font color="red">*</font>'+Eway.locale.commen.type,
							xtype : 'field_planState',
							name : 'planState',
							value :'Normal',
							allowBlank : false
						} ]
					} ]
				}, {
					xtype : 'panel',
					frame : true,
					layout : 'card',
					activeItem: 1,
					height : 350,
					itemId: 'cardPanelId',
					items : [ {
						title : Eway.locale.tip.planWeek,
						layout : 'column',
						border : false,
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
								border : 'false',
								items : [ {
									columnWidth : .5,
									border : false,
									layout : 'anchor',
									defaultType : 'textfield',
									defaults : {
										anchor : '90%',
										style : 'padding-top:10px'
									},
									items : [ {
										fieldLabel : '<font color="red">*</font>'+ Eway.locale.machine.device.person.openClose,
										xtype : 'radiogroup',
										name : 'openClose',
										allowBlank : false,
										columns: 1,
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
									},{
										xtype : 'fieldcontainer',
										fieldLabel :Eway.locale.system.startDate,
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
											width : 45
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
											width : 45
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
											width : 45
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
										style : 'padding-top:10px'
									},
									items : [ {
										fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.device.person.week,
										xtype : 'checkboxgroup',
										allowBlank : false,
										itemId: 'checkboxgroupId',
										msgTarget:'qtip',
										blankText :Eway.locale.report.openplan.lastOneGroup,
										columns: 4,
							            items: [
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
											width : 45
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
											width : 45
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
											width : 45
										}, {
											xtype : 'displayfield',
											value : Eway.locale.advert.secondeDisplay
										} ]
									} ]
								} ],
								fbar: [{
									text: Eway.locale.card.add,
									iconCls :'sureBtn',
									action: 'add'
								},{
									text: Eway.locale.card.dell,
									iconCls :'deleteBtn',
									action: 'remove'
								}]
							} ,{
								title : Eway.locale.report.openplan.lanDetailWeek,
								itemId : 'weekPlanInfoID',
								height : 180,
								width : 730,
								autoScroll : true,
								xtype : 'planInfo_grid'
							}]
						} ]
					}, {
						title : Eway.locale.tip.planDate,
						layout : 'column',
						border : false,
							items : [ {
							xtype : 'form',
							height : 350,
							itemId : 'dateFormId',
							bodyStyle : 'padding:10px 10px 10px 10px',
							defaults : {
								anchor : '100%'
							},
							items : [ {
								layout : 'column',
								border : 'false',
								items : [ {
									columnWidth : .5,
									border : false,
									layout : 'anchor',
									defaultType : 'textfield',
									defaults : {
										anchor : '90%',
										style : 'padding-top:10px'
									},
									items : [ {
										fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.device.person.openClose ,
										xtype : 'radiogroup',
										name : 'openClose',
										allowBlank : false,
										items : [ {
											boxLabel :Eway.locale.machine.device.person.Open,
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
									columnWidth : .5,
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
											width : 45
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
											width : 45
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
											width : 45
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
											width : 45
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
											width : 45
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
											width : 45
										}, {
											xtype : 'displayfield',
											value :Eway.locale.advert.secondeDisplay
										} ]
									} ]
								} ],
								fbar: [{
									text: Eway.locale.card.add,
									iconCls :'sureBtn',
									action: 'add'
								},{
									text: Eway.locale.card.dell,
									iconCls :'deleteBtn',
									action: 'remove'
								}]
							} ,{
								title : Eway.locale.report.openplan.planDetailDay,
								height : 180,
								itemId : 'datePlanInfoID',
								width : 730,
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
					text: '确定',
					action: 'confirm',
					iconCls:'db-save'
				}]
			/*	dockedItems : [{
					width : 800,
					xtype: 'toolbar',
					dock: 'bottom',
					items :[{
				        	xtype: 'tbspacer',
				       		width: 350
				        },{
				        	text: '确定',
				        	xtype:'button',
							iconCls:'db-save',
							action: 'confirm'
					}]
				}]*/
			}
		});
		this.callParent(arguments);
	},
	onOver : function() {
		this.up('window').close();
	}
});