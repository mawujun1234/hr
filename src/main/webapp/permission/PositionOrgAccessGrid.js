Ext.define('Ems.permission.PositionOrgAccessGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.org.Org'
	],
	columnLines :true,
	stripeRows:true,
	viewConfig:{
		enableTextSelection:true
	},
//	selModel: {
//    	selType: 'checkboxmodel',
//    	checkOnly:true
//    },
	initComponent: function () {
      var me = this;
      me.columns=[
      	{xtype: 'rownumberer'},
		{dataIndex:'org_code',header:'编码'
        },
		{dataIndex:'org_name',header:'名称'
        },
        { xtype: 'checkcolumn', text: '查看', dataIndex: 'look' ,
        	listeners:{
			checkchange:function(checkcolumn, rowIndex, checked){
				var record=me.getStore().getAt(rowIndex);
				if(checked){
					me.fireEvent('orgSelect',record,'look');
				} else {
					if(record.get("edit")){
						Ext.Msg.alert("消息","不可取消,可编辑就肯定可以看!");
						record.set("look",true);
						return;
					}
					me.fireEvent('orgDeselect',record,'look');
				}
				
			}
        	}
        },
        { xtype: 'checkcolumn', text: '编辑/操作', dataIndex: 'edit',
        	listeners:{
				checkchange:function(checkcolumn, rowIndex, checked){
					var record=me.getStore().getAt(rowIndex);
					if(checked){
						record.set("look",true);
						me.fireEvent('orgSelect',record,'edit');	
					} else {
						me.fireEvent('orgDeselect',record,'edit');
					}
				}
        	}
        }
      ];
      

	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:false,
			model: 'Ems.org.Org',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/position/querySelectOrgs.do',
			    headers:{ 'Accept':'application/json;'},
			    actionMethods: { read: 'POST' },
			    extraParams:{
			    	limit:50
			    },
			    reader:{
					type:'json',//如果没有分页，那么可以把后面三行去掉，而且后台只需要返回一个数组就行了
					rootProperty:'root',
					successProperty:'success',
					totalProperty:'total'		
				}
			}
	  });

	  me.dockedItems=[];
	  me.dockedItems.push({
	  		xtype: 'toolbar',
	  		dock:'top',
		  	items:[{
				text: '刷新',
				itemId:'reload',
				disabled:me.disabledAction,
				handler: function(btn){
					var grid=btn.up("grid");
					grid.getStore().reload();
				},
				iconCls: 'icon-refresh'
			}]
		});

       
      me.callParent();
	}
//	onSave : function() {
//				var grid = this;
//				var records = grid.getSelectionModel().getSelection();
//				if (records == null || records.length == 0) {
//					Ext.Msg.alert("消息", "请先选择行!");
//					return;
//				}
//				var mlornoes = [];
//				for (var i = 0; i < records.length; i++) {
//					mlornoes.push({
//						position_id:window.selected_position.get("id"),
//						org_id:records[i].get("id")
//					
//					});
//				}
//				Ext.Ajax.request({
//							url : Ext.ContextPath + '/ord/ordMgr/isfect_no.do',
//							params : {
//								mlornoes : mlornoes
//							},
//							method : 'POST',
//							success : function(response) {
//								var obj = Ext.decode(response.responseText);
//								if (obj.success == false) {
//									Ext.Msg.alert("消息", obj.msg);
//									return;
//								}
//								grid.getStore().reload();
//							}
//						});
//	}
//	
});
