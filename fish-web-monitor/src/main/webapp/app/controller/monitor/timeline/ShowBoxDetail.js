Ext.define('Eway.controller.monitor.timeline.ShowBoxDetail',{
	
	name : 'BoxDetail',
	
	statics : {
		expandCashDetails:function(_this){
			if(_this.parentElement.nextElementSibling.className == "detailsOneShow"){
				_this.innerText = 'EXPAND';
				_this.className = 'news-toggle-cash';
				_this.style.cursor='pointer';
				_this.parentElement.nextElementSibling.className="detailsOneHide";
				_this.parentElement.nextElementSibling.nextElementSibling.className="detailsTwoHide";
				_this.parentElement.nextElementSibling.lastChild.firstChild.nodeValue = "EXPAND";
				_this.parentElement.nextElementSibling.lastElementChild.style.width = "80px";
				_this.parentElement.previousElementSibling.style.height = '105px';
			}else{
				_this.innerText = 'COLLAPSE';
				_this.style.cursor='pointer';
				_this.className = 'news-toggle-cash-coll';
				_this.parentElement.nextElementSibling.className = "detailsOneShow";
				_this.parentElement.previousElementSibling.style.height = '295px';
			}
		},
		expandBoxDetails:function(_this){
			if(_this.parentElement.nextElementSibling.className == "detailsTwoShow"){
				_this.innerText = EwayLocale.monitor.devMonitor.cash.boxDetail;
				_this.style.width = "80px";
				_this.className = 'news-toggle';
				_this.style.cursor='pointer';
				_this.parentElement.nextElementSibling.className="detailsTwoHide";
				_this.parentElement.previousElementSibling.previousElementSibling.style.height = '295px';
			}else{
				_this.innerText = 'COLLAPSE';
				_this.style.width = "92px";
				_this.style.cursor='pointer';
				_this.className = 'news-toggle-coll';
				_this.parentElement.nextElementSibling.className="detailsTwoShow";
				_this.parentElement.previousElementSibling.previousElementSibling.style.height = '560px';
			}
		}
	}
});