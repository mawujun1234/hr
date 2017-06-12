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
							}
						});
				var vm = this.up('app-main').getViewModel()
				//var menus = vm.get('systemMenu');
				var root = this.store.getRootNode();
				//root.appendChild(menus);
				
				Ext.Ajax.request({
					url : Ext.ContextPath+'/menu/queryByUser.do',
					
					success : function(response) {
						var text = response.responseText;
								// 将字段串转换成本地变量
						var applicationInfo = Ext.decode(text);
								// 把从后台传过来的参数加入到data中去
						//Ext.apply(me.data.systemMenu, applicationInfo.systemMenu);
						//Ext.apply(me.data.user, applicationInfo.user);
						root.appendChild(applicationInfo.systemMenu);
						//vm.data.user.loginName='aaa';
						vm.set("user",applicationInfo.user);
					}
				});
				
//				for (var i in menus) {
//					var menugroup = menus[i];
//					var menuitem = root.appendChild({
//								text : menugroup.text,
//								// 节点默认是否展开
//								expanded : menugroup.expanded,
//								icon : menugroup.icon,
//								glyph : menugroup.glyph
//							});
//					for (var j in menugroup.items) {
//						var menumodule = menugroup.items[j];
//
//						//var module = vm.getModuleDefine(menumodule.module);
//						//if (module) {
//							var childnode = {
//								moduleId : menumodule.module,
//								moduleName : menumodule.text,
//								text : menumodule.text,
//								leaf : true
//							};
//							menuitem.appendChild(childnode);
//						//}
//					}
//				}
				this.callParent(arguments);
			}
		})