package org.xwb.springcloud.entities;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@SuppressWarnings("DuplicatedCode")
public class HttpClientUtil {


    /**
     * 发送GET请求
     *
     * @param url 目的地址
     *            请求参数，Map类型。
     *            , Map<String, String> parameters
     * @return 远程响应结果
     */
    public static String sendGet(String url) {
        StringBuilder result = new StringBuilder();
        // 读取响应输入流
        BufferedReader in = null;
        try {
            // 创建URL对象
            java.net.URL connUrl = new java.net.URL(url);

            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connUrl.openConnection();

            httpConn.setConnectTimeout(60000);
            httpConn.setReadTimeout(60000);
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String sendPost(String url, Map<String, String> parameters) {
        return sendPost(url, parameters, null);
    }


    /**
     * 发送POST请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendPost(String url, Map<String, String> parameters, Map<String, String> headers) {
        // 返回的结果
        StringBuilder result = new StringBuilder();
        // 读取响应输入流
        BufferedReader in = null;
        PrintWriter out = null;
        // 处理请求参数
        StringBuilder sb = new StringBuilder();
        // 编码之后的参数
        String params = "";
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"))
                            .append("&");
                }
                String tempParams = sb.toString();
                params = tempParams.substring(0, tempParams.length() - 1);
            }
            // 创建URL对象
            java.net.URL connUrl = new java.net.URL(url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connUrl.openConnection();
            // 设置通用属性
            httpConn.setConnectTimeout(60000);
            httpConn.setReadTimeout(60000);
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            if (null != headers) {
                headers.forEach(httpConn::setRequestProperty);
            }
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String post(String url, Map<String, String> paramsMap) throws IOException {
        // 创建HttpClient实例
        HttpClient client = HttpClientBuilder.create().build();
        // 根据URL创建HttpPost实例
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        post.setHeader("authorization", "Basic c2FiZXI6c2FiZXJfc2VjcmV0");

        // 构造post参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        paramsMap.forEach((k, v) -> {
            params.add(new BasicNameValuePair(k, v));
        });
        // 编码格式转换
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        // 传入请求体
        post.setEntity(entity);
        // 发送请求，得到响应体
        HttpResponse response = client.execute(post);
        // 判断是否正常返回
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("返回异常状态值" + response.getStatusLine().getStatusCode());
        }
        // 解析数据
        HttpEntity resEntity = response.getEntity();
        return EntityUtils.toString(resEntity);
    }


    /**
     * @param url
     * @param json
     * @return 通过HTTPClient发送post形式的json数据
     */
    public static String HttpPostWithJson(String url, String json) {
        // 返回值
        String returnValue = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();
            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);
            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            //第四步：发送HttpPost请求，获取返回值 调接口获取返回值时，必须用此方法
            returnValue = httpClient.execute(httpPost, responseHandler);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭资源
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

}
