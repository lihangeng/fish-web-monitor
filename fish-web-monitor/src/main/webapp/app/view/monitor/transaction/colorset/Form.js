Ext.define('Eway.view.monitor.transaction.colorset.Form', {

	extend : 'Eway.view.base.Form',
	alias : 'widget.monitor_taansaction_colorset_from',

	requires : [ 'Eway.lib.ColorField' ],

	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '91%',
				labelWidth : 120,
				margin : '10 0 0 0',
				labelAlign : 'right',
				msgTarget : 'title'
			},
			items : [ {
				fieldLabel : '<font color="red">*</font> ' + EwayLocale.monitor.business.transactionColor.hostRet,
				xtype : 'textfield',
				name : 'hostRet',
				maxLength : 20,
				labelAlign:'right',
				labelWidth: 150,
				regex: /^([a-zA-Z0-9_]){1,20}$/,
				allowBlank : false
			}, {
				fieldLabel : '<font color="red">*</font> ' + EwayLocale.monitor.business.transactionColor.backgroundColor,
				xtype : 'colorfield',
				name : 'backgroundColor',
				maxLength : 20,
				editable : false,
				allowBlank : false,
				labelAlign:'right',
				labelWidth: 150,
				listeners : {
					afterrender : function(me, eOpts ) {
						this.formColorRender(me, eOpts);
					},
					scope : this
				}
			}, {
				fieldLabel : '<font color="red">*</font> ' + EwayLocale.monitor.business.transactionColor.fontColor,
				xtype : 'colorfield',
				name : 'fontColor',
				maxLength : 20,
				editable : false,
				allowBlank : false,
				labelAlign:'right',
				labelWidth: 150,
				listeners : {
					afterrender : function(me, eOpts ) {
						this.formColorRender(me, eOpts);
					},
					scope : this
				}
			},{
				fieldLabel : EwayLocale.monitor.business.transactionColor.hostRetDes,
				xtype : 'textfield',
				name : 'hostRetDes',
				labelAlign:'right',
				labelWidth: 150,
				maxLength : 20
			},{
				fieldLabel : EwayLocale.monitor.business.transactionColor.localRet,
				xtype : 'textfield',
				name : 'localRet',
				maxLength : 20,
				labelWidth: 150,
				labelAlign:'right',
				regex: /^([a-zA-Z0-9_]){1,20}$/
			}, {
				fieldLabel : EwayLocale.monitor.business.transactionColor.localBackgroundColor,
				xtype : 'colorfield',
				name : 'localBackgroundColor',
				maxLength : 20,
				labelAlign:'right',
				labelWidth: 150,
				editable : false,
				listeners : {
					afterrender : function(me, eOpts ) {
						this.formColorRender(me, eOpts);
					},
					scope : this
				}
			}, {
				fieldLabel : EwayLocale.monitor.business.transactionColor.localFontColor,
				xtype : 'colorfield',
				name : 'localFontColor',
				maxLength : 20,
				labelAlign:'right',
				labelWidth: 150,
				editable : false,
				listeners : {
					afterrender : function(me, eOpts ) {
						this.formColorRender(me, eOpts);
					},
					scope : this
				}
			}, {
				fieldLabel : EwayLocale.monitor.business.transactionColor.localRetDes,
				xtype : 'textfield',
				name : 'localRetDes',
				labelAlign:'right',
				labelWidth: 150,
				maxLength : 20
			},{
				xtype : 'textarea',
				name : 'remark',
				fieldLabel : EwayLocale.monitor.business.transactionColor.remark,
				autoScroll : true,
				maxLength : 50,
				labelAlign:'right',
				labelWidth: 150,
				allowBlank : true
			} ]
		});
		this.callParent(arguments);
	},

	formColorRender : function(me, eOpts ) {
		var value = me.value;
		if (!value) {
			return;
		}
		var newValue = value.substring(1);

		var r = parseInt(newValue.substring(0, 2), 16);
		var g = parseInt(newValue.substring(2, 4), 16);
		var b = parseInt(newValue.substring(4, 6), 16);

		var a = new Ext.draw.Color(r, g, b);
		var l = a.getHSL()[2];
		if (l > 0.5) {
			me.setFieldStyle('background:#' + newValue
					+ ';color:#000000');
		} else {
			me.setFieldStyle('background:#' + newValue
					+ ';color:#FFFFFF');
		}
	}
});