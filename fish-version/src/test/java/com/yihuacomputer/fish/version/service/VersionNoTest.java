package com.yihuacomputer.fish.version.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.yihuacomputer.common.util.StringUtils;
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
    
    @Test
    @Ignore
    public void testVersionNO(){
    	System.out.println(generateVersionStr("1.0.0"));
    	//00000001 00000000 00000000 000000-1
    	//versionNo.getRevision()的值为-1，所以VersionNo的实现由瑕疵;
    }
    
    private String generateVersionStr(String version){
    	VersionNo versionNo = new VersionNo(version);
		StringBuffer versionNoSb = new StringBuffer();
		versionNoSb.append(StringUtils.preZeroStr(String.valueOf(versionNo.getMajor()), 8)).
		append(StringUtils.preZeroStr(String.valueOf(versionNo.getMinor()), 8)).
		append(StringUtils.preZeroStr(String.valueOf(versionNo.getIncremental()), 8)).
		append(StringUtils.preZeroStr(String.valueOf(versionNo.getRevision()), 8));
		
		return versionNoSb.toString();
    }
}
