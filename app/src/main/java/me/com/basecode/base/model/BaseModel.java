package me.com.basecode.base.model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.com.basecode.base.delegate.ModelDelegate;
import me.com.basecode.base.delegate.NetWorkDelegate;
import me.com.basecode.base.network.CoreRequest;
import me.com.basecode.base.network.CoreResponse;
import me.com.basecode.config.Constant;

/**
 * Created by xuan on 19/02/2017.
 */

public class BaseModel {

    private NetWorkDelegate netWorkDelegate;
    private CoreRequest coreRequest;
    private ModelDelegate modelDelegate;
    private Map<String,Object> hashMap ;
    private int METHOD = Constant.POST;
    protected String url = "";
    private Map<String,String> mapHeader = new HashMap<>();

    public BaseModel(){
        hashMap = new HashMap<>();
    }


    public void addParam(String tag, String value){
        hashMap.put(tag,value);
    }
    public void addParam(String tag,int value){
        hashMap.put(tag,value);
    }
    public void addParam(String tag, JSONObject value){
        hashMap.put(tag,value);
    }
    public void addParam(String tag, JSONArray value){
        hashMap.put(tag,value);
    }

    public void setDelegate(ModelDelegate modelDelegate) {
        this.modelDelegate = modelDelegate;
    }


    public void setMethod(int METHOD) {
        this.METHOD = METHOD;
    }

    private boolean isHideKeyboad = true;

    public void setHideKeyboad(boolean hideKeyboad) {
        isHideKeyboad = hideKeyboad;
    }

    public void request(){
        //prepare parameter
        this.initRequest();
        //request data
        coreRequest.setMethod(METHOD);
        coreRequest.setHashMap(hashMap);
        coreRequest.setMapHeader(mapHeader);
        if(!isHideKeyboad){
            coreRequest.request(url,isHideKeyboad);
        }else{
            coreRequest.request(url);
        }
    }

    private void initRequest(){
        setUrl();
        this.initDelegate();
        coreRequest = new CoreRequest(netWorkDelegate);
    }

    private  void initDelegate(){
        netWorkDelegate = new NetWorkDelegate() {
            @Override
            public void callBack(CoreResponse coreResponse, boolean isSuccess) {
                modelDelegate.callBack(coreResponse,isSuccess);
            }
        };
    }

    public void setMapHeader(Map<String, String> mapHeader) {
        this.mapHeader = mapHeader;
    }


    public void setUrl(){

    }

    public void setUrl(String urlExtension){

    }

}