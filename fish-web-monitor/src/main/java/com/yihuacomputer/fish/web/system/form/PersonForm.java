package com.yihuacomputer.fish.web.system.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.person.Gender;
import com.yihuacomputer.fish.api.person.IPerson;

/**
 * 人员基本信息实体：
 *
 * @author huxiaobao
 *
 */
public class PersonForm {
    private String guid;

    private String code;

    private String name;;

    private String birthday;

    private String email;

    private Gender gender;

    private String mobile;

    private String phone;

    private String type;

    private String organizationName;

    private String organizationId;

    private String state;

    private String jobNum;

    private String remark;

    /**
     * 人员岗位编号
     */
    private String personJobCode;

    /**
     * 人员岗位名称
     */
    private String personJobName;

    /**
     * 核心柜员号（江苏农信）
     */
    private String reserve1;
    /**
     * 预留字段
     */
    private String reserve2;
    private String reserve3;

    public PersonForm() {
    }

    public PersonForm(IPerson person) {
        guid = person.getGuid();
        name = person.getName();
        if (person.getEmail() != null) {
            email = person.getEmail().trim();
        }
        code = person.getCode();
        Date date = person.getBirthday();
        if (date != null) {
            birthday = new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
        else {
            birthday = "";
        }
        gender = person.getGender();
        if (person.getMobile() != null) {
        	mobile = person.getMobile().trim();
        }
        if (person.getPhone() != null) {
        	phone = person.getPhone().trim();
        }
        if (person.getType() != null) {
            type = Integer.toString(person.getType().getId());
        }
        if (person.getOrganization() != null) {
            organizationId = person.getOrganization().getGuid();
            organizationName = person.getOrganization().getName();
        }
        if (person.getState() != null) {
            state = Integer.toString(person.getState().getId());
        }
        jobNum = person.getJobNum();
        remark = person.getRemark();

        if (person.getPersonJob() != null) {
            personJobCode = person.getPersonJob().getCode();
            personJobName = person.getPersonJob().getName();
        }
        reserve1 = person.getReserve1();
        reserve2 = person.getReserve2();
        reserve3 = person.getReserve3();
    }

    /**
     *
     * 方法描述 : 转换
     *
     * @param resources
     * @return List
     */

    public static List<PersonForm> convert(List<IPerson> resources) {
        List<PersonForm> result = new ArrayList<PersonForm>();
        for (IPerson resource : resources) {
            result.add(new PersonForm(resource));
        }
        return result;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static Date parsedate(String text, String format) {
        try {
            return new SimpleDateFormat(format).parse(text);
        }
        catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    public String getPersonJobCode() {
        return personJobCode;
    }

    public void setPersonJobCode(String personJobCode) {
        this.personJobCode = personJobCode;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public String getPersonJobName() {
        return personJobName;
    }

    public void setPersonJobName(String personJobName) {
        this.personJobName = personJobName;
    }

}
