package com.mawujun.org;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.service.AbstractService;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class PositionService extends AbstractService<Position, String>{

	@Autowired
	private PositionRepository positionRepository;
	
	@Override
	public PositionRepository getRepository() {
		return positionRepository;
	}

	public List<PositionVO> queryByOrg(String org_id) {
		return positionRepository.queryByOrg(org_id);
	}
}
