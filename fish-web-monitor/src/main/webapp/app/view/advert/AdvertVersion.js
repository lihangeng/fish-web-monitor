function advertVesionDown(fileName){

}
Ext.define('Eway.view.advert.AdvertVersion', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.advert_advertVersion',
			bodyStyle : 'padding: 5px 0px 0px 11px',
			initComponent : function() {
				Ext.apply(this, {
							tpl : '<p>制作人：    {createdUser}</p>'
									+ '<p>制作时间: {createdTime}</p>'
									+ '<p>版本号:   {versionNo}</p>'
									+ "<p>版本文件：{versionFile} <a class='link' href='api/version/version/download?typeName=advert&fileName={versionFile}' target='_blank'>下载版本文件</a></p>"
									+ "<p>版本状态：{versionStatus}</p>",
							html:'还没有生成版本信息，可以单击按钮[生成版本]。',
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
							            	panel.update('还没有生成版本信息，可以单击按钮[生成版本]。');
							            }
							            return true;
							     }
							}
						});

				this.callParent(arguments);
			}
		});