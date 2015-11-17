
Ext.define('Eway.lib.Util', {
	singleton: true,

	dictRenderer: function(storeName) {
		return function(value) {
			var store = Ext.data.StoreManager.lookup(storeName);
			return store.getById(value).get('display');
		}
	},
	typeRenderer: function(storeName) {
		return function(abbr) {
			var store = Ext.data.StoreManager.lookup(storeName);
			return store.getById(abbr).get('name');
		}
	}
});
//custom Vtype ： zip
Ext.apply(Ext.form.field.VTypes,{
	zip :function(v){
		return  /^\d{6}$/.test(v);
	},
	zipText : EwayLocale.vtype.zip,
	zipMask : /[\d]/
});
//custom Vtype : ip地址
Ext.apply(Ext.form.field.VTypes,{
	ip :function(v){
		return /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/.test(v);
	},
	ipText : EwayLocale.vtype.ip,
	ipMask : /[\d\.]/i
});
//custom Vtype : 版本号
Ext.apply(Ext.form.field.VTypes,{/*(-{1}\w+){0,1}*/
	versionNo :function(v){
		return /^(\d{1,8})(\.{1}\d{1,8}){0,3}$/.test(v);
	},
	versionNoText : EwayLocale.vtype.versionNo,
	versionNoMask : /[\d\.]/i
});
//自定义验证类型设备号
Ext.apply(Ext.form.field.VTypes,{
	terminalId :function(v){
		return /^([a-zA-Z0-9]+[a-zA-Z0-9-\.]*){1,20}$/.test(v);
	},
	terminalIdText : EwayLocale.vtype.terminalId,
	terminalIdMask : /[\d\w-_\.]/i
});
//自定义手机号码的验证
Ext.apply(Ext.form.field.VTypes,{
	mobile :function(v){
		return /^\d{8,11}$/.test(v);
	},
	mobileText : EwayLocale.vtype.mobile,
	mobileMask : /[\d]/i
});
//自定义银行卡号的验证
Ext.apply(Ext.form.field.VTypes,{
	cardNo :function(v){
		return /^\d{16,19}$/.test(v);
	},
	cardNoText : EwayLocale.vtype.cardNo,
	cardNoMask : /[\d]/i
});
//自定义固定号码的验证
Ext.apply(Ext.form.field.VTypes,{
	telephone :function(v){
		return /^(\d{3}-?|\d{4}-?)?(\d{8}|\d{7})$/.test(v);
	},
	telephoneText : EwayLocale.vtype.telephone,
	telephoneMask : /[\d-]/i
});

Ext.apply(Ext.form.field.VTypes, {
    daterange: function(val, field) {
        var date = field.parseDate(val);
        if (!date) {
            return false;
        }
        if (field.startDateField && (!field.dateRangeMax || (date.getTime() != field.dateRangeMax.getTime()))) {
            var start = field.up('form').getForm().findField(field.startDateField);
            start.setMaxValue(date);
            field.dateRangeMax = date;
        }
        else if (field.endDateField && (!field.dateRangeMin || (date.getTime() != field.dateRangeMin.getTime()))) {
            var end = field.up('form').getForm().findField(field.endDateField);
            end.setMinValue(date);
            field.dateRangeMin = date;
        }
        /*
         * Always return true since we're only using this vtype to set the
         * min/max allowed values (these are tested for after the vtype test)
         */
        return true;
    },
    daterangeText: EwayLocale.vtype.daterange
});

//冠字号历史查询时时间段为30天
Ext.apply(Ext.form.field.VTypes, {
    crowndaterange: function(val, field) {
        var date = field.parseDate(val);
        if (!date) {
            return false;
        }
        if (field.startDateField && (!field.dateRangeMax || (date.getTime() != field.dateRangeMax.getTime()))) {
            var start = field.up('form').getForm().findField(field.startDateField);
            start.setMaxValue(date);
            start.setMinValue(Ext.Date.format(Ext.Date.add(date, Ext.Date.DAY, -30), 'Y-m-d'));
            field.dateRangeMax = date;
        }
        else if (field.endDateField && (!field.dateRangeMin || (date.getTime() != field.dateRangeMin.getTime()))) {
            var end = field.up('form').getForm().findField(field.endDateField);
            end.setMinValue(date);
            end.setMaxValue(Ext.Date.format(Ext.Date.add(date, Ext.Date.DAY, 30), 'Y-m-d'));
            field.dateRangeMin = date;
        }
        /*
         * Always return true since we're only using this vtype to set the
         * min/max allowed values (these are tested for after the vtype test)
         */
        return true;
    },
    crowndaterangeText: EwayLocale.vtype.daterange
});

Ext.apply(Ext.form.field.VTypes, {
	numberrange : function(val, field) {
    	var value = field.parseValue(val);
    	if (field.lessThan) {
    		var endAtm = field.up('form').getForm().findField(field.lessThan);
    		endAtm.setMinValue(value);
    	} else if (field.greaterThan) {
    		var startAtm = field.up('form').getForm().findField(field.greaterThan);
    		startAtm.setMaxValue(value);
    	}
    	return true;
    },
    numberrangeText : EwayLocale.vtype.numberrange
});

