package com.mawujun.generator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.mawujun.permission.Role;

import freemarker.template.TemplateException;
/**
 * 
 * @author mawujun email:16064988@163.com qq:16064988
 *
 */
public class GeneratorCode {
	static GeneratorService generatorService=new GeneratorService();

	public static void main(String[] args) throws TemplateException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		
		//这里弄成更加好用的方式，弄成级联的方式，例如getExtjsConfig.set。。。
		ExtenConfig aa=new ExtenConfig();
		aa.extjs_treeForm_model=false;
		aa.extjs_packagePrefix="Ems";
		aa.extjs_form_layoutColumns=-1;//form的布局是不是变成多列布局
		
		aa.extjs_grid_createDelUpd_button=true;
		aa.extjs_grid_enable_cellEditing=false;
		generatorService.setExtenConfig(aa);
		
		generatorService.generatorAllFile(Role.class);

	}
	


}
