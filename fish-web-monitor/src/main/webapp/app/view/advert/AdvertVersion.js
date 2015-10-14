function advertVesionDown(fileName){

}
Ext.define('Eway.view.advert.AdvertVersion', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.advert_advertVersion',
			bodyStyle : 'padding: 5px 0px 0px 11px',
			initComponent : function() {
				Ext.apply(this, {
							tpl : '<p>'+Eway.locale.advert.userName+'：    {createdUser}</p>'//制作人
									+ '<p>'+Eway.locale.advert.createdTime+': {createdTime}</p>'//制作时间
									+ '<p>'+Eway.locale.version.View.versionNo+':   {versionNo}</p>'//版本号
									+ "<p>"+Eway.locale.version.View.versionFile+"：{versionFile} <a class='link' href='api/version/version/download?typeName=advert&fileName={versionFile}' target='_blank'>"+Eway.locale.version.downloadVerFile+"</a></p>"
									+ "<p>"+Eway.locale.version.View.versionStatus+"：{versionStatus}</p>",
							html:Eway.locale.advert.toVersionButton,//'还没有生成版本信息，可以单击按钮[生成版本]。',
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
							            	panel.update(Eway.locale.advert.toVersionButton);//'还没有生成版本信息，可以单击按钮[生成版本]。');
							            }
							            return true;
							     }
							}
						});

				this.callParent(arguments);
			}
		});