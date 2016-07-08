Ext.define('Eway.model.monitor.companynews.News', {
    extend: 'Eway.model.monitor.companynews.Base',

    fields: [
        'type',
        { name: 'date', type: 'date', dateFormat: 'Y-m-d H:i:s' },
        'time',
        'author',
        'group',
        'title',
        'paragraph'
    ]
});
