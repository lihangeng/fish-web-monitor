function advertVesionDown(fileName){

}
Ext.define('Eway.view.advert.AdvertVersion', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.advert_advertVersion',
			bodyStyle : 'padding: 5px 0px 0px 11px',
			initComponent : function() {
				Ext.apply(this, {
							tpl : '<p>'+EwayLocale.advert.userName+'：    {createdUser}</p>'//制作人
									+ '<p>'+EwayLocale.advert.createdTime+': {createdTime}</p>'//制作时间
									+ '<p>'+EwayLocale.version.View.versionNo+':   {versionNo}</p>'//版本号
									+ "<p>"+EwayLocale.version.View.versionFile+"：{versionFile} <a class='link' href='api/version/version/download?typeName=advert&fileName={versionFile}' target='_blank'>"+EwayLocale.version.downloadVerFile+"</a></p>"
									+ "<p>"+EwayLocale.version.View.versionStatus+"：{versionStatus}</p>",
							html:EwayLocale.advert.toVersionButton,//'还没有生成版本信息，可以单击按钮[生成版本]。',
							loader : {
								url : 'api/advert/version',
								renderer : function(loader, response, active) {
							            var text = Ext.decode(response.responseText);
							            var data = text.data;
							            var exist = data.exist;
							            var panel = loader.getTarget();
							            var fileName = data.versionFile;
							            if(exist){
							            	panel.tpl.overwrite(panel.body, data);
                    						panel.doComponentLayout();
							            }else{
							            	panel.update(EwayLocale.advert.toVersionButton);//'还没有生成版本信息，可以单击按钮[生成版本]。');
							            }
							            return true;
							     }
							}
						});

				this.callParent(arguments);
			}
		});
