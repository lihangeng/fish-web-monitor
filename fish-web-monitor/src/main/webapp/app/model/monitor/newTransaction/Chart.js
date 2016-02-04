Ext.define('Eway.model.monitor.newTransaction.Chart', {
    extend: 'Ext.data.Model',
    fields : [ 'date', // 时间 
               'trans_total_tps', // 时间段内交易每秒交易数
               'local_total_tps', // 本行卡交易总数
               'other_total_tps' // 他行卡交易总数
     ]
});