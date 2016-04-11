package com.yihuacomputer.fish.web.atm;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetail;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.web.atm.format.ParamUpdateMsg;

/**
 * 参数下发
 * 
 * @author zhengnan
 *
 */

@Controller
@RequestMapping("/msg/paramUpdate")
public class ParamUpdateController {

	// private Logger logger =
	// LoggerFactory.getLogger(AutoUpdateController.class);

	@Autowired
	private IParamElementService paramElementService;

	@Autowired
	private IAppSystemService appSystemService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IParamDeviceDetailService paramDeviceDetailService;

	/**
	 * 接收参数自动更新请求
	 *
	 * @param paramUpdateMsg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap reciveMsg(@RequestBody ParamUpdateMsg msg) {
		ModelMap result = new ModelMap();
		try {
			IDevice device = deviceService.get(msg.getTerminalId());

			// 查出所有的版本数据
			List<IParamDeviceDetail> pdd = paramDeviceDetailService.getVersionTimeStampData(device.getId(), msg.getTimestamp());

			// 查出所有的应用类型
			List<IAppSystem> as = appSystemService.getBelongs();

			// 查出所有的归属和参数值
			List<IParamElement> pe = paramElementService.getValue();

			if (pdd != null) {
				for (int i = 0; i < pe.size(); i++) {
					for (int j = 0; j < as.size(); j++) {
						if(pe.get(i).getParamBelongs().getId() == as.get(j).getId()){
							String fileName = new String();
								fileName = as.get(j).getConfigName() + as.get(j).getConfigForm().getText();
						        File file = new File(FishCfg.getFishHome() + FishCfg.fileSep + "temp" + FishCfg.fileSep + "paramUpdate" + pdd.get(i).getVersionTimeStamp() + fileName);
								BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
								bw.write(pe.get(i).getParamName() + " = " + pdd.get(i).getParamValue());
							    bw.newLine();
						        bw.close();
						}
					}
				}
				for(int i = 0; i < pdd.size(); i++){
					Long maxVersionTimeStamp = pdd.get(0).getVersionTimeStamp();
					if(pdd.get(i).getVersionTimeStamp() > maxVersionTimeStamp){
						maxVersionTimeStamp = pdd.get(i).getVersionTimeStamp();
						File file = new File(FishCfg.getFishHome() + FishCfg.fileSep + "temp" + FishCfg.fileSep + "paramUpdate" + pdd.get(i).getVersionTimeStamp() + FishCfg.fileSep +"Description.ini");
						BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
						for(int j = 0; j < as.size(); j++){
								String fileName = new String();
								fileName = as.get(j).getConfigName() + as.get(j).getConfigForm().getText();
						        String Path = as.get(j).getConfigPath() + "/" + fileName;
								bw.write("[" + fileName + "]");
							    bw.newLine();
								bw.write("Name = " + as.get(j).getConfigName());
							    bw.newLine();
							    bw.write("VersionNo = " + maxVersionTimeStamp);
							    bw.newLine();
							    bw.write("Path = " + Path);
							    bw.newLine();
							    bw.write("Restart = ");
							    bw.newLine();
						        bw.close();
						}
					}
				}
			}
			for (int i = 0; i < pe.size(); i++) {
				for (int j = 0; j < as.size(); j++) {
					String fileName = new String();
					fileName = as.get(j).getConfigName() + as.get(j).getConfigForm().getText();
					File fileConf = new File(FishCfg.getFishHome() + FishCfg.fileSep + "temp" + FishCfg.fileSep + "paramUpdate" + pdd.get(i).getVersionTimeStamp() + FishCfg.fileSep + fileName); 
					File fileDescription = new File(FishCfg.getFishHome() + FishCfg.fileSep + "temp" + FishCfg.fileSep + "paramUpdate" + pdd.get(i).getVersionTimeStamp() + FishCfg.fileSep + "Description.ini"); 
					List<File> files = new ArrayList<File>();
					files.add(fileConf);
					files.add(fileDescription);
					File file = new File(FishCfg.getFishHome() + FishCfg.fileSep + "temp" + FishCfg.fileSep + "paramUpdate" + pdd.get(i).getVersionTimeStamp() + FishCfg.fileSep + "param.zip");
		            if (!file.exists()){   
		                file.createNewFile();   
		            }
		            FileOutputStream fous = new FileOutputStream(file); 
		            ZipOutputStream zipOut = new ZipOutputStream(fous);
		            zipFile(files, zipOut);
		            zipOut.close();
		            fous.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
    public static void zipFile(List<File> files,ZipOutputStream outputStream) {
    	int size = files.size();
    	for(int i = 0; i < size; i++) {
    		File file = (File) files.get(i);
    		zipFile(file, outputStream);
    	}
    }
    
    public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据   
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象   
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
