/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.dao;

import com.yuga.common.persistence.CrudDao;
import com.yuga.common.persistence.annotation.MyBatisDao;
import com.yuga.modules.cst.entity.YgFavorites;

/**
 * FavoritesDAO接口
 * @author zengk
 * @version 2017-04-13
 */
@MyBatisDao
public interface YgFavoritesDao extends CrudDao<YgFavorites> {
	
}