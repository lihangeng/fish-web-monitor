Ext.define('Eway.view.person.user.PersonGrid', {
	alias : 'widget.user_personGrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util' ],

	border : false,

	initComponent : function() {
		var store = Ext.create('Eway.store.person.user.Person');
		Ext.apply(this, {
			store : store,
			initRegion : true,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'queryPerson'
			}, {
				text : EwayLocale.button.choose,
				action : 'select'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ {
				header : EwayLocale.commen.name,
				dataIndex : 'name',
				width : 80
			}, {
				header : EwayLocale.commen.personJobName,
				dataIndex : 'personJobName',
				width : 100
			}, {
				header : EwayLocale.commen.gender,
				dataIndex : 'gender',
				width : 80,
				renderer : function(value, metadata, record) {
					if (value == "MALE") {
						return EwayLocale.commen.comboxGender.male;
					} else if (value == "FEMALE") {
						return EwayLocale.commen.comboxGender.female;
					} else {
						return EwayLocale.commen.comboxGender.know;
					}
				}
			}, {
				header : EwayLocale.commen.birthday,
				dataIndex : 'birthday',
				xtype : 'datecolumn',
				format : 'Y-m-d'
			}, {
				header : EwayLocale.commen.mobile,
				dataIndex : 'mobile'
			}, {
				header : EwayLocale.commen.phone,
				dataIndex : 'phone'
			}, {
				header : EwayLocale.commen.email,
				dataIndex : 'email',
				width : 130
			}, {
				header : EwayLocale.person.bankPer.organizationName,
				dataIndex : 'organizationName',
				width : 130
			}, {
				header : 'guid',
				dataIndex : 'ID',
				hidden : true
			}, {
				header : EwayLocale.commen.state,
				dataIndex : 'state',
				width : 60,
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return EwayLocale.commen.comboxStatus.onJob;
					} else if (value == 2) {
						return EwayLocale.commen.comboxStatus.onAdjust;
					} else if (value == 3) {
						return EwayLocale.commen.comboxStatus.onVacation;
					} else if (value == 0) {
						return EwayLocale.commen.comboxStatus.other;
					}
				}
			} ],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});