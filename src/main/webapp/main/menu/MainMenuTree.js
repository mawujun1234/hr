/**
 * 树状菜单，显示在主界面的左边
 */
Ext.define('Ems.main.menu.MainMenuTree', {
			extend : 'Ext.tree.Panel',
			alias : 'widget.mainmenutree',
			title : '系统菜单',

			rootVisible : false,
			lines : false,
			listeners : {
//				itemclick:function(container){
//					this.up('app-main').getController().onMainMenuTree_itemclick();
//				}
				itemclick:'onMainMenuTree_itemclick'
			},
			displayField:'name',
			//glyph : 0xf0c9,
			iconCls:"icon-list",
			initComponent : function() {
				this.store = Ext.create('Ext.data.TreeStore', {
					root : {
						text : '系统菜单',
						leaf : false,
						expanded : true
					},
					autoLoad:false,
					proxy:{
						type:'ajax',
						actionMethods: { read: 'POST' },
						timeout :600000,
						headers:{ 'Accept':'application/json;'},
						writer:{
							type:'json',
							writeAllFields:true
						},
						url:Ext.ContextPath+'/menu/queryByUser.do'
					}		
				});
				var vm = this.up('app-main').getViewModel()
				//var menus = vm.get('systemMenu');
				var root = this.store.getRootNode();
				//root.appendChild(menus);
				
				Ext.Ajax.request({
					url : Ext.ContextPath+'/user/queryCurrentUser.do',
					
					success : function(response) {
						var text = response.responseText;
						//console.log(text);
								// 将字段串转换成本地变量
						var applicationInfo = Ext.decode(text);
						vm.set("user",applicationInfo);
					}
				});
				

				this.callParent(arguments);
			}
		})