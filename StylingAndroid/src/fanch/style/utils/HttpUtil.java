package fanch.style.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

public class HttpUtil
{
    /**
     * TestGet
     * 
     * @param path
     * @throws Exception
     */
    public void testGet(String path) throws Exception
    {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(path + "/TestServlet?id=1001&name=john&age=60");
        HttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            InputStream is = response.getEntity().getContent();
            String result = inStream2String(is);
            Assert.assertEquals(result, "GET_SUCCESS");
        }
    }

    /**
     * TestPost
     * 
     * @param path
     * @throws Exception
     */
    public void testPost(String path) throws Exception
    {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(path + "/TestServlet");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", "1001"));
        params.add(new BasicNameValuePair("name", "john"));
        params.add(new BasicNameValuePair("age", "60"));
        HttpEntity formEntity = new UrlEncodedFormEntity(params);
        post.setEntity(formEntity);

        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            InputStream is = response.getEntity().getContent();
            String result = inStream2String(is);
            Assert.assertEquals(result, "POST_SUCCESS");
        }
    }

    /**
     * TestUpLoad
     * 
     * @param context
     * @param path
     * @throws Exception
     */
    public void testUpload(Context context, String path) throws Exception
    {
        InputStream is = context.getAssets().open("books.xml");
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(path + "/UploadServlet");

        InputStreamBody isb = new InputStreamBody(is, "books.xml");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart("file", isb);
        builder.addTextBody("desc", "this is description.");
        HttpEntity httpEntity = builder.build();
        post.setEntity(httpEntity);

        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            is = response.getEntity().getContent();
            String result = inStream2String(is);
            Assert.assertEquals(result, "UPLOAD_SUCCESS");
        }
    }

    // 将输入流转换成字符串
    private String inStream2String(InputStream is) throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = is.read(buf)) != -1)
        {
            baos.write(buf, 0, len);
        }
        return new String(baos.toByteArray());
    }
}
