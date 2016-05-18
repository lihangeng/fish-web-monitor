Ext.define('Eway.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationTree',
    root: {
        expanded: true,
        children: [
            {
				text: '银行机构管理',
				view: 'bankOrg.Organization',
				leaf: true,
				iconCls: 'x-fa fa-lightbulb-o',
				routeId:'bankOrg'
            }
        ]
    },
    fields: [
        {
            name: 'text'
        }
    ]
});
