Ext.define('Eway.view.machine.device.graphic.View', {
	extend : 'Ext.window.Window',
	alias : 'widget.machine_device_graphic_view',

	title : Eway.locale.machine.device.devDetailInfo,
	modal : true,
	resizable : false,
	constrainHeader : true,
	layout : 'fit',
	width : 900,
	height : 650,
	style : {
		background : 'none repeat scroll 0 0 white'
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : 'image',
				id : 'img_crs_id',
				src : 'resources/images/atm_status/IMG_CRS.jpg',
				width : 500,
				height : 500,
				margin : '40px 0 0 10px'
			},
//			, {
//				xtype : 'image',
//				id : 'img_jpr',
//				width : 93,
//				height : 34,
//				style : {
//					position : 'absolute',
//					cursor : 'pointer'
//				}
//			}, {
//				xtype : 'image',
//				id : 'img_cdm',
//				width : 93,
//				height : 34,
//				style : {
//					position : 'absolute',
//					cursor : 'pointer'
//				}
//			}, {
//				xtype : 'image',
//				id : 'img_cim',
//				width : 93,
//				height : 34,
//				style : {
//					position : 'absolute',
//					cursor : 'pointer'
//				}
//			}, {
//				xtype : 'image',
//				id : 'img_cashbox',
//				src : 'resources/images/atm_status/CASHBOX_Empty.gif',
//				width : 93,
//				height : 34,
//				style : {
//					position : 'absolute',
//					cursor : 'pointer'
//				},
//				width : 93,
//				height : 56,
//				margin : '340px 0 0 25px'
//			}, {
//				xtype : 'image',
//				id : 'img_idc',
//				width : 93,
//				height : 34,
//				style : {
//					position : 'absolute',
//					cursor : 'pointer'
//				}
//			}, {
//				xtype : 'image',
//				id : 'img_rpr',
//				width : 93,
//				height : 34,
//				status : 'Warning',
//				style : {
//					position : 'absolute',
//					cursor : 'pointer'
//				},
//				listeners : {
//					click : {
//						element : 'el',
//						fn : function() {
//							Ext.getCmp('img_help').setSrc(Ext.getCmp(this.id).img_help);
//						}
//					}
//				}
//			}, 
			{
				xtype : 'image',
				id : 'img_help',
				width : 320,
				height : 240
			} ]
		});

		this.callParent(arguments);
	}
});