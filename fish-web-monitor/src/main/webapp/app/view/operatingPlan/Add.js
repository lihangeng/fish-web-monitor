Ext.define('Eway.view.operatingPlan.Add',{

	extend: 'Ext.window.Window',
	alias : 'widget.operatingPlan_form',

	title : '增加方案',
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
							fieldLabel : '<font color="red">*</font> 名称',
							labelWidth: 60,
							xtype : 'textfield',
							name : 'name',
							maxLength : 30,
							allowBlank : false
						},{
							xtype : 'textarea',
							labelWidth: 60,
						    fieldLabel : '&nbsp;&nbsp;&nbsp;备注',
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
							fieldLabel : '<font color="red">*</font>有效开始日期',
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
							fieldLabel : '<font color="red">*</font>有效结束日期',
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
							fieldLabel : '<font color="red">*</font> 类型',
							xtype : 'radiogroup',
							allowBlank : false,
							items : [ {
								boxLabel : '星期',
								name : 'planType',
								checked : true,
								inputValue : 'WEEK'
							}, {
								boxLabel : '日期',
								name : 'planType',
								inputValue : 'DATE'
							}]
						},{
							fieldLabel : '<font color="red">*</font> 状态',
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
						title : '方案详情（星期）',
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
										fieldLabel : '<font color="red">*</font> 开机/关机',
										xtype : 'radiogroup',
										name : 'openClose',
										labelWidth: 70,
										allowBlank : false,
										columns: 1,
										items : [ {
											boxLabel : '开机',
											name : 'openClose',
											checked : true,
											inputValue : 'Open'
										}, {
											boxLabel : '关机',
											name : 'openClose',
											inputValue : 'Close'
										}]
									},{
										xtype : 'fieldcontainer',
										fieldLabel : '开始时间',
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
											value : '时'

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
											value : '分'
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
											value : '秒'
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
										fieldLabel : '<font color="red">*</font> 星期',
										xtype : 'checkboxgroup',
										allowBlank : false,
										itemId: 'checkboxgroupId',
										labelWidth:65,
										columns: 4,
										msgTarget:'qtip',
										blankText :'请在组内至少选中一项',
							            items: [
						                    {boxLabel: '一', name: 'Mon', checked: true},
						                    {boxLabel: '二', name: 'Tues'},
						                    {boxLabel: '三', name: 'Wed'},
						                    {boxLabel: '四', name: 'Thur'},
						                    {boxLabel: '五', name: 'Fri'},
						                    {boxLabel: '六', name: 'Sat'},
						                    {boxLabel: '日', name: 'Sun'}
							              ]
									}, {
										xtype : 'fieldcontainer',
										fieldLabel : '    结束时间',
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
											value : '时'
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
											value : '分'
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
											value : '秒'
										} ]
									} ]
								} ],
								fbar: [{
									text: '添加',
									iconCls :'sureBtn',
									action: 'add'
								},{
									text: '删除',
									iconCls :'deleteBtn',
									action: 'remove'
								}]
							} ,{
								title : '星期方案详细列表',
								itemId : 'weekPlanInfoID',
								height : 180,
								width : 730,
								autoScroll : true,
								xtype : 'planInfo_grid'
							}]
						} ]
					}, {
						title : '方案详情（日期）',
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
										fieldLabel : '<font color="red">*</font> 开机/关机',
										xtype : 'radiogroup',
										name : 'openClose',
										allowBlank : false,
										items : [ {
											boxLabel : '开机',
											name : 'openClose',
											checked : true,
											inputValue : 'Open'
										}, {
											boxLabel : '关机',
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
										fieldLabel : '开始时间',
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
											value : '时'

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
											value : '分'
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
											value : '秒'
										} ]
									}, {
										xtype : 'fieldcontainer',
										fieldLabel : '结束时间',
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
											value : '时'
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
											value : '分'
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
											value : '秒'
										} ]
									} ]
								} ],
								fbar: [{
									text: '添加',
									iconCls :'sureBtn',
									action: 'add'
								},{
									text: '删除',
									iconCls :'deleteBtn',
									action: 'remove'
								}]
							} ,{
								title : '日期方案详细列表',
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
					text: '确认',
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