package com.mawujun.permission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mawujun.annotation.FK;
import com.mawujun.annotation.FieldDefine;
import com.mawujun.annotation.ShowType;
import com.mawujun.constant.ConstantItem;

@Entity
@Table(name="t_menu")
public class Menu {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(
	        name = "uuid",
	        strategy = "org.hibernate.id.UUIDGenerator"
	    )
	@FieldDefine(title="id",hidden=true)
	@Column(length=36)
	private String id;
	
	@Column(length=50,nullable=true,unique=true)
	@FieldDefine(title="编码",sort=3)
	private String code;
	
	@Column(length=30,nullable=false)
	@FieldDefine(title="菜单名称",sort=5)
	private String name;
	
	@Column(length=80,nullable=true)
	@FieldDefine(title="地址",sort=4)
	private String url;
	
	@Column(length=80,nullable=true)
	@FieldDefine(title="是否叶子节点",sort=4)
	private Boolean leaf=true; 
	
	@Column(length=15,nullable=false)
	@Enumerated(EnumType.STRING)
	@FieldDefine(title="菜单类型",sort=5,showType=ShowType.combobox,hidden=true)//
	private MenuType menuType=MenuType.menu;
	
	@FieldDefine(title="父id",hidden=true)
	@Column(length=36) 
	@FK(cls=Menu.class,column="id")
	private String parent_id;//上级rolegroup的id
	
	@FieldDefine(title="排序")//
	private Integer sort;
	
	public MenuType getMenuType() {
		return menuType;
	}
	
	
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	@Column(length=150)
	@FieldDefine(title="备注")
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public Boolean getLeaf() {
		return leaf;
	}


	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

}
