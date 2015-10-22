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
				text : Eway.locale.button.search,
				glyph : 0xf002,
				action : 'queryPerson'
			}, {
				text : Eway.locale.button.choose,
				action : 'select'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ {
				header : Eway.locale.commen.name,
				dataIndex : 'name',
				width : 80
			}, {
				header : Eway.locale.commen.personJobName,
				dataIndex : 'personJobName',
				width : 100
			}, {
				header : Eway.locale.commen.gender,
				dataIndex : 'gender',
				width : 80,
				renderer : function(value, metadata, record) {
					if (value == "MALE") {
						return Eway.locale.commen.comboxGender.male;
					} else if (value == "FEMALE") {
						return Eway.locale.commen.comboxGender.female;
					} else {
						return Eway.locale.commen.comboxGender.know;
					}
				}
			}, {
				header : Eway.locale.commen.birthday,
				dataIndex : 'birthday',
				xtype : 'datecolumn',
				format : 'Y-m-d'
			}, {
				header : Eway.locale.commen.mobile,
				dataIndex : 'mobile'
			}, {
				header : Eway.locale.commen.phone,
				dataIndex : 'phone'
			}, {
				header : Eway.locale.commen.email,
				dataIndex : 'email',
				width : 130
			}, {
				header : Eway.locale.person.bankPer.organizationName,
				dataIndex : 'organizationName',
				width : 130
			}, {
				header : 'guid',
				dataIndex : 'ID',
				hidden : true
			}, {
				header : Eway.locale.commen.state,
				dataIndex : 'state',
				width : 60,
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return Eway.locale.commen.comboxStatus.onJob;
					} else if (value == 2) {
						return Eway.locale.commen.comboxStatus.onAdjust;
					} else if (value == 3) {
						return Eway.locale.commen.comboxStatus.onVacation;
					} else if (value == 0) {
						return Eway.locale.commen.comboxStatus.other;
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