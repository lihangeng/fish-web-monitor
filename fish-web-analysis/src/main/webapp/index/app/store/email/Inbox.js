Ext.define('Eway.store.email.Inbox', {
    extend: 'Ext.data.Store',

    alias: 'store.emailinbox',

    model: 'Eway.model.email.Email',

    pageSize: 20,

    autoLoad: true,

    proxy: {
        type: 'ajax',
        url: '~api/email/inbox',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});
