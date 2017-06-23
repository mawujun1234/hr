package com.mawujun.constant;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/constant")
public class ConstantController {

	@Resource
	private ConstantService constantService;


	/**
	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param start
	 * @param limit
	 * @param userName
	 * @return
	 */
	@RequestMapping("/constant/queryPager.do")
	@ResponseBody
	public Pager<Constant> queryPager(Pager<Constant> pager){
		
		return constantService.queryPage(pager);
	}

	@RequestMapping("/constant/queryAll.do")
	@ResponseBody
	public List<Constant> queryAll() {	
		List<Constant> constantes=constantService.queryAll();
		return constantes;
	}
	

	@RequestMapping("/constant/load.do")
	public Constant load(String id) {
		return constantService.get(id);
	}
	
	@RequestMapping("/constant/create.do")
	@ResponseBody
	public Constant create(@RequestBody Constant constant) {
		constantService.create(constant);
		return constant;
	}
	
	@RequestMapping("/constant/update.do")
	@ResponseBody
	public  Constant update(@RequestBody Constant constant) {
		constantService.update(constant);
		return constant;
	}
	
	@RequestMapping("/constant/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		constantService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/constant/destroy.do")
	@ResponseBody
	public Constant destroy(@RequestBody Constant constant) {
		constantService.delete(constant);
		return constant;
	}
	
	
}
