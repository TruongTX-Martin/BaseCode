package me.com.basecode.base.network;


import org.json.JSONArray;
import org.json.JSONObject;

import me.com.basecode.config.Constant;

/**
 * Created by xuan on 19/02/2017.
 */

public class CoreResponse {

    protected JSONArray arrayData;
    protected String message = Constant.EMPTY;
    protected String status = Constant.EMPTY;
    protected boolean isSuccess;
    protected JSONObject mJson;


    public boolean parser(String json) {
        try {
            mJson = new JSONObject(json);
            if (mJson.has("meta")) {
                JSONObject objectMeta = mJson.getJSONObject("meta");
                if (objectMeta.has("message")) {
                    message = objectMeta.getString("message");
                }
                if (objectMeta.has("statusCode")) {
                    status = objectMeta.getString("statusCode");
                }
                if (objectMeta.has("isSuccess")) {
                    isSuccess = objectMeta.getBoolean("isSuccess");
                }
            }
            try {
                if (mJson.has("data")) {
                    arrayData = mJson.getJSONArray("data");
                }
            } catch (Exception e) {
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    public String getStatus() {
        return status;
    }

    public JSONObject getJsonData() {
        return mJson;
    }

    public JSONArray getArrayData() {
        return arrayData;
    }
}
