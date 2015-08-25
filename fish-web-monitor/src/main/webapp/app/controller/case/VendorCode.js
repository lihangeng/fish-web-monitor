Ext.define('Eway.controller.case.VendorCode',{
	extend : 'Eway.controller.base.FishController',

	stores : ['case.VendorCode',
			'monitor.card.DeviceAtmVendor'],

	views : ['Eway.view.case.vendorCode.VendorCodeView'],

	refs : [{
		ref : 'ewayView',
		selector : 'vendorCode_VendorCodeView',
		autoCreate : true,
		xtype : 'vendorCode_VendorCodeView'
	},{
		ref : 'vendorCodeGrid',
		selector : 'vendorCode_VendorCodeGrid'
	},{
		ref : 'importWin',
		selector : 'vendorCode_ImportVendorCode'
	},{
		ref : 'rmWin',
		selector : 'vendorCode_RemoveWin'
	}],

	init : function(){
		this.control({
			'vendorCode_VendorCodeView button[action=query]' : {
				click : this.onQuery
			},
			'vendorCode_VendorCodeView button[action=remove]' : {
				click : this.onRemove
			},
			'vendorCode_VendorCodeView button[action=import]' : {
				click : this.onImport
			}
		});
	},

	onImport : function(){
		var win = Ext.create('Eway.view.case.vendorCode.ImportVendorCode');
		this.win = win;
		win.down('button[action="import"]').on('click',this.onImportConfirm,this);
		win.show();
	},

	onImportConfirm :function(){
		var win = this.win;
		var importForm = this.getImportWin().down("form").getForm();
		var vendor = win.down('common_orgComboOrgTree').getValue();
		if(importForm.isValid()){
			Ext.Msg.wait("正在导入文件");
			importForm.submit({
				url : 'api/case/vendorCode/import',
				params : {
					vendor : vendor
				},
				success : function(form, action){
					Ext.Msg.hide();
					win.close();
					var store = this.getVendorCodeGrid().getStore();
					store.load();
					Eway.alert("导入厂商故障信息成功.");
				},
				failure :function(form, action){
					Eway.alert(action.result.content);
				},
				scope :this
			});
		}
	},

	onRemove : function(){
		var rmWin = Ext.create('Eway.view.case.vendorCode.RemoveWin');
		this.rmWin = rmWin;
		rmWin.down('button[action="delete"]').on('click',this.onDelete,this);
		rmWin.show();
	},

	onDelete :function(){
		var rmWin = this.rmWin;
		var form = this.getRmWin().down('form').getForm();
		var vendor = rmWin.down('common_orgComboOrgTree').getValue();
		if(form.isValid()){
			form.submit({
				url : 'api/case/vendorCode/remove',
				params : {
					vendor : vendor
				},
				success : function(form, action){
					rmWin.close();
					var store = this.getVendorCodeGrid().getStore();
					store.load();
					Eway.alert(Eway.deleteSuccess);
				},
				failure : function(form, action){
					Eway.alert(action.result.content);
				},
				scope : this
			});
		}

	}


});