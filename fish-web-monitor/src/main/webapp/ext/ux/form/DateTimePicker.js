Ext.define('Ext.ux.form.DateTimePicker', {
	extend: 'Ext.picker.Date',
      alias: 'widget.datetimepicker',
      todayText: '确定',
      timeLabel: '时间',
      requires: ['Ext.ux.form.TimePickerField'],
      timefield:"",
      readOnly:false,
      editable: true,
      focusable: true,
      config:{
    	oldTime:''  
      },
      initComponent: function() {
          // keep time part for value
          var value = this.value || new Date();
          this.callParent();
          this.value = value;
      },
      onRender: function(container, position) {
      		var me = this;
          this.timefield = Ext.create('Ext.ux.form.TimePickerField', {
              	width:'100%',
              	oldTime:me.getOldTime(),
              	valueChange:function(){
              		me.fillDateTime(me.value);
              	}
              });
          
          this.timefield.ownerCt = this;
//          this.timefield.on('change', this.timeChange, this);
                    
          var table = Ext.get(Ext.DomQuery.selectNode('table', this.el.dom));
          var tfEl = Ext.core.DomHelper.insertAfter(table, {
                tag: 'div',
                style: 'border:0px;',
                children: [{
                      tag: 'div',
                      cls: 'x-datepicker-footer ux-timefield'
                  }]
            }, true);
            
          this.timefield.render(this.el.child('div div.ux-timefield'));
          
          var p = this.getEl().parent('div.x-layer');
          if(p) {
              p.setStyle("height", p.getHeight() + 31);
          }
          
          this.callParent(arguments);
      },
      // listener 时间域修改, timefield change
      timeChange: function(tf, time, rawtime) {
          // if(!this.todayKeyListener) { // before render
          this.value = this.fillDateTime(this.value);
          // } else {
          // this.setValue(this.value);
          // }
      },
      // @private
      fillDateTime: function(value) {
          if(this.timefield) {
              var rawtime = this.timefield;
              value.setHours(rawtime.getValueH());
              value.setMinutes(rawtime.getValueI());
              value.setSeconds(rawtime.getValueS());
          }
          this.mySetValue(value);
          return value;
      },
      // @private
      changeTimeFiledValue: function(value) {
//          this.timefield.un('change', this.timeChange, this);
//          this.timefield.setValue(this.value);
//          this.timefield.on('change', this.timeChange, this);
      },
      /* TODO 时间值与输入框绑定, 考虑: 创建this.timeValue 将日期和时间分开保存. */
      // overwrite
      setValue: function(value) {
          this.value = value;
          this.changeTimeFiledValue(value);
          return this.update(this.value);
      },// overwrite
      getValue: function() {
          return this.fillDateTime(this.value);
      },
      // overwrite : fill time before setValue
      handleDateClick: function(e, t) {
          var me = this,
              handler = me.handler;
          e.stopEvent();
          
          if(!me.disabled && t.dateValue && !Ext.fly(t.parentNode).hasCls(me.disabledCellCls)) {
              me.doCancelFocus = me.focusOnSelect === false;
              me.setValue(this.fillDateTime(new Date(t.dateValue))); // overwrite: fill time before setValue
//              me.setValue(new Date(t.dateValue));
//              delete me.doCancelFocus;
//              me.fireEvent('select', me, me.value);
//              if(handler) {
//                  handler.call(me.scope || me, me, me.value);
//              }
              me.onSelect();
          }
      },
      // overwrite : fill time before setValue
      selectToday: function() {
          var me = this,
              btn = me.todayBtn,
              handler = me.handler;
          if(btn && !btn.disabled) {
              // me.setValue(Ext.Date.clearTime(new Date())); //src
//              me.setValue(new Date());// overwrite: fill time before setValue
              me.fireEvent('select', me, me.value);
              if(handler) {
                  handler.call(me.scope || me, me, me.value);
              }
              me.onSelect();
          }
          
          me.collapse();
          return me;
      }
  });