package com.yihuacomputer.fish.web.machine.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 型号与模板的关联信息
 * 
 * @author pengwenchao
 *
 */
public class AtmTypeLinkModuleForm {

    public Map<String, List<String>> typeLink = new HashMap<String, List<String>>();

    public AtmTypeLinkModuleForm() {

    }

    public Map<String, List<String>> getTypeLink() {
        return typeLink;
    }

    public void setTypeLink(Map<String, List<String>> typeLink) {
        this.typeLink = typeLink;
    }

}
