/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.school.entity.base.YgTeacherUser;
import com.yuga.modules.school.dao.base.YgTeacherUserDao;

/**
 * 教师信息Service
 * @author 曾康
 * @version 2016-10-23
 */
@Service
@Transactional(readOnly = true)
public class YgTeacherUserService extends CrudService<YgTeacherUserDao, YgTeacherUser> {

	public YgTeacherUser get(String id) {
		return super.get(id);
	}
	
	public List<YgTeacherUser> findList(YgTeacherUser ygTeacherUser) {
		return super.findList(ygTeacherUser);
	}
	
	public Page<YgTeacherUser> findPage(Page<YgTeacherUser> page, YgTeacherUser ygTeacherUser) {
		return super.findPage(page, ygTeacherUser);
	}
	
	@Transactional(readOnly = false)
	public void save(YgTeacherUser ygTeacherUser) {
		super.save(ygTeacherUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgTeacherUser ygTeacherUser) {
		super.delete(ygTeacherUser);
	}
	
}