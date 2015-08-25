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
				text : '查询',
				glyph : 0xf002,
				action : 'query'
			},{
				text : '批量导入',
				iconCls : 'importBtn',
				action : 'import',
				code : 'vendorCodeImport',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},
			{
				  text:'模板下载',
				  iconCls : 'exportBtn',
				  code : 'vendorCodeDownLoad',
				  handler:function(){
                      var requestName ="fault message.xls"
                      requestName = requestName.replace("&","%26");//将文件名含有&符号的用URL编码“%26”替换
                      window.location.href = 'api/case/vendorCode/downloadFile?fileName=' + requestName ;
                   }
			},
			{
				text : '批量删除',
				glyph : 0xf014,
				action : 'remove',
				code : 'vendorCodeDelete',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
			}
			}],
			columns : [{
				header : '厂商',
				dataIndex : 'vendorName',
				flex : 2
			},{
				header : '厂商故障码',
				dataIndex : 'code',
				flex : 2
			},{
				header : '厂商故障描述',
				dataIndex : 'description',
				flex : 4
			},{
				header : '解决方案',
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