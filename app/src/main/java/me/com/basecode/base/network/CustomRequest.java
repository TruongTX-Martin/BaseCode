package me.com.basecode.base.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuan on 20/02/2017.
 */

public class CustomRequest extends StringRequest{

    private Map<String, String> mParam = new HashMap<String, String>();
    private String url ;
    private Map<String, String> mHeaders = new HashMap<>();
    private Map<String, String> mapHeader = new HashMap<>();
    public CustomRequest(int method, String url, Response.Listener<String> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.url = url;
    }
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        mHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        if(mapHeader.size() > 0){
            for (Map.Entry<String,String> entry : mapHeader.entrySet()){
                mHeaders.put(entry.getKey(),entry.getValue());
            }
        }
        return mHeaders;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParam;
    }
    public void setParam(String name, String param){
        mParam.put(name,param);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded";
    }

    public void setMapHeader(Map<String, String> mapHeader) {
        this.mapHeader = mapHeader;
    }
}
