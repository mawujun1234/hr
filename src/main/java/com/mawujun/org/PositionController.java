package com.mawujun.org;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;

import com.mawujun.org.Position;
import com.mawujun.org.PositionService;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Controller
//@RequestMapping("/position")
public class PositionController {

	@Resource
	private PositionService positionService;


//	/**
//	 * 这是基于分页的几种写法,的例子，请按自己的需求修改
//	 * @author mawujun email:16064988@163.com qq:16064988
//	 * @param start
//	 * @param limit
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping("/position/queryPager.do")
//	@ResponseBody
//	public Pager<Position> queryPager(Pager<Position> pager){
//		
//		return positionService.queryPage(pager);
//	}

	@RequestMapping("/position/queryByOrg.do")
	@ResponseBody
	public List<PositionVO> queryByOrg(String org_id) {	
		List<PositionVO> positiones=positionService.queryByOrg(org_id);
		return positiones;
	}
	

	@RequestMapping("/position/load.do")
	@ResponseBody
	public Position load(String id) {
		return positionService.get(id);
	}
	
	@RequestMapping("/position/create.do")
	@ResponseBody
	public Position create(@RequestBody Position position) {
		positionService.create(position);
		return position;
	}
	
	@RequestMapping("/position/update.do")
	@ResponseBody
	public  Position update(@RequestBody Position position) {
		positionService.update(position);
		return position;
	}
	
	@RequestMapping("/position/deleteById.do")
	@ResponseBody
	public String deleteById(String id) {
		positionService.deleteById(id);
		return id;
	}
	
	@RequestMapping("/position/destroy.do")
	@ResponseBody
	public Position destroy(@RequestBody Position position) {
		positionService.delete(position);
		return position;
	}
	
	
}
