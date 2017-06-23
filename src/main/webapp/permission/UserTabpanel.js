Ext.define('Ems.permission.UserTabpanel',{
	extend:'Ext.tab.Panel',
	initComponent: function () {
      var me = this;

      me.items=[];
      
      me.createRoleTab();
      me.createMenuTab();
//      me.createOrgPositionTab();
//      me.createStoreTab("/user/queryStoreByUser.do","可访问仓库");
//      me.createStoreTab("/user/queryWorkunitByUser.do","可访问作业单位");
//      me.createStoreTab("/user/queryRepair_centreByUser.do","可访问维修中心");
      
      me.callParent();
     },
     createRoleTab:function(){
     	var me=this;
     	var store = Ext.create('Ext.data.TreeStore', {
     		//autoLoad:true,
		    root: {
		    	id:'root',
		        expanded: true,
		        name:"角色" 
		    },
		    nodeParam :'parent_id',
		    //model:'Ems.permission.Role',
		    fields:['id','name']
		    
		});
		
		
		var tree=Ext.create('Ext.tree.Panel', {
		    title: '所属角色',
		    displayField:'name',
		    store: store,
		    rootVisible: false
		});
		
		Ext.Ajax.request({
			url : Ext.ContextPath+'/user/queryRoleByUser.do',
			headers:{ 'Accept':'application/json;'},
			params:{
				user_id:me.user_id
			},
			success:function(response){
				var array=Ext.decode(response.responseText);
//				for(var i=0;i<array.length;i++){
//					store.getRootNode().appendChild(array[i]);
//				}
				store.getRootNode().appendChild(array);
				tree.expandAll();
			}
		
		});
		
		me.items.push(tree);
     
     },
     createMenuTab:function(){
     	var me=this;
     	var store = Ext.create('Ext.data.TreeStore', {
     		autoLoad:true,
	       	nodeParam :'parent_id',//传递到后台的数据，默认是node
	       	//model:'Ems.permission.Menu',
	       	fields:['id','name'],
			root: {
			    expanded: true,
			    //checked:false,
			    name:"根菜单"
			},
			proxy:{
				type: 'ajax',
				extraParams:{expanded:false,user_id:me.user_id},
				url:Ext.ContextPath+"/user/queryMenuByUser.do"
			}
		});
		
		
		var tree=Ext.create('Ext.tree.Panel', {
		    title: '可访问的菜单',
		    displayField:'name',
		    store: store,
		    rootVisible: true
		});

		
		me.items.push(tree);
     
     },
     createOrgPositionTab:function(){
     	var me=this;
     	var store = Ext.create('Ext.data.Store', {
     		autoLoad:true,
	       	nodeParam :'parent_id',//传递到后台的数据，默认是node
	       	fields:['name','org_all_name'],
			proxy:{
				type: 'ajax',
				extraParams:{user_id:me.user_id},
				url:Ext.ContextPath+"/user/queryOrgPositionByUser.do",
				reader:{
					type:'json'
					///rootProperty:'root',
					//successProperty:'success',
					//totalProperty:'total'		
				}
			}
		});
		
		
		var grid=Ext.create('Ext.grid.Panel', {
		    title: '所属职位',
		    store: store,
		    columnLines :true,
			stripeRows:true,
			columns:[
		      	{xtype: 'rownumberer'},
				{dataIndex:'name',header:'职位'
		        },
				{dataIndex:'org_all_name',header:'所属组织单元',flex:1
		        }
		      ]
		});

		
		me.items.push(grid);
     
     },
     createStoreTab:function(url,title){
     	var me=this;
     	var store = Ext.create('Ext.data.Store', {
     		autoLoad:true,
	       	nodeParam :'parent_id',//传递到后台的数据，默认是node
	       	fields:['org_code','org_name','look','edit'],
			proxy:{
				type: 'ajax',
				extraParams:{user_id:me.user_id},
				url:Ext.ContextPath+url,
				reader:{
					type:'json'
					///rootProperty:'root',
					//successProperty:'success',
					//totalProperty:'total'		
				}
			}
		});
		
		
		var grid=Ext.create('Ext.grid.Panel', {
		    title: title,
		    store: store,
		    columnLines :true,
			stripeRows:true,
			columns:[
		      	{xtype: 'rownumberer'},
				{dataIndex:'org_code',header:'编码'
		        },
				{dataIndex:'org_name',header:'名称',width:200
		        },
		        { xtype: 'checkcolumn', text: '查看', dataIndex: 'look' ,editable:false
		        	,listeners:{
						checkchange:function(checkcolumn, rowIndex, checked){
							alert("只读，修改无效!");
							return false;
						}
		        	}
		        },
		        { xtype: 'checkcolumn', text: '编辑/操作', dataIndex: 'edit',
		        	listeners:{
						checkchange:function(checkcolumn, rowIndex, checked){
							alert("只读，修改无效!");
							return false;
						}
		        	}
		        }
		      ]
		});

		
		me.items.push(grid);
     
     }
})