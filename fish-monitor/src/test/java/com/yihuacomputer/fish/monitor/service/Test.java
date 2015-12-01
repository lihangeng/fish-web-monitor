package com.yihuacomputer.fish.monitor.service;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.monitor.entity.xfs.status.XfsStatus;


public class Test {

	public static void main(String[] args) {
		IXfsStatus status = new XfsStatus();
		String json = JsonUtils.toJson(status);
		System.out.println(json);
		XfsStatus x = JsonUtils.fromJson(json, XfsStatus.class);
		System.out.println(x.getBoxStatus().getText());
		B b = new B();
		b.setC("c");
		
		A a = new A();
		a.setA("a");
		a.setBb(b);
		
		System.out.println(JsonUtils.toJson(a));

	}
}
class A {
	private String a;
	private B bb;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public B getBb() {
		return bb;
	}
	public void setBb(B bb) {
		this.bb = bb;
	}
}
class B{
	private String c;

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}
	
}
