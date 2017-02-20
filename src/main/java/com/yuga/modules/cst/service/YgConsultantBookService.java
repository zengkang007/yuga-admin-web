/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/ksm">ksm</a> All rights reserved.
 */
package com.yuga.modules.cst.service;

import java.util.ArrayList;
import java.util.List;

import com.yuga.common.config.Global;
import com.yuga.common.utils.IdGen;
import com.yuga.modules.cst.entity.Consultant;
import com.yuga.modules.cst.registries.NotifyTypeConst;
import com.yuga.modules.oa.entity.OaNotify;
import com.yuga.modules.oa.entity.OaNotifyRecord;
import com.yuga.modules.oa.service.OaNotifyService;
import com.yuga.modules.sys.entity.User;
import com.yuga.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuga.common.persistence.Page;
import com.yuga.common.service.CrudService;
import com.yuga.modules.cst.entity.YgConsultantBook;
import com.yuga.modules.cst.dao.YgConsultantBookDao;

/**
 * consultant bookingService
 * @author 曾康
 * @version 2017-02-16
 */
@Service
@Transactional(readOnly = true)
public class YgConsultantBookService extends CrudService<YgConsultantBookDao, YgConsultantBook> {
	@Autowired
	private OaNotifyService oaNotifyService;

	@Autowired
	private ConsultantService consultantService;

	public YgConsultantBook get(String id) {
		return super.get(id);
	}
	
	public List<YgConsultantBook> findList(YgConsultantBook ygConsultantBook) {
		return super.findList(ygConsultantBook);
	}
	
	public Page<YgConsultantBook> findPage(Page<YgConsultantBook> page, YgConsultantBook ygConsultantBook) {
		return super.findPage(page, ygConsultantBook);
	}
	
	@Transactional(readOnly = false)
	public void save(YgConsultantBook ygConsultantBook) {
		super.save(ygConsultantBook);
	}

	/**
	 * 接受咨询请求，将结果存在book表中
	 * @param consultantId
	 */
	@Transactional(readOnly = false)
	public void acceptRequest(String consultantId){
		Consultant consultant = consultantService.get(consultantId);
		if (consultant != null) {
			YgConsultantBook book = new YgConsultantBook();
			book.setConsultantId(consultantId);
			book.setUser(UserUtils.getUser());
			save(book);
			logger.info("save booking information,prepare create notify.");
			String consultantName = UserUtils.getUser().getName();
			String title = Global.getConfig("notify.accept.title").replace("{CONSULTANT}", consultantName);
			String content = Global.getConfig("notify.accept.content").replace("{CONSULTANT}", consultantName);;
			createNotify(consultant, consultant.getBookUserId(), title, content, NotifyTypeConst.REQUEST);

		} else {
			logger.error("Cann't find consultant.");
		}
	}

	/**
	 * 创建消息
	 * @param title
	 * @param content
	 * @param type
	 */
	private void createNotify(Consultant consultant, String receiveUserId, String title, String content, String type){
		OaNotify notify = new OaNotify();
		notify.setStatus("1");
		notify.setType(type);
		notify.setTitle(title);
		notify.setContent(content);
		notify.setConsultantId(consultant.getId());

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
		oaNotifyService.save(notify);
		logger.info("Create a notify success.");
	}

	/**
	 * 1. 预定顾问
	 * 2. 发送通知
	 * @param consultant
	 */
	@Transactional(readOnly = false)
	public void doBook(Consultant consultant){
		//添加到book表中
		if(consultant.getId() != null) {
			String userName = UserUtils.getUser().getName();
			String title = Global.getConfig("notify.booking.title").replace("{USER}", userName);
			String content = Global.getConfig("notify.booking.content").replace("{USER}", userName);;
			createNotify(consultant, consultant.getSubmitter(), title, content, NotifyTypeConst.BOOKING);
		} else
		{
			logger.error("empty consultant id.");
		}
	}

	@Transactional(readOnly = false)
	public void delete(YgConsultantBook ygConsultantBook) {
		super.delete(ygConsultantBook);
	}
	
}