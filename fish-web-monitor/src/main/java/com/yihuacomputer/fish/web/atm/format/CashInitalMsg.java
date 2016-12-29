package com.yihuacomputer.fish.web.atm.format;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.business.IBoxInitDetail;
import com.yihuacomputer.fish.api.monitor.business.ICashInit;
import com.yihuacomputer.fish.monitor.entity.business.BoxInitDetail;

/**
 * C端应用加钞信息
 *
 * @author YiHua
 *
 */
public class CashInitalMsg {
    private String termId;

    private String date;

    private String uuId;

    private long amt;

    private List<BoxInitDetail> boxDetail;

    public CashInitalMsg() {
    }

    /**
     * @param cashInit
     */
    public CashInitalMsg(ICashInit cashInit) {
        // 初始化加钞信息
        setTermId(cashInit.getTerminalId());
        setDate(cashInit.getDate());
        setUuId(cashInit.getUuId());
        setAmt(cashInit.getAmt());

        List<BoxInitDetail> detailList = new ArrayList<BoxInitDetail>();
        long counter=0;
        for (IBoxInitDetail detail : cashInit.getBoxDetail()) {
        	BoxInitDetail bid = new BoxInitDetail(detail);
        	bid.setId(++counter);
            detailList.add(bid);
        }
        setBoxDetail(detailList);
    }

    /**
     * @param cashInitList
     * @return
     */
    public static List<CashInitalMsg> convert(List<ICashInit> cashInitList) {
        List<CashInitalMsg> result = new ArrayList<CashInitalMsg>();
        for (ICashInit init : cashInitList) {
            result.add(new CashInitalMsg(init));
        }
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public long getAmt() {
        return amt;
    }

    public void setAmt(long amt) {
        this.amt = amt;
    }

    public List<BoxInitDetail> getBoxDetail() {
        return boxDetail;
    }

    public void setBoxDetail(List<BoxInitDetail> boxDetail) {
        this.boxDetail = boxDetail;
    }

}
