package com.mawujun.constant;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/constantItem")
public class ConstantItemController {

	@Resource
	private ConstantItemService constantItemService;


	/**
	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
	 * @author mawujun email:16064988@163.com qq:16064988
	 * @param start
	 * @param limit
	 * @param userName
	 * @return
	 */
	@RequestMapping("/constantItem/queryPager.do")
	@ResponseBody
	public Pager<ConstantItem> queryPager(Pager<ConstantItem> pager){
		
		return constantItemService.queryPage(pager);
	}

	@RequestMapping("/constantItem/query4Combo.do")
	@ResponseBody
	public List<ConstantItem> query4Combo(String constant_id,Boolean showBlank,Boolean status,String query) {	
		List<ConstantItem> constantItemes=constantItemService.query(Cnd.select()
				.andEquals(M.ConstantItem.constant_id, constant_id)
				.andEquals(M.ConstantItem.status, status)
				.andLikeIf(M.ConstantItem.name, query, true)
				.asc(M.ConstantItem.sort)
			);
		
		if(showBlank!=null && showBlank==true){
			ConstantItem wu=new ConstantItem();
			wu.setId("");
			wu.setName("无");
			constantItemes.add(0, wu);
		}
		return constantItemes;
	}
	

	@RequestMapping("/constantItem/load.do")
	public ConstantItem load(String id) {
		return constantItemService.get(id);
	}
	
	@RequestMapping("/constantItem/create.do")
	@ResponseBody
	public ConstantItem create(@RequestBody ConstantItem constantItem) {
		constantItemService.create(constantItem);
		return constantItem;
	}
	
	@RequestMapping("/constantItem/update.do")
	@ResponseBody
	public  ConstantItem update(@RequestBody ConstantItem constantItem) {
		constantItemService.update(constantItem);
		return constantItem;
	}
	
	@RequestMapping("/constantItem/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		constantItemService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/constantItem/destroy.do")
	@ResponseBody
	public ConstantItem destroy(@RequestBody ConstantItem constantItem) {
		constantItemService.delete(constantItem);
		return constantItem;
	}
	
	
}
