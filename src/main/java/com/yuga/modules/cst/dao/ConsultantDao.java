/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.dao;

import com.yuga.common.persistence.CrudDao;
import com.yuga.common.persistence.annotation.MyBatisDao;
import com.yuga.modules.cst.entity.Consultant;

import java.util.List;

/**
 * ConsultantDAO接口
 * @author zengk
 * @version 2017-01-06
 */
@MyBatisDao
public interface ConsultantDao extends CrudDao<Consultant> {
    List<Consultant> findMyList(Consultant consultant);
}