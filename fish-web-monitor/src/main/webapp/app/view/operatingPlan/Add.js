Ext.define('Eway.view.operatingPlan.Add',{

	extend: 'Ext.window.Window',
	alias : 'widget.operatingPlan_form',

	title : Eway.locale.report.plan.addPlan,
	modal : true,
	constrainHeader : true,
	width : 810,
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
							anchor : '85%',
							style : 'padding-top:10px'
						},
						items : [ {
							fieldLabel : '<font color="red">*</font>'+Eway.locale.report.openplan.name,
							labelWidth: 60,
							xtype : 'textfield',
							name : 'name',
							maxLength : 30,
							allowBlank : false
						},{
							xtype : 'textarea',
							labelWidth: 60,
						    fieldLabel : '&nbsp;&nbsp;&nbsp;'+Eway.locale.commen.remark,
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
							minValue : Ext.Date.format(new Date(), 'Y-m-d'),
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
						},{
							fieldLabel : '<font color="red">*</font>'+Eway.locale.report.openplan.state,
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
					width :810,
					itemId: 'cardPanelId',
					items : [ {
						title : Eway.locale.tip.planDate,
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
										fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.device.person.openClose,
										xtype : 'radiogroup',
										name : 'openClose',
										labelWidth: 70,
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
										fieldLabel : Eway.locale.system.startDate,
										labelWidth: 70,
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
										style : 'padding:10px 0px 0px 62px'
									},
									items : [ {
										fieldLabel : '<font color="red">*</font>'+Eway.locale.report.openplan.week,
										xtype : 'checkboxgroup',
										allowBlank : false,
										itemId: 'checkboxgroupId',
										labelWidth:65,
										columns: 4,
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
									text: Eway.locale.button.add,
									iconCls :'sureBtn',
									action: 'add'
								},{
									text: Eway.locale.button.remove,
									iconCls :'deleteBtn',
									action: 'remove'
								}]
							} ,{
								title : Eway.locale.report.openplan.weekPlanInfoID,
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
							width : 730,
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
										anchor : '100%',
										style : 'padding:10px 0px 0px 20px'
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
									text: Eway.locale.button.add,
									iconCls :'sureBtn',
									action: 'add'
								},{
									text: Eway.locale.button.remove,
									iconCls :'deleteBtn',
									action: 'remove'
								}]
							} ,{
								title : Eway.locale.report.openplan.datePlanInfoID,
								itemId : 'datePlanInfoID',
								height : 180,
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
					text: Eway.locale.button.confirm,
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