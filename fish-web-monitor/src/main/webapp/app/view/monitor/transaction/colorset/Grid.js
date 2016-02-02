Ext.define('Eway.view.monitor.transaction.colorset.Grid', {
	extend : 'Eway.view.base.Grid',
	alias : 'widget.monitor_transaction_colorset_grid',

	requires : [],

	border : false,

	initComponent : function() {
		var store = Ext.create('Eway.store.monitor.transaction.colorset.ColorSet');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query'
			}, {
				text : EwayLocale.button.add,
				glyph : 0xf067,
				action : 'add',
				code : 'transactionColorAdd',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text :EwayLocale.button.update,
				glyph : 0xf040,
				action : 'update',
				code : 'transactionColorUpdate',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}, {
				text : EwayLocale.button.remove,
				glyph : 0xf014,
				action : 'remove',
				code : 'transactionColorDel',
				listeners : {
					'beforerender' : Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			} ],
			store : store,
			columns : [ {
				header : 'ID',
				dataIndex : 'id'
			}, {
				header : EwayLocale.monitor.business.transactionColor.hostRet,
				dataIndex : 'hostRet'
			}, {
				header : EwayLocale.monitor.business.transactionColor.backgroundColor,
				dataIndex : 'backgroundColor',
				renderer : this.gridColorRenderer
			}, {
				header : EwayLocale.monitor.business.transactionColor.fontColor,
				dataIndex : 'fontColor',
				renderer : this.gridColorRenderer
			},  {
				header : EwayLocale.monitor.business.transactionColor.hostRetDes,
				dataIndex : 'hostRetDes',
				width : 150
			}, {
				header : EwayLocale.monitor.business.transactionColor.localRet,
				dataIndex : 'localRet'
			}, {
				header : EwayLocale.monitor.business.transactionColor.localBackgroundColor,
				dataIndex : 'localBackgroundColor',
				renderer : this.gridColorRenderer
			}, {
				header : EwayLocale.monitor.business.transactionColor.localFontColor,
				dataIndex : 'localFontColor',
				renderer : this.gridColorRenderer
			}, {
				header : EwayLocale.monitor.business.transactionColor.localRetDes,
				dataIndex : 'localRetDes',
				width : 150
			}, {
				header : EwayLocale.monitor.business.transactionColor.updateDateTime,
				width : 150,
				dataIndex : 'updateDateTime'
			}, {
				header : EwayLocale.monitor.business.transactionColor.title,
				width : 300,
				dataIndex : 'remark'
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	},

	gridColorRenderer : function(value, meta, recor) {
	    
	    if (!value) {
	        return '';
	    }
	    
		var r = parseInt(value.substring(1, 3), 16);
		var g = parseInt(value.substring(3, 5), 16);
		var b = parseInt(value.substring(5, 7), 16);
		var a = new Ext.draw.Color(r, g, b);
		var l = a.getHSL()[2];
		if (l > 0.5) {
			meta.style='background:' + value + ';color:#000000';
		} else {
			meta.style='background:' + value + ';color:#FFFFFF';
		}
		return value;
	}
});