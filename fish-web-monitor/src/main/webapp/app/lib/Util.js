
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
	zipText : '请输入正确的邮编格式，6位的数字',
	zipMask : /[\d]/
});
//custom Vtype : ip地址
Ext.apply(Ext.form.field.VTypes,{
	ip :function(v){
		return /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/.test(v);
	},
	ipText : '请输入正确的IP地址',
	ipMask : /[\d\.]/i
});
//custom Vtype : 版本号
Ext.apply(Ext.form.field.VTypes,{/*(-{1}\w+){0,1}*/
	versionNo :function(v){
		return /^(\d{1,8})(\.{1}\d{1,8}){0,3}$/.test(v);
	},
	versionNoText : '不是正确的版本号格式,格式说明：1.版本号由4个部分组成 A.B.C.D ;2.只有A部分是必须的 ；3. A、B、C、D必须为大于等于0的整数 ,每个部分最大长度为8位； 4.ABCD部分必须用.分隔',
	versionNoMask : /[\d\.]/i
});
//自定义验证类型设备号
Ext.apply(Ext.form.field.VTypes,{
	terminalId :function(v){
		return /^([a-zA-Z0-9]+[a-zA-Z0-9-\.]*){1,20}$/.test(v);
	},
	terminalIdText : '输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。',
	terminalIdMask : /[\d\w-_\.]/i
});
//自定义手机号码的验证
Ext.apply(Ext.form.field.VTypes,{
	mobile :function(v){
		return /^\d{8,11}$/.test(v);
	},
	mobileText : '输入错误,手机号码只能输入8到11位数字。',
	mobileMask : /[\d]/i
});
//自定义银行卡号的验证
Ext.apply(Ext.form.field.VTypes,{
	cardNo :function(v){
		return /^\d{16,19}$/.test(v);
	},
	cardNoText : '输入错误,银行卡号只能输入16到19位数字。',
	cardNoMask : /[\d]/i
});
//自定义固定号码的验证
Ext.apply(Ext.form.field.VTypes,{
	telephone :function(v){
		return /^(\d{3}-?|\d{4}-?)?(\d{8}|\d{7})$/.test(v);
	},
	telephoneText : '输入错误,固定电话号码只能输入8到11位数字。',
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
    daterangeText: '日期段不正确.'
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
    crowndaterangeText: '日期段不正确.'
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
    numberrangeText : '金额范围不正确.'
});

