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
				text : '查询',
				glyph : 0xf002,
				action : 'queryPerson'
			}, {
				text : '选择',
				action : 'select'
			} ],
			viewConfig : {
				forceFit : true,
				stripeRows : true
			},
			columns : [ {
				header : '姓名',
				dataIndex : 'name',
				width : 80
			}, {
				header : '岗位',
				dataIndex : 'personJobName',
				width : 100
			}, {
				header : '性别',
				dataIndex : 'gender',
				width : 80,
				renderer : function(value, metadata, record) {
					if (value == "MALE") {
						return "男";
					} else if (value == "FEMALE") {
						return "女";
					} else {
						return "未知";
					}
				}
			}, {
				header : '出生年月',
				dataIndex : 'birthday',
				xtype : 'datecolumn',
				format : 'Y-m-d'
			}, {
				header : '手机',
				dataIndex : 'mobile'
			}, {
				header : '固话',
				dataIndex : 'phone'
			}, {
				header : '邮箱',
				dataIndex : 'email',
				width : 130
			}, {
				header : '机构',
				dataIndex : 'organizationName',
				width : 130
			}, {
				header : 'guid',
				dataIndex : 'ID',
				hidden : true
			}, {
				header : '状态',
				dataIndex : 'state',
				width : 60,
				renderer : function(value, metadata, record) {
					if (value == 1) {
						return "在岗";
					} else if (value == 2) {
						return "调休";
					} else if (value == 3) {
						return "休假";
					} else if (value == 0) {
						return "其他";
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