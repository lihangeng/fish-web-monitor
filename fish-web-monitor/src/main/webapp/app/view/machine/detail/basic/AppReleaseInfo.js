Ext.define('Eway.view.machine.detail.basic.AppReleaseInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_appReleaseInfo',
    
    tools: [ {
        itemId: 'refresh',
        type: 'refresh',
        tooltip: EwayLocale.button.refresh,
        callback: function() {
            // do refresh
        }
    }],
	
	requires : [  ],
    layout: {
        type: 'column',
    },
    closable:false,
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'left',
				xtype : 'displayfield',
				labelWidth : 100,
				readOnly : true,
				width : '50%'
			},
			items : [ {
				columnWidth : .33,
				fieldLabel : '当前版本',
				name : 'appRelease',
				   // a_link : true
					//value:'<a href="#" class="link">'+value+'</a>'
					renderer: function(value,meta,record) {
						return '<a href="#" class="link">'+value+'</font></a>';
					}
			},{
				columnWidth : .33,
				fieldLabel : '上次升级时间',
				name : 'ip'
			},{
				columnWidth : .33,
				fieldLabel : '可升级版本',
				name : 'statusName'
			}]
		});

		this.callParent(arguments);
	}
});
