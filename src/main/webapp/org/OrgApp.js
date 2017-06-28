Ext.require("Ems.org.Org");
Ext.require("Ems.org.OrgTree");
Ext.require("Ems.org.OrgForm");
Ext.require('Ems.org.OrgFormRead');
Ext.require('Ems.org.PositionGrid');
Ext.onReady(function(){
	var tree=Ext.create('Ems.org.OrgTree',{
		title:'组织维护(右键)',
		width:400,
		split:true,
		collapsible : true,
		region:'west'
	});
	var orgFormRead=Ext.create('Ems.org.OrgFormRead',{
		title:'基本信息',
		region:'center'
	});
	
	var positionGrid=Ext.create('Ems.org.PositionGrid',{
		title:'拥有的岗位'
	})

	var tabpanel=Ext.create('Ext.tab.Panel',{
		region:'center',
		items:[orgFormRead,positionGrid],
		listeners:{
	    	render:function(tabpanel){
	    		tabpanel.mask();
	    	}
	    }
	});
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[tree,tabpanel]
	});
	
	tree.on("itemclick",function( view, record, item, index, e, eOpts ){
	
		if(record.get("id")!="root"){		
			tabpanel.unmask();
		} else {
			tabpanel.mask();
			return;
		}
		
		orgFormRead.load(record.get("id"));

//		window.selected_position=record;
		positionGrid.getStore().getProxy().extraParams=Ext.apply(positionGrid.getStore().getProxy().extraParams,{
			"org_id":record.get("id")
		});
		positionGrid.getStore().reload();
//
//		//刷新整颗权限树
//		positionStoreGrid.getStore().getProxy().extraParams=Ext.apply(positionStoreGrid.getStore().getProxy().extraParams,{
//			position_id:record.get("id")
//		});
//		positionStoreGrid.getStore().reload();
	});



});