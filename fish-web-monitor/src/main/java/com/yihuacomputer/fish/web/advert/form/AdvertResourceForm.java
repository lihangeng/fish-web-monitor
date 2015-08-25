package com.yihuacomputer.fish.web.advert.form;

import java.io.File;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.IAdvertResource;
import com.yihuacomputer.fish.api.advert.Screen;
import com.yihuacomputer.fish.api.advert.util.AdvertTypeConversionService;
import com.yihuacomputer.fish.api.version.VersionCfg;

public class AdvertResourceForm {
    private long id;

    private long advertId;

    private int playTime;

    private String beginDate;

    private String endDate;

    private String beginTime;

    private String endTime;

    private String fileSize;

    private String content;

    private Screen screen;

    public AdvertResourceForm() {
    }

    public AdvertResourceForm(IAdvertResource advRes) {
        this.id = advRes.getId();
        this.advertId = advRes.getAdvert().getId();
        this.playTime = advRes.getPlayTime();
        this.beginDate = advRes.getBeginDate() == null ? "" : DateUtils.getDate(advRes.getBeginDate());
        this.endDate = advRes.getEndDate() == null ? "" : DateUtils.getDate(advRes.getEndDate());
        this.beginTime = advRes.getBeginTime();
        this.endTime = advRes.getEndTime();
        this.content = advRes.getContent();
        this.fileSize = getResourceFileSize(advRes.getAdvert().getAdvertType(),advRes.getAdvert().getId());
        this.screen = advRes.getScreen();
    }

    private String getResourceFileSize(AdvertType type,long id) {
        if (AdvertType.isWords(type)) {
            return "0";
        }
        else {
            String fileName = VersionCfg.getAdvertDir() + File.separator + id + File.separator +
            		AdvertTypeConversionService.convert(type)  + File.separator + this.content;
            File file = new File(fileName);
            long size = file.length();
            if(size < 1024){
                return size + " 字节";
            }else{
                return size/1024 + " KB";
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAdvertId() {
        return advertId;
    }

    public void setAdvertId(long advertId) {
        this.advertId = advertId;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

}
