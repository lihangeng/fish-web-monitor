Ext.define('Eway.view.operatingPlan.Add',{

	extend: 'Ext.window.Window',
	alias : 'widget.operatingPlan_form',

	title : EwayLocale.report.plan.addPlan,
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
					fieldLabel : '<font color="red">*</font>'+EwayLocale.report.openplan.name,
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
							fieldLabel : '<font color="red">*</font>'+EwayLocale.report.plan.startDate,
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
							fieldLabel : '<font color="red">*</font>'+EwayLocale.report.plan.endDate,
							xtype : 'datefield',
							name : 'endDate',
							format : 'Y-m-d',
							allowBlank : false,
							editable : false,
							value:Ext.Date.format(Ext.Date.add(new Date(), Ext.Date.YEAR, 10),'Y-m-d'),
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
							fieldLabel : '<font color="red">*</font>'+EwayLocale.report.openplan.state,
							xtype : 'field_planState',
							name : 'planState',
							value :'Normal',
							allowBlank : false
							} ,{
							fieldLabel : '<font color="red">*</font>'+EwayLocale.report.plan.type,
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : EwayLocale.report.openplan.week,
								name : 'planType',
								checked : true,
								inputValue : 'WEEK'
							}, {
								boxLabel : EwayLocale.report.openplan.date,
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
										fieldLabel : '<font color="red">*</font>'+EwayLocale.report.openplan.week,
										xtype : 'checkboxgroup',
										allowBlank : false,
										itemId: 'checkboxgroupId',
										labelWidth:65,
										columns: 7,
										msgTarget:'qtip',
										blankText :EwayLocale.report.openplan.chooseOne,
							            items : [
						                    {boxLabel: EwayLocale.report.openplan.Mon, name: 'Mon', checked: true},
						                    {boxLabel: EwayLocale.report.openplan.Tues, name: 'Tues'},
						                    {boxLabel: EwayLocale.report.openplan.Wed, name: 'Wed'},
						                    {boxLabel: EwayLocale.report.openplan.Thur, name: 'Thur'},
						                    {boxLabel: EwayLocale.report.openplan.Fri, name: 'Fri'},
						                    {boxLabel: EwayLocale.report.openplan.Sat, name: 'Sat'},
						                    {boxLabel: EwayLocale.report.openplan.Sun, name: 'Sun'}
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
									value:'<b>'+EwayLocale.report.openplan.lanDetailWeek+'</b>'
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
										value:'<b>' + EwayLocale.report.openplan.planDetailDay + '</b>'
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