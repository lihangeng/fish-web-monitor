/**
 *
 */
package com.yihuacomputer.fish.api.advert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.common.util.ZipUtils;
import com.yihuacomputer.fish.api.advert.util.AdvertTypeConversionService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.version.VersionCfg;

/**
 * @author xuxiang
 *
 */
public class AbstractAdvertZipGenerator implements IAdvertZipGenerator {
    @Override
    public void generateZipFile(IAdvert advert) {
        getAdvertConfigInfoAndSaveToFile(advert);
        beforeZip(advert);
        zip(advert);
    }

    protected void zip(IAdvert advert) {
        ZipUtils.zip(getAdvertSourcePath(advert), getZipFileName(advert), "GBK");
    }

    protected void beforeZip(IAdvert advert) {
        genenateMetaFile(advert);// 二代应用需要
    }

    protected void getAdvertConfigInfoAndSaveToFile(IAdvert advert) {
        String configInfo = advert.getAdvertConfig();

        String iniFileName = "config.ini";
        if (advert.getLevel() != null && !OrganizationLevel.TOTAL.equals(advert.getLevel())) {
            iniFileName = "config_b.ini";
        }

        IOUtils.writeStringToFile(
                getAdvertSourcePath(advert) + File.separator
                        + AdvertTypeConversionService.convert(advert.getAdvertType()) + File.separator + iniFileName,
                configInfo);
    }

    /**
     * 得到广告的压缩文件夹的根路径
     *
     * @param advert
     * @return
     */
    private String getAdvertSourcePath(IAdvert advert) {
        return VersionCfg.getAdvertDir() + File.separator + advert.getId();
    }

    /**
     * 得到广告压缩包文件名
     *
     * @param advert
     * @return
     */
    private String getZipFileName(IAdvert advert) {
        return VersionCfg.getVersionDir() + File.separator + "advert" + File.separator + "advert_" + advert.getId()
                + ".zip";
    }

    protected String getConfigFileBasePath(IAdvert advert) {
        return getAdvertSourcePath(advert) + File.separator
                + AdvertTypeConversionService.convert(advert.getAdvertType());
    }

    private void genenateMetaFile(IAdvert advert) {
        File file = new File(getAdvertSourcePath(advert) + File.separator + "META-INF");
        if (!file.exists()) {
            file.mkdirs();
        }

        File meta = new File(file.getAbsolutePath() + File.separator + "MANIFEST.MF");
        if (!meta.exists()) {
            try {
                meta.createNewFile();
            } catch (IOException e) {
                throw new AppException("创建meta文件失败");
            }
        }

        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(meta);
            bw = new BufferedWriter(fw);
            bw.write("Manifest-Version: 1.0");
            bw.newLine();
            bw.write("Gump-Scheduler: IMMEDIATE");
            bw.newLine();
            bw.write("Gump-Type: ADVERTISE");
            bw.newLine();
            bw.write("Gump-AppName: YHATMC-AD");
            bw.newLine();
            bw.write("Built-By: yihua");
            bw.newLine();
            bw.write("Build-Jdk: 1.6.0_25");
            bw.newLine();
            bw.write("Gump-Version: 0001");
            bw.newLine();
            bw.write("Created-By: monitor");
            bw.newLine();
            bw.write("Archiver-Version: Plexus Archiver");
            bw.newLine();
            bw.write("Gump-InstallDate: 2012-02-01 00:00:00");
            bw.newLine();
            bw.write("Gump-InstallEndDate: 2012-02-01 00:00:00");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if (bw != null) {
                try {
                	bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
