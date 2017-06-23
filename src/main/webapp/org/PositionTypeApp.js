Ext.require("Ems.org.PositionType");
Ext.require("Ems.org.PositionTypeGrid");
Ext.require("Ems.org.PositionTypeForm");
Ext.onReady(function(){
	var grid=Ext.create('Ems.org.PositionTypeGrid',{
		region:'center'
	});
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[grid]
	});



});