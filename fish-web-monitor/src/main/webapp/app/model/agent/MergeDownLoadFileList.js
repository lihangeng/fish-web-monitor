Ext.define('Eway.model.agent.MergeDownLoadFileList', {
			extend : 'Ext.data.Model',		
			fields: ['name','path','type','lastTime','size'],
			proxy : {
				type : 'memory',
				reader : {
					type : 'json',
					rootProperty : 'data'
				}
			}

		});