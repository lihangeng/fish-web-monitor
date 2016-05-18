Ext.define('Eway.store.email.Friends', {
    extend: 'Ext.data.Store',

    alias: 'store.emailfriends',

    model: 'Eway.model.email.Friend',

    proxy: {
        type: 'ajax',
        url: '~api/email/friends',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    },

    sorters: {
        direction: 'DESC',
        property: 'online'
    }
});
