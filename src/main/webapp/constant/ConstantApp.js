Ext.require("Ems.constant.Constant");
Ext.require("Ems.constant.ConstantGrid");
Ext.require("Ems.constant.ConstantForm");
Ext.require("Ems.constant.ConstantItemGrid");
Ext.onReady(function(){
	var grid=Ext.create('Ems.constant.ConstantGrid',{
		region:'west',
		width:410
		,split:true
		//title:'XXX表格'
	});
	
	var constantItemGrid=Ext.create('Ems.constant.ConstantItemGrid',{
		region:'center',
		split:true
	});
	
	grid.on("itemclick",function(view, record, item, index, e){
		constantItemGrid.getStore().getProxy().extraParams=Ext.apply(constantItemGrid.getStore().getProxy().extraParams,{
			"params['constant_id']":record.get("id")
		});
		constantItemGrid.getStore().reload();
	});
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[grid,constantItemGrid]
	});



});