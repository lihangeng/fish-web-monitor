package com.yihuacomputer.fish.web.version.form;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("rawtypes")
public class Excel {
	
	private static Logger logger = LoggerFactory.getLogger(Excel.class);

    private String[] headers;

    private List<String> colsType;

    private List<List> data;

    private int startRowNumber = 0;

    public Excel() {
    }

    public void export(String fileName, String sheetName) {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet(sheetName);

        beforeHeader();

        int currentRowNum = startRowNumber;

        // 设置标题头
        HSSFRow startRow = sheet.createRow(startRowNumber);
        for (int headerIndex = 0; headerIndex < headers.length; headerIndex++) {
            HSSFCell cell = startRow.createCell(headerIndex);
            cell.setCellValue(headers[headerIndex]);
        }

        // 设置数据
        for (int rowNum = 0; rowNum < data.size(); rowNum++) {
            HSSFRow row = sheet.createRow(++currentRowNum);
            List columns = data.get(rowNum);
            for (int col = 0; col < columns.size(); col++) {
                HSSFCell cell = row.createCell(col);
                cell.setCellValue(columns.get(col) == null ? "" : columns.get(col).toString());
            }
        }

        // 保存
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            workBook.write(fos);
        }
        catch (Exception e) {
        	logger.error(String.format("[%s]", e));
        }
        finally {
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (IOException e) {
                	logger.error(String.format("[%s]", e));
                }
            }
        }
    }

    private void beforeHeader() {

    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<String> getColsType() {
        return colsType;
    }

    public void setColsType(List<String> colsType) {
        this.colsType = colsType;
    }

    public List<List> getData() {
        return data;
    }

    public void setData(List<List> data) {
        this.data = data;
    }

    public int getStartRowNumber() {
        return startRowNumber;
    }

    public void setStartRowNumber(int startRowNumber) {
        this.startRowNumber = startRowNumber;
    }

}
