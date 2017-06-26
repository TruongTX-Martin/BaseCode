package me.com.basecode.base.network;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.HashMap;
import java.util.Map;
import me.com.basecode.AppController;
import me.com.basecode.R;
import me.com.basecode.base.delegate.NetWorkDelegate;
import me.com.basecode.config.Constant;
import me.com.basecode.config.Rconfig;
/**
 * Created by xuan on 19/02/2017.
 */

public class CoreRequest {

    protected NetWorkDelegate delegate;
    private Map<String, Object> hashMap = new HashMap<>();
    private int METHOD = Constant.POST;
    private Map<String, String> mapHeader = new HashMap<>();

    public CoreRequest(NetWorkDelegate netWorkDelegate) {
        this.delegate = netWorkDelegate;
    }

    public void request(String url) {
        prepareRequest();
        requestData(url);
    }

    public void request(String url,boolean isHideKeyboard) {
        prepareRequest();
        requestData(url);
    }

    public void setHashMap(Map<String, Object> hashMap) {
        this.hashMap = hashMap;
    }

    private void requestData(String url) {
        String URL = Constant.URL_BASE + url;
        CustomRequest request = new CustomRequest(METHOD, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        excuteResult(response, true);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    if (error instanceof NoConnectionError) {
                        excuteResult(Rconfig.getInstance().getStringLanguage(R.string.noticeInternet), false);
                    } else {
                        excuteResult(error.getMessage(), false);
                    }


                } catch (Exception e) {
                }
            }
        });
        if (hashMap.size() > 0) {
            for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                request.setParam(entry.getKey(), entry.getValue().toString());
            }
        }
        request.setMapHeader(mapHeader);
        request.setRetryPolicy(new DefaultRetryPolicy(3 * DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 0));
        request.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(request, "hiajson");
    }

    public void setMapHeader(Map<String, String> mapHeader) {
        this.mapHeader = mapHeader;
    }

    private void prepareRequest() {

    }

    private void excuteResult(String result, boolean isSuccess) {
        CoreResponse coreResponse = new CoreResponse();
        if (coreResponse.parser(result)) {
            delegate.callBack(coreResponse, coreResponse.isSuccess);
        } else {
            coreResponse.setMessage(Rconfig.getInstance().getStringLanguage(R.string.strErrorConnection));
            delegate.callBack(coreResponse, false);
        }

    }

    public void setMethod(int METHOD) {
        this.METHOD = METHOD;
    }
}
