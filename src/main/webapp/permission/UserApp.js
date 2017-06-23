Ext.require("Ems.permission.User");
Ext.require("Ems.permission.UserGrid");
Ext.require("Ems.permission.UserForm");
Ext.onReady(function(){
	var grid=Ext.create('Ems.permission.UserGrid',{
		region:'center'
	});
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[grid]
	});



});