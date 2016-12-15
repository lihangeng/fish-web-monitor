package com.yihuacomputer.fish.web.util;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class ExcelViewUtils extends AbstractXlsxView {

	/**
	 * excel title
	 */
	public static final String TITLE = "title";
	/**
	 * excel名称
	 */
	public static final String FILE_NAME = "fileName";
	/**
	 * sheet名称
	 */
	public static final String SHEET_NAME = "sheetName";
	/**
	 * 表头集合 List<String>
	 */
	public static final String HEADER_NAMES = "gridInfoHeaderNames";
	/**
	 * 表头集合 List<String>
	 */
	public static final String COLUMN_DATA_INDEXS = "gridInfoColIndexs";
	/**
	 * 列宽集合 List<Integer>
	 */
	public static final String COLUMN_WIDTH = "gridInfoColWidths";
	/**
	 * 表内容集合 List<Object>
	 */
	public static final String BODY_CONTEXTS = "bodys";

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = model.get(TITLE) == null ? "" : String.valueOf(model.get(TITLE));
		String fileName = model.get(FILE_NAME) == null ? null : String.valueOf(model.get(FILE_NAME));
		String sheetName = model.get(SHEET_NAME) == null ? null : String.valueOf(model.get(SHEET_NAME));
		String headerStr = request.getParameter(HEADER_NAMES).replaceAll("<br/>","");
		Object contextObjects = model.get(BODY_CONTEXTS);
		String colIndexStrs = request.getParameter(COLUMN_DATA_INDEXS);
		String colWidthStrs = request.getParameter(COLUMN_WIDTH);
		Sheet sheet = null;
		//sheetName不存在则创建默认的sheet
		if(null!=sheetName&&!"".equals(sheetName)){
			sheet = workbook.createSheet(sheetName);
		}else{
			sheet = workbook.createSheet();
		}
		List<Object> contextList = (List<Object>) contextObjects;
		//没有设备列宽，列宽则为默认
		if ( null != colWidthStrs) {
			String[]colWidthArrays = colWidthStrs.split(",");
			int columnIndex=0;
			for(String width:colWidthArrays){
				if(!"".equals(width)&&width!=null){
				sheet.setColumnWidth(columnIndex, Integer.parseInt(width)*35);
				columnIndex++;
				}
			}
		}
		if (headerStr != null) {
			String headerArgs []=headerStr.split(",");
			createSheetTitle(sheet, headerArgs.length, title);
			createHeaderTitle(sheet, headerArgs);
		}
		if (contextList.size() != 0 && null != colIndexStrs) {
			String []colIndexs =  colIndexStrs.split(",");
			createContext(sheet, contextList, colIndexs);
		}
		if(fileName!=null&&!"".equals(fileName)){
			response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(request, String.valueOf(fileName) + ".xlsx") + "\"");
			response.setContentType("application/x-msdownload;charset=UTF-8");
		}
	}

	/**
	 * 浏览器中文乱码处理
	 * @param request
	 * @param name
	 * @return
	 * @throws Exception
	 */
	private String getFileName(HttpServletRequest request, String name) throws Exception {
		if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0 || request.getHeader("User-Agent").toUpperCase().indexOf("FIREFOX") > 0) {
			return new String(name.getBytes("UTF-8"), "ISO8859-1");
		} else {
			// IE浏览器
			return URLEncoder.encode(name, "UTF-8");
		}
	}

	/**
	 * 设置Excel的主标题
	 * @param sheet
	 * @param cols
	 * @param title
	 */
	private void createSheetTitle(Sheet sheet, int cols, String title) {
		Row sheetTitleRow = sheet.createRow(0);
		Cell cellTitle = sheetTitleRow.createCell(0);
		//主标题的单元格合并
		CellRangeAddress cellRange = new CellRangeAddress(0, 0, 0, cols-1);
		sheet.addMergedRegion(cellRange);
		cellTitle.setCellValue(title);
		cellTitle.setCellStyle(getTitleFont(sheet.getWorkbook()));
	}

	/**
	 * 创建excel的header名称
	 * @param sheet
	 * @param headerList
	 */
	private void createHeaderTitle(Sheet sheet, String[] headerList) {
		Row headerRow = sheet.createRow(1);
		for (int i = 0; i < headerList.length; i++) {
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(headerList[i]);
			headerCell.setCellStyle(getHeaderFont(sheet.getWorkbook()));
		}
	}

	/**
	 * 主标题单元格字体样式
	 * @param workbook
	 * @return
	 */
	private CellStyle getTitleFont(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 16);// 设置字体大小
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		return style;
	}

	/**
	 * 数据表头字体样式
	 * @param workbook
	 * @return
	 */
	private CellStyle getHeaderFont(Workbook workbook) {
		Font font = (Font) workbook.createFont();
		font.setFontHeightInPoints((short) 11);// 设置字体大小
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(font);
		style.setWrapText(true);
		return style;
	}

	/**
	 * 数据区字体样式
	 * @param workbook
	 * @return
	 */
	private CellStyle getOtherFont(Workbook workbook) {
		Font font = (Font) workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		return style;
	}

	/**
	 * 向表中填充数据内容
	 * @param sheet
	 * @param contextList
	 * @param colIndexList
	 */
	private void createContext(Sheet sheet, List<Object> contextList, String[] colIndexList) {
		int rowNumber = 2;
		Workbook workBook = sheet.getWorkbook();
		CellStyle cellStyle = getOtherFont(workBook);
		Class<? extends Object> clazz = contextList.get(0).getClass();
		for (Object objects : contextList) {
			Row row = sheet.createRow(rowNumber);
			initRowData(row, objects, colIndexList, clazz, cellStyle);
			rowNumber++;
		}
	}

	/**
	 * 通过反射机制一行一行填写数据
	 * @param row
	 * @param obj
	 * @param colIndexList
	 * @param clazz
	 * @param cellStyle
	 */
	private void initRowData(Row row, Object obj, String[] colIndexList, Class<? extends Object> clazz, CellStyle cellStyle) {
		int cellNumber = 0;
		for (String colIndex : colIndexList) {
			Cell cell = row.createCell(cellNumber);
			try {
				//获取填充的对象属性
				Field field = clazz.getDeclaredField(colIndex);
				//使得私有字段可反射
				field.setAccessible(true);
				cell.setCellStyle(cellStyle);
				if(field.get(obj)==null){
					cell.setCellValue("");
				}
				else{
					cell.setCellValue(String.valueOf(field.get(obj)));
				}
			} catch (SecurityException e) {
				logger.error(String.format("SecurityException is [%s]", e.getMessage()));
			} catch (NoSuchFieldException e) {
				logger.error(String.format("NoSuchFieldException is [%s]", e.getMessage()));
			} catch (IllegalArgumentException e) {
				logger.error(String.format("IllegalArgumentException is [%s]", e.getMessage()));
			} catch (IllegalAccessException e) {
				logger.error(String.format("IllegalAccessException is [%s]", e.getMessage()));
			}
			cellNumber++;
		}
	}
}
