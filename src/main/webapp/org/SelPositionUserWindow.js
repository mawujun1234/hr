/**
 *  co从职位上选择用户
 */
Ext.define('Ems.org.SelPositionUserWindow',{
	extend:'Ext.window.Window',
	requires: [
	     'Ems.org.OrgTree',
	     'Ems.org.PositionUserGridQuery'
	],
	layout:'border',
    title:'双击选择用户',
    modal:true,
    width:700,
    height:500,
    closeAction:'hide',
    initComponent: function () {
        var me = this;
        var tree=Ext.create('Ems.org.OrgTree',{
			//title:'选择组织',
			width:250,
			split:true,
			collapsible : true,
			region:'west'
		});
		var usergrid=Ext.create('Ems.org.PositionUserGridQuery',{
			//title:'双击选择用户',
			region:'center',
			listeners:{
		    	render:function(){
		    		usergrid.mask();
		    	},
		    	itemdblclick:function(view, record, item, index, e, eOpts){
		    		//usergrid.getStore().remove(record);
		    		usergrid.getStore().reload();
		    	    me.fireEvent("userdbclick",record);
		    	}
		    }
		
		});
		
		tree.on("itemclick",function( view, record, item, index, e, eOpts ){
			if(record.get("type")!="position" ){
				usergrid.mask();
				return;
			} else {
				usergrid.unmask();
			}
	
			window.selected_position=record;
			usergrid.getStore().getProxy().extraParams=Ext.apply(usergrid.getStore().getProxy().extraParams,{
				"params['position_id']":record.get("id"),
				"params['org_id']":record.get("org_id")
			});
			usergrid.getStore().reload();
	
			
		});
		me.items=[tree,usergrid];
		
		//me.addEvents("userdbclick");
        me.callParent();
    }
	
	
});