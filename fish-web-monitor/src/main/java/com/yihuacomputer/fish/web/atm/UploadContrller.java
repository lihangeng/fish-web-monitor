package com.yihuacomputer.fish.web.atm;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadContrller {
	
	private Logger logger = LoggerFactory.getLogger(UploadContrller.class);

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,HttpServletResponse response) {

        System.out.println(file.getOriginalFilename());

        try {
            File destFilePath = new File("D:\\abcTest");
            if(!destFilePath.exists()){
                destFilePath.mkdir();
            }
            file.transferTo(new File(destFilePath.getAbsolutePath()+ File.separator+ file.getOriginalFilename()));

        }
        catch (IllegalStateException e) {
            logger.error(e.getMessage());
        }
        catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @RequestMapping(value="/task/planReqAct.ebf",method = RequestMethod.GET)
    public @ResponseBody String get(HttpServletRequest request,HttpServletResponse response) {
        String xml = "<root>"+
                "<disttaskno>2</disttaskno>"+
                "<disttask>"+
                    "<tasktype>restartAct</tasktype>"+
                    "<planid>a59a3aa2beb13bbbc2cc5cade362d0dd</planid>"+
                    "<taskid>a3b5c6d7f09c8e6abbccddee55446677</taskid>"+
                    "<termid>test0091</termid>"+
                    "<exectime>2012-7-19 9:37:15.000</exectime>"+
                    "<valid>20</valid>"+
                "</disttask>"+

                "<disttask>"+
                    "<tasktype>getTermFileAct</tasktype>"+
                    "<planid>a59a3aa2beb13bbbc2cc5cade362d0dd</planid>"+
                    "<taskid>a3b5c6d7f09c8e6abbccddee55446677</taskid>"+
                    "<termid>test0091</termid>"+
                    "<exectime>2012-7-19 9:37:15.000</exectime>"+
                    "<valid>20</valid>"+
                    "<filename>0411AA03_2783f008e2a84046840d5b71713cf04a_f77ee4e92f204b4c8475f996aec4f2a2_SrchFile</filename>"+
                    "<filesvr>http://10.1.91.11:8080/FileServer/UpLoadFileAct.ebf</filesvr>"+
                    "<extrapara>"+
                        "<SearchType>0</SearchType>"+
                        "<FilePath>C:\\log|C:\\log|D:\\temp</FilePath>"+
                        "<Wildcard>*.txt|com*.jou|2011*.log</Wildcard>"+
                        "<CmpType>0</CmpType>"+
                        "<SearchRangeBegin>2011-09-01 12:01:01</SearchRangeBegin>"+
                        "<SearchRangeEnd>2011-09-21 12:01:01</SearchRangeEnd>"+
                        "<IncludeSubDir>Y</IncludeSubDir>"+
                    "</extrapara>"+
                "</disttask>"+
                "</root>";
        return xml;

    }

    @RequestMapping(value="/task/planReqAct1.ebf",method = RequestMethod.POST)
    public @ResponseBody String post(HttpServletRequest request,HttpServletResponse response) {
        String xml = "<root>"+
                "<disttaskno>2</disttaskno>"+
                "<disttask>"+
                    "<tasktype>restartAct</tasktype>"+
                    "<planid>a59a3aa2beb13bbbc2cc5cade362d0dd</planid>"+
                    "<taskid>a3b5c6d7f09c8e6abbccddee55446677</taskid>"+
                    "<termid>test0091</termid>"+
                    "<exectime>2012-7-19 9:37:15.000</exectime>"+
                    "<valid>20</valid>"+
                "</disttask>"+

                "<disttask>"+
                    "<tasktype>getTermFileAct</tasktype>"+
                    "<planid>a59a3aa2beb13bbbc2cc5cade362d0dd</planid>"+
                    "<taskid>a3b5c6d7f09c8e6abbccddee55446677</taskid>"+
                    "<termid>test0091</termid>"+
                    "<exectime>2012-7-19 9:37:15.000</exectime>"+
                    "<valid>20</valid>"+
                    "<filename>0411AA03_2783f008e2a84046840d5b71713cf04a_f77ee4e92f204b4c8475f996aec4f2a2_SrchFile</filename>"+
                    "<filesvr>http://10.1.91.11:8080/FileServer/UpLoadFileAct.ebf</filesvr>"+
                    "<extrapara>"+
                        "<SearchType>0</SearchType>"+
                        "<FilePath>C:\\log|C:\\log|D:\\temp</FilePath>"+
                        "<Wildcard>*.txt|com*.jou|2011*.log</Wildcard>"+
                        "<CmpType>0</CmpType>"+
                        "<SearchRangeBegin>2011-09-01 12:01:01</SearchRangeBegin>"+
                        "<SearchRangeEnd>2011-09-21 12:01:01</SearchRangeEnd>"+
                        "<IncludeSubDir>Y</IncludeSubDir>"+
                    "</extrapara>"+
                "</disttask>"+
                "</root>";
        return xml;

    }



}
