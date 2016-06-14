Ext.define('Eway.view.version.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.versionView',

	requires : [ 'Eway.view.version.Grid', 'Eway.view.version.ChartsGrid',
			'Eway.view.version.charts.Bar3dBasic',
			'Eway.view.version.FilterForm',
			'Eway.view.version.VersionInstallInfo' ],

	title : EwayLocale.version.View.title,//'版本管理'
	scrollable : true,
	autoScroll : true,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'panel',
				items : [ {
					xtype : 'panel',
					items : [ {
						xtype : 'version_filterForm',
						region : "north"
					}, {
						xtype : 'version_grid',
						height : 200,
						region : 'center'
					} ]
				}, {
					 xtype : 'panel',
					 itemId: 'versionPic',
					 layout: {
					        type: 'table',
					        columns:2
				    },
					defaultType : 'displayfield',
					title:EwayLocale.version.View.selectVersion,
					items : [
					{
						xtype : 'bar_3d',
						width : 680,
						rowField : 'title',
						columnField : 'value',
						title:'',
						rowspan: 5
					},{
						xtype: 'propertygrid',
		                nameColumnWidth: 180,
		                margin:'25 0 0 20',
		                height: 300,
		                width:390,
		                sortableColumns:false,
		                source: {
		                	versionType: '',
		                	versionPath: '',
		                	versionTime: '',
		                	versionPerson: '',
		                	versionStatus: '',
		                	autoDown:'',
		                	desc: ''
		                },
		                sourceConfig:{
		                	versionType :{
		                		editor: Ext.create('Ext.form.field.Text', {
		                			readOnly :true
			                		}
		                		)
		                	},
		                	versionPath :{
		                		editor: Ext.create('Ext.form.field.Text', {
		                			readOnly :true
			                		}
		                		)
		                	},
		                	versionTime :{
		                		editor: Ext.create('Ext.form.field.Text', {
		                			readOnly :true
			                		}
		                		)
		                	},
		                	versionPerson:{
		                		editor: Ext.create('Ext.form.field.Text', {
		                			readOnly :true
			                		}
		                		)
		                	},
		                	versionStatus:{
		                		editor: Ext.create('Ext.form.field.Text', {
		                			readOnly :true
			                		}
		                		)
		                	},
		                	autoDown:{
		                		editor: Ext.create('Ext.form.field.Text', {
		                			readOnly :true
			                		}
		                		)
		                	},
		                	desc :{
		                		editor: Ext.create('Ext.form.field.Text', {
		                			readOnly :true
			                		}
		                		)
		                	}
		                },
		                propertyNames:{versionType:EwayLocale.version.View.versionTypeCode,
		                			   versionPath:EwayLocale.version.installPath,
		                			   versionTime:EwayLocale.version.View.versionTime,
		                			   versionPerson:EwayLocale.version.View.versionPerson,
		                			   versionStatus:EwayLocale.version.View.versionStatus,
		                			   autoDown:EwayLocale.version.View.autoUpdate,
		                			   desc:EwayLocale.version.View.remark},
					} ]
				}, {// 当选择一个图形的内容时，现实对应的设备信息（Grid）
					xtype : 'version_charts_grid',
					height : 350,
					region : "south"
				} ]
			} ],
			listeners : {
				activate : function(panel) {
					if (panel.isLoad) {
						return;
					}
					panel.isLoad = true;
					panel.down('version_grid').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	}
});