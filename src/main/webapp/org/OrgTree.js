/**
 * 包含组织和职位
 */
Ext.define('Ems.org.OrgTree', {
    extend: 'Ext.tree.Panel',
    requires:['Ems.org.Org'],
    displayField:'name',
    readOnly:false,//true表示就不能在上面进行增，删，改
    initComponent: function () {
		var me = this;

        me.store = Ext.create('Ext.data.TreeStore', {
	       	autoLoad:true,
	       	nodeParam :'parent_id',//传递到后台的数据，默认是node
	       	//model:'Ems.org.Org',
	       	fields:['id','name','leaf','parent_id','orgtype_id'],
			root: {
			    expanded: true,
			    name:"根节点" 
			},
			proxy:{
				type:'ajax',
				url:Ext.ContextPath+'/org/query.do',
				actionMethods: { read: 'POST' },
				timeout :600000,
				headers:{ 'Accept':'application/json;'}
			
			}
//			listeners:{
//				beforeload:function(store,operation){
//					//var record=me.getSelectionModel( ).getSelection( );
//					//console.log(record);
//					//store.getProxy().extraParams=Ext.apply(store.getProxy().extraParams,{
//					//	parent_no:"root"//record.get("orgno")
//					//});
//				}
//			}
		});
		if(!me.readOnly){
			me.initAction();
		}
		//me.initTbar();
       
		me.callParent(arguments);
    },
//
//    initTbar:function(){
//    	var me=this;
//
//		me.tbar={
//			itemId:'action_toolbar',
//			layout: {
//	               overflowHandler: 'Menu'
//	        },
//			items:[dim_combobox]
//			//,autoScroll:true		
//		};
//		me.dim_combobox=dim_combobox;
//		
//    },
    initAction:function(){
     	var me = this;
     	var actions=[];
     	
     	var reload = new Ext.Action({
		    text: '刷新',
		    itemId:'reload',
		    handler: function(){
		    	me.onReload();
		    },
		    iconCls: 'icon-refresh'
		});
		//me.addAction(reload);
		actions.push(reload);
		
		var create = new Ext.Action({
		    text: '新建组织',
		    itemId:'create',
		    handler: function(b){
		    	me.onCreate(null,b);
		    },
		    iconCls: 'icon-plus'
		});
		//me.addAction(create);
		actions.push(create);
		
		var update = new Ext.Action({
		    text: '更新组织',
		    itemId:'update',
		    handler: function(){
		    	me.onUpdate();
				
		    },
		    iconCls: 'icon-edit'
		});
		actions.push(update);
		
		var destroy = new Ext.Action({
		    text: '删除组织',
		    itemId:'destroy',
		    handler: function(){
		    	me.onDelete();    
		    },
		    iconCls: 'icon-trash'
		});
		//me.addAction(destroy);
		actions.push(destroy);
		
		var updateParentOrg = new Ext.Action({
		    text: '组织隶属关系调整',
		    itemId:'updateParentOrg',
		    handler: function(){
		    	me.updateParentOrg();    
		    },
		    iconCls: 'icon-random'
		});
		actions.push(updateParentOrg);
		
     	
//       var createPosition = new Ext.Action({
//		    text: '新建职位',
//		    itemId:'createPosition',
//		    handler: function(b){
//		    	me.onCreatePosition();
//		    },
//		    iconCls: 'icon-plus'
//		});
//		//me.addAction(create);
//		actions.push(createPosition);
//		
//		var updatePosition = new Ext.Action({
//		    text: '更新职位',
//		    itemId:'updatePosition',
//		    handler: function(){
//		    	me.onUpdatePosition();
//				
//		    },
//		    iconCls: 'icon-edit'
//		});
//		actions.push(updatePosition);
//		
//		var destroyPosition = new Ext.Action({
//		    text: '删除职位',
//		    itemId:'destroyPosition',
//		    handler: function(){
//		    	me.onDeletePosition();    
//		    },
//		    iconCls: 'icon-trash'
//		});
//		//me.addAction(destroy);
//		actions.push(destroyPosition)
		
		
		

		var menu=Ext.create('Ext.menu.Menu', {
			items: actions
		});	
		me.on('itemcontextmenu',function(tree,record,item,index,e){
			menu.showAt(e.getXY());
			e.stopEvent();
		});
		me.contextMenu=menu;
		
		
    },
     onCreatePosition:function(){
    	var me=this;
		
    	var parent=me.getSelectionModel( ).getLastSelected( )||me.getRootNode( );   
    	if(parent.get("type")=='position'){
    		Ext.Msg.alert("消息","职位下不能新建职位和组织单元");
    		return;
    	}
		
		var child=Ext.create('Ems.org.Position',{
		    'org_id':parent.get("id"),
		    name:''
		});
		child.set("id",null);
		
		var formpanel=Ext.create('Ems.org.PositionForm',{});
		formpanel.loadRecord(child);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新增',
    		modal:true,
    		width:400,
    		height:400,
    		closeAction:'hide',
    		items:[formpanel],
    		listeners:{
    			close:function(){
    				me.onReload(parent);
    			}
    		}
    	});
    	win.show();
    },
    
     onUpdatePosition:function(){
    	var me=this;

    	var node=me.getSelectionModel( ).getLastSelected();
    	if(node==null || node.isRoot()){
    		Ext.Msg.alert("提醒","请选择一个不是根节点的节点!");
    		return;
    	}
    	if(node.get("type")!='position'){
    		return;
    	}
//从后台获取这个职位的数据，然后在load到form中
		var formpanel=Ext.create('Ems.org.PositionForm',{});
		
		Ems.org.Position.load(node.get("id"),{
			success:function(record){
				formpanel.loadRecord(record);
				var win=Ext.create('Ext.window.Window',{
		    		layout:'fit',
		    		title:'更新',
		    		modal:true,
		    		width:400,
		    		height:300,
		    		closeAction:'hide',
		    		items:[formpanel]
		    	});
		    	win.show();
			}
		});
		
		
    	
    },
    
    onDeletePosition:function(){
    	var me=this;
    	var node=me.getSelectionModel( ).getLastSelected( );

		if(!node){
		    Ext.Msg.alert("消息","请先选择节点");	
			return;
		}
		if(node.get("type")!='position'){
			Ext.Msg.alert("消息","职位不能通过这个功能删除!");
    		return;
    	}
		if(node.isRoot()){
			Ext.Msg.alert("消息","根节点不能删除!");	
			return;
		}
		var parent=node.parentNode;
		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
				if (btn == 'yes'){
				Ext.Ajax.request({
					url:Ext.ContextPath+'/position/destroy.do',
					jsonData:node.getData(),
					headers:{ 'Accept':'application/json;'},
					success:function(){
						//button.up('window').close();
						me.onReload(parent);
					}
				});
			}
		});
    },
    onCreate:function(){
    	var me=this;

    	var parent=me.getSelectionModel( ).getLastSelected( )||me.getRootNode( );  
    	if(parent.get("type")=='position'){
    		Ext.Msg.alert("消息","职位下不能新建职位和组织单元");
    		return;
    	}
		
		var child=Ext.create('Ems.org.Org',{
		    'parent_id':parent.get("id"),
		    status:1
		});
		child.set("id",null);
		
		var formpanel=Ext.create('Ems.org.OrgForm',{});
		formpanel.loadRecord(child);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新增',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[formpanel],
    		listeners:{
    			close:function(){
    				me.onReload(parent);
    			}
    		}
    	});
    	win.show();
    },
    
     onUpdate:function(){
    	var me=this;

    	var node=me.getSelectionModel( ).getLastSelected();
    	if(node==null || node.isRoot()){
    		Ext.Msg.alert("提醒","请选择一个不是根节点的节点!");
    		return;
    	}
    	if(node.get("type")=='position'){
    		return;
    	}
    	Ems.org.Org.load(node.get("id"), {
		    success: function(org) {
		        var formpanel=Ext.create('Ems.org.OrgForm',{});
				formpanel.loadRecord(org);
				
		    	var win=Ext.create('Ext.window.Window',{
		    		layout:'fit',
		    		title:'更新',
		    		modal:true,
		    		width:400,
		    		height:300,
		    		closeAction:'hide',
		    		items:[formpanel],
		    		listeners:{
		    			close:function(){
		    				me.onReload(node.parentNode);
		    			}
		    		}
		    	});
		    	win.show();
		    }
		});

		
    },
    
    onDelete:function(){
    	var me=this;
    	var node=me.getSelectionModel( ).getLastSelected( );

		if(!node){
		    Ext.Msg.alert("消息","请先选择节点");	
			return;
		}
		if(node.isRoot()){
			Ext.Msg.alert("消息","根节点不能删除!");	
			return;
		}
		if(node.get("type")=='position'){
			Ext.Msg.alert("消息","组织单元不能通过这个功能删除!");
    		return;
    	}
		var parent=node.parentNode;
		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:Ext.ContextPath+'/org/destroy.do',
					jsonData:node.getData(),
					params:{
						
					},
					headers:{ 'Accept':'application/json;'},
					success:function(){
						//button.up('window').close();
						me.onReload(parent);
					}
				});
			}
		});
    },
    onReload:function(node){
    	var me=this;
    	var parent=node||me.getSelectionModel( ).getLastSelected( );
		if(parent){
		    me.getStore().reload({node:parent});
		} else {
		    me.getStore().reload();	
		}      
    },
	updateParentOrg:function(){
		var me=this;
   		var node=node||me.getSelectionModel( ).getLastSelected( );
   		if(!node){
		    Ext.Msg.alert("消息","请先选择节点");	
			return;
		}
		if(node.isRoot()){
			Ext.Msg.alert("消息","根节点不能更改!");	
			return;
		}
		if(node.get("type")=='position'){
			Ext.Msg.alert("消息","职位不能更改隶属关系！");
    		return;
    	}
    	var orgTree=Ext.create('Ems.org.OrgTreeQuery',{
    		exclude_id:node.get("id"),//这个节点，及子节点不显示，防止循环嵌套
    		listeners:{
    			itemdblclick:function( view , newparent , item , index , e , eOpts ) {
    				Ext.Ajax.request({
						url:Ext.ContextPath+'/org/updateParentOrg.do',
						params:{
							org_id:node.get("id"),
							new_parent_id:newparent.get("id"),
							old_parent_id:node.get("parent_id")
						},
						method:'POST',
						headers:{ 'Accept':'application/json;'},
						success:function(){
							//button.up('window').close();
							//me.onReload(parent);
							win.close();
							me.onReload(node.parentNode);
						}
					});
    			}
    		}
    	});
    	var win=Ext.create('Ext.window.Window',{
    		title:'双击选择新的上级组织',
    		layout:'fit',
    		modal:true,
    		height:500,
    		width:300,
    		items:[orgTree]
    	});
    	win.show();
   	
    },
    
    getContextMenu:function(){
    	return this.contextMenu;
    },
    getLastSelected:function(){
    	return this.getSelectionModel( ).getLastSelected( );
    }
    
});
