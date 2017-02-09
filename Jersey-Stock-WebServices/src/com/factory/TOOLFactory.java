package com.factory;

import com.tools.*;

public class TOOLFactory {
	public static stringTool getstringToolInstance() {
		return new stringTool();
	}
	public static networkTool getnetworkToolInstance() {
		return new networkTool();
	}
	public static jsonTool getjsonToolInstance() {
		return new jsonTool();
	}
}
