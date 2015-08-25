/**
 * 以后如果，有什么工具类的方法，或者不适合放在其他js文件的方法，可以暂时放在这个文件里
 */

Ext.define('Eway.lib.FunctionUtils',{
	
	name : 'functionUtils',
	
	constructor : function(name){
		Ext.tip.QuickTipManager.init();
		return this;
	},
	
	statics : {
		toolTip : function (element,title,content) {
			var value = "";
			value = content;
			Ext.tip.QuickTipManager.register({
				target: element,
				text: title+":"+value,
				dismissDelay: 10000 // Hide after 10 seconds hover
			});
		}
	}
});