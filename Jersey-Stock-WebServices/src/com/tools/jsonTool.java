package com.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class jsonTool {

	// ת����������ҳ�Ϻÿ���ʾ��json��ʽ
	public String prettyJSON(Object object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(object);

		return jsonOutput;
	}

}
