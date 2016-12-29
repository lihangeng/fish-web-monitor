package com.yihuacomputer.fish.web.mock;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;

/**
 * @author YiHua
 *
 */
public class MonitorDataLoader {
    @Autowired
    private ITransactionService transService;

    /**
     * 初始化交易
     */
    public void init() {
        initTrans();
    }

    /**
     * 初始化交易
     */
    private void initTrans() {
        ITransaction tran = null;
        for (int i = 1; i <= 40; i++) {
            tran = transService.make();
            tran.setTerminalId("A#000" + i);
            tran.setAmt(i * 1000d);
            tran.setCreditAccount("0000 0000 0000 " + i);
            tran.setCurrency(i % 2 == 0 ? "美元" : "人民币");
            tran.setDateTime(new Date());
            tran.setDebitAccount(i + " 0000 0000 0000");
            tran.setHostRet("0000");
            tran.setLocalRet("0000");
            tran.setTransCode("0000" + i);
            tran.setTransId(String.valueOf(i));
            transService.save(tran);
        }
    }
}
