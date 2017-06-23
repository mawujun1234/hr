Ext.require("Ems.permission.Role");
Ext.require("Ems.permission.RoleTree");
Ext.require("Ems.permission.RoleForm");
Ext.require('Ems.permission.RoleUserGrid');
Ext.require('Ems.permission.MenuTreeCheckbox');
Ext.require('Ems.permission.UserGridQuery');
Ext.onReady(function(){
	var tree=Ext.create('Ems.permission.RoleTree',{
		title:'角色管理',
		width:400,
		split:true,
		collapsible : true,
		region:'west'
	});
	
	var userGrid=Ext.create('Ems.permission.RoleUserGrid',{
		
		title:'用户选择'
	});
	
	
	var menuTreeCheckbox=Ext.create('Ems.permission.MenuTreeCheckbox',{
		title:'菜单选择'
	});

	
	var tabpanel=Ext.create('Ext.tab.Panel', {
	    region:'center',
	    items: [userGrid,menuTreeCheckbox],
	    listeners:{
	    	render:function(){
	    		tabpanel.mask();
	    	}
	    }
	});
	
	
	tree.on("itemclick",function( view, record, item, index, e, eOpts ){
		if(record.get("roleType")=="rolegroup" || !record.get("roleType")){
			tabpanel.mask();
			return;
		} else {
			tabpanel.unmask();
		}

		window.selected_role=record;
		userGrid.getStore().getProxy().extraParams=Ext.apply(userGrid.getStore().getProxy().extraParams,{
			"params['role_id']":record.get("id")
		});
		userGrid.getStore().reload();
		
		menuTreeCheckbox.getStore().getProxy().extraParams=Ext.apply(menuTreeCheckbox.getStore().getProxy().extraParams,{
			role_id:record.get("id")
		});
		menuTreeCheckbox.query_checked_node();
		

		
	});
	
	

	
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[tree,tabpanel]
	});



});