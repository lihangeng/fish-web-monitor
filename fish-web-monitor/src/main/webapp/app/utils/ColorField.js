Ext.define('Eway.utils.ColorField', {
	extend : 'Ext.form.field.Picker',
	alias : 'widget.colorfield',
	requires : [ 'Ext.picker.Color' ],
	triggerCls : 'x-form-color-trigger',
	createPicker : function() {
		var me = this;
		return Ext.create('Ext.picker.Color', {
			pickerField : me,
			renderTo : document.body,
			floating : true,
			hidden : true,
			focusOnShow : true,
			listeners : {
				select : function(picker, selColor) {
					me.setValue('#' + selColor);
					// 实现根据选择的颜色来改变背景颜色,根据背景颜色改变字体颜色,防止看不到值

					var r = parseInt(selColor.substring(0, 2), 16);
					var g = parseInt(selColor.substring(2, 4), 16);
					var b = parseInt(selColor.substring(4, 6), 16);
					var a = new Ext.draw.Color(r, g, b);
					var l = a.getHSL()[2];
					if (l > 0.5) {
						me.setFieldStyle('background:#' + selColor
								+ ';color:#000000');
					} else {
						me.setFieldStyle('background:#' + selColor
								+ ';color:#FFFFFF');
					}
					this.hide();
				}
			},
			height : 182,
			colors : ['000000','000000','000000','002B00','005600','008100','00AC00','00FF00','330000','332B00','335600','338100','33AC00','33FF00','660000','662B00','665600','668100','66AC00','66FF00',
			          '2B2B2B','000000','00002B','002B2B','00562B','00812B','00AC2B','00FF2B','33002B','332B2B','33562B','33812B','33AC2B','33FF2B','66002B','662B2B','66562B','66812B','66AC2B','66FF2B',
			          '565656','000000','000056','002B56','005656','008156','00AC56','00FF56','330056','332B56','335656','338156','33AC56','33FF56','660056','662B56','665656','668156','66AC56','66FF56',
			          '818181','000000','000081','002B81','005681','008181','00AC81','00FF81','330081','332B81','335681','338181','33AC81','33FF81','660081','662B81','665681','668181','66AC81','66FF81',
			          'ACACAC','000000','0000AC','002BAC','0056AC','0081AC','00ACAC','00FFAC','3300AC','332BAC','3356AC','3381AC','33ACAC','33FFAC','6600AC','662BAC','6656AC','6681AC','66ACAC','66FFAC',
			          'FFFFFF','000000','0000FF','002BFF','0056FF','0081FF','00ACFF','00FFFF','3300FF','332BFF','3356FF','3381FF','33ACFF','33FFFF','6600FF','662BFF','6656FF','6681FF','66ACFF','66FFFF',
			          'FF0000','000000','990000','992B00','995600','998100','99AC00','99FF00','CC0000','CC2B00','CC5600','CC8100','CCAC00','CCFF00','FF0000','FF2B00','FF5600','FF8100','FFAC00','FFFF00',
			          '00FF00','000000','99002B','992B2B','99562B','99812B','99AC2B','99FF2B','CC002B','CC2B2B','CC562B','CC812B','CCAC2B','CCFF2B','FF002B','FF2B2B','FF562B','FF812B','FFAC2B','FFFF2B',
			          '0000FF','000000','990056','992B56','995656','998156','99AC56','99FF56','CC0056','CC2B56','CC5656','CC8156','CCAC56','CCFF56','FF0056','FF2B56','FF5656','FF8156','FFAC56','FFFF56',
			          'FFFF00','000000','990081','992B81','995681','998181','99AC81','99FF81','CC0081','CC2B81','CC5681','CC8181','CCAC81','CCFF81','FF0081','FF2B81','FF5681','FF8181','FFAC81','FFFF81',
			          '00FFFF','000000','9900AC','992BAC','9956AC','9981AC','99ACAC','99FFAC','CC00AC','CC2BAC','CC56AC','CC81AC','CCACAC','CCFFAC','FF00AC','FF2BAC','FF56AC','FF81AC','FFACAC','FFFFAC',
			          'FF00FF','000000','9900FF','992BFF','9956FF','9981FF','99ACFF','99FFFF','CC00FF','CC2BFF','CC56FF','CC81FF','CCACFF','CCFFFF','FF00FF','FF2BFF','FF56FF','FF81FF','FFACFF','FFFFFF']
		});
	},
	trigger1Cls : Ext.baseCSSPrefix + "form-clear-trigger",
	trigger2Cls : Ext.baseCSSPrefix + "form-arrow-trigger",
	onTrigger1Click : function() {
		var me = this;
		me.setValue('');
		me.setFieldStyle('background: none; color: none;');
	}
});