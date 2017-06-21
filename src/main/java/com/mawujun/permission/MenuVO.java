package com.mawujun.permission;

import java.util.List;

public class MenuVO extends Menu{
	private Boolean checked=null;
	private Boolean expanded=false;
	
	private List<MenuVO> children;
	
	
	public String getIconCls(){
		if(super.getMenuType()==MenuType.element){
			return "icon-cog";
			//return "fa fa-cog";
		}
		return "icon-cogs";
		//return "fa fa-cogs";
	}




	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<MenuVO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuVO> children) {
		this.children = children;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
}
