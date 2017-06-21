package com.mawujun.permission;

import java.util.ArrayList;
import java.util.List;

public class RoleVO extends Role{
	private Boolean expanded=false;
	
	private List<RoleVO> children;
	
	public void addChild(RoleVO child) {
		if(this.children==null){
			this.children=new ArrayList<RoleVO>();
		}
		this.children.add(child);
	}

	public List<RoleVO> getChildren() {
		return children;
	}

	public void setChildren(List<RoleVO> children) {
		this.children = children;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

}
