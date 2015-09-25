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
				itemclick : this.diplayVersionStatusPie
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
				var statuspieStore = statuspolar.getStore();
				Ext.Msg.alert(records[0].get("versionNo"),records[0].get("versionId"));
//				statuspieStore.load({
//					params : {
//						versionId :records[0].get("versionId")
//					}
//				});
			}
		})

	},
	diplayVersionStatusPie:function(series, item, event, eOpts ){
//		itemclick:function( series, item, event, eOpts ){
//    		var gridFlag = item.record.data.flag;
//    		var gridVersionId = item.record.data.versionId;
//		  fields: ['versionNo', 'versionNoNumber', 'versionId', 'versionTypeId' ],
		Ext.Msg.alert(item.record.data.versionNo,item.record.data.versionNoNumber);
//    		var grid = me.up("versionView").down("version_charts_grid")
//    		grid.getStore().load({
//    			 params: {
//    			        versionId:gridVersionId,
//    			        flag:gridFlag
//    			    }
//    		 });
//    		grid.setTitle(item.record.data.title+"信息")
//    	}
	}
});
