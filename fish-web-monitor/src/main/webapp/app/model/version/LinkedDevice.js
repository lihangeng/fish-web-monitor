Ext.define('Eway.model.version.LinkedDevice', {
			extend : 'Ext.data.Model',
			idProperty : 'id',
			fields : ['id', 'code', 'ip', 'port', 'address', 'installDate', 'deviceVersion', 'orgName',
					'deviceType'],
			proxy : {
				type : 'memory',
				reader : {
					type : 'json',
					rootProperty : 'data'
				},
				wirter : {
					type : 'json'
				}
			}

		});