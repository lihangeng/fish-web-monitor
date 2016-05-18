Ext.define('Eway.view.charts.ChartsModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.charts',

    stores: {
        marketshareoneyear: {
            model: 'Eway.model.DataXY',
            autoLoad: true,

            proxy: {
                type: 'ajax',
                url: '~api/marketshare/oneyear',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        },

        marketsharemultiyear: {
            model: 'Eway.model.MultiDataXY',
            autoLoad: true,

            proxy: {
                type: 'ajax',
                url: '~api/marketshare/multiyear',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        },

        gaugechartstore: {
            data: [
                {
                    position: 40
                }
            ],

            fields: [
                {
                    name: 'position'
                }
            ]
        },

        radialchartstore: {
            model: 'Eway.model.DataXY',
            autoLoad: true,

            proxy: {
                type: 'ajax',
                url: '~api/radial',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        },

        marketshareoneentitystore: {
            model: 'Eway.model.DataXY',
            autoLoad: true,

            proxy: {
                type: 'ajax',
                url: '~api/marketshare/oneentity',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        },

        yearwisemarketsharestore: {
            model: 'Eway.model.MultiDataXY',
            autoLoad: true,

            proxy: {
                type: 'ajax',
                url: '~api/marketshare/yearwise',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        },

        piedatastore: {
            model: 'Eway.model.DataXY',
            autoLoad: true,

            proxy: {
                type: 'ajax',
                url: '~api/pie',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }            
        },

        dashboardfulllinechartstore: {
            model: 'Eway.model.MultiDataXY',
            autoLoad: true,

            proxy: {
                type: 'ajax',
                url: '~api/dashboard/full',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        }
    }
});
