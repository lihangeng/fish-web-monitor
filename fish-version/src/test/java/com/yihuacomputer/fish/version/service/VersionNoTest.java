package com.yihuacomputer.fish.version.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.yihuacomputer.fish.api.version.VersionNo;

public class VersionNoTest {
    //@Test
    public void test(){
        assertTrue(bigger("19.4","2.1"));
        assertTrue(bigger("19.4","0.7.1"));
        assertTrue(bigger("19.4-v","2.1"));
        assertTrue(bigger("19.4","2.1.3"));
        assertFalse(bigger("2.2","19"));
        assertFalse(bigger("1.01","1.1")); /*==*/
    }
    
    private boolean bigger(String one,String two){
        return new VersionNo(one).isBiggerThan(new VersionNo(two));
    }
}
