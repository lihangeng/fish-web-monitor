Ext.define('Eway.store.parameter.template.AddedParam', {
	extend : 'Ext.data.Store',

	model : 'Eway.model.parameter.template.AddedParam',

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