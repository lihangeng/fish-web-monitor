Ext.define('Eway.controller.monitor.card.CardAction', {
	extend : 'Eway.controller.base.FishController',

	stores : ['monitor.card.CardAction', 'monitor.card.ActionStatus','monitor.card.CardStatusComboBox',
			'monitor.card.CardTypeComboBox', 'monitor.card.InoutComboBox',
			'monitor.card.DeviceAtmVendor',
			'monitor.card.OrganizationComboBox', 'monitor.card.DeviceType'],

	models : ['monitor.card.CardInfo', 'Dict','monitor.card.CardAction'],
	views : ['Eway.view.monitor.card.CardActionView'],

	ReceiveCardWin : 'Eway.view.monitor.card.ReceiveCardWin',
	CardHandoverWin : 'Eway.view.monitor.card.CardHandoverWin',

	refs : [{
				ref : 'ewayView',
				selector : 'card_CardActionView',
				autoCreate : true,
				xtype : 'card_CardActionView'
			}, {
				ref : 'cardActionFilterForm',
				selector : 'card_CardActionFilterForm'
			}, {
				ref : 'cardActionGrid',
				selector : 'card_CardActionGrid'
			}, {
				ref : 'receiveCardWin',
				selector : 'card_ReceiveCardWin'
			}, {
				ref : 'cardHandoverWin',
				selector : 'card_CardHandoverWin'
			}],
	init : function() {
		this.control({
					'card_CardActionGrid button[action=receive]' : {
						click : this.onReceive
					},
					'card_CardActionGrid button[action=handover]' : {
						click : this.onHandover
					},
					'card_CardActionGrid button[action=query]' : {
						click : this.onQuery
					}
				});
	},



	onHandover : function() {
		var me = this;
		var grid = this.getCardActionGrid();
		var sm = grid.getSelectionModel();
		var record = sm.getLastSelected();
		var cardHandoverWin = this.CardHandoverWin;
		if (sm.getCount() == 1) {
			Ext.Ajax.request({
				method: 'GET',
				url: 'api/monitor/retainCard/checkGuid',
				params:{
					id: record.data.id,
					organizationId : Eway.user.getOrgId()
				},
				success: function(response){
					var object = Ext.decode(response.responseText);
					if(object.success == true){
						var win = Ext.create(cardHandoverWin);
						record.data.treatmentPeople = Eway.user.getName();
						win.down('form').getForm().loadRecord(record);
						var button = win.query('button[action=confirm]')[0];
						button.on('click', me.onHandoverConfirm, me);
						win.show();
					}else{
						Eway.alert(object.errorMsg);
					}
				},
				failure: function(response){
					Eway.alert(EwayLocale.tip.business.card.returnFail);
				}

			});
		} else {
			Eway.alert(EwayLocale.tip.business.card.choose);
		}
	},

	onHandoverConfirm : function() {
		var grid = Ext.ComponentQuery.query('card_CardActionGrid')[0];
		var store = grid.getStore();
		var sm = grid.getSelectionModel();
		var record = sm.getLastSelected();
		var bool = this.getCardHandoverWin().down('form').getForm().isValid();
		var form = this.getCardHandoverWin().down('form').getForm();
		var view = this.getEwayView();
		var viewValues = view.down('form').getForm().getValues();
		var values = form.getValues();
		if (bool == true) {
			Ext.Ajax.request({
				method : 'GET',
				url : 'api/monitor/retainCard/handover',
				params : {
					organizationId : Eway.user.getOrgId(),//当前登录用户所属的机构Id
					id : record.data.id,
					orgGuid : values.orgGuid,
					status : values.status,
					treatmentPeople : Eway.user.getName()
				},
				success : function(response) {
					var object = Ext.decode(response.responseText);
					if(object.success == true){
						Eway.alert(EwayLocale.tip.business.card.returnSucess);
						var win = Ext.ComponentQuery.query('card_CardHandoverWin')[0];
						win.close();
						store.setUrlParamsByObject(viewValues);
						store.setBaseParam('organizationId',Eway.user.getOrgId());
						store.loadPage(1);
					}else{
						Eway.alert(object.errorMsg);
					}
				}
			});
		} else {
		}
	},

	onReceive : function() {
		var grid = Ext.ComponentQuery.query('card_CardActionGrid')[0];
		var sm = grid.getSelectionModel();
		var receiveCardWin = this.ReceiveCardWin;
		var record = sm.getLastSelected();
		var me = this;
		if (sm.getCount() == 1) {
			Ext.Ajax.request({
				method: 'GET',
				url: 'api/monitor/retainCard/checkGuidReveice',
				params:{
					id: record.data.id,
					organizationId : Eway.user.getOrgId()
				},
				success: function(response){
					var object = Ext.decode(response.responseText);
					var errorMsg = object.errorMsg;
					if(object.success == true){
						var win = Ext.create(receiveCardWin);
						var record = sm.getLastSelected();
						record.data.treatmentPeople = Eway.user.getName();
						win.down('form').getForm().loadRecord(record);
						var button = win.query('button[action=confirm]')[0];
						button.on('click', me.onReceiveConfirm, me);
						win.show();
					}else{
						Eway.alert(errorMsg);
					}

				}
			});
//			var win = Ext.create(this.ReceiveCardWin);
//			var record = sm.getLastSelected();
//			record.data.treatmentPeople = Eway.user.name;
//			win.down('form').getForm().loadRecord(record);
//			var button = win.query('button[action=confirm]')[0];
//			button.on('click', this.onReceiveConfirm, this);
//			win.show();
		} else {
			Eway.alert(EwayLocale.tip.business.card.chooseBack);
		}
	},

	onReceiveConfirm : function() {
		var grid = Ext.ComponentQuery.query('card_CardActionGrid')[0];
		var store = grid.getStore();
		var sm = grid.getSelectionModel();
		var record = sm.getLastSelected();
		var form = this.getReceiveCardWin().down('form').getForm();
		var values = form.getValues();
		var bool = this.getReceiveCardWin().down('form').getForm().isValid();
		var view = this.getEwayView();
		var viewValues = view.down('form').getForm().getValues();
		if (bool == true) {
			Ext.Ajax.request({
				method : 'GET',
				url : 'api/monitor/retainCard/receive',
				params : {
					organizationId : Eway.user.getOrgId(),//当前登录用户所属的机构Id
					id : record.data.id,
					cardType : values.cardType,
					customerName : values.customerName,
					customerPapers : values.customerPapers,
					customerPhone : values.customerPhone,
					status : values.status,
					treatmentPeople : Eway.user.getName()
				},
				success : function(response) {
					var object = Ext.decode(response.responseText);
					if(object.success == true){
						Eway.alert(EwayLocale.tip.business.card.getSuccess);
						var win = Ext.ComponentQuery.query('card_ReceiveCardWin')[0];
						win.close();
						store.remove(record);
						store.setUrlParamsByObject(viewValues);
						store.setBaseParam('organizationId',Eway.user.getOrgId());
						store.loadPage(1);
					}else if(object.success == false){
						Eway.alert(object.errorMsg);
					}

				}
			});
		} else {
		}
	},

	onQuery : function() {
		var view = this.getEwayView();
		var values = view.down('form').getForm().getValues();
		var form = view.down('form').getForm();
		if(form.isValid() == true){
			/**
		 * 处理如果组织机构为空的时候的情况
		 */
//			if(Ext.isEmpty(values.orgName)||values.orgName==''){
//				values.orgId = '';
//			}
			var store = view.down('gridpanel').getStore();
			store.setUrlParamsByObject(values);
			store.setBaseParam('organizationId',Eway.user.getOrgId());
			store.loadPage(1);
		}else{
			Eway.alert(EwayLocale.tip.input);
		}

	}

});