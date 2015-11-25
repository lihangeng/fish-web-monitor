Ext.define('Eway.view.advert.field.TextResourceFieldSet', {
	extend : 'Ext.form.FieldSet',
	alias : 'widget.field_textResourceFieldSet',

	requires : [ 'Eway.store.Hour', 'Eway.store.Minute' ],
	checkboxToggle : false,
	title : EwayLocale.advert.textMoreTitle,//'添加文字滚动页面广告',
	collapsed : false,
	collapsible : true,
	defaults : {
		anchor : '100%',
		layout : {
			type : 'hbox',
			defaultMargins : {
				top : 0,right : 5,bottom : 0,left : 0
			}
		}
	},

/*	afterRender: function () {
        var me = this;
        me.callParent(arguments);
        if (!me.legend.closable) {
            me.legend.insert(1, Ext.widget('tool', {
                type: 'close',
                handler: me.onCloseClick,
                scope: me
            }));
            me.legend.closable = true;
        }
    },
*/
    onCloseClick: function () {
        if (this.ownerCt) {
            this.ownerCt.remove(this, true);
        }
    },

	initComponent : function() {
		Ext.apply(this, {
		    items :[ {
		    	fieldLabel :EwayLocale.advert.textContext,//'滚动文字',
		    	xtype: 'textarea',
				name:'content',
				allowBlank : false,
				maxLength: 140,
				height:80,
				regex: /^\S+$/,
				regexText:EwayLocale.advert.annoucementContextRegText//'不能包含空格'
		    },{
		    	xtype: 'fieldcontainer',
		        fieldLabel: EwayLocale.advert.playTime,//'广告播放时长',
		        combineErrors: true,
		        msgTarget: 'under',
		        defaults: {
		            hideLabel: true
		        },
		        items:[{
		        	xtype:'numberfield',
		        	hideTrigger:true,
		        	fieldLabel: EwayLocale.advert.times,//'时长',
		        	value: 5,
		            minValue: 1,
		            maxValue: 60,
		            name:'playTime',
		            width:50
		        },{
		        	xtype: 'displayfield',
		        	value :EwayLocale.advert.timesTips//'单位:秒，提示：广告播放时长请控制在60秒内'
		        }]
		    }, {
		    	xtype: 'container',
		        msgTarget: 'under',
		        items:[{
			    	xtype:'datefield',
			    	fieldLabel: EwayLocale.advert.beginDate,//'开始日期',
			    	name:'beginDate',
			    	disabled: false,
			    	editable: true,
			        format: 'Y-m-d',
			        hidden:true,
			        minValue : new Date(),
			        width:250
			    },{
			    	xtype:'datefield',
			    	fieldLabel: EwayLocale.advert.endDate,//'结束日期',
			    	labelAlign :'right',
			    	name:'endDate',
			    	disabled: false,
			    	editable: true,
			        format: 'Y-m-d',
			        hidden: true,
			        minValue : new Date(),
			        width:250
			    }]
		     },{
		        xtype: 'fieldcontainer',
		        fieldLabel: EwayLocale.advert.beginTime,//'开始时间',
		        combineErrors: true,
		        defaults: {
		            hideLabel: true
		        },
		        items: [
		            /*{xtype: 'displayfield', value: '开始时间：'
		            },*/{
		            	xtype: 'combobox',
		            	fieldLabel: 'hour',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Hour"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name: 'hour',
		                width:50
		            },
		            {xtype: 'displayfield', value: EwayLocale.advert.hourDisplay},//'时'},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'minute',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name:'minute',
		                width:50
		            }, {xtype: 'displayfield', value: EwayLocale.advert.minuteDisplay},//'分'},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'second',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name:'second',
		                width:50
		            },
		            {xtype: 'displayfield', value: EwayLocale.advert.secondDisplay}//'秒'}

		        ]
		    },{
		        xtype: 'fieldcontainer',
		        fieldLabel: EwayLocale.advert.endTime,//'结束时间',
		        combineErrors: true,
		        defaults: {
		            hideLabel: true
		        },
		        items: [
		            /*{xtype: 'displayfield', value: '结束时间：'
		            },*/{
		            	xtype: 'combobox',
		            	fieldLabel: 'hour',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Hour"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'23',
		                name:'hour',
		                width:50
		            },
		            {xtype: 'displayfield', value: EwayLocale.advert.hourDisplay},//'时'},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'minute',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'59',
		                name:'minute',
		                width:50
		            }, {xtype: 'displayfield', value: EwayLocale.advert.minuteDisplay},//'分'},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'second',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'59',
		                name:'second',
		                width:50
		            },
		            {xtype: 'displayfield', value: EwayLocale.advert.secondDisplay}//'秒'}
		           ]
		       }]
		});
		this.callParent(arguments);
	}

});