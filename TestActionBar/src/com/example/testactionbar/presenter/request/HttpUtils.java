package com.example.testactionbar.presenter.request;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpUtils
{
    private static AsyncHttpClient client;

    private HttpUtils()
    {
        if (client == null)
        {
            client = new AsyncHttpClient();
            client.setTimeout(11000); // 设置链接超时，如果不设置，默认为10s
        }
    }

    private static HttpUtils httpUtils;

    public static HttpUtils getHttpUtils()
    {
        if (httpUtils == null)
        {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    /**
     * 返回string
     * 
     * @param urlString
     * @param res
     */
    public void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
    {
        client.get(urlString, res);
    }

    /**
     * 返回string
     * 
     * @param urlString
     * @param params
     * @param res
     */
    public void get(String urlString, RequestParams params, AsyncHttpResponseHandler res)
    {
        client.get(urlString, params, res);
    }

    /**
     * 返回 Json
     * 
     * @param urlString
     * @param res
     */
    public void get(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
    {
        client.get(urlString, res);
    }

    /**
     * 返回 Json
     * 
     * @param urlString
     * @param params
     * @param res
     */
    public void get(String urlString, RequestParams params, JsonHttpResponseHandler res) // 带参数，获取json对象或者数组
    {
        client.get(urlString, params, res);
    }

    /**
     * 下载数据
     * 
     * @param uString
     * @param bHandler
     */
    public void get(String uString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
    {
        client.get(uString, bHandler);
    }
}
