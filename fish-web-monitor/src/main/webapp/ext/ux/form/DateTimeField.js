Ext.define("Ext.ux.form.DateTimeField",{
	extend:"Ext.form.DateField",
	alias:"widget.datetimefield",
	format:"Y-m-d H:i:s",
	requires:['Ext.ux.form.DateTimePicker'],
//	value:Ext.util.Format.date(Ext.Date.add(new Date(),Ext.Date.MONTH+1,0),"Y-m-d")
	_picker:"",
	config : {
		hideTrigger : false,
		canClear : true,
		triggers : {
			clear : {
				cls : Ext.baseCSSPrefix + "form-clear-trigger",
				hidden : true,
				handler : 'onClearClick',
				scope : 'this'
			}
		},
		listeners : {
			afterrender : function(text) {
				var clearTip = text.getTrigger("clear");
				if (this.canClear) {
					return;
				} else {
					clearTip.hide();
				}
			}
		}
	},
	
	
	getErrors: function(value) {
        value = arguments.length > 0 ? value : this.formatDate(this.processRawValue(this.getRawValue()));

        var me = this;
        var   format = Ext.String.format;
        var   clearTime = Ext.Date.clearTime;
        var   errors =me.callParent(arguments);
        var   disabledDays = me.disabledDays;
        var    disabledDatesRE = me.disabledDatesRE;
        var    minValue = me.minValue;
        var    maxValue = me.maxValue;
        var    len = disabledDays ? disabledDays.length : 0;
        var    i = 0;
        var     svalue;
        var    fvalue;
        var    day;
        var    time;


        if (!me.allowBlank && (me.value ==""||me.value ==null)) {
            errors.push(me.blankText);
        }

        if (value === null || value.length < 1) { // if it's blank and textfield didn't flag it then it's valid
             return errors;
        }

        svalue = value;
        value = me.parseDate(value);

        if (!value) {
            errors.push(format(me.invalidText, svalue, Ext.Date.unescapeFormat(me.format)));
            return errors;
        }

        time = value.getTime();
        if (minValue && time < minValue.getTime()) {
            errors.push(format(me.minText, me.formatDate(minValue)));
        }

        if (maxValue && time > maxValue.getTime()) {
            errors.push(format(me.maxText, me.formatDate(maxValue)));
        }

        if (disabledDays) {
            day = value.getDay();

            for(; i < len; i++) {
                if (day === disabledDays[i]) {
                    errors.push(me.disabledDaysText);
                    break;
                }
            }
        }

        fvalue = me.formatDate(value);
        if (disabledDatesRE && disabledDatesRE.test(fvalue)) {
            errors.push(format(me.disabledDatesText, fvalue));
        }

        return errors;
    },
	
	
	
	
	
	
	
	
	
	
	
	expand: function() {
        var me = this,
            bodyEl, picker, collapseIf;

        if (me.rendered && !me.isExpanded && !me.isDestroyed) {
            me.expanding = true;
            bodyEl = me.bodyEl;
            picker = me.getPicker();
            collapseIf = me.collapseIf;
			this._picker = picker;
            // show the picker and set isExpanded flag
            picker.show();
            me.isExpanded = true;
            me.alignPicker();
            bodyEl.addCls(me.openCls);

            // monitor clicking and mousewheel
            me.mon(Ext.getDoc(), {
                mousewheel: collapseIf,
                mousedown: collapseIf,
                scope: me
            });
            Ext.on('resize', me.alignPicker, me);
            me.fireEvent('expand', me);
            me.onExpand();
            doc = Ext.getDoc();
            me.hideListeners = doc.on({
                mousewheel: me.collapseIf,
                touchstart: me.collapseIf,
                scope: me,
                delegated: false,
                destroyable: true
            });
            Ext.on('resize', me.alignPicker, me);
            me.fireEvent('expand', me);
            me.onExpand();
            delete me.expanding;
        }
    },
    collapseIf: function(e) {
        var me = this;

        // If what was mousedowned on is outside of this Field, and is not focusable, then collapse.
        // If it is focusable, this Field will blur and collapse anyway.
        if (!me.isDestroyed && !e.within(me._picker.getEl(), false, true) && !me.owns(e.target) && !Ext.fly(e.target).isFocusable()) {
            me._collapse();
        }
    },

    _collapse: function() {
        var me = this;
        
        if (me.isExpanded && !me.isDestroyed && !me.destroying) {
            var openCls = me.openCls,
                picker = me.picker,
                doc = Ext.getDoc(),
                collapseIf = me.collapseIf,
                aboveSfx = '-above';

            // hide the picker and set isExpanded flag
            picker.hide();
            me.isExpanded = false;

            // remove the openCls
            me.bodyEl.removeCls([openCls, openCls + aboveSfx]);
            picker.el.removeCls(picker.baseCls + aboveSfx);

            // remove event listeners
            doc.un('mousewheel', collapseIf, me);
            doc.un('mousedown', collapseIf, me);
            Ext.un('resize', me.alignPicker, me);
            me.fireEvent('collapse', me);
            me.onCollapse();
        }
    },
    collapse: function() {
        
    },
    
    createPicker: function() {
        var me = this,
            format = Ext.String.format;

        // Create floating Picker BoundList. It will acquire a floatParent by looking up
        // its ancestor hierarchy (Pickers use their pickerField property as an upward link)
        // for a floating component.
        return new Ext.ux.form.DateTimePicker({
	            pickerField: me,
	            floating: true,
	            focusable: true, // Key events are listened from the input field which is never blurred
	            hidden: true,
	            disabledDatesRE: me.disabledDatesRE,
	            disabledDatesText: me.disabledDatesText,
	            disabledDays: me.disabledDays,
	            disabledDaysText: me.disabledDaysText,
	            format: me.format,
	            oldTime:me.getValue(),
	            minDate: me.minValue,
	            maxDate: me.maxValue,
	            minText: format(me.minText, me.formatDate(me.minValue)),
	            maxText: format(me.maxText, me.formatDate(me.maxValue)),
	            showToday: me.showToday,
	            width:250,
	            listeners: {
	                scope: me,
	                select: me.onSelect
	            },
	            keyNavConfig: {
	                esc: function() {
	                    me.collapse();
	                }
	            },    
	            setMaxValue : function(value){
	                var me = this,
	                picker = me.picker,
	                maxValue = (Ext.isString(value) ? me.parseDate(value) : value);

		            me.maxValue = maxValue;
		            if (picker) {
		                picker.maxText = Ext.String.format(me.maxText, me.formatDate(me.maxValue));
		                picker.setMaxDate(maxValue);
		            }
		        },
	            onSelect: function() {
//	            	Ext.Date.format(this.getValue(),'Y-m-d')+" "+Ext.Date.format(this.timefield.getValue(),'H:i:s')
	            	me.setValue(Ext.Date.format(this.getValue(),'Y-m-d H:i:s'));
//			        if (this.hideOnSelect) {
//			             this.hide();
//			        }
			    },
			    collapse:function(){
			    	me._collapse();
			    },
			    mySetValue:function(val){
			    	me.setValue(Ext.Date.format(val,'Y-m-d H:i:s'));
			    }
        	});
    }
});