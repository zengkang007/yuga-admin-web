/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.cst.entity.YgFavorites;
import com.yuga.modules.cst.dao.YgFavoritesDao;

/**
 * FavoritesService
 * @author zengk
 * @version 2017-04-13
 */
@Service
@Transactional(readOnly = true)
public class YgFavoritesService extends CrudService<YgFavoritesDao, YgFavorites> {

	public YgFavorites get(String id) {
		return super.get(id);
	}
	
	public List<YgFavorites> findList(YgFavorites ygFavorites) {
		return super.findList(ygFavorites);
	}
	
	public Page<YgFavorites> findPage(Page<YgFavorites> page, YgFavorites ygFavorites) {
		return super.findPage(page, ygFavorites);
	}
	
	@Transactional(readOnly = false)
	public void save(YgFavorites ygFavorites) {
		super.save(ygFavorites);
	}
	
	@Transactional(readOnly = false)
	public void delete(YgFavorites ygFavorites) {
		super.delete(ygFavorites);
	}
	
}