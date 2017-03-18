/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.oa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yuga.common.utils.IdGen;
import com.yuga.modules.cst.entity.Consultant;
import com.yuga.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.oa.dao.OaNotifyDao;
import com.yuga.modules.oa.dao.OaNotifyRecordDao;
import com.yuga.modules.oa.entity.OaNotify;
import com.yuga.modules.oa.entity.OaNotifyRecord;

/**
 * 通知通告Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OaNotifyService extends CrudService<OaNotifyDao, OaNotify> {

	@Autowired
	private OaNotifyRecordDao oaNotifyRecordDao;

	public OaNotify get(String id) {
		OaNotify entity = dao.get(id);
		return entity;
	}
	
	/**
	 * 获取通知发送记录
	 * @param oaNotify
	 * @return
	 */
	public OaNotify getRecordList(OaNotify oaNotify) {
		oaNotify.setOaNotifyRecordList(oaNotifyRecordDao.findList(new OaNotifyRecord(oaNotify)));
		return oaNotify;
	}
	
	public Page<OaNotify> find(Page<OaNotify> page, OaNotify oaNotify) {
		oaNotify.setPage(page);
		page.setList(dao.findList(oaNotify));
		return page;
	}
	
	/**
	 * 获取通知数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(OaNotify oaNotify) {
		return dao.findCount(oaNotify);
	}
	
	@Transactional(readOnly = false)
	public void save(OaNotify oaNotify) {
		super.save(oaNotify);
		
		// 更新发送接受人记录
		oaNotifyRecordDao.deleteByOaNotifyId(oaNotify.getId());
		if (oaNotify.getOaNotifyRecordList().size() > 0){
			oaNotifyRecordDao.insertAll(oaNotify.getOaNotifyRecordList());
		}
	}
	
	/**
	 * 更新阅读状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(OaNotify oaNotify) {
		OaNotifyRecord oaNotifyRecord = new OaNotifyRecord(oaNotify);
		oaNotifyRecord.setUser(oaNotifyRecord.getCurrentUser());
		oaNotifyRecord.setReadDate(new Date());
		oaNotifyRecord.setReadFlag("1");
		oaNotifyRecordDao.update(oaNotifyRecord);
	}

	/**
	 * 创建通知消息
	 * @param consultantId   顾问ID，要预定、拍卖
	 * @param receiveUserId	 接受通知人ID
	 * @param title			 标题
	 * @param content	     内容
	 * @param type			 类型
	 */
	@Transactional(readOnly = false)
	public void createConsultantNotify(String consultantId,
										String receiveUserId,
										String title,
										String content,
										String type){
		OaNotify notify = new OaNotify();
		notify.setStatus("1");
		notify.setType(type);
		notify.setTitle(title);
		notify.setContent(content);
		notify.setConsultantId(consultantId);

		logger.info("save notify success, insert user notify.");
		OaNotifyRecord notifyRecord = new OaNotifyRecord();
		notifyRecord.setOaNotify(notify);
		User receiveUser = new User();
		receiveUser.setId(receiveUserId);
		notifyRecord.setUser(receiveUser);
		notifyRecord.setId(IdGen.uuid());
		notifyRecord.setReadFlag("0");
		//create receive list
		List<OaNotifyRecord> lstRecord = new ArrayList<>();
		lstRecord.add(notifyRecord);
		notify.setOaNotifyRecordList(lstRecord);
		save(notify);
		logger.info("Create a notify success.");
	}
}