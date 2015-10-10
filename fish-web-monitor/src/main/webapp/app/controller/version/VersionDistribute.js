Ext.define('Eway.controller.version.VersionDistribute', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'version.ComboVersionType' ],
	// models : [ 'version.VersionAutoUpdate' ],
	views : [ 'version.distribute.View', 'version.distribute.VersionPie',
			'version.distribute.VersionStatusPie' ],

	refs : [ {
		ref : 'ewayView',
		selector : 'version_distributeView',
		autoCreate : true,
		xtype : 'version_distributeView'
	}, {
		ref : 'versionPie',
		selector : 'version_pie'
	}, {
		ref : 'versionStatusPie',
		selector : 'versionStatus_pie'
	}, {
		ref : 'field_versionTypeComboBoxAdd',
		selector : 'field_versionTypeComboBoxAdd'
	} ],

	init : function() {
		this.control({
			'version_distributeView field_versionTypeComboBoxAdd' : {
				change : this.onQuery
			},
			'version_distributeView version_pie polar' : {
				itemmouseover : this.diplayVersionStatusPie
			},
			'version_distributeView versionStatusPie polar' : {
				itemmouseover : this.diplayVersionStatusDetail
			}
		})
	},

	onQuery : function(_this, newValue, oldValue, eOpts) {
		var view = this.getEwayView();
		var versionTypeCombo = view.down('field_versionTypeComboBoxAdd');
		var record = versionTypeCombo.getSelectedRecord();
		var selectedVersionName = record.get("desc");
		var panel = view.down('version_pie'); 
		panel.setTitle(selectedVersionName);
		var polar = view.down('version_pie polar');
		var pieStore = polar.getStore();
		pieStore.load({
			params : {
				versionTypeId : newValue,
				displayNumber : 0
			},
			callback:function(records, operation, success){
				var statuspolar = view.down('versionstatus_pie polar');
				statuspolar.setTitle(records[0].get("versionNo")+"版本下发历史状态分布图");
				var statuspieStore = statuspolar.getStore();
				statuspieStore.load({
					params : {
						versionId :records[0].get("versionId")
					}
				});
			}
		})

	},
	diplayVersionStatusDetail:function(series, item, event, eOpts){
		
	},
	diplayVersionStatusPie:function(series, item, event, eOpts ){
//item.record.data.versionId
//		Ext.Msg.alert(item.record.data.versionNo,item.record.data.versionNoNumber);
		var view = this.getEwayView();
		var statuspolar = view.down('versionstatus_pie polar');
		statuspolar.setTitle(item.record.data.versionNo+"版本下发历史状态分布图");
		var statuspieStore = statuspolar.getStore();
		statuspieStore.load({
			params : {
				versionId :item.record.data.versionId
			}
		});
	}
});
