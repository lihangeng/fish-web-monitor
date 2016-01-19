Ext.define('Eway.controller.monitor.business.BlackListCard', {
			extend : 'Eway.controller.base.FishController',

			stores : ['monitor.business.BlackListCard'],
			models : ['monitor.business.BlackListCard'],

			views : ['Eway.view.monitor.business.BlackListCardView'],

			refs : [{
						ref : 'ewayView',
						selector: 'business_BlackListCardView',
						autoCreate : true,
						xtype : 'business_BlackListCardView'
					}, {
						ref : 'filterForm',
						selector : 'business_BlackListCardFilterForm'
					}, {
						ref : 'grid',
						selector : 'business_BlackListCardGrid'
					},{
						ref : 'addWin',
						selector : 'business_AddBlackListCard'
					},{
						ref : 'importWin',
						selector : 'business_ImportBlackListCard'
					}],


			init : function() {
				this.control({'business_BlackListCardView button[action=query]' :{
								click : this.onQuery
							},
							'business_BlackListCardView button[action=add]': {
								click: this.onAdd
							},
							'business_BlackListCardView button[action=update]': {
								click: this.onUpdate
							},
							'business_BlackListCardView button[action=remove]' : {
								click : this.onRemove
							},
							'business_BlackListCardView button[action=import]' : {
								click : this.onImport
							}
						});
			},

			onAdd: function() {
				var win = Ext.create('Eway.view.monitor.business.AddBlackListCard');
				this.win = win;
				win.down('button[action="add"]').on('click',this.onAddConfirm,this);
				win.show();
			},

			onAddConfirm: function() {
				var win = this.win;
				var addForm =win.down("form").getForm();
				data = addForm.getValues();
				var record = Ext.create('Eway.model.monitor.business.BlackListCard',data);
				var store = this.getGrid().getStore();
				var view = this.getEwayView();
				var viewValues = view.down('form').getForm().getValues();
				if(addForm.isValid()){
					record.save({
						success : function(record,operation){
								store.load();
								Eway.alert(EwayLocale.addSuccess);
								win.close();
								store.setUrlParamsByObject(viewValues);
								store.loadPage(1);
					    },
					    failure: function(record,operation){
							Eway.alert(operation.getError());
						},
						button:win.down('button[action="add"]')
					});
				}
			},

			onUpdate : function(){
				var grid = this.getGrid();
				var sm = grid.getSelectionModel();
				if(sm.getCount() == 1) {
					var win = Ext.create('Eway.view.monitor.business.Update');
					var record = sm.getLastSelected();
					win.down('form').getForm().loadRecord(record);
					var button = win.query('button[action=confirm]')[0];
					button.on('click',Ext.bind(this.onUpdateConfirm,this,[win]), this);
					win.show();
				}
				else {
					Eway.alert(EwayLocale.choiceUpdateMsg);
				}
			},

			onUpdateConfirm : function(win){
				var sm = this.getGrid().getSelectionModel();
				var record = sm.getLastSelected();
				var view = this.getEwayView();
				var store = this.getGrid().getStore();
				var viewValues = view.down('form').getForm().getValues();
				if(win.down('form').getForm().isValid()){
					var data = win.down('form').getForm().getValues();
					record.set('cardNo',data.cardNo);
					record.set('userName',data.userName);
					record.set('organization',data.organization);
					record.set('addDate',data.addDate);
					record.save({
						success : function(record,operation){
							Eway.alert(EwayLocale.updateSuccess);
							win.close();
							store.setUrlParamsByObject(viewValues);
							store.loadPage(1);
					    },
					    failure: function(record,operation){
					    	store.rejectChanges();
							Eway.alert(operation.getError());
						},
						button:win.down('button[action="confirm"]')
					});
				}
			},

			onRemove: function() {
				var grid = this.getGrid();
				var sm = grid.getSelectionModel();
				var store = this.getGrid().getStore();
				var view = this.getEwayView();
				var viewValues = view.down('form').getForm().getValues();
				if(sm.getCount() == 1) {
					Ext.MessageBox.confirm(EwayLocale.tip.confirm.title,
							EwayLocale.tip.confirm.info,
							function(button,text) {
								if(button=="yes"){
									var record = sm.getLastSelected();
									record.erase({
										success: function(){
											Eway.alert(EwayLocale.deleteSuccess);
											grid.down('pagingtoolbar').doRefresh();
											store.setUrlParamsByObject(viewValues);
											store.loadPage(1);
										},
										failure: function(record,operation){
											//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
											record.dropped = false;
											Eway.alert(operation.getError());
										},
										scope:this
									});
								}
							}, this);
				}
				else {
					Eway.alert(EwayLocale.choiceDeleteMsg);
				}
			},

			onImport: function() {
				var win = Ext.create('Eway.view.monitor.business.ImportBlackListCard');
				this.win = win;
				win.down('button[action="import"]').on('click',this.onImportConfirm,this);
				win.down('button[action="down"]').on('click',this.onLoad,this);
				win.show();
			},

			onImportConfirm: function() {
				var win = this.win;
				var importForm = this.getImportWin().down("form").getForm();
				if(importForm.isValid()){
					Ext.Msg.wait(EwayLocale.tip.business.blackList.importing);
					importForm.submit({
					 	url: 'api/monitor/blacklistcard/import',
					    success: function(form, action) {
					    	Ext.Msg.hide();
							win.close();
							var store = this.getGrid().getStore();
							store.load();
							Eway.alert(EwayLocale.tip.business.blackList.importSuccess);

					    },
					    failure: function(form, action) {
					    	//form.findField("file").setRawValue('');
					    	Eway.alert(action.result.content);

					    },
					    scope: this
					});
				}
			},
			onLoad : function(){
				window.location.href = 'api//monitor/blacklistcard/downloadFile';
			}
		});