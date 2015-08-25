Ext.define('Eway.store.person.user.AddedRole', {
	extend : 'Ext.data.Store',

	model : 'Eway.model.person.user.AddedRole',

	pageSize : 5,
	autoLoad : false,
	listeners : {
		load : function(store, records) {
			store.filterBy(function(record, id) {

				if (record.get('system') == true) {
					return false;
				}

				return true;

			});
		}
	}
});