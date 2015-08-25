
/**
 * 时间输入框, 三个整数框分别输入时,分,秒.
 * @author wangzilong
 * update Ext - 4.1 2012/04/27
 */
Ext.define('Ext.ux.form.TimePickerField', {
    extend: 'Ext.form.field.Text',
    alias: 'widget.timepickerfield',
//    alternateClassName: 'Ext.form.Spinner',
    requires: [
        'Ext.form.trigger.Spinner',
        'Ext.util.KeyNav'
    ],

    config: {
    	oldTime:'',
        triggers: {
            spinner: {
                type: 'spinner',
                upHandler: 'onSpinnerUpClick',
                downHandler: 'onSpinnerDownClick',
                scope: 'this'
            }
        }
    },

    /**
     * @cfg {Boolean} spinUpEnabled
     * Specifies whether the up spinner button is enabled. Defaults to true. To change this after the component is
     * created, use the {@link #setSpinUpEnabled} method.
     */
    spinUpEnabled: true,

    /**
     * @cfg {Boolean} spinDownEnabled
     * Specifies whether the down spinner button is enabled. Defaults to true. To change this after the component is
     * created, use the {@link #setSpinDownEnabled} method.
     */
    spinDownEnabled: true,

    /**
     * @cfg {Boolean} keyNavEnabled
     * Specifies whether the up and down arrow keys should trigger spinning up and down. Defaults to true.
     */
    keyNavEnabled: true,

    /**
     * @cfg {Boolean} mouseWheelEnabled
     * Specifies whether the mouse wheel should trigger spinning up and down while the field has focus.
     * Defaults to true.
     */
    mouseWheelEnabled: true,

    /**
     * @cfg {Boolean} repeatTriggerClick
     * Whether a {@link Ext.util.ClickRepeater click repeater} should be attached to the spinner buttons.
     * Defaults to true.
     */
    repeatTriggerClick: true,

    /**
     * @method
     * @protected
     * This method is called when the spinner up button is clicked, or when the up arrow key is pressed if
     * {@link #keyNavEnabled} is true. Must be implemented by subclasses.
     */
    onSpinUp: Ext.emptyFn,

    /**
     * @method
     * @protected
     * This method is called when the spinner down button is clicked, or when the down arrow key is pressed if
     * {@link #keyNavEnabled} is true. Must be implemented by subclasses.
     */
    onSpinDown: Ext.emptyFn,
    
    ariaRole: 'spinbutton',

    /**
     * @event spin
     * Fires when the spinner is made to spin up or down.
     * @param {Ext.form.field.Spinner} this
     * @param {String} direction Either 'up' if spinning up, or 'down' if spinning down.
     */

    /**
     * @event spinup
     * Fires when the spinner is made to spin up.
     * @param {Ext.form.field.Spinner} this
     */

    /**
     * @event spindown
     * Fires when the spinner is made to spin down.
     * @param {Ext.form.field.Spinner} this
     */

    applyTriggers: function(triggers) {
        var me = this,
            spinnerTrigger = triggers.spinner;

            
        spinnerTrigger.upEnabled = me.spinUpEnabled;
        spinnerTrigger.downEnabled = me.spinDownEnabled;

        return me.callParent([triggers]);
    },

    initComponent: function(){
    	var me = this;
    	me.on('afterrender',function(){
    		var me = this, spinnerWrapDom, spinnerWrap,inputdiv;   
    		
	    	var eldom = this.getEl().dom;
	      	spinnerWrapDom = Ext.dom.Query.select('div',eldom )[0]; // 4.0 ->4.1 div->td
	      	
	      	inputdiv = Ext.dom.Query.select("div[id$='triggerWrap']", eldom)[0]; 	      	
	      	inputdiv.style.display='none';
//	      	inputdiv.setStyle("display","none");
	      	spinnerWrap = Ext.get(spinnerWrapDom);
	      	var times = me.getOldTime();
	      	var hour=min=sec=0;
	      	if(undefined!=times&&""!=times){
	      		hour = times.getHours()
	      		min = times.getMinutes();
	      		sec = times.getSeconds();
	      	}
	      	me.hoursSpinner = Ext.create('Ext.form.field.Number', {
	                  minValue: 0,
	                  maxValue: 23,
	                  value:hour,
                      fieldLabel : "时间",
                      editable:false,
                      labelWidth:30,
                      width:90
	              });
	        me.hoursSpinner.on("change",this.valueChange);
	        
	        me.minutesSpinner = Ext.create('Ext.form.field.Number',{
	                  minValue: 0,
	                  maxValue: 59,
	                  value:min,
	                  fieldLabel : "",
	                  editable:false,
                      labelWidth:13,
                      width:60
	              });      
	        me.minutesSpinner.on("change",this.valueChange);
	      	me.secondsSpinner = Ext.create('Ext.form.field.Number',{
	                  minValue: 0,
	                  value:sec,
	                  maxValue: 59,
	                  fieldLabel : "",
	                  editable:false,
                      labelWidth:13,
                      width:60
	        });
	        me.secondsSpinner.on("change",this.valueChange);
	        
	        
	        var temp = Ext.create("Ext.Panel",{
	        	layout: 'column',
	        	width:'100%',
	        	items : [
		        	{
		        		xtype: 'panel',
						border:false,
						columnWidth: .44,
						items:[me.hoursSpinner]
		        	},
		        	{
		        		xtype: 'panel',
						border:false,
						columnWidth: .28,
						items:[me.minutesSpinner]
		        	},{
		        		xtype: 'panel',
						border:false,
						columnWidth: .28,
						items:[me.secondsSpinner]
		        	}
	        	]
	        });
//	        ,,
	        temp.render(spinnerWrap);
	        
//	        me.hoursSpinner.render(spinnerWrap);
//	        me.minutesSpinner.render(spinnerWrap);
//	        me.secondsSpinner.render(spinnerWrap);
	        
	      	Ext.dom.Helper.append(spinnerWrap, {
	      	      tag: 'div',
	            	cls: 'x-form-clear-left'
	        }); 
	        
    	}),
        me.value = me.value || Ext.Date.format(new Date(), 'H:i:s');
        me.callParent();
    },
    /**
     * @private
     * Override.
     */
    onRender: function() {
        var me = this,
            spinnerTrigger = me.getTrigger('spinner');

        me.callParent();

        // Init up/down arrow keys
        if (me.keyNavEnabled) {
            me.spinnerKeyNav = new Ext.util.KeyNav(me.inputEl, {
                scope: me,
                up: me.spinUp,
                down: me.spinDown
            });
        }

        // Init mouse wheel
        if (me.mouseWheelEnabled) {
            me.mon(me.bodyEl, 'mousewheel', me.onMouseWheel, me);
        }

        // in v4 spinUpEl/spinDownEl were childEls, now they are children of the trigger.
        // create references for compatibility
        me.spinUpEl = spinnerTrigger.upEl;
        me.spinDownEl = spinnerTrigger.downEl;
        
    },
    
    valueChange:Ext.emptyFn,
    
	getValue: function() {		
		if(this.hoursSpinner != undefined && this.minutesSpinner != undefined && this.secondsSpinner != undefined 
			&& this.hoursSpinner.getValue() != null && this.minutesSpinner.getValue() != null && this.secondsSpinner.getValue() != null)
          return this.hoursSpinner.getValue()+":"+this.minutesSpinner.getValue()+":"+this.secondsSpinner.getValue();
        return "00:00:01";
    },
    getValueH:function(){
    	if(this.hoursSpinner != undefined 
			&& this.hoursSpinner.getValue() != null)
          return this.hoursSpinner.getValue();
        return "00";
    },
    getValueI:function(){
    	if(this.minutesSpinner != undefined
			&&this.minutesSpinner.getValue() != null)
          return this.minutesSpinner.getValue();
        return "00";
    },
    getValueS:function(){
    	if(this.secondsSpinner != undefined 
			&& this.secondsSpinner.getValue() != null)
          return this.secondsSpinner.getValue();
        return "01";
    },
    setValue:function(val){
    	
    },	
    setValueH:function(val){
    	if(this.hoursSpinner != undefined)
          this.hoursSpinner.setValue(val);
    },
    setValueI:function(val){
    	if(this.minutesSpinner != undefined)
          return this.minutesSpinner.setValue(val);
    },
    setValueS:function(val){
    	if(this.secondsSpinner != undefined)
          this.secondsSpinner.setValue(val);
    },
    spinners : [],
    
    callSpinnersFunction: function(funName, args) {
          for(var i = 0; i < this.spinners.length; i++) {
              this.spinners[i][funName](args);
          }
    },
      
    /**
     * @private
     * Handles the spinner up button clicks.
     */
    onSpinnerUpClick: function() {
        this.spinUp();
    },

    /**
     * @private
     * Handles the spinner down button clicks.
     */
    onSpinnerDownClick: function() {
        this.spinDown();
    },

    /**
     * Triggers the spinner to step up; fires the {@link #spin} and {@link #spinup} events and calls the
     * {@link #onSpinUp} method. Does nothing if the field is {@link #disabled} or if {@link #spinUpEnabled}
     * is false.
     */
    spinUp: function() {
        var me = this;
        if (me.spinUpEnabled && !me.disabled) {
            me.fireEvent('spin', me, 'up');
            me.fireEvent('spinup', me);
            me.onSpinUp();
        }
    },

    /**
     * Triggers the spinner to step down; fires the {@link #spin} and {@link #spindown} events and calls the
     * {@link #onSpinDown} method. Does nothing if the field is {@link #disabled} or if {@link #spinDownEnabled}
     * is false.
     */
    spinDown: function() {
        var me = this;
        if (me.spinDownEnabled && !me.disabled) {
            me.fireEvent('spin', me, 'down');
            me.fireEvent('spindown', me);
            me.onSpinDown();
        }
    },

    /**
     * Sets whether the spinner up button is enabled.
     * @param {Boolean} enabled true to enable the button, false to disable it.
     */
    setSpinUpEnabled: function(enabled) {
        var me = this,
            wasEnabled = me.spinUpEnabled;
        me.spinUpEnabled = enabled;
        if (wasEnabled !== enabled && me.rendered) {
            me.getTrigger('spinner').setUpEnabled(enabled);
        }
    },

    /**
     * Sets whether the spinner down button is enabled.
     * @param {Boolean} enabled true to enable the button, false to disable it.
     */
    setSpinDownEnabled: function(enabled) {
        var me = this,
            wasEnabled = me.spinDownEnabled;
        me.spinDownEnabled = enabled;
        if (wasEnabled !== enabled && me.rendered) {
            me.getTrigger('spinner').setDownEnabled(enabled);
        }
    },

    /**
     * @private
     * Handles mousewheel events on the field
     */
    onMouseWheel: function(e) {
        var me = this,
            delta;
        if (me.hasFocus) {
            delta = e.getWheelDelta();
            if (delta > 0) {
                me.spinUp();
            } else if (delta < 0) {
                me.spinDown();
            }
            e.stopEvent();
        }
    },

    onDestroy: function() {
        Ext.destroyMembers(this, 'spinnerKeyNav');
        this.callParent();
    }

});