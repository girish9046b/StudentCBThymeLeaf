package com.student.app.couchbase.model;

import com.google.gson.Gson;

public class GsonConvertable {
	protected static Gson _gson = new Gson();
	/**
	 * returns class properties in json format
	 * @return 
	 */
	public final String getValue() {
		String json = _gson.toJson(this);
		return json;
	};
}
