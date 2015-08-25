package com.yihuacomputer.fish.web.fault.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.fault.FaultStatus;
import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.fault.ICaseFaultService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.fault.form.CaseFaultForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/case/caseFault")
public class FaultController
{

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(FaultController.class);

    @Autowired
    private ICaseFaultService service;

    /**
     * 根据条件得到故障列表
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req)
    {
        logger.info(String.format("search caseFault : start = %s ,limt = %s ", start, limit));
        ModelMap result = new ModelMap();
        UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
        long orgId = userSession.getOrgId();
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext())
        {
            String name = iterator.next();
            if (FishWebUtils.isIgnoreRequestName(name))
            {
                continue;
            }
            else
            {
                if (request.getParameter(name).isEmpty())
                {
                    continue;
                }
                else
                {
                    if ("sort".equals(name))
                    { // 去掉前端页面传来的sort排序字段
                        continue;
                    }
                    else
                    {
                        filter.eq(name, request.getParameter(name));
                    }
                }
            }
        }
        IPageResult<ICaseFault> pageResult = service.page(start, limit, filter, orgId);
        List<ICaseFault> list = pageResult.list();
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        result.addAttribute("data", CaseFaultForm.convert(list));
        return result;
    }

    /**
     * 根据条件得到故障列表
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ModelMap searchById(@RequestParam long faultId, HttpServletRequest req)
    {
        logger.info(String.format("search caseFault by Id"));
        ModelMap result = new ModelMap();
        ICaseFault caseFault = service.getFault(faultId);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("data", new CaseFaultForm(caseFault));
        return result;
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap poi(WebRequest request, HttpServletRequest req, HttpServletResponse response)
    {
        logger.info(String.format("export caseFault"));
        UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
        long orgId = userSession.getOrgId();
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext())
        {
            String name = iterator.next();
            if (FishWebUtils.isIgnoreRequestName(name))
            {
                continue;
            }
            else
            {
                if (request.getParameter(name).isEmpty())
                {
                    continue;
                }

                else
                {
                    if ("sort".equals(name))
                    { // 去掉前端页面传来的sort排序字段
                        continue;
                    }
                    else
                    {
                        String value = req.getParameter(name);
                        if ("undefined".equals(value))
                        {
                            continue;
                        }
                        else
                        {
                            filter.eq(name, request.getParameter(name));
                        }
                    }
                }
            }
        }

        // 创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("故障列表");
        // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("设备号");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("故障模块");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("故障分类");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("厂商故障码");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("故障开始时间");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("故障关闭时间");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("持续时长");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("故障状态");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("升级次数");
        cell.setCellStyle(style);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));

        List<ICaseFault> list = service.list(orgId, filter);
        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow(i + 1);
            ICaseFault fault = list.get(i);

            cell = row.createCell(0);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(cellValue(fault.getTerminalId()));

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            if (null == fault.getDevMod())
            {
                cell.setCellValue(cellValue(""));
            }
            else
            {
                cell.setCellValue(cellValue(fault.getDevMod().getText()));
            }

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            if (null == fault.getFaultClassify())
            {
                cell.setCellValue(cellValue(""));
            }
            else
            {
                cell.setCellValue(cellValue(fault.getFaultClassify().getClassifyName()));
            }

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(cellValue(fault.getVendorHwCode()));

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(cellValue(fault.getFaultTime()));

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(cellValue(fault.getClosedTime()));

            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(cellValue(fault.getDuration()));

            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            if (fault.getFaultStatus().equals(FaultStatus.OPEN))
            {
                cell.setCellValue("未关闭");
            }
            else if (fault.getFaultStatus().equals(FaultStatus.CLOSED))
            {
                cell.setCellValue("已关闭");
            }

            cell = row.createCell(8);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(cellValue(fault.getUpgrade()));
        }

        String date = DateUtils.getDate(new Date());
        String name = "caseFault_" + date + ".xls";
        try
        {
            FileOutputStream fout = new FileOutputStream(FishCfg.getTempDir() + File.separator + name);
            wb.write(fout);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        File file = new File(FishCfg.getTempDir() + System.getProperty("file.separator") + name);
        this.download(file, response, "gb2312", "application/x-xls");

        return null;
    }

    /**
     * 下载文件
     *
     * @param file
     *            文件
     * @param response
     *            请求响应
     * @param encoding
     *            编码
     * @param contentType
     *            头信息
     */
    private void download(File file, HttpServletResponse response, String encoding, String contentType)
    {
        response.setCharacterEncoding(encoding);
        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment;filename=" + file.getName());

        // response.setHeader("charset", "UTF-8");

        // OutputStream os = null;
        InputStream is = null;
        // InputStreamReader isr = null;
        // OutputStreamWriter osw = null;
        ServletOutputStream out = null;
        try
        {
            is = new FileInputStream(file);
            // osw = new OutputStreamWriter(os, encoding);

            out = response.getOutputStream();
            // os = response.getOutputStream();

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1)
            {
                out.write(buffer, 0, len);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private String cellValue(Object obj)
    {
        if (obj == null)
        {
            return "";
        }
        if (obj instanceof String)
        {
            return obj.toString();
        }
        else if (obj instanceof Date)
        {
            return DateUtils.getDate((Date) obj);
        }
        else if (obj instanceof Integer || obj instanceof Long || obj instanceof Double)
        {
            return String.valueOf(obj.toString());
        }
        return obj.toString();
    }
}
