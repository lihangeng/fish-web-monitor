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
							'template_View button[action=updateElement]' : {
								click : this.onUpdateElement,
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
							}
						});
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
							if (sm.getCount() == 1) {

								var record = sm.getLastSelected();
								var win = Ext.create('Eway.view.parameter.template.UpdateTemplate');
								var templateId = record.data.id;
								win.down('button[action="confirm"]').on('click',Ext.bind(this.onAddUpdateConfirm,this,[win,action,templateId]),this);
								paramGrid = this.getParamGrid();
								addedParamGrid = this.getAddedParamGrid();
								flag = '0';
							}
							
						}
						
						addedParamGrid.getStore().load(
								{
									params : {
										id : templateId,
										flag: flag
									},
									callback : function(records, operation,success) {
											
										if (success == false) {
											win.close();
										}
									}
								});
						paramGrid.getStore().load(
										{
											params : {
												id : templateId,
												flag: flag
											},
											callback : function(records,operation, success) {
												if (success == false) {
													Eway.alert(EwayLocale.tip.user.update.fail);
													win.close();
												}
											}
										});

						win.show();
					},
					
					onAddUpdateConfirm: function(win,action,templateId) {
						var ewayView = this.getAddWin();
						var addForm = win.down("form").getForm();
						var data = addForm.getValues();
						
						var grid = this.getAddedParamGrid();
						var store = grid.getStore();
						var tempRess = [];
						this.generateTemplateDetail(tempRess,store);
						var resources = '[';
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
											Eway.alert('新增模板成功');
											win.close();
											this.onQueryAfterOperate();
										 },
										 failure: function(record,operation){
											Eway.alert('新增模板失败 '+operation.error);
										 },
										 
										 scope : this
									});
										
								}else{
									
								}
						}
						else{
							var temp = Ext.create("Eway.model.parameter.template.TemplateDetail",{
								
								templateId:templateId,
								resources : resources
					    		
					    	});
							
							temp.save({
								 success: function(ed) {
									 	Eway.alert('修改成功');
									 },
									 failure: function(record,operation){
										Eway.alert('修改失败');
									 },
									 scope : this
								});
							win.close();
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
									if (object.success == true) {
										Eway.alert('应用模板成功');
										this.onQueryAfterOperate();
									} else {
										Eway.alert('应用模板失败');
										this.onQueryAfterOperate();
									}
								},
								failure : function() {
										Eway.alert('应用模板失败');
										this.onQueryAfterOperate();
								},
								scope : this
							});
						}
						else {
								Eway.alert(EwayLocale.tip.bankPer.link.linkBankPerson);
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
								Eway.alert(EwayLocale.tip.bankPer.link.linkBankPerson);
									
						}
					},
					
					/**
					 * 解除关联
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
											Eway.alert(EwayLocale.tip.bankPer.link.unLinkDevFail);
										},
										scope : this
									});
						} else {
							Eway.alert(EwayLocale.tip.bankPer.link.unlinkDev);
						}
					},

					/**
					 * 进行关联
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
													
											Eway.alert(EwayLocale.tip.bankPer.link.unLinkPersonFail);
													
										},
										scope : this
									});
						} else {
							Eway.alert(EwayLocale.tip.bankPer.link.linkDev);
						}
					},

					// 刷新“已/可关联的设备”列表
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
					 * 获得选中的行id数组：
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