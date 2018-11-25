package crawler.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 1.this class is used to get a html by HttpClient
 */
public class HelloHttp {
    public static void main(String[] args) {

        test4();

    }

    public static void test4() {
        CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpClient实例
        HttpGet httpGet = new HttpGet("http://www.csdn.net"); //创建httpGet实例
        CloseableHttpResponse response = null;//指向http get请求
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();//获取返回实体
        System.out.println(entity.isRepeatable());//

        try {
            System.out.println("1:"+EntityUtils.toString(entity).substring(0,10));
            System.out.println("2:"+EntityUtils.toString(entity).substring(0,10));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void test3() throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpClient实例
        HttpGet httpGet = new HttpGet("https://www.cnblogs.com/"); //创建httpGet实例

        HttpHost proxy = new HttpHost("114.235.22.147", 9000);
        RequestConfig config = RequestConfig
                .custom()
                .setProxy(proxy)
                .setConnectTimeout(10000)//连接超时
                .setSocketTimeout(10000)//读取超时
                .build();

        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:63.0) Gecko/20100101 Firefox/63.0");
        httpGet.setConfig(config);
        CloseableHttpResponse response = httpClient.execute(httpGet);//指向http get请求

        HttpEntity entity = response.getEntity();//获取返回实体

        //System.out.println("网页内容："+ EntityUtils.toString(entity,"utf-8"));//获取网页内容
        System.out.println("Content-Type :"+entity.getContentType());//获取内容类型
        System.out.println("Status : "+response.getStatusLine());//判断响应状态

        String content = EntityUtils.toString(entity);

        //way 1:
        Document docment = Jsoup.parse(content);
        Elements elements = docment.getElementsByTag("title");
        Element speciEle = elements.get(0);
        String title = speciEle.text();
        System.out.println("网页标题是："+title);


        //way 2
        Element site_nav_top = docment.getElementById("site_nav_top");
        String slogan = site_nav_top.text();
        System.out.println("slogan :" + slogan);

        response.close();
        httpClient.close();
    }

    public static void test2() throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpClient实例
        HttpGet httpGet = new HttpGet("http://www.tuicool.com/"); //创建httpGet实例

        HttpHost proxy = new HttpHost("61.135.155.82", 443);
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:63.0) Gecko/20100101 Firefox/63.0");

        CloseableHttpResponse response = httpClient.execute(httpGet);//指向http get请求

        HttpEntity entity = response.getEntity();//获取返回实体

        //System.out.println("网页内容："+ EntityUtils.toString(entity,"utf-8"));//获取网页内容
        System.out.println("Content-Type :"+entity.getContentType());//获取内容类型
        System.out.println("Status : "+response.getStatusLine());//判断响应状态

        response.close();
        httpClient.close();
    }

    public static void test1() throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpClient实例
        HttpGet httpGet = new HttpGet("http://www.tuicool.com/dsfsd"); //创建httpGet实例

        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:63.0) Gecko/20100101 Firefox/63.0");

        CloseableHttpResponse response = httpClient.execute(httpGet);//指向http get请求

        HttpEntity entity = response.getEntity();//获取返回实体


        System.out.println(entity.isRepeatable());

        //System.out.println("网页内容："+ EntityUtils.toString(entity,"utf-8"));//获取网页内容
        System.out.println("Content-Type :"+entity.getContentType());//获取内容类型
        System.out.println("Content-Type :"+entity.getContentType());

        response.close();
        httpClient.close();
    }
}
