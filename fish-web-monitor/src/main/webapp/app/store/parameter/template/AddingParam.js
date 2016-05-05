Ext.define('Eway.store.parameter.template.AddingParam', {
	extend : 'Eway.store.base.Store',

	model : 'Eway.model.parameter.template.AddingParam',

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