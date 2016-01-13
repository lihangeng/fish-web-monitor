/**
 * 1.重写Rest，解决通过Rest方式增加的时候，会在URL中增加/0?。。的问题
 * @since extjs4.2.1
 */
/*Ext.data.proxy.Rest.override({
    isValidId: function(id) {
        return (typeof id ==='number') && id > 0;//5中增加
    }
});*/

/**
 * 2.重写模型，解决在关联操作过程中，post操作被当作update操作的问题
 * @since extjs4.2.1
 */
/*Ext.data.Model.override({
	hasId: function(id) {
	    if (arguments.length === 0) {
	        id = this.getId();
	    }
	    return !!(id || id === 0);
	}
});*/

Ext.override(Ext.data.Model,{
	 drop: function (cascade) {
	        var me = this,
	            associations = me.associations,
	            session = me.session,
	            roleName;

	        if (me.erased || me.dropped) {
	            return;
	        }

	        me.dropped = true;
	        if (associations && cascade !== false) {
	            for (roleName in associations) {
	                associations[roleName].onDrop(me, session);
	            }
	        }
//	        me.callJoined('afterDrop');
	        if (me.phantom) {
	            me.setErased();
	        }
	    }
});


/**
 * 在extjs5中，更新操作中只更新修改的字段，导致数据库中数据被清空或存在约束异常，
 * 导致数据更新失败
 */
Ext.override(Ext.data.writer.Writer,{
	getWriteAllFields : function(){
		return true;
	},
	getWriteRecordId : function(){
		return false;
	}
});

/**
 * extjs5中，快速获取服务器返回的错误信息，operation.getError()
 * 与服务器约定在报文中错误key是errorMsg,服务器端是FishConstant.ERROR_MSG
 */
Ext.override(Ext.data.reader.Reader,{
	getMessageProperty: function(){
		return "errorMsg";
	}
});

/**
 *
 * 3.重写Ext.form.field.Text类的getErrors方法
 * 文本框必填时，输入空格验证将不通过。
 * @since extjs4.1.0
 *
 **/
Ext.override(Ext.form.field.Text, {
    getErrors: function(value) {
        var me = this,
//            errors = me.callParent(arguments), // 当value为""时，会出现两次验证提示，所以注释掉
            validator = me.validator,
            emptyText = me.emptyText,
            allowBlank = me.allowBlank,
            vtype = me.vtype,
            vtypes = Ext.form.field.VTypes,
            regex = me.regex,
            format = Ext.String.format,
            msg;

        var errors = [];

        value = value || me.processRawValue(me.getRawValue());

        if (Ext.isFunction(validator)) {
            msg = validator.call(me, value);
            if (msg !== true) {
                errors.push(msg);
            }
        }

        // 去除前后空格,只给allowBlank属性验证用
        var allowBlankValue = Ext.String.trim(value);
        if (allowBlankValue.length < 1 || allowBlankValue === emptyText) {
            if (!allowBlank) {
                errors.push(me.blankText);
            }
            //if value is blank, there cannot be any additional errors
            return errors;
        }

        if (value.length < me.minLength) {
            errors.push(format(me.minLengthText, me.minLength));
        }

        if (value.length > me.maxLength) {
            errors.push(format(me.maxLengthText, me.maxLength));
        }

        if (vtype) {
            if(!vtypes[vtype](value, me)){
                errors.push(me.vtypeText || vtypes[vtype +'Text']);
            }
        }

        if (regex && !regex.test(value)) {
            errors.push(me.regexText || me.invalidText);
        }

        return errors;
    },
   
    //支持所有的Text增加 clear功能
    config : {
    	hideTrigger: false,
    	canClear: true,
    	triggers :{
    		clear:{
    			cls:Ext.baseCSSPrefix + "form-clear-trigger",
    			hidden:true,
    			handler: 'onClearClick',
                scope: 'this'
    		}
    	}
    },
	
	listeners:{
		change:function(text,newValue,oldValue){
			var clearTip = text.getTrigger("clear");
			if(undefined==clearTip){
				return;
			}
			if(this.canClear && !this.readOnly && !this.hideTrigger){
				if(newValue && newValue!== "" ){
					text.getTrigger("clear").show();
				}else{
					text.getTrigger("clear").hide();
				}
			}
		}
	},
	
	//可重写此方式，实现具体的业务逻辑
	onClearClick : function(){
		this.setValue(null);
	}
});


