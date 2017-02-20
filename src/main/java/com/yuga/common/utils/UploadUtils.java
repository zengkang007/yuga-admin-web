package com.yuga.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.yuga.common.config.Global;

/**
 * 文件上传工具类
 * 
 * @author yangdc
 * @date Apr 18, 2012
 * 
 *       <pre>
 *       </pre>
 */
public class UploadUtils {
	
	private static Logger log = LoggerFactory.getLogger(UploadUtils.class);
	/**
	 * 表单字段常量
	 */
	public static final String FORM_FIELDS = "form_fields";
	/**
	 * 文件域常量
	 */
	public static final String FILE_FIELDS = "file_fields";

	/** 
     * 上传文件集合 
     */  
    private Map<String, FileItem> files = new HashMap<String, FileItem>();  
  
    /** 
     * 文本域数据集合 
     */  
    private Map<String, Object> fields = new HashMap<String, Object>();  
    
    /** 
     * 变量，永远在request.setAttribute的时候的键，方便统一取值 
     */  
    public String reqattributename = "__UploadFile";  
    
	// 最大文件大小
	private long maxSize = 1000000;
	// 定义允许上传的文件扩展名
	private Map<String, String> extMap = new HashMap<String, String>();
	// 文件保存目录相对路径
	private String basePath = "upload";
	// 文件的目录名
	private String dirName = "images";
	// 上传临时路径
	private static final String TEMP_PATH = "/temp";
	private String tempPath = basePath + TEMP_PATH;
	// 若不指定则文件名默认为 yyyyMMddHHmmss_xyz
	private String fileName;

	// 文件保存目录路径
	private String savePath;
	// 文件保存目录url
	private String saveUrl;
	// 文件最终的url包括文件名
	private String fileUrl;

	 /** 
     * 默认临时存储目录 
     */  
    public static String TEMPREPOSITORY = System.getProperty("java.io.tmpdir");  
    /** 
     * 在内存中的最大限度 
     */  
    public static int SIZETHRESHOLD = 1000000;  
  
