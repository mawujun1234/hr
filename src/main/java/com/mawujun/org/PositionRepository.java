package com.mawujun.org;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface PositionRepository extends IRepository<Position, String>{

	public List<PositionVO> queryByOrg(@Param("org_id")String org_id);
}
