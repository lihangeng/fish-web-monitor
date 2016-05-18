Ext.define('Eway.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',

    storeId: 'NavigationTree',
    root: {
        expanded: true,
        children: [
            {
                text:   '仪表盘',
                view:   'dashboard.Dashboard',
                leaf:   true,
                iconCls: 'right-icon x-fa fa-desktop', /*new-icon **/
                routeId: 'dashboard'
            }, 
            {
                text:   '个人信息',
                view:   'profile.UserProfile',
                leaf:   true,
                iconCls: 'x-fa fa-user',
                routeId:'profile'
            },
            {
				text: '修改密码',
				view: 'authentication.PasswordReset',
				leaf: true,
				iconCls: 'x-fa fa-lightbulb-o',
				routeId:'authentication.passwordreset'
            }
        ]
    },
    fields: [
        {
            name: 'text'
        }
    ]
});
