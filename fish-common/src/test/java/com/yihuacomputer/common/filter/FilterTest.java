/**
 *
 */
package com.yihuacomputer.common.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;

/**
 * @author dell
 *
 */
public class FilterTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testFilterEquals(){
		IFilter a = new Filter();
		a.addFilterEntry(FilterFactory.eq("aa", "aa"));
		a.addFilterEntry(FilterFactory.gt("bb",123));
		a.eq("cc", 3434);

		assertEquals(a.getEntrySize(),3);

		IFilter b = new Filter();
		b.addFilterEntry(FilterFactory.eq("aa", "aa"));
		b.gt("bb",123).eq("cc", 123);
		assertEquals(b.getEntrySize(),3);

		assertFalse(a.equals(b));
	}

	@Test
	public void testFilterEntryEquals(){
		IFilterEntry a = FilterFactory.eq("aa", "aa");
		IFilterEntry b = FilterFactory.eq("aa", "aa");
		assertTrue(a.equals(b));
	}
}
