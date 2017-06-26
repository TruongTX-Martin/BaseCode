package me.com.basecode.base.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseEntity {
	protected JSONObject mJSON;

	public void setJSONObject(JSONObject json) {
		this.mJSON = json;
	}

	public JSONObject getJSONObject() {
		return mJSON;
	}

	public String getData(String key) {
		String content = "";
		if (mJSON != null && mJSON.has(key)) {
			try {
				content = mJSON.getString(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
	public  int getDataInt(String key){
		int content = 0;
		if (mJSON != null && mJSON.has(key)) {
			try {
				content = mJSON.getInt(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
	public  boolean getDataBoolean(String key){
		boolean value = false;
		if (mJSON != null && mJSON.has(key)) {
			try {
				value = mJSON.getBoolean(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	public long getDataLong(String key){
		Long content = 0L;
		if (mJSON != null && mJSON.has(key)) {
			try {
				content = mJSON.getLong(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
}
