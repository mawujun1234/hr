package com.mawujun.generator;

import java.io.IOException;

import com.mawujun.generator.MT.GeneratorMT;
import com.mawujun.utils.file.FileUtils;

/**
 * 生成M类。
 * @author mawujun 16064988@qq.com  
 *
 */
public class GeneratorM {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GeneratorMT generatorMT=new GeneratorMT();
		//System.out.println(generatorMT.getClass().getResource("/").getPath());
		generatorMT.generateM("com.mawujun",generatorMT.getClass().getResource("/").getPath()+"../../src/main/java","com.mawujun.utils");
		//generatorMT.generateM("com.mawujun","/Users/mawujun/Documents/workspace_module/ems_new/src/main/java","com.mawujun.utils");
	}

}
