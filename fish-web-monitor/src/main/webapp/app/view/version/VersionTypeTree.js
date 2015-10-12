Ext.define("Eway.view.version.VersionTypeTree",{
	extend : 'Ext.tree.Panel',
	alias : 'widget.versionTypeTree',
	
	store : 'version.VersionTypeTree',
	width : 280,
	border : false,
	padding : '0 2 0 0',
	rootVisible : true,
	
	dockedItems: [{
        xtype: 'toolbar',
        dock: 'top',
        items: [{
            text: Eway.locale.button.refresh,//刷新
            iconCls: 'refreshBtn',
            action: 'freshTree'
        },{
        	text : Eway.locale.button.add,//新增
        	glyph : 0xf067,
        	action : 'addVersionType',
        	code : 'versionTypeAdd',
			listeners:{
				'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
			}
        },{
        	text : Eway.locale.button.update,//更改
        	glyph : 0xf040,
        	action : 'updateVersionType',
        	code : 'versionTypeUpdate',
			listeners:{
				'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
			}
        },{
        	text : Eway.locale.button.remove,//'删除'
        	glyph : 0xf014,
        	action : 'removeVersionType',
        	code : 'versionTypeDel',
			listeners:{
				'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
			}
        }]	
	}]	
});