package com.yihuacomputer.fish.web.fault.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.fault.IVendorCode;
import com.yihuacomputer.fish.api.fault.IVendorCodeService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.web.fault.form.VendorCodeForm;

@Controller
@RequestMapping("/case/vendorCode")
public class VendorCodeController {
	private final Logger logger = LoggerFactory.getLogger(VendorCodeController.class);

	@Autowired
	private IVendorCodeService vendorCodeService;

	@Autowired
	private IOrganizationService organizationService;
	
	@Autowired
	protected MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		ModelMap result = new ModelMap();
		logger.info(String.format("search vendorCode : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);
		IPageResult<IVendorCode> pageResult = vendorCodeService.page(start, limit, filter);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		logger.info("vendorCode size:" + pageResult.getTotal());
		result.addAttribute("data", VendorCodeForm.convert(pageResult.list(), organizationService));
		return result;
	}

	/**
	 * 导入厂商故障信息
	 *
	 * @param vendor
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/import")
	public @ResponseBody
	String importFile(@RequestParam long vendor, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");// 解决IE9 上传文件乱码问题
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = multipartRequest.getFiles("file");
		MultipartFile file = files.get(0);
		if (!file.getOriginalFilename().isEmpty() && file.getSize() > 10485760) {
			return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.fileSize", null, FishCfg.locale)+"'}";
		}
		String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		if (!file.getOriginalFilename().isEmpty()) {
			try {
				File readFile = new File(FishCfg.getTempDir() + System.getProperty("file.separator")
						+ UUID.randomUUID());
				file.transferTo(readFile);
				/* 读文件内容 */
				ArrayList<IVendorCode> vendorCodeList = new ArrayList<IVendorCode>();
				if (fileType.equals(".xls")) {
					vendorCodeList = this.readExl(readFile, "0");// 从excel
																	// 2003文件解析数据：
				} else if (fileType.equals(".xlsx")) {
					vendorCodeList = this.readExl(readFile, "1");// 从excel
																	// 2007文件解析数据：
				} else {
					return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.fileType", null, FishCfg.locale)+"'}";
				}
				if (vendorCodeList != null && !vendorCodeList.isEmpty()) {
					if (this.check(vendorCodeList, vendor)) {
						return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.fileComment", null, FishCfg.locale)+"'}";
					} else {
						for (IVendorCode item : vendorCodeList) {
							item.setVendor(vendor);
							vendorCodeService.save(item);
						}
					}
				} else {
					return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.fileEmpty", null, FishCfg.locale)+"'}";
				}
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.fileComment", null, FishCfg.locale)+"'}";
			}
		}
		else
		{
			return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.fileComment", null, FishCfg.locale)+"'}";
		}
		return "{'success':true}";
	}

	/**
	 * 批量删除厂商故障信息
	 *
	 * @param vendor
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody
	String remove(@RequestParam long vendor) {
		logger.info(String.format("remove vendorCode by vendor"));
		List<IVendorCode> list = vendorCodeService.getByVendor(vendor);
		if (list == null || list.isEmpty()) {
			return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.deleteFail", null, FishCfg.locale)+"'}";
		} else {
			try {
				vendorCodeService.deleteByVendor(vendor);
			} catch (Exception e) {
				return "{'success':false,'content'"+messageSource.getMessage("person.delError", null, FishCfg.locale)+"'}";
			}
		}
		return "{'success':true}";
	}

	/**
	 * 判断导入的数据中是否有已经存在的记录
	 *
	 * @param list
	 * @param vendor
	 * @return true:表示存在形同的记录 false:表示不存在相同记录
	 */
	private boolean check(List<IVendorCode> list, long vendor) {
		boolean flag = false;
		List<IVendorCode> vendorCodeList = vendorCodeService.getByVendor(vendor);
		if (vendorCodeList.size() == 0) {
			flag = false;
		} else {
			for (IVendorCode item : list) {
				for (IVendorCode vendorCode : vendorCodeList) {
					if (item.getCode().equals(vendorCode.getCode())) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 从excel文件中解读数据
	 *
	 * @param file
	 * @param flag
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private ArrayList<IVendorCode> readExl(File file, String flag) throws FileNotFoundException, IOException {
		ArrayList<IVendorCode> vendorCodeList = new ArrayList<IVendorCode>();
		Workbook xls = null;
		if ("0".equals(flag)) {
			xls = new HSSFWorkbook(new FileInputStream(file));// 指定文件
		} else {
			xls = new XSSFWorkbook(new FileInputStream(file));// 指定文件
		}
		Sheet sheet = xls.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();// 行数
		for (int r = 1; r < rows; r++) { // 第一行为标题，从第二行开始
			Row row = (Row) sheet.getRow(r);
			if (row != null) {
				IVendorCode vendorCode = vendorCodeService.make();
				Cell cell1 = (Cell) row.getCell(0);
				Cell cell2 = (Cell) row.getCell(1);
				Cell cell3 = (Cell) row.getCell(2);
				if (cell1 != null) {
					switch (cell1.getCellType()) {
					case Cell.CELL_TYPE_FORMULA:
						break;
					case Cell.CELL_TYPE_NUMERIC:
						vendorCode.setCode(String.valueOf((long) cell1.getNumericCellValue()));
						break;
					case Cell.CELL_TYPE_STRING:
						vendorCode.setCode(String.valueOf(cell1.getStringCellValue()));
						break;
					default:
					}
				} else {
					return null;
				}
				if (cell2 != null && cell2.getStringCellValue() != null) {
					switch (cell2.getCellType()) {
					case Cell.CELL_TYPE_FORMULA:
						break;
					case Cell.CELL_TYPE_NUMERIC:
						vendorCode.setDescription(String.valueOf((long) cell2.getNumericCellValue()));
						break;
					case Cell.CELL_TYPE_STRING:
						vendorCode.setDescription(cell2.getStringCellValue());
						break;
					default:
					}

				} else {
					return null;
				}
				if (cell3 != null && cell3.getStringCellValue() != null) {
					switch (cell3.getCellType()) {
					case Cell.CELL_TYPE_FORMULA:
						break;
					case Cell.CELL_TYPE_NUMERIC:
						vendorCode.setSolution(String.valueOf((long) cell3.getNumericCellValue()));
						break;
					case Cell.CELL_TYPE_STRING:
						vendorCode.setSolution(cell3.getStringCellValue());
						break;
					default:
					}
				} else {
					return null;
				}
				vendorCodeList.add(vendorCode);
			}
		}
		return vendorCodeList;
	}

	private IFilter request2filter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			} else {
				String value = request.getParameter(name);
				if (value.isEmpty()) {
					continue;
				} else if (name.equals("sort")) {
					continue;
				} else if (name.equals("vendor")) {
					filter.eq(name, Long.parseLong(value));
				}
				else if(name.equals("code"))
				{
					filter.like(name, value);
				}
			}
		}
		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}

	 /**
     * 下载文件到浏览器端：
     */
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public void download(@RequestParam String fileName,HttpServletRequest request,HttpServletResponse response) throws Exception {
    	String path = request.getSession().getServletContext().getRealPath("resources/file/" + fileName);
    	File file = new File(path);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(request,fileName) + "\"");
        response.addHeader("Content-Length", "" + file.length());
        response.setContentType("application/x-msdownload;charset=UTF-8");
        OutputStream out = null;
        RandomAccessFile randomFile = new RandomAccessFile(file, "r");
        try{
            out = response.getOutputStream();
            int len = 0;
            long contentLength = 0;
            contentLength = contentLength + randomFile.length();
            randomFile.seek(0);
            byte[] cache= new byte[1024];
            while ((len = randomFile.read(cache)) != -1){
                out.write(cache, 0, len);
                contentLength += len;
            }
        }catch(Exception ex){
            logger.error(ex.getMessage());
        }finally{
            if (out != null)
            {
                out.close();
            }
            if (randomFile != null)
            {
                randomFile.close();
            }
        }
    }

    private String getFileName(HttpServletRequest request,String name) throws Exception{
        if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
            //IE浏览器
            return URLEncoder.encode(name, "UTF-8");
        }else{
            return new String(name.getBytes("UTF-8"),"ISO8859-1");
        }
    }
}
