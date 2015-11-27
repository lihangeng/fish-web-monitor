Ext.define('Eway.controller.monitor.card.CardInfo', {
			extend : 'Eway.controller.base.FishController',

			stores : [	'monitor.card.CardInfo',
						'monitor.card.CardStatusComboBox',
						'monitor.card.DeviceAtmVendor',
						'monitor.card.DeviceType',
						'monitor.card.InoutComboBox'],

			models : [	'monitor.card.CardInfo', 'Dict'],
			views : ['Eway.view.monitor.card.CardInfoView'],

			addView : 'Eway.view.monitor.card.AddCardInfo',
			refs : [{
						ref : 'ewayView',
						selector : 'card_CardInfoView',
						autoCreate : true,
						xtype : 'card_CardInfoView'
					}, {
						ref : 'addWin',
						selector : 'card_AddCardInfo'
					}, {
						ref : 'cardInfoGrid',
						selector : 'card_CardInfoGrid'
					}, {
						ref : 'cardInfoFilterForm',
						selector : 'card_cardInfoFilterForm'
					}],
				init : function() {
				this.control({
							'card_CardInfoGrid button[action=add]' : {
								click : this.onAdd
							},
							'card_CardInfoGrid button[action=remove]' : {
								click : this.onRemove
							},
							'card_CardInfoGrid button[action=query]' : {
								click : this.onQuery
							},
							'card_CardInfoGrid button[action=export]' : {
								click : this.onExport
							}
						});
			},

			onExport : function(){
				var view = this.getEwayView();
				var form = view.down('form').getForm();
				var bool = form.isValid();
				// 查询输入验证
				if (bool == false) {
					Eway.alert(EwayLocale.tip.searchOfNoLegal);
					return
				}

               var values = form.getValues();
               var params = "?";
               for(var key in values){
            	   if(!Ext.isEmpty(values[key])){
            		   var prefix = params == "?" ? "" : "&";
            		   params = params + prefix + key + "=" + values[key];
            	   }
               }
			   window.location.href = 'api/monitor/retainCard/poiExcel'+params;
			},

			onAdd : function() {
				var win = Ext.create(this.addView);
				win.down('button[action="confirm"]').on('click',this.onAddConfirm, this);
				win.show();
			},

			onAddConfirm : function() {
				var win = this.getAddWin();
				var form = win.down('form');
				var data = win.down('form').getForm().getValues();
				var status = data.status;
				var accountNo = data.accountNo;
				var cardDistributionBank = data.cardDistributionBank;
				var cardRetainTime = data.cardRetainTime;
				var reason = data.reason;
				var terminalId = data.terminalId;
				var view = this.getEwayView();
				var viewValues = view.down('form').getForm().getValues();
				var record = Ext.create('Eway.model.monitor.card.CardInfo',data);

				record.set("userOrgId" , ewayUser.getOrgId());
				var bool = this.getAddWin().down('form').getForm().isValid();
				var store = this.getCardInfoGrid().getStore();
				if(data.cardRetainTime == ''){
					var field = form.down('datetimefield');
					field.setActiveError(EwayLocale.tip.notNull);
				}
				if (bool == true && data.cardRetainTime != '') {
					record.save({
						success : function(record,operation){
							Eway.alert(EwayLocale.addSuccess);
							win.close();
							    //点击增加成功后查询条件不带入重新查询。
								store.setUrlParamsByObject(null);
								store.setBaseParam('organizationId',ewayUser.getOrgId());
								store.loadPage(1);
						},
						failure : function(record,operation){
							Eway.alert(operation.getError());
						}
					});
				}
			},

			addRecord : function(data) {
				var record = Ext.create('Eway.model.monitor.card.CardInfo',data);
				this.getCardInfoGrid().getStore().add(record);
			},

			onQuery : function() {
				var view = this.getEwayView();
				var values = view.down('form').getForm().getValues();
				var form = view.down('form').getForm();
				if(form.isValid() == true){
					var store = view.down('gridpanel').getStore();
					store.setUrlParamsByObject(values);
					store.setBaseParam('organizationId',ewayUser.getOrgId());
					store.loadPage(1);
				}else{
					Eway.alert(EwayLocale.tip.search.warn);
				}

			},

			onRemove : function() {
				var me = this;
				var grid = this.getCardInfoGrid();
				var sm = grid.getSelectionModel();
				var record = sm.getLastSelected();
				var view = this.getEwayView();
				var store = this.getCardInfoGrid().getStore();
				var viewValues = view.down('form').getForm().getValues();
				if (sm.getCount() == 1) {
					Ext.Ajax.request({
						method: 'GET',
						url: 'api/monitor/retainCard/checkGuid',
						params:{
							id: record.data.id,
							organizationId : ewayUser.getOrgId()
						},
						success: function(response){
							var object = Ext.decode(response.responseText);
							if(object.success == true){
								Ext.MessageBox.confirm(EwayLocale.tip.confirm.title, EwayLocale.tip.confirm.info, function(
									button, text) {
								if (button == 'yes') {
									var record = sm.getLastSelected();
									me.removeRecord(record);
								}
							}, this);
								store.setUrlParamsByObject(viewValues);
								store.setBaseParam('organizationId',ewayUser.getOrgId());
								store.loadPage(1);
							}else{
								Eway.alert(object.errorMsg);
							}
						}
					});

				} else {
					Eway.alert(EwayLocale.choiceDeleteMsg);
				}
			},

			removeRecord : function(record) {
				var grid = this.getCardInfoGrid();
				var sm = grid.getSelectionModel();
				var record = sm.getLastSelected();
				record.erase({
					success: function(record,operation){
						Eway.alert(EwayLocale.deleteSuccess);
						grid.getStore().remove(record);
					},
					failure: function(record,operation){
						//删除失败后，再次执行save操作时，会依据dropped属性判断执行什么操作，if true再次执行earse操作，false 则执行update
						record.dropped = false;
						Eway.alert(operation.getError());
					},
					scope:this
				});
			}

		});