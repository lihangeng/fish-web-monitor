Ext.define('Eway.controller.parameter.template.Template', {
			extend : 'Eway.controller.base.FishController',

			stores : ['parameter.template.Template','machine.DeviceAtmType','machine.atmType.DeviceAtmCatalog'],
			models : ['parameter.template.Template'],

			views : ['parameter.template.View'],

			refs : [{
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
						selector : 'template_update'
					}
					],

			formConfig : {
						form : 'Eway.view.parameter.template.Form',
						xtype : 'parameter_template_form',
						width: 500,
						height:280,
						title : EwayLocale.machine.atmBrand.title
					},
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
							'template_View button[action=update]' : {
								click : this.onUpdate,
								scope : this
							},
							'template_View button[action=remove]' : {
								click : this.onRemove
							},
							'template_View button[action=link]' : {
								click : this.onGroupLink
							}
						});
			},
						
			onGroupLink : function(){
				var grid = this.getTemplateGrid();
				var sm = grid.getSelectionModel();
				if(sm.getCount() == 1) {
					var record = sm.getLastSelected();
					
					var templateDeviceWin = Ext.create('Eway.view.parameter.template.TemplateDevice');
					var linkedFilterForm = templateDeviceWin.down('parameter_linkedDeviceFilter');
					var linkedDeviceGrid = templateDeviceWin.down('parameter_linkedDeviceGrid');

					var linkingDeviceGrid = templateDeviceWin.down('parameter_linkingDeviceGrid');
					var linkingFilterForm = templateDeviceWin.down('parameter_linkingDeviceFilter');

					linkedDeviceGrid.down('button[action="unlink"]').on('click',
							Ext.bind(this.onUnlinkConfirm,this,[linkedDeviceGrid,linkingDeviceGrid,record]),this
						);
					linkedDeviceGrid.down('button[action="query"]').on('click',
							Ext.bind(this.onQueryDevice,this,[0,linkedDeviceGrid,linkedFilterForm,record]),this
						);
					linkedDeviceGrid.down('pagingtoolbar').on('beforechange',
							Ext.bind(this.onLinkDeviceFresh,this,[0,linkedDeviceGrid,linkedFilterForm,record]),this
						);
					linkingDeviceGrid.down('button[action="link"]').on('click',
							Ext.bind(this.onLinkConfirm,this,[linkingDeviceGrid,linkedDeviceGrid,record]),this
						);
					linkingDeviceGrid.down('button[action="query"]').on('click',
							Ext.bind(this.onQueryDevice,this,[1,linkingDeviceGrid,linkingFilterForm,record]),this
						);
					linkingDeviceGrid.down('pagingtoolbar').on('beforechange',
							Ext.bind(this.onLinkDeviceFresh,this,[1,linkingDeviceGrid,linkingFilterForm,record]),this
					);

					templateDeviceWin.show();
					linkingDeviceGrid.getStore().load({
						params : {
							flag:1,
							guid:record.data.id,
							organizationId:record.data.orgId
						}
					});
					linkedDeviceGrid.getStore().load({
						params : {
							flag:0,
							guid:record.data.id,
							organizationId:record.data.orgId
						}
					});
				}
				else {
					Eway.alert(EwayLocale.tip.bankPer.link.linkBankPerson);
				}
			},
			/**
			 * 解除关联
			 * @param {} linkedDeviceGrid
			 * @param {} linkingDeviceGrid
			 * @param {} record
			 */
			onUnlinkConfirm : function(linkedDeviceGrid,linkingDeviceGrid,record){
				var array = this.multiSelect(linkedDeviceGrid);
				if(array != null) {
					var info = '';
					for(var i=0;i<array.length;i++){
		                    info += array[i] + ',';
		            }
					Ext.Ajax.request({
						scope : this,
						method : 'POST',
						url : 'api/parameter/template/unlink',
						params : {groupId :record.data.id,deviceId:info},
						success: function(){
							linkedDeviceGrid.getStore().load({
								params : {
									flag:0,
									guid:record.data.id,
									organizationId:record.data.orgId
								}
							});
							linkingDeviceGrid.getStore().cleanUrlParam();
							linkingDeviceGrid.getStore().load({
								params : {
									flag:1,
									guid:record.data.id,
									organizationId:record.data.orgId
								}
							});
						},
						failure: function(){
							Eway.alert(EwayLocale.tip.bankPer.link.unLinkDevFail);
						},
						scope:this
					});
				}else{
					Eway.alert(EwayLocale.tip.bankPer.link.unlinkDev);
				}
			},

			/**
			 * 进行关联
			 * @param {} linkingDeviceGrid
			 * @param {} linkedDeviceGrid
			 * @param {} record
			 */
			onLinkConfirm : function(linkingDeviceGrid,linkedDeviceGrid,record){

				var array = this.multiSelect(linkingDeviceGrid);
				if(array != null) {
					linkingDeviceGrid.down('button[action="link"]').disable();
					var data = new Object();
					var record2 = Ext.create('Eway.model.parameter.template.TemplateDevice',data);
					for(var i=0;i<array.length-1;i++){
		            	record2.set('id',0);
						record2.set('groupId',record.data.id);
						record2.set('deviceId',array[i]);
						record2.save();
		            }
					record2.set('id',0);
					record2.set('groupId',record.data.id);
					record2.set('deviceId',array[array.length-1]);
					record2.save({
						success: function(){
							linkingDeviceGrid.getStore().load({
								params : {
									flag:1,
									guid:record.data.id,
									organizationId:record.data.orgId
								},
								callback: function(records, operation, success) {
									linkingDeviceGrid.down('button[action="link"]').enable();
								}
							});
							linkedDeviceGrid.getStore().cleanUrlParam();
							linkedDeviceGrid.getStore().load({
								params : {
									flag:0,
									guid:record.data.id,
									organizationId:record.data.orgId
								}
							});
						},
						failure: function(){
							linkingDeviceGrid.down('button[action="link"]').enable();
							Eway.alert(EwayLocale.tip.bankPer.link.unLinkPersonFail);
						},
						scope:this
					});
				}else{
					Eway.alert(EwayLocale.tip.bankPer.link.linkDev);
				}
			},
			
			//刷新“已/可关联的设备”列表
			onLinkDeviceFresh : function(flag,grid,form,record){
				var store = grid.getStore();
				store.cleanUrlParam();
				var data = form.getForm().getValues();
				store.setUrlParamsByObject(data);
				store.setBaseParam('flag',flag);
				store.setBaseParam('guid',record.data.id);
				store.setBaseParam('organizationId',record.data.orgId);
			},

			onQueryDevice : function(flag,grid,form,record){
				var store = grid.getStore();
				store.cleanUrlParam();
				var data = form.getForm().getValues();
				store.setUrlParamsByObject(data);
				store.setBaseParam('flag',flag);
				store.setBaseParam('guid',record.data.id);
				store.setBaseParam('organizationId',record.data.orgId);
				store.loadPage(1);
			},

			/**
			 * 获得选中的行id数组：
			 * @param {} grid
			 * @return {}
			 */
			multiSelect : function(grid){
				var record=grid.getSelectionModel().getSelection();
		        if(record == null || record.length == 0){
		        	return null;
		        }
		        var array = new Array(record.length);
		        for(var i=0;i<record.length;i++)
		        {
		            array[i] = record[i].get('id');
		        }
		        return array;
			}
});