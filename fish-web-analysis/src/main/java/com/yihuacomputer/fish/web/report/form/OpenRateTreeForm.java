package com.yihuacomputer.fish.web.report.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.person.IOrganization;

public class OpenRateTreeForm {

    private String id;

    private String cls;

    private String text;

    private boolean leaf = true;

    public OpenRateTreeForm() {
    }

    public OpenRateTreeForm(IOrganization organization) {
        id = organization.getGuid();
        cls = organization.getCode();
        text = organization.getName();
        leaf = !(organization.listChildren().iterator().hasNext());
    }
    
    public static List<OpenRateTreeForm> convert(List<IOrganization> target) {
        List<OpenRateTreeForm> result = new ArrayList<OpenRateTreeForm>();

        for (IOrganization organization : target) {
            result.add(new OpenRateTreeForm(organization));
        }
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCls() {
        return cls;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

}
