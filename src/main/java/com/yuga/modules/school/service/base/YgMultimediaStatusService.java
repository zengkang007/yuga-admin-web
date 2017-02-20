/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.base.YgMultimediaStatus;
import com.yuga.modules.school.dao.base.YgMultimediaStatusDao;

/**
 * 多媒体使用情况Service
 * @author 曾康
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class YgMultimediaStatusService extends CrudService<YgMultimediaStatusDao, YgMultimediaStatus> {

	public YgMultimediaStatus get(String id) {
		return super.get(id);
	}
	
	public List<YgMultimediaStatus> findList(YgMultimediaStatus ygMultimediaStatus) {
		return super.findList(ygMultimediaStatus);
	}
	
	public Page<YgMultimediaStatus> findPage(Page<YgMultimediaStatus> page, YgMultimediaStatus ygMultimediaStatus) {
		return super.findPage(page, ygMultimediaStatus);
	}
	
	@Transactional(readOnly = false)
	public void save(YgMultimediaStatus ygMultimediaStatus) {
		super.save(ygMultimediaStatus);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgMultimediaStatus ygMultimediaStatus) {
		super.delete(ygMultimediaStatus);
	}
	
}