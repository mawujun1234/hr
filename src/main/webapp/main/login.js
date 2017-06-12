Ext.require("Ems.main.LoginWindow");
Ext.onReady(function(){
	var win=Ext.create('Ems.main.LoginWindow',{
		frame:true
	});
	win.show();
});