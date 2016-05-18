Ext.define('Eway.model.ChatMessages', {
    extend: 'Eway.model.Base',

    fields: [
        {
            type: 'string',
            name: 'message'
        },
        {
            type: 'string',
            defaultValue: 'user',
            name: 'sender'
        }
    ]
});
