/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.school.dao.checker;

import java.util.List;

import com.yuga.common.persistence.CrudDao;
import com.yuga.common.persistence.annotation.MyBatisDao;
import com.yuga.modules.school.entity.checker.ClassCheckDTO;
import com.yuga.modules.school.entity.checker.YgClassCheck;

/**
 * 巡查情况DAO接口
 * @author 曾康
 * @version 2016-11-02
 */
@MyBatisDao
public interface YgClassCheckDao extends CrudDao<YgClassCheck> {
	public List<ClassCheckDTO> findTop10List();
}