    /** 
     * 最大上传文件大小 
     */  
    public static int SIZEMAX = 5024000;  
    
    
	public UploadUtils() {
		// 其中images,flashs,medias,files,对应文件夹名称,对应dirName
		// key文件夹名称
		// value该文件夹内可以上传文件的后缀名
		extMap.put("images", "gif,jpg,jpeg,png,bmp");
		extMap.put("flashs", "swf,flv");
		extMap.put("medias", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("files", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	}
	
	/**
	 * 返回缩略图地址
	 * @param userId
	 * @param fileName
	 * @return
	 */
	private String createThumbsUrl(String userId) {
		String urlThumbs = "userfiles/{userId}/_thumbs/images/admin/mobile/{year}/{month}/";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		log.info("year:" + year);
		log.info("month:" + month);
		urlThumbs = urlThumbs.replace("{userId}", userId).replace("{year}", ""+year).replace("{month}", ""+month);
		log.info("temp1 : " + urlThumbs);
		return urlThumbs;
	}
	
	/**
	 * 创建原始上传图片
	 * @param userId
	 * @return
	 */
	private String createFileUrl(String userId) {
		String urlOrginal = "userfiles/{userId}/images/admin/mobile/{year}/{month}/";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		
		urlOrginal = urlOrginal.replace("{userId}", userId).replace("{year}", ""+year).replace("{month}", ""+month);
		return urlOrginal;
	}
	
	/**
	 * 处理文件上传
	 * @param request
	 * @throws Exception
	 */
	public void uploadImages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		if(userId == null) {
			log.error("User id is empty.");
			//返回前端
			try {
				PrintWriter writer = response.getWriter();
				JSONObject resultMap = new JSONObject();
				resultMap.put("result", "error");
				resultMap.put("code", "-1");
				resultMap.put("data", "user not exist");
				writer.println(resultMap);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return;
		}
		
		// 判断是否为上传
		if (ServletFileUpload.isMultipartContent(request)) {
			// 初始一个DiskFileItemFactory
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置临时存放目录
			File file = new File(TEMPREPOSITORY);
			if (!file.exists() || !file.isDirectory()) {
				file.mkdirs();
			}
			factory.setRepository(file);
			// 设置在内存中的最大限度
			factory.setSizeThreshold(SIZETHRESHOLD);
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置最大上传文件大小
			upload.setSizeMax(SIZEMAX);
			List<FileItem> items = null;
			try {
				// 格式化
				items = upload.parseRequest(request);
			} catch (Exception e) {
				log.debug("上传文件格式化request错误", e);
				e.printStackTrace();
				if (e instanceof SizeLimitExceededException) {
					throw new Exception("ERROR:上传文件过大，文件最大：" + SIZEMAX / 1024 + "KB");
				} else {
					throw new Exception("格式化请求错误" + e.getMessage());
				}
			}
			// 取得item的迭代对象
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					String fieldName = new String(item.getFieldName().getBytes("ISO-8859-1"), "UTF-8");
					String fieldValue = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
					if (fields.containsKey(fieldName)) {
						Object obj = fields.get(fieldName);
						if (obj instanceof List) {
							List<Object> ob = (List<Object>) obj;
							ob.add(fieldValue);
						} else {
							List<Object> list = new ArrayList<Object>();
							list.add(obj);
							list.add(fieldValue);
							fields.remove(fieldName);
							fields.put(fieldName, list);
						}
					} else
						fields.put(fieldName, fieldValue);
				//如果是上传的文件	
				} else if (item.get() != null && item.get().length > 0) {
					files.put(item.getName(), item);
					
					String extension = item.getName().substring(item.getName().lastIndexOf("."));
					String newFileName = System.currentTimeMillis() + extension;
					String urlOrginal = createFileUrl(userId);
					//上下文
					String urlContext = Global.getUserfilesBaseDir();
					
					File uploadedOrginalDir = new File(urlContext + urlOrginal);
					if (!uploadedOrginalDir.exists() || !uploadedOrginalDir.isDirectory()) {
						log.info("create orginal directory : " + (urlContext + urlOrginal));
						uploadedOrginalDir.mkdirs();
					}
					
					File uploadedFile = new File(urlContext + urlOrginal, newFileName);
					item.write(uploadedFile);
					String urlOrginalFile = urlOrginal+newFileName;
					log.info("orginal image create success, url: " + urlOrginalFile);
					//生成缩略图
					String urlThumbs = createThumbsUrl(userId) ;
					File uploadedThumbsDir = new File(urlContext + urlThumbs);
					if (!uploadedThumbsDir.exists() || !uploadedThumbsDir.isDirectory()) {
						log.info("create thumbs directory : " + (urlContext + urlThumbs));
						uploadedThumbsDir.mkdirs();
					}
					
					ImageUtil.scale(urlContext + urlOrginalFile, urlContext + urlThumbs + newFileName , 3, false);
					log.info("create thumb image success, url:" + (urlContext + urlThumbs));
					
					//返回前端
					try {
						PrintWriter writer = response.getWriter();
						JSONObject resultMap = new JSONObject();
						resultMap.put("result", "success");
						resultMap.put("code", "0");
						resultMap.put("data", urlOrginalFile);
						writer.println(resultMap);
						log.info("上传图片返回结果：" + resultMap);
						writer.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			request.setAttribute(reqattributename, this);
		} else {
			log.debug("上传文件ERROR:该请求不是上传文件");
			throw new Exception("ERROR:该请求不是上传文件 ");
		}
	}

	/**
	 * 创建用户路径
	 * @param userId
	 * @return
	 */
	private String createUrl(String userId){
		String userUrl = Global.getUserfilesBaseDir();
		//create url like
		//userfiles/{userId}/_thumbs/files/admin/mobile/{year}/{month}/{name}.jpeg
		//userfiles/{userId}/images/{year}/{month}/{name}.jpeg
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		
		return "";
	}
	
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @return infos info[0] 验证文件域返回错误信息 info[1] 上传文件错误信息 info[2] savePath
	 *         info[3] saveUrl info[4] fileUrl
	 */
	@SuppressWarnings("unchecked")
	public String[] uploadFile(HttpServletRequest request) {
		String[] infos = new String[5];
		// 验证
		infos[0] = this.validateFields(request);
		// 初始化表单元素
		Map<String, Object> fieldsMap = new HashMap<String, Object>();
		if (infos[0].equals("true")) {
			fieldsMap = this.initFields(request);
		}
		// 上传
		List<FileItem> fiList = (List<FileItem>) fieldsMap.get(UploadUtils.FILE_FIELDS);
		if (fiList != null) {
			for (FileItem item : fiList) {
				infos[1] = this.saveFile(item);
			}
			infos[2] = savePath;
			infos[3] = saveUrl;
			infos[4] = fileUrl;
		}
		return infos;
	}

	/**
	 * 上传验证,并初始化文件目录
	 * 
	 * @param request
	 */
	private String validateFields(HttpServletRequest request) {
		String errorInfo = "true";
		// boolean errorFlag = true;
		// 获取内容类型
		String contentType = request.getContentType();
		int contentLength = request.getContentLength();
		// 文件保存目录路径
		savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
		// 文件保存目录URL
		saveUrl = request.getContextPath() + "/" + basePath + "/";
		File uploadDir = new File(savePath);
		if (contentType == null || !contentType.startsWith("multipart")) {
			// TODO
			System.out.println("请求不包含multipart/form-data流");
			errorInfo = "请求不包含multipart/form-data流";
		} else if (maxSize < contentLength) {
			// TODO
			System.out.println("上传文件大小超出文件最大大小");
			errorInfo = "上传文件大小超出文件最大大小[" + maxSize + "]";
		} else if (!ServletFileUpload.isMultipartContent(request)) {
			// TODO
			errorInfo = "请选择文件";
		} else if (!uploadDir.isDirectory()) {// 检查目录
			// TODO
			errorInfo = "上传目录[" + savePath + "]不存在";
		} else if (!uploadDir.canWrite()) {
			// TODO
			errorInfo = "上传目录[" + savePath + "]没有写权限";
		} else if (!extMap.containsKey(dirName)) {
			// TODO
			errorInfo = "目录名不正确";
		} else {
			// .../basePath/dirName/
			// 创建文件夹
			savePath += dirName + "/";
			saveUrl += dirName + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			// .../basePath/dirName/yyyyMMdd/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			// 获取上传临时路径
			tempPath = request.getSession().getServletContext().getRealPath("/") + tempPath + "/";
			File file = new File(tempPath);
			if (!file.exists()) {
				file.mkdirs();
			}
		}

		return errorInfo;
	}

	/**
	 * 处理上传内容
	 * 
	 * @param request
	 * @param maxSize
	 * @return
	 */
	// @SuppressWarnings("unchecked")
	private Map<String, Object> initFields(HttpServletRequest request) {

		// 存储表单字段和非表单字段
		Map<String, Object> map = new HashMap<String, Object>();

		// 第一步：判断request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 第二步：解析request
		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 阀值,超过这个值才会写到临时目录,否则在内存中
			factory.setSizeThreshold(1024 * 1024 * 10);
			factory.setRepository(new File(tempPath));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setHeaderEncoding("UTF-8");

			// 最大上传限制
			upload.setSizeMax(maxSize);

			/* FileItem */
			List<FileItem> items = null;
			// Parse the request
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 第3步：处理uploaded items
			if (items != null && items.size() > 0) {
				Iterator<FileItem> iter = items.iterator();
				// 文件域对象
				List<FileItem> list = new ArrayList<FileItem>();
				// 表单字段
				Map<String, String> fields = new HashMap<String, String>();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 处理所有表单元素和文件域表单元素
					if (item.isFormField()) { // 表单元素
						String name = item.getFieldName();
						String value = item.getString();
						fields.put(name, value);
					} else { // 文件域表单元素
						list.add(item);
					}
				}
				map.put(FORM_FIELDS, fields);
				map.put(FILE_FIELDS, list);
			}
		}
		return map;
	}

	/**
	 * 保存文件
	 * 
	 * @param obj
	 *            要上传的文件域
	 * @param file
	 * @return
	 */
	private String saveFile(FileItem item) {
		String error = "true";
		String fileName = item.getName();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

		if (item.getSize() > maxSize) { // 检查文件大小
			// TODO
			error = "上传文件大小超过限制";
		} else if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {// 检查扩展名
			error = "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。";
		} else {
			String newFileName;
			if ("".equals(fileName.trim())) {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			} else {
				newFileName = fileName + "." + fileExt;
			}
			// .../basePath/dirName/yyyyMMdd/yyyyMMddHHmmss_xxx.xxx
			fileUrl = saveUrl + newFileName;
			try {
				File uploadedFile = new File(savePath, newFileName);

				item.write(uploadedFile);

				/*
				 * FileOutputStream fos = new FileOutputStream(uploadFile); //
				 * 文件全在内存中 if (item.isInMemory()) { fos.write(item.get()); }
				 * else { InputStream is = item.getInputStream(); byte[] buffer
				 * = new byte[1024]; int len; while ((len = is.read(buffer)) >
				 * 0) { fos.write(buffer, 0, len); } is.close(); } fos.close();
				 * item.delete();
				 */
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("上传失败了！！！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return error;
	}

	/** **********************get/set方法********************************* */

	public String getSavePath() {
		return savePath;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public Map<String, String> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, String> extMap) {
		this.extMap = extMap;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
		tempPath = basePath + TEMP_PATH;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
