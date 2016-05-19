Ext.define('Eway.view.main.Viewport', {
    extend: 'Ext.container.Viewport',
    xtype: 'mainviewport',

    requires: [
        'Ext.list.Tree'
    ],

    controller: 'mainviewport',
    viewModel: {
        type: 'mainviewport'
    },

    cls: 'sencha-dash-viewport',
    itemId: 'mainView',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    listeners: {
        render: 'onMainViewRender'
    },

    items: [
        {
            xtype: 'toolbar',
            cls: 'sencha-dash-dash-headerbar toolbar-btn-shadow',
            height: 64,
            itemId: 'headerBar',
			defaults:{
				iconAlign:'top'
			},
            items: [
                {
                    xtype: 'component',
                    reference: 'senchaLogo',
                    cls: 'sencha-logo',
                    html: '<div class="main-logo"><img src="../resources/images/sencha-icon.png">ATMV</div>',
                    width: 250
                },
                {
                    margin: '0 0 0 8',
                    cls: 'delete-focus-bg',
                    iconCls:'x-fa fa-navicon',
                    id: 'main-navigation-btn',
                    handler: 'onToggleNavigationSize'
                },
				{
                    text:'首页',
					//cls: 'delete-focus-bg',
                    iconCls: 'fa fa-home',
					iconAlign:'top',
                    href: Ext.cxtPath + '/index/index.jsp',
                    hrefTarget: '_self',
					view:'index'
                },
				{
                    text:'系统管理',
					//cls: 'delete-focus-bg',
                    cls:'active-menu1',
                    iconCls:'x-fa fa-envelope',
					iconAlign:'top',
                    href: Ext.cxtPath + '/system/index.jsp',
                    hrefTarget: '_self',
					view:'system'
                },
				{
                    text:'设备管理',
					//cls: 'delete-focus-bg',
                    iconCls:'x-fa fa-envelope',
					iconAlign:'top',
                    href: Ext.cxtPath + '/device/index.jsp',
                    hrefTarget: '_self',
				   	view:'device'
                },
				{
                    text:'监控管理',
					//cls: 'delete-focus-bg',
                    iconCls:'x-fa fa-envelope',
					iconAlign:'top',
                    href: Ext.cxtPath + '/monitor/index.jsp',
                    hrefTarget: '_self'
                },
				{
                    text:'应用管理',
					//cls: 'delete-focus-bg',
                    iconCls:'x-fa fa-envelope',
					iconAlign:'top',
                    href: Ext.cxtPath + '/application/index.jsp',
                    hrefTarget: '_self'
                },
				{
                    text:'故障管理',
					//cls: 'delete-focus-bg',
                    iconCls:'x-fa fa-envelope',
					iconAlign:'top',
                    href: Ext.cxtPath + '/fault/index.jsp',
                    hrefTarget: '_self'
                },
				{
                    text:'报表管理',
					//cls: 'delete-focus-bg',
                    iconCls:'x-fa fa-envelope',
					iconAlign:'top',
                    href:Ext.cxtPath +  '/report/index.jsp',
                    hrefTarget: '_self'
                },
                {
                    xtype: 'tbspacer',
                    flex: 1
                },
				{
//                    cls: 'delete-focus-bg',
                    iconCls:'x-fa fa-bell'
                },
                {
                    xtype: 'tbtext',
                    text: 'Welcome,Goff',
                    cls: 'top-user-name'
                }
            ]
        },
        {
            xtype: 'maincontainerwrap',
            id: 'main-view-detail-wrap',
            reference: 'mainContainerWrap',
            flex: 1,
            items: [
                {
                    xtype: 'treelist',
                    reference: 'navigationTreeList',
                    itemId: 'navigationTreeList',
                    ui: 'navigation',
                    store: 'NavigationTree',
                    width: 250,
                    expanderFirst: false,
                    expanderOnly: false,
                    listeners: {
                        selectionchange: 'onNavigationTreeSelectionChange'
                    }
                },
                {
                    xtype: 'container',
                    flex: 1,
                    reference: 'mainCardPanel',
                    cls: 'sencha-dash-right-main-container',
                    itemId: 'contentPanel',
                    layout: {
                        type: 'card',
                        anchor: '100%'
                    }
                }
            ]
        }
    ]
});
