package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.person.IPersonJob;

/**
 * @author YiHua
 *
 */
public class PersonJobForm {
    /**
     * ID,自增长
     */
    private long id;

    /**
     * 岗位编号(唯一)
     */
    private String code;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    public PersonJobForm() {

    }

    /**
     * @param personJob
     */
    public PersonJobForm(IPersonJob personJob) {
        id = personJob.getId();
        code = personJob.getCode();
        name = personJob.getName();
        remark = personJob.getRemark();
    }

    /**
     * 方法描述 : 转换
     *
     * @param listPersonJob
     * @return
     */
    public static List<PersonJobForm> convert(List<IPersonJob> listPersonJob) {
        List<PersonJobForm> result = new ArrayList<PersonJobForm>();
        for (IPersonJob personJob : listPersonJob) {
            result.add(new PersonJobForm(personJob));
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
