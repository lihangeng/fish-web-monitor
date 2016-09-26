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
		String title = model.get(TITLE) == null ? "aaa" : String.valueOf(model.get(TITLE));
		String fileName = model.get(FILE_NAME) == null ? "aaa" : String.valueOf(model.get(FILE_NAME));
		String sheetName = model.get(SHEET_NAME) == null ? "aaa" : String.valueOf(model.get(SHEET_NAME));
		String headerStr = request.getParameter(HEADER_NAMES);
		Object contextObjects = model.get(BODY_CONTEXTS);
		String colIndexStrs = request.getParameter(COLUMN_DATA_INDEXS);
		String colWidthStrs = request.getParameter(COLUMN_WIDTH);
		
		Sheet sheet = workbook.createSheet(sheetName);
		List<Object> contextList = null;
		if ( null != colWidthStrs) {
			String[]colWidthArrays = colWidthStrs.split(",");
			int columnIndex=0;
			for(String width:colWidthArrays){
				sheet.setColumnWidth(columnIndex, Integer.parseInt(width)*30);
				columnIndex++;
			}
		}
		if (headerStr != null) {
			String headerArgs []=headerStr.split(",");
			createSheetTitle(sheet, headerArgs.length, title);
			createHeaderTitle(sheet, headerArgs);
		}
		if (contextObjects != null && null != colIndexStrs) {
			String []colIndexs =  colIndexStrs.split(",");
			contextList = (List<Object>) contextObjects;
			createContext(sheet, contextList, colIndexs);
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(request, String.valueOf(fileName) + ".xlsx") + "\"");
		response.setContentType("application/x-msdownload;charset=UTF-8");
	}

	private String getFileName(HttpServletRequest request, String name) throws Exception {
		if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0 || request.getHeader("User-Agent").toUpperCase().indexOf("FIREFOX") > 0) {
			return new String(name.getBytes("UTF-8"), "ISO8859-1");
		} else {
			// IE浏览器
			return URLEncoder.encode(name, "UTF-8");
		}
	}

	private void createSheetTitle(Sheet sheet, int cols, String title) {
		Row sheetTitleRow = sheet.createRow(0);
		Cell cellTitle = sheetTitleRow.createCell(0);
		CellRangeAddress cellRange = new CellRangeAddress(0, 0, 0, cols-1);
		sheet.addMergedRegion(cellRange);
		cellTitle.setCellValue(title);
		cellTitle.setCellStyle(getTitleFont(sheet.getWorkbook()));
	}

	private void createHeaderTitle(Sheet sheet, String[] headerList) {
		Row headerRow = sheet.createRow(1);
		for (int i = 0; i < headerList.length; i++) {
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(headerList[i]);
			headerCell.setCellStyle(getHeaderFont(sheet.getWorkbook()));
		}
	}

	private CellStyle getTitleFont(Workbook workbook) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 16);// 设置字体大小
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		return style;
	}

	private CellStyle getHeaderFont(Workbook workbook) {
		Font font = (Font) workbook.createFont();
		font.setFontHeightInPoints((short) 14);// 设置字体大小
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		return style;
	}

	private CellStyle getOtherFont(Workbook workbook) {
		Font font = (Font) workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		return style;
	}

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

	private void initRowData(Row row, Object obj, String[] colIndexList, Class<? extends Object> clazz, CellStyle cellStyle) {
		int cellNumber = 0;
		for (String colIndex : colIndexList) {
			Cell cell = row.createCell(cellNumber);
			try {
				Field field = clazz.getDeclaredField(colIndex);
				field.setAccessible(true);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(String.valueOf(field.get(obj)));
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			cellNumber++;
		}
	}
}
