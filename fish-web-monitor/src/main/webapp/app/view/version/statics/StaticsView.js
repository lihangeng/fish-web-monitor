Ext.define('Eway.view.version.statics.StaticsView', {
			extend : 'Ext.window.Window',
			alias : 'widget.version_statics',

			requires : ['Eway.view.version.statics.Downed'],
			title : Eway.locale.statics.title,//'下发结果统计',
			layout : 'border',

			initComponent : function() {
				Ext.apply(this, {
							items : [{
										region : 'west',
										html : Eway.locale.statics.versionInfo//'版本信息'
									}, {
										region : 'center',
										xtype : 'tabpanel',
										items : [{
													xtype : 'version_downed'
												}/*, {
													xtype : 'version_grid'
												}*/]
									}]
						});

				this.callParent(arguments);
			}

		});