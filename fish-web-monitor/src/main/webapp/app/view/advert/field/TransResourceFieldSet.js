Ext.define('Eway.view.advert.field.TransResourceFieldSet', {
	extend : 'Ext.form.FieldSet',
	alias : 'widget.field_transResourceFieldSet',

	requires : [ 'Eway.store.Hour', 'Eway.store.Minute' ],
	checkboxToggle : false,
	title : Eway.locale.advert.transMoreTitle,//'添加交易页面广告',
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

	afterRender: function () {
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

    onCloseClick: function () {
        if (this.ownerCt) {
            this.ownerCt.remove(this, true);
        }
    },

	initComponent : function() {
		Ext.apply(this, {
		    items :[ {
		    	xtype:'form',
		   		border:false,
		    	items:[{
		    		xtype: 'fieldcontainer',
			        fieldLabel: Eway.locale.advert.chooseMediaFile,//'请选择媒体文件',
			        combineErrors: true,
			        msgTarget: 'under',
			        defaults: {
			            hideLabel: true
			        },
			        items:[{
				    	xtype: 'filefield',
						buttonOnly:true,
						name: 'file',
				    	allowBlank: false,
				    	blankText :Eway.locale.advert.uploadResourceBlank,//'请上传资源',
						buttonText: Eway.locale.advert.uploadResource,//'上传资源...',
						regex : /\.(jpg|gif)$/i,
						regexText:Eway.locale.advert.uploadRegText//'上传的资源格式不支持,只能上传.jpg、.avi格式的文件'
			    	},{
			        	xtype: 'displayfield',
			        	name:'oFileName',
			        	value :Eway.locale.advert.resourceFormatTips//'(仅支持.jpg、.avi格式的文件)'
		       	    },{
				    	xtype:'hidden',
				    	fieldLabel:Eway.locale.advert.resourceAlias,//'修改后的文件名',
				    	name:'content'
				    }]
		   	     }]
		    },{
		    	xtype: 'fieldcontainer',
		        fieldLabel: Eway.locale.advert.playTime,//'广告播放时长',
		        combineErrors: true,
		        msgTarget: 'under',
		        defaults: {
		            hideLabel: true
		        },
		        items:[{
		        	xtype:'numberfield',
		        	fieldLabel: Eway.locale.advert.times,//'时长',
		        	hideTrigger:true,
		        	value: 10,
		            minValue: 1,
		            maxValue: 60,
		            name:'playTime',
		            width:50
		        },{
		        	xtype: 'displayfield',
		        	value :Eway.locale.advert.timesTips//'单位:秒，提示：广告播放时长请控制在60秒内'
		        }]
		    }, {
		    	xtype: 'container',
		        msgTarget: 'under',
		        items:[{
			    	xtype:'datefield',
			    	fieldLabel: Eway.locale.advert.beginDate,//'开始日期',
			    	name:'beginDate',
			    	disabled: false,
			    	editable: true,
			        format: 'Y-m-d',
			        hidden:true,
			        minValue : new Date(),
			        width:250
			    },{
			    	xtype:'datefield',
			    	fieldLabel: Eway.locale.advert.endDate,//'结束日期',
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
		        fieldLabel: Eway.locale.advert.beginTime,//'开始时间',
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
		            {xtype: 'displayfield', value: Eway.locale.advert.hourDisplay},//'时'},
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
		            }, {xtype: 'displayfield', value: Eway.locale.advert.minuteDisplay},//'分'},
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
		            {xtype: 'displayfield', value: Eway.locale.advert.secondDisplay},//'秒'}

		        ]
		    },{
		        xtype: 'fieldcontainer',
		        fieldLabel: Eway.locale.advert.endTime,//'结束时间',
		        combineErrors: true,
		        msgTarget: 'under',
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
		            {xtype: 'displayfield', value: Eway.locale.advert.hourDisplay},//'时'},
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
		            }, {xtype: 'displayfield', value: Eway.locale.advert.minuteDisplay},//'分'},
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
		            {xtype: 'displayfield', value: Eway.locale.advert.secondDisplay},//'秒'}
		           ]
		       }]
		});
		this.callParent(arguments);
	}

});