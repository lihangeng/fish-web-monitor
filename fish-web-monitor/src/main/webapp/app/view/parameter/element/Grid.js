
Ext.define('Eway.view.parameter.element.Grid', {
	alias: 'widget.element_Grid',
	extend: 'Eway.view.base.Grid',

	border : false,
	autoFit:true,

	initComponent: function() {
		var store = Ext.create('Eway.store.parameter.element.Element');
		store.loadPage(1);
		Ext.apply(this,{
			initRegion : true,
			store : store,
			tbar: ['->',{
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: EwayLocale.button.add,
				glyph : 0xf067,
				action: 'add',
				code : 'elementAdd',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.button.update,
				glyph : 0xf040,
				action:'update',
				code : 'elementUpdate',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [{
				header : EwayLocale.param.element.paramName,
				dataIndex : 'paramName',
				flex : 1
			},{
				header : EwayLocale.param.element.paramValue,
				dataIndex : 'paramValue',
				flex : 2
			},{
				header : EwayLocale.param.element.paramType,
				dataIndex : 'paramType',
				flex : 1,
				width :180,
				sortable:true,
				renderer:function(value,metadata,record){
					if(value==1){
						return EwayLocale.param.element.integer;
					}
					if(value==2){
						return EwayLocale.param.element.character;
					}
				}
			},{
				header : EwayLocale.param.element.paramClassify,
				dataIndex : 'paramClassify',
				flex : 2
			},{
				header : EwayLocale.param.element.paramRights,
				dataIndex : 'paramRights',
				flex : 1,
				width :180,
				sortable:true,
				renderer:function(value,metadata,record){
					if(value==1){
						return EwayLocale.param.element.editable;
					}
					if(value==2){
						return EwayLocale.param.element.uneditable;
					}
				}
			},{
				header : EwayLocale.param.element.paramBelongs,
				dataIndex : 'paramBelongsName',
				flex : 2,
				width :180,
			},{
				header : EwayLocale.param.element.createTime,
				dataIndex : 'createTime',
				flex : 2
			},{
				header : EwayLocale.param.element.lastModifyTime,
				dataIndex : 'lastModifyTime',
				flex : 2
			},
			{
					header:EwayLocale.param.element.versionNo,
					dataIndex:'versionNo',
					hidden:true,
					flex: 2
			},
			{
				header : EwayLocale.param.element.remark,
				dataIndex : 'remark',
				flex : 2
			}
			],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	onReload: function() {
		this.getStore().load();
	}
});