Ext.override(Ext.form.field.ComboBox, {
    //支持所有的Text增加 clear功能
    config : {
    	hideTrigger: false,
    	canClear: true,
    	triggers :{
    		clear:{
    			cls:Ext.baseCSSPrefix + "form-clear-trigger",
    			hidden:true,
    			handler: 'onClearClick',
                scope: 'this'
    		}
    	}
    },
	
	listeners:{
		change:function(text,newValue,oldValue){
			var clearTip = text.getTrigger("clear");
			if(undefined==clearTip){
				return;
			}
			if(this.canClear && !this.readOnly){
				if(newValue && newValue!== "" ){
					text.getTrigger("clear").show();
				}else{
					text.getTrigger("clear").hide();
				}
			}
		}
	},
	
	//可重写此方式，实现具体的业务逻辑
	onClearClick : function(){
		this.setValue(null);
	}
});

/**
 * 重写picker，增加clear
 */
Ext.override(Ext.form.field.Picker, {
	config: {
    	hideTrigger: false,
    	canClear: true,
        triggers: {
    		picker: {
                handler: 'onTriggerClick',
                scope: 'this'
            },
            clear:{
    			cls:Ext.baseCSSPrefix + "form-clear-trigger",
    			hidden:true,
    			handler: 'onClearClick',
                scope: 'this'
    		}
        }
    }
});

//Ext.form.field.Picker.override({initEvents:function(){
//    	var me = this;
//        me.callParent();
//        me.keyNav = new Ext.util.KeyNav(me.inputEl, {
//            down: me.onDownArrow,
//            esc: {
//                handler: me.onEsc,
//                scope: me,
//                defaultEventAction: false
//            },
//            scope: me,
//            forceKeyDown: true
//        });
//        if(me.readOnly){
//        	return;
//        }
//        if (me.isOrg) {
//            me.removeManagedListener(me.inputEl, 'click', me.onTriggerClick, me);
//            me.mon(me.inputEl, 'click', me.onTrigger1Click, me);
//        }
//        else{
//        	me.mon(me.inputEl, 'click', me.onTriggerClick, me);
//        }
//
//        // Disable native browser autocomplete
//        if (Ext.isGecko) {
//            me.inputEl.dom.setAttribute('autocomplete', 'off');
//        }
//}
//});



//修复model.destroy()时，因row.className = row.className错误，无法调用回调函数的问题
Ext.define('Ext.view.override.Table', {
    override: 'Ext.view.Table',

    doStripeRows: function(startRow, endRow) {
        var me = this,
            rows,
            rowsLn,
            i,
            row;

        if (me.rendered && me.stripeRows) {
            rows = me.getNodes(startRow, endRow);

            for (i = 0, rowsLn = rows.length; i < rowsLn; i++) {
                row = rows[i];

                if (row) { // self updating; check for row existence
                    row.className = row.className.replace(me.rowClsRe, ' ');
                    startRow++;

                    if (startRow % 2 === 0) {
                        row.className += (' ' + me.altRowCls);
                    }
                }
            }
        }
    }

});

/**
 * hack IE8.0-上的文件上传2次提交问题
 */
Ext.form.field.File.override({
	checkChange : function() {
		if (!this.suspendCheckChange) {
			var me = this,
				newVal = me.getValue(),
				oldVal = me.lastValue;

			if (!me.isEqual(newVal, oldVal) && !me.isDestroyed
					&& !Ext.isEmpty(newVal)) {
				me.lastValue = newVal;
				me.fireEvent('change', me, newVal, oldVal);
				me.onChange(newVal, oldVal);
			}
		}
	}
});


Ext.define('Ext.grid.OrgPanel', {
    extend: 'Ext.panel.Table',
    requires: ['Ext.view.Table'],
    alias: ['widget.orgGridPanel'],
    viewType: 'tableview',

    lockable: false,

    rowLines: true

    
});
