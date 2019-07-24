package com.example.httpdemo.http.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpURLConnectionUtil {
    public static String BASE_URL = "http://39.106.90.146:8080/MyServlet/";

    public static String getContextByHttp(String urlStr) {
        //新建一个可变字符串sb用来存储账号是否存在结果
        StringBuilder sb = new StringBuilder();
        try {
            //将服务器程序的路径传入url类型的对象中
            URL url = new URL(urlStr);
            // 将路径传入，建立HttpURLConnection连接对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置连接的方式是post：post与get有什么区别
            connection.setRequestMethod("POST");
            //允许读取和连接最长时间为5秒
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            //允许输入输出
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //如果连接上服务器端，客户端程序使用BufferedReader读取connection传来的数据
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp;
                //如果传入的数据不为空，则可变字符串添加该数据
                while ((temp = reader.readLine()) != null) {
                    sb.append(temp);
                    break;
                }
                reader.close();
            } else {
                return "connection error:" + connection.getResponseCode();
            }
            connection.disconnect();
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    private static String getStringFromOutput(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        /**
         *  entrySet是 键-值 对的集合，Set里面的类型是Map.Entry,这里的目的是遍历map
         */
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (isFirst)
                isFirst = false;
            else
                sb.append("&");
            sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return sb.toString();
    }
}
