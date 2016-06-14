
//修改pie支持自定义颜色
Ext.chart.series.Pie.override({
    provideLegendInfo: function (target) {
        var me = this,
            store = me.getStore();
        if (store) {
            var items = store.getData().items,
                labelField = me.getLabel().getTemplate().getField(),
                field = me.getAngleField(),
                hidden = me.getHidden(),
                i, style, fill;

            for (i = 0; i < items.length; i++) {
                style = me.getStyleByIndex(i);
                fill = style.fillStyle;
                if (Ext.isObject(fill)) {
                    fill = fill.stops && fill.stops[0].color;
                }
                target.push({
//                    name: labelField ? String(items[i].get(labelField))  : field + ' ' + i,
                    name: labelField ? String(items[i].get(labelField))+': '+items[i].get(field)  : field + ' ' + i,
                    mark: fill || style.strokeStyle || 'black',
                    disabled: hidden[i],
                    series: me.getId(),
                    index: i
                });
            }
        }
    }
});
/**
 * 增加和更改页面的确定按钮是否可用
 */
Ext.data.Model.override({
	  save: function(options) {
	        options = Ext.apply({}, options);
	        if(options.button){
	        	options.button.disable();
	        }
	        var me = this,
	            phantom = me.phantom,
	            dropped = me.dropped,
	            action = dropped ? 'destroy' : (phantom ? 'create' : 'update'),
	            scope  = options.scope || me,
	            callback = options.callback,
	            proxy = me.getProxy(),
	            operation;
	            
	        options.records = [me];
	        options.internalCallback = function(operation) {
	            var args = [me, operation],
	                success = operation.wasSuccessful();
	            if (success) {
	                Ext.callback(options.success, scope, args);
	            } else {
	                Ext.callback(options.failure, scope, args);
	                if(options.button){
	                	options.button.enable();
	                }
	            }
	            args.push(success);
	            Ext.callback(callback, scope, args);
	        };
	        delete options.callback;
	        
	        operation = proxy.createOperation(action, options);

	        // Not a phantom, then we must perform this operation on the remote datasource.
	        // Record will be removed from the store in the callback upon a success response
	        if (dropped && phantom) {
	            // If it's a phantom, then call the callback directly with a dummy successful ResultSet
	            operation.setResultSet(Ext.data.reader.Reader.prototype.nullResultSet);
	            me.setErased();
	            operation.setSuccessful(true);
	        } else {
	            operation.execute();
	        }
	        return operation;
	    }
}),


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
//           errors = me.callParent(arguments), // 当value为""时，会出现两次验证提示，所以注释掉
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
					clearTip.show();
				}else{
					clearTip.hide();
				}
			}
		},
		afterrender: function(text){
			var clearTip = text.getTrigger("clear");
			if(undefined==clearTip){
				return;
			}else{
				clearTip.hide();
			}
		}
	},
	
	//可重写此方式，实现具体的业务逻辑
	onClearClick : function(){
		this.setValue(null);
	}
});

//支持所有的ComboBox增加 clear功能,如果不需要clear功能，直接将canClear设置为false
Ext.override(Ext.form.field.ComboBox, {
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
   	},
   	listeners:{
		afterrender: function(text){
			var clearTip = text.getTrigger("clear");
			if(this.canClear){
				return;
			}else{
				clearTip.hide();
			}
		}
	}
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
//针对pivot报表leftAxis进行重写;Default config  text: me.textRowLabels,
Ext.pivot.matrix.Base.override({
	generateCompactLeftAxis: function(disableChangeModel) {
        var me = this;
        if (!disableChangeModel) {
            me.model.push({
                name: me.compactViewKey,
                type: 'string'
            });
        }
        me.columns.push({
            dataIndex: me.compactViewKey,
            text: me.leftAxis.dimensions.items[0].header,
            leftAxis: true,
            width: 200
        });
    }
});

//针对pivot报表进行渲染，将leftAxis的宽度设置为可调整的
Ext.pivot.feature.PivotView.override({
	recordCompactRenderer: function(config) {
	    var me = this,
	        prevRenderer = config['renderer'],
	        group = config['group'];
	    return function(value, metaData, record, rowIndex, colIndex, store, view) {
	        if (Ext.isFunction(prevRenderer)) {
	            value = prevRenderer.apply(this, arguments);
	        }
	        // the value has to be encoded to avoid messing up the DOM
	        value = me.encodeValue(value, group);
	        if (group.level > 0) {
	            metaData.style = (me.isRTL() ? 'margin-right: ' : 'margin-left: ') + (me.compactLayoutPadding * group.level) + 'px;';
	        }
	        //将leftAxis的宽度设置为可调整的
	        metaData.column.width= group.dimension.width;
	        metaData.tdCls = me.groupHeaderCls + ' ' + me.groupTitleCls + ' ' + me.compactGroupHeaderCls;
	        return value;
	    };
	}
});
