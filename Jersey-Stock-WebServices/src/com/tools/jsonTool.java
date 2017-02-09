package com.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class jsonTool {

	// 转换可以在网页上好看显示的json格式
	public String prettyJSON(Object object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(object);

		return jsonOutput;
	}

}
