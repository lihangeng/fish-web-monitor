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
						height : 350,
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
					items : [
					{
						xtype : 'bar_3d',
						width : 600,
						rowField : 'title',
						columnField : 'value',
						title:'',
						rowspan: 5
					} ,{
						name: 'versionType',
						fieldLabel : EwayLocale.version.View.versionTypeCode,//'版本路径',
				        text: '',
				        style: {
				            fontSize: '20px'
				        },
				        margin: '0 0 0 100'
				    },{
						name: 'versionPath',
						fieldLabel : EwayLocale.version.View.versionPath,//'版本路径',
				        text: '',
				        style: {
				            fontSize: '20px'
				        },
				        margin: '0 0 0 100'
				    },{
				    	name: 'versionTime',
						fieldLabel : EwayLocale.version.View.versionTime,//'创建时间',
				        text: '',
				        style: {
				            fontSize: '20px'
				        },
				        margin: '0 0 0 100'
				    },{
				    	name: 'versionPerson',
						fieldLabel : EwayLocale.version.View.versionPerson,//'创建人',
				        text: '',
				        style: {
				            fontSize: '20px'
				        },
				        margin: '0 0 0 100'
				    },{
				    	name: 'desc',
						fieldLabel : EwayLocale.version.View.remark,//'备注',
				        text: '',
				        style: {
				            fontSize: '20px'
				        },
				        margin: '0 0 0 100'
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