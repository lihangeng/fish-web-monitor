Ext.define('Eway.view.person.person.LinkingDeviceGrid', {
	alias : 'widget.person_linkingDeviceGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,
	autoFit : true,
	
	viewConfig : {
		plugins : {
			ptype : 'gridviewdragdrop',
			dragGroup : 'linkingDeviceGridId',
			dropGroup : 'linkedDeviceGridId',
			enableDrop : true
		}
	},
	
	multiSelect:true,
	selModel:{selType:'checkboxmodel'},
	
	initComponent : function() {
		var store = Ext.create('Eway.store.person.person.LinkingDevice');
		store.cleanUrlParam();
		
		var checkboxSM = new Ext.create('Ext.selection.CheckboxModel', {
			checkOnly : false,
			singleSelect : false
		});

		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar: [{text:EwayLocale.commen.canBindMachine,xtype:'tbtext'},'->', {
				text:EwayLocale.button.search,
				glyph : 0xf002,
				action:'query'
			},{
				text: EwayLocale.commen.bind,
				glyph : 0xf0c1,
				action: 'link'
			}],
			columns : [ {
				header : EwayLocale.commen.terminalId,
				dataIndex : 'terminalId',
				width:80,
				storable : true
			}, {
				header : EwayLocale.commen.ip,
				dataIndex : 'ip'
			}, {
				header : EwayLocale.commen.orgNameBelongs,
				dataIndex : 'orgName'
			}, {
				header : EwayLocale.commen.devTypeName,
				dataIndex : 'devTypeName'
			}, {
				header : EwayLocale.commen.devVendorName,
				dataIndex : 'devVendorName'
			}, {
				header : EwayLocale.commen.devCatalogName,
				dataIndex : 'devCatalogName'
			}, {
				header : EwayLocale.commen.devStatus,
				dataIndex : 'status',
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return EwayLocale.commen.comboxDevStatus.upOpen;
					}
					if (value == 2) {
						return EwayLocale.commen.comboxDevStatus.open;
					}
					if (value == 3) {
						return EwayLocale.commen.comboxDevStatus.stop;
					}
					if (value == 4) {
						return EwayLocale.commen.comboxDevStatus.scrapped;
					}
				}
			}, {
				header : EwayLocale.commen.devServiceName,
				dataIndex : 'devServiceName'
			}, {
				header : EwayLocale.commen.address,
				dataIndex : 'address',
				minWidth:100,
				flex:1
			} , {
				header : 'ID',
				dataIndex : 'id',
				hidden : true,
				storable : true
			} ],
			
			sm: checkboxSM,
			enableColumnMove : true,
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},

	onReload : function() {
		this.getStore().load();
	}
});