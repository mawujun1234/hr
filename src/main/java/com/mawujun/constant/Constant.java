package com.mawujun.constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mawujun.annotation.FieldDefine;

/**
 * 常量
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
@Entity
@Table(name="t_constant")
public class Constant {
	@Id
	@FieldDefine(title="编码",sort=7,hidden=false,genQuery=true)
	@Column(length=36,nullable=false)
	private String id;
	@Column(length=30,nullable=true,unique=false)
	@FieldDefine(title="名称",sort=6,genQuery=true)
	private String name;
	@FieldDefine(title="排序",sort=6)
	private Integer sort=0;
	
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

}
