package com.yihuacomputer.fish.web.monitor.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.business.IBlackListCard;

public class BlackListCardForm {
    private long id;

    private String cardNo;

    private String userName;
    
    private String organization;

    private String addDate;

    public BlackListCardForm() {
    }

    public BlackListCardForm(IBlackListCard ann) {
        id = ann.getId();
        Date date = ann.getAddDate();
        if (date != null) {
            addDate = DateUtils.getTimestamp(date);
        }
        else {
            addDate = "";
        }
        this.cardNo = ann.getCardNo();
        this.userName = ann.getUserName();
        this.organization = ann.getOrganization();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddDate() {
        return this.addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public static Date parsedate(String text, String format) {
        try {
            return new SimpleDateFormat(format).parse(text);
        }
        catch (ParseException e) {
            throw new RuntimeException(String.format("[%s]", e));
        }

    }

    /**
     * 
     * 方法描述 : 转换
     * 
     * @param resources
     * @return List
     */
    public static List<BlackListCardForm> convert(List<IBlackListCard> resources) {
        List<BlackListCardForm> result = new ArrayList<BlackListCardForm>();
        for (IBlackListCard resource : resources) {
            result.add(new BlackListCardForm(resource));
        }
        return result;
    }

}
