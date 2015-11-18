
Ext.define('Eway.controller.base.Controller', {
	extend: 'Ext.app.Controller',

	init: function() {
		this.children = new Ext.util.MixedCollection();
		this.actived = undefined;
	},

	active : function() {
		if(this.parent) {
			this.parent.activeController(this);
			this.parent.changeActive();
		}
	},

	changeActive : function() {
		if(this.parent) {
			this.parent.actived = this;
			this.parent.changeActive();
		}
	},

	changeDeactive : function() {
		if(this.parent) {
			this.parent.actived = null;
			this.parent.changeDeactive();
		}
	},

	activeTabController : function(controller){

	},

	activeController: function(controller,title) {
		this.active();
		var childView = undefined;
		var child = this.getController(controller);
		var childView = child.getEwayView();
		if(title){
			childView.setTitle(title);
		}
		childView.controllers=controller;
		this.activeControllerView(childView);
		this.actived = child;
		child.parent = this;
	},

	activeControllerView : function(childView) {
		if(this.getEwayView() && childView) {
			this.getEwayView().activeEwayView(childView);
		}
		else if(this.parent) {
			this.parent.activeControllerView(childView);
		}
	}

});