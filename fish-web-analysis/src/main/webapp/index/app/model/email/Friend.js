Ext.define('Eway.model.email.Friend', {
    extend: 'Eway.model.Base',

    fields: [
        {
            type: 'int',
            name: 'id'
        },
        {
            type: 'string',
            name: 'name'
        },
        {
            type: 'string',
            name: 'thumbnail'
        },
        {
            type: 'boolean',
            name: 'online'
        }
    ]
});
