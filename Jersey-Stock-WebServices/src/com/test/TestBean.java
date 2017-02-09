package com.test;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestBean {
	public String a;
	public int b;
	public long c;

	public TestBean() {
	}

	public TestBean(String a, int b, long c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public String getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public long getC() {
		return c;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TestBean that = (TestBean) o;
		if (b != that.b)
			return false;
		if (c != that.c)
			return false;
		if (a != null ? !a.equals(that.a) : that.a != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = a != null ? a.hashCode() : 0;
		result = 31 * result + b;
		result = 31 * result + (int) (c ^ (c >>> 32));
		return result;
	}

}