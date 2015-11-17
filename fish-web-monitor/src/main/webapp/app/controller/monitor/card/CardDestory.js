Ext.define('Eway.controller.monitor.card.CardDestory', {
			extend : 'Eway.controller.base.FishController',

			stores : ['monitor.card.CardDestroy',
					'monitor.card.ActionStatus',
					'monitor.card.CardTypeComboBox',
					'monitor.card.InoutComboBox',
					'monitor.card.DeviceAtmVendor',
					'monitor.card.OrganizationComboBox',
					'monitor.card.DeviceType'],
			models : ['monitor.card.CardDestroy', 'Dict'],
			views : ['Eway.view.monitor.card.CardDestoryView'],

			refs : [{
						ref : 'ewayView',
						selector : 'card_CardDestoryView',
						autoCreate : true,
						xtype : 'card_CardDestoryView'
					}, {
						ref : 'cardDestoryFilterForm',
						selector : 'card_CardDestoryFilterForm'
					}, {
						ref : 'cardDestoryGrid',
						selector : 'card_CardDestoryGrid'
					}],
			init : function() {
				this.control({
							'card_CardDestoryGrid button[action=destory]' : {
								click : this.onDestory
							},
							'card_CardDestoryGrid button[action=query]' : {
								click : this.onQuery
							}
						});
			},

			onQuery : function() {
				var view = this.getEwayView();
				var values = view.down('form').getForm().getValues();
				var form = view.down('form').getForm();
				if(form.isValid() == true){
						/**
					 * 处理如果组织机构为空的时候的情况
					 */
//					if (Ext.isEmpty(values.orgName) || values.orgName == '') {
//						values.orgId = '';
//					}
					var store = view.down('gridpanel').getStore();
					store.setUrlParamsByObject(values);
					store.setBaseParam('organizationId', ewayUser.getOrgId());
					store.loadPage(1);
				}else{
					Eway.alert(EwayLocale.tip.input);
				}

			},

			onDestory : function() {
				var grid = this.getCardDestoryGrid();
				var store = grid.getStore();
				var sm = grid.getSelectionModel();
				var record = sm.getLastSelected();
				var view = this.getEwayView();
				var viewValues = view.down('form').getForm().getValues();
				if (sm.getCount() == 1) {
					Ext.MessageBox.confirm(EwayLocale.tip.confirm.title, EwayLocale.tip.business.card.destroyConfirm, function(button, text) {
								if (button == 'yes') {
									Ext.Ajax.request({
										scope : this,
										method : 'GET',
										url : 'api/monitor/retainCard/destory',
										params : {
											id : record.data.id,
											name : ewayUser.getName(),
											organizationId : ewayUser.getOrgId()
										},
										success : function(response) {
											var object = Ext.decode(response.responseText);
											if(object.success == true){
												Eway.alert(EwayLocale.tip.business.card.destroySuccess);
												store.remove(record);
												store.setUrlParamsByObject(viewValues);
												store.setBaseParam('organizationId',ewayUser.getOrgId());
												store.loadPage(1);
											}else{
												Eway.alert(object.errorMsg);
											}

										}
										});
								}
							}, this);
				} else {
					Eway.alert(EwayLocale.tip.business.card.chooseDestroy);
				}
			}

		});