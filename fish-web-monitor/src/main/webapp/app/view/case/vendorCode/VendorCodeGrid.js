Ext.define('Eway.view.case.vendorCode.VendorCodeGrid', {
	alias : 'widget.vendorCode_VendorCodeGrid',
	extend : 'Eway.view.base.Grid',

	border : false,

	initComponent : function(){
		var store = Ext.create('Eway.store.case.VendorCode');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar : ['->',{
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			},{
				text : EwayLocale.button.massExport,
				iconCls : 'importBtn',
				action : 'import',
				code : 'vendorCodeImport',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},
			{
				  text:EwayLocale.cases.vendorCode.templateLoad,
				  glyph : 0xf1c3,
				  code : 'vendorCodeDownLoad',
				  handler:function(){
                      var requestName =EwayLocale.cases.vendorCode.formwork
                      requestName = requestName.replace("&","%26");//将文件名含有&符号的用URL编码“%26”替换
                      window.location.href = 'api/case/vendorCode/downloadFile?fileName=' + requestName ;
                   }
			},
			{
				text : EwayLocale.cases.vendorCode.massRemove,
				glyph : 0xf014,
				action : 'remove',
				code : 'vendorCodeDelete',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
			}
			}],
			columns : [{
				header : EwayLocale.cases.vendorCode.provider,
				dataIndex : 'vendorName',
				flex : 2
			},{
				header : EwayLocale.cases.caseFault.providerFaultCode,
				dataIndex : 'code',
				flex : 2
			},{
				header : EwayLocale.cases.vendorCode.providerDescription,
				dataIndex : 'description',
				flex : 4
			},{
				header : EwayLocale.cases.vendorCode.solveProject,
				dataIndex : 'solution',
				flex : 4
			}],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	}

});