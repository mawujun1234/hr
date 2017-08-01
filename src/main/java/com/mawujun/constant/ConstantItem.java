package com.mawujun.constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mawujun.generator.model.FK;
import com.mawujun.generator.model.FieldDefine;
import com.mawujun.generator.model.ShowType;

@Entity
@Table(name="t_constantitem")
public class ConstantItem {

	@Id
	@FieldDefine(title="编码",sort=7,hidden=false,genQuery=true)
	@Column(length=36,nullable=false)
	private String id;
	@Column(length=30,nullable=true,unique=false)
	@FieldDefine(title="名称",sort=6,genQuery=true)
	private String name;
	@FieldDefine(title="排序",sort=6)
	private Integer sort=0;
	@FieldDefine(title="状态",sort=6,showType=ShowType.radio)
	private Boolean status;
	
	@FieldDefine(title="constant_id",sort=7,hidden=true)
	@Column(length=36,nullable=false)
	@FK(cls=Constant.class,column="id")
	private String constant_id;

	public String getKey() {
		return id;
	}
	
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getConstant_id() {
		return constant_id;
	}

	public void setConstant_id(String constant_id) {
		this.constant_id = constant_id;
	}
}
