/**
 * 凭条打印机(RPR是RECEIPT PRINTER的缩写)的模型定义
 */
Ext.define('Eway.model.device.RPR', {
	extend : 'Ext.data.Model',
	idProperty : 'id',
	fields : [ 'id',
	           'type',//凭条类型
	           'canPopupReceipt',//能否弹出凭条
	           'canEatReceipt',//能否吞凭条
	           'canTempStore',//能否暂存
	           'maxRetrieveSize',//最大回收张数
	           'deviceId'//归属的设备ID
	          ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device/rpr',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});