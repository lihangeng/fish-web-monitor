Ext.define('Eway.controller.parameter.template.Template',
				{
					extend : 'Eway.controller.base.FishController',

					stores : ['parameter.template.Template',
							'machine.DeviceAtmType',
							'machine.atmType.DeviceAtmCatalog',
							'parameter.template.AddedParam',
							'parameter.template.AddingParam',
							'parameter.template.TemplateDetail'],

					models : ['parameter.template.Template',
							'parameter.template.AddedParam',
							'parameter.template.AddingParam',
							'parameter.template.TemplateParam',
							'parameter.template.TemplateDetailList'],

					views : ['parameter.template.View'],

					refs : [ {
						ref : 'ewayView',
						selector : 'template_View',
						autoCreate : true,
						xtype : 'template_View'
					}, {
						ref : 'templateFilterForm',
						selector : 'template_FilterForm'
					}, {
						ref : 'templateGrid',
						selector : 'template_Grid'
					}, {
						ref : 'addWin',
						selector : 'template_add'
					}, {
						ref : 'updateWin',
						selector : 'template_updateTemplate'
					}, {
						ref : 'paramGrid',
						selector : 'param_paramGrid'
					}, {
						ref : 'paramValueGrid',
						selector : 'param_paramValueGrid'
					}, {
						ref : 'addedParamGrid',
						selector : 'param_addedParamGrid'
					} ],


					init : function() {
						this.initBaseControl();
						this.control({
							'template_View button[action=query]' : {
								click : this.onQuery
							},
							'template_View button[action=add]' : {
								click : this.onAdd,
								scope : this
							},
							'template_View button[action=updateValue]' : {
								click : this.onUpdate,
								scope : this
							},
							'template_View button[action=remove]' : {
								click : this.onRemove
							},
							'template_View button[action=link]' : {
								click : this.onGroupLink
							},
							'template_View button[action=confirm]' : {
								click : this.onAdd
							},
							'template_View button[action=apply]' : {
								click : this.onApply
							},
							'template_add field_paramElement_ParamBelongsRadioGroup' : {
								change : this.onChange
							},
							'template_updateTemplate field_paramElement_ParamBelongsRadioGroup' : {
								change : this.onChange2
							},
							'param_paramGrid':{
								afterrender:this.onRerender
							}

						});
					},
					
					onRerender:function( _this, eOpts ){
			    		var store = _this.getStore();
			    		var addedGrid = _this.up("window").down("param_addedParamGrid");
			    		var addedStore = addedGrid.getStore();
			    		store.on("load",function( _this, records, successful, eOpts){
				    		var addedSum = addedStore.getCount();
			    			for(var index=0;index<records.length;index++){
			    				for(var addedIndex = 0 ;addedIndex<addedSum;addedIndex++){
			    					if(records[index].get("id")==addedStore.getAt(addedIndex).get("id")){
			    						store.remove(records[index]);
			    					}
			    				}
			    			}
			    		},this);
			    	},

					onChange: function(_this, newValue, oldValue, eOpts){
//						var view = this.getEwayView();
						var grid = this.getTemplateGrid();

						var sm = grid.getSelectionModel();

						var record = sm.getLastSelected();
						var appSystem=this.getAddWin().down("field_paramElement_ParamBelongsRadioGroup").getValue().appSystem;
						var flag = 1;
//						var store = Ext.create('Eway.store.parameter.template.AddingParam');
						var store = this.getParamGrid().getStore();
						var storeAdded = this.getAddedParamGrid().getStore();
//						store.setUrlParamsByObject(paramBelongsRadioGroup);
						store.setBaseParam ('id',1);
						store.setBaseParam ('appSystem',appSystem);
						store.setBaseParam ('flag',flag);
						store.loadPage(1);
//						storeAdded.setUrlParamsByObject(appSystem);
//						storeAdded.setBaseParam ('id',record.get("id"));
//						storeAdded.setBaseParam ('flag',flag);
//						storeAdded.setBaseParam ('appSystem',appSystem);
//						storeAdded.loadPage(1);
					},
					onChange2 : function(_this, newValue, oldValue, eOpts){
						var grid = this.getTemplateGrid();

						var sm = grid.getSelectionModel();
						var flag = 0;

						var record = sm.getLastSelected();
						var appSystem=this.getUpdateWin().down("field_paramElement_ParamBelongsRadioGroup").getValue().appSystem;
//						var store = Ext.create('Eway.store.parameter.template.AddingParam');
						var store = this.getParamGrid().getStore();
						var storeAdded = this.getAddedParamGrid().getStore();
						store.setBaseParam('appSystem',appSystem);
						store.setBaseParam ('id',record.get("id"));
						store.setBaseParam ('flag',flag);
						store.loadPage(1);
//						storeAdded.setBaseParam('appSystem',appSystem);
//						storeAdded.setBaseParam ('id',record.get("id"));
//						storeAdded.setBaseParam ('flag',1);
//						storeAdded.loadPage(1);
					},

					onAdd : function(){
						this.onAddUpdate('add');
					},

					onUpdate : function(){
						this.onAddUpdate('update');
					},

					onAddUpdate: function(action) {

						var flag = '1';

						var paramGrid = this.getParamGrid();
						var addedParamGrid = this.getAddedParamGrid();
						if(action=='add'){

							var win = Ext.create('Eway.view.parameter.template.Add');
							win.down('button[action="add"]').on('click',Ext.bind(this.onAddUpdateConfirm,this,[win,action,templateId]),this);

							var templateId = "1";
							flag = '1';
							paramGrid = this.getParamGrid();
							addedParamGrid = this.getAddedParamGrid();
						}else{
							var grid = this.getTemplateGrid();
							var sm = grid.getSelectionModel();
							var record = sm.getLastSelected();
							if (sm.getCount() == 1) {

								var record = sm.getLastSelected();
								var win = Ext.create('Eway.view.parameter.template.UpdateTemplate');
								var templateId = record.data.id;
								win.down('form').getForm().loadRecord(record);
								win.down('button[action="confirm"]').on('click',Ext.bind(this.onAddUpdateConfirm,this,[win,action,templateId]),this);
								paramGrid = this.getParamGrid();
								addedParamGrid = this.getAddedParamGrid();
								flag = '0';
							}else{
								Eway.alert(EwayLocale.tip.paramTemplate.one);

							}

						}

						addedParamGrid.getStore().load(
								{
									params : {
										id : templateId,
										appSystem : 1,
										flag: flag
									},
									callback : function(records, operation,success) {

										if (success == false) {
											win.close();
										}
										else{
											paramGrid.getStore().load(
													{
														params : {
															id : templateId,
															appSystem : 1,
															flag: flag
														},
														callback : function(records,operation, success) {
															if (success == false) {
																Eway.alert(EwayLocale.tip.paramTemplate.failedElement);
																win.close();
															}
														}
													});
										}
										
									}
								});
						

						win.show();
					},

					onAddUpdateConfirm: function(win,action,templateId) {

						var addForm = win.down("form").getForm();
						var data = addForm.getValues();

						var grid = this.getAddedParamGrid();
						var store = grid.getStore();
						var tempRess = [];
						this.generateTemplateDetail(tempRess,store);
						var resources = '[';

						if(addForm.isValid()){
						for(var i in tempRess){
				    		var res = tempRess[i];
							var tempRes_str = "{'paramName':'" + res.data.paramName
							+ "','paramValue':'"+res.data.paramValue
							+ "','elementId':'"+res.data.elementId+"'}";

							if(resources == '['){
								resources = resources + tempRes_str;
							}else{
								resources = resources + "," + tempRes_str;
							}
				    	}
						resources = resources + "]";

						var record = store.getAt(i);

						if(action=='add'){
								var addWin = this.getAddWin();
								addWin.down('button[action="add"]').setDisabled(true);
								var form = win.down('form').getForm();
								if(form.isValid()){
									var temp = Ext.create("Eway.model.parameter.template.Template",{
							    		id : null,
							    		name: data.name,
							    		remark: data.remark,
							    		resources : resources

							    	});

									temp.save({
										 success: function(ed) {
											Eway.alert(EwayLocale.tip.paramTemplate.addSuccess);
											win.close();
											this.onQueryAfterOperate();
										 },
										 failure: function(record,operation){
											Eway.alert(EwayLocale.tip.paramTemplate.addFailure+operation.error);
										 },
										 scope : this
									});

								}else{

								}
						}
						else{
							var updateWin = this.getUpdateWin();
							updateWin.down('button[action="confirm"]').setDisabled(true);
							var temp = Ext.create("Eway.model.parameter.template.TemplateDetail",{
					    		name: data.name,
					    		remark: data.remark,
								templateId:templateId,
								resources : resources

					    	});

							temp.save({
								 success: function(ed) {
									 	Eway.alert(EwayLocale.tip.paramTemplate.updateSuccess);
									 	win.close();
										this.onQueryAfterOperate();
									 },
									 failure: function(record,operation){
										Eway.alert(EwayLocale.tip.paramTemplate.updateFailure+operation.error);
									 },
									 scope : this
								});


						}
					}

					},

					generateTemplateDetail : function(tempRess,store){
						for(var i = 0; i < store.getCount(); i++){

							var record = store.getAt(i);
							var tempDetail = Ext.create("Eway.model.parameter.template.TemplateDetailList",{
									elementId: record.data.id,
									paramName:record.data.paramName,
									paramValue:record.data.paramValue
								});

							tempRess.push(tempDetail);
						}
					},

					onApply :function(){
						var grid = this.getTemplateGrid();
						var sm = grid.getSelectionModel();

						if (sm.getCount() == 1) {
							var record = sm.getLastSelected();
							var templateId = record.data.id;
							Ext.Ajax.request({
								method : 'POST',
								url : 'api/parameter/template/issueParam',
								params : {
									templateId : templateId
								},
								success : function(response) {
									var object = Ext.decode(response.responseText);
									if(object.data){
									var jobId = object.data;
									Ext.MessageBox.confirm(EwayLocale.confirm.title,//'提示',
											EwayLocale.confirm.taskConfirmInfo0+jobId+EwayLocale.confirm.taskConfirmInfo2,
											function(button, text) {
												if (button == "yes") {
												var controller = this.parent.activeController('parameter.paramMonitor.ParamMonitor');
												controller.autoJobDetail(jobId);
												}
												},this);
									  }
									if (object.success == true) {
										this.onQueryAfterOperate();
									} else {
										Eway.alert(object.errorMsg);
										this.onQueryAfterOperate();
									}
									
								},
								failure : function() {
										Eway.alert(EwayLocale.tip.paramTemplate.applyFailure);
										this.onQueryAfterOperate();
								},
								scope : this
							});
						}
						else {
								Eway.alert(EwayLocale.tip.paramTemplate.one);
						}
					},

					onGroupLink : function() {
						var grid = this.getTemplateGrid();
						var sm = grid.getSelectionModel();
						if (sm.getCount() == 1) {
							var record = sm.getLastSelected();

							var templateDeviceWin = Ext.create('Eway.view.parameter.template.TemplateDevice');

							var linkedFilterForm = templateDeviceWin.down('parameter_linkedDeviceFilter');

							var linkedDeviceGrid = templateDeviceWin.down('parameter_linkedDeviceGrid');


							var linkingDeviceGrid = templateDeviceWin.down('parameter_linkingDeviceGrid');

							var linkingFilterForm = templateDeviceWin.down('parameter_linkingDeviceFilter');

							linkedDeviceGrid.down('button[action="unlink"]')
									.on('click',Ext.bind(this.onUnlinkConfirm,this, [ linkedDeviceGrid,linkingDeviceGrid,record ]), this);

							linkedDeviceGrid.down('button[action="query"]').on('click',

							Ext.bind(this.onQueryDevice, this, [ 0,linkedDeviceGrid, linkedFilterForm,record ]), this);


							linkedDeviceGrid.down('pagingtoolbar').on('beforechange',

							Ext.bind(this.onLinkDeviceFresh, this, [ 0,linkedDeviceGrid, linkedFilterForm,record ]), this);


							linkingDeviceGrid.down('button[action="link"]').on('click',Ext.bind(this.onLinkConfirm, this, [linkingDeviceGrid,linkedDeviceGrid, record ]), this);

							linkingDeviceGrid.down('button[action="query"]').on('click',

							Ext.bind(this.onQueryDevice, this,[ 1, linkingDeviceGrid,linkingFilterForm,record ]), this);

							linkingDeviceGrid.down('pagingtoolbar').on('beforechange',Ext.bind(this.onLinkDeviceFresh, this, [ 1,linkingDeviceGrid,linkingFilterForm, record ]), this);

							templateDeviceWin.show();
							linkingDeviceGrid.getStore().load({
								params : {
									flag : 1,
									guid : record.data.id,
									organizationId : record.data.orgId
								}
							});
							linkedDeviceGrid.getStore().load({
								params : {
									flag : 0,
									guid : record.data.id,
									organizationId : record.data.orgId
								}
							});
						} else {
								Eway.alert(EwayLocale.tip.paramTemplate.one);

						}
					},

					/**
					 * unlink
					 *
					 */
					onUnlinkConfirm : function(linkedDeviceGrid,linkingDeviceGrid, record) {

						var array = this.multiSelect(linkedDeviceGrid);
						if (array != null) {
							var info = '';
							for (var i = 0; i < array.length; i++) {
								info += array[i] + ',';
							}
							Ext.Ajax.request({

										scope : this,
										method : 'POST',
										url : 'api/parameter/template/unlink',
										params : {
											templateId : record.data.id,
											deviceId : info,
											id:record.data.id
										},
										success : function() {
											linkedDeviceGrid.getStore().load(

															{
																params : {
																	flag : 0,
																	guid : record.data.id,
																	organizationId : record.data.orgId
																}
															});
											linkingDeviceGrid.getStore().cleanUrlParam();

											linkingDeviceGrid.getStore().load(

															{
																params : {
																	flag : 1,
																	guid : record.data.id,
																	organizationId : record.data.orgId
																}
															});
										},
										failure : function() {
											Eway.alert(EwayLocale.tip.paramTemplate.unlinkedFailure);
										},
										scope : this
									});
						} else {
							Eway.alert(EwayLocale.tip.paramTemplate.one);
						}
					},

					/**
					 * link
					 *
					 * @param {}
					 *            linkingDeviceGrid
					 * @param {}
					 *            linkedDeviceGrid
					 * @param {}
					 *            record
					 */
					onLinkConfirm : function(linkingDeviceGrid,
							linkedDeviceGrid, record) {

						var array = this.multiSelect(linkingDeviceGrid);
						if (array != null) {
							linkingDeviceGrid.down('button[action="link"]')
									.disable();
							var data = new Object();
							var record2 = Ext.create('Eway.model.parameter.template.TemplateDevice',data);
							for (var i = 0; i < array.length - 1; i++) {
								record2.set('id', 0);
								record2.set('groupId', record.data.id);
								record2.set('deviceId', array[i]);
								record2.save();
							}
							record2.set('id', 0);
							record2.set('groupId', record.data.id);
							record2.set('deviceId', array[array.length - 1]);
							record2.save({
										success : function() {
											linkingDeviceGrid.getStore().load(
															{
																params : {
																	flag : 1,
																	guid : record.data.id,
																	organizationId : record.data.orgId
																},
																callback : function(records,operation,success) {
																		linkingDeviceGrid.down('button[action="link"]').enable();
																}
															});
											linkedDeviceGrid.getStore().cleanUrlParam();

											linkedDeviceGrid.getStore().load(
															{
																params : {
																	flag : 0,
																	guid : record.data.id,
																	organizationId : record.data.orgId
																}
															});
										},
										failure : function() {
											linkingDeviceGrid.down('button[action="link"]').enable();

											Eway.alert(EwayLocale.tip.paramTemplate.unlinkedFailure);

										},
										scope : this
									});
						} else {
							Eway.alert(EwayLocale.tip.paramTemplate.one);
						}
					},


					onLinkDeviceFresh : function(flag, grid, form, record) {
						var store = grid.getStore();
						store.cleanUrlParam();
						var data = form.getForm().getValues();
						store.setUrlParamsByObject(data);
						store.setBaseParam('flag', flag);
						store.setBaseParam('guid', record.data.id);
						store.setBaseParam('organizationId', record.data.orgId);
					},

					onQueryDevice : function(flag, grid, form, record) {
						var store = grid.getStore();
						store.cleanUrlParam();
						var data = form.getForm().getValues();
						store.setUrlParamsByObject(data);
						store.setBaseParam('flag', flag);
						store.setBaseParam('guid', record.data.id);
						store.setBaseParam('organizationId', record.data.orgId);
						store.loadPage(1);
					},

					/**
					 * id
					 *
					 * @param {}
					 *            grid
					 * @return {}
					 */
					multiSelect : function(grid) {
						var record = grid.getSelectionModel().getSelection();
						if (record == null || record.length == 0) {
							return null;
						}
						var array = new Array(record.length);
						for (var i = 0; i < record.length; i++) {
							array[i] = record[i].get('id');
						}
						return array;
					},

					onQueryAfterOperate : function(){
						var store = this.getGridPanel().getStore();
						store.setUrlParamsByObject();
						store.loadPage(1);
					}
				});
