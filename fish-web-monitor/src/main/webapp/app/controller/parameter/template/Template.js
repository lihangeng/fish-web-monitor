Ext.define('Eway.controller.parameter.template.Template', {
			extend : 'Eway.controller.base.FishController',

			stores : ['parameter.template.Template'],
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
			}
});