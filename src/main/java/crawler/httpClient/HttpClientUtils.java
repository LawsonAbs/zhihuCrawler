package crawler.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import crawler.utils.other.CustomedMethod;
import crawler.utils.json.JsonUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class HttpClientUtils {
    private String dirPath = "E:\\intellij_Project\\zhihu_picture\\";
    //the url we get picture's url
    //private static List<String> topicUrl = new ArrayList<String>();

    private static String topicUrl = "https://www.zhihu.com/api/v4/questions/31123603/answers?include=data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,is_labeled;data[*].mark_infos[*].url;data[*].author.follower_count,badge[*].topics&offset=0&limit=4&sort_by=created";
    private static Set<String> pictureUrls = new HashSet<String>();
    //01.CloseableHttpClient is a abstract class => in order to use in all method
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private static JsonUtils jsonUtils = new JsonUtils();

    public static void main(String[] args) {
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        //httpClientUtils.initial();

        Set<String> tempPictureUrls = null;
        //01.通过url获取entity
        boolean flag = false;
        while(!flag) {
            CustomedMethod.printDelimiter(topicUrl);
            String jsonString = httpClientUtils.getEntityContent(topicUrl,"utf-8");
            tempPictureUrls = httpClientUtils.getImageUrlByJson(jsonString);
            flag = jsonUtils.isEnd(jsonString);

            Iterator<String> iterator = tempPictureUrls.iterator();
            while (iterator.hasNext()) {
                pictureUrls.add(iterator.next()); // add the total set -> pictureUrl
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //print the next url
            topicUrl = jsonUtils.getNext(jsonString);
            System.out.println(pictureUrls.size());
        }

        //print the pictureUrl's size
        System.out.println(pictureUrls.size());

        //pictureUrl is a list,so you must use get() rather than index to fetch a value
        for(String imageUrl : pictureUrls) {
            //System.out.println(imageUrl);
            httpClientUtils.getPicture(imageUrl);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //TODO This part should be more automatic,such as get some information about your interesting automatic
    //initial some url
    public void initial(){
        //topicUrl.add("https://www.zhihu.com/question/263952082");
        //topicUrl.add("https://www.zhihu.com/question/21575743");
        //topicUrl.add("https://www.zhihu.com/api/v4/questions/285906324/answers?");
        //topicUrl.add("https://www.zhihu.com/api/v4/questions/23776164/answers?include=data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,is_labeled;data[*].mark_infos[*].url;data[*].author.follower_count,badge[*].topics&offset=0&limit=20&sort_by=created");
    }

    public Set<String> getImageUrlByJson(String  jsonString) {
        Set<String> pictureUrl ;
        jsonUtils.getImageUrl(jsonString);
        //01. get pictureUrl
        pictureUrl  = jsonUtils.getPictureUrls();
        //02. output the
        //jsonUtils.outputUrl(pictureUrl);
        return pictureUrl;
    }

    //get picture from specific url
    public void getPicture(String url) {
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        HttpEntity entity = httpClientUtils.getEntity(url);
        httpClientUtils.writeImageInDisk(entity);
    }

    //get html entity from specific url
    public HttpEntity getEntity(String url) {
        //use get
        HttpGet httpGet = new HttpGet(url);
       // HttpHost proxy = new HttpHost("115.153.146.73", 8060);

        RequestConfig config = RequestConfig
                .custom()
         //       .setProxy(proxy)
                .setConnectTimeout(10000)//连接超时
                .setSocketTimeout(10000)//读取超时
                .build();
        httpGet.setConfig(config);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:63.0) Gecko/20100101 Firefox/63.0");

        //get the request's response
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        return entity;
    }

    public String getEntityContent(String url,String charset) {
        String jsonContent = null;

        //use get
        HttpGet httpGet = new HttpGet(url);

        RequestConfig config = RequestConfig
                .custom()
                //.setProxy(proxy)
                .setConnectTimeout(10000)//连接超时
                .setSocketTimeout(10000)//读取超时
                .build();
        httpGet.setConfig(config);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:63.0) Gecko/20100101 Firefox/63.0");

        //get the request's response
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();

        try {
            jsonContent = EntityUtils.toString(entity, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonContent;
    }

    //this part,use a customed method,rather than a specific path to store new file
    public String getImageName() {
        String imageName;
        Date date = new Date();
        //DateFormat dateFormat = DateFormat.getDateInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        //System.out.println(simpleDateFormat.format(date));
        imageName = dirPath + simpleDateFormat.format(date) + ".jpg";
        return imageName;
    }

    public void writeImageInDisk(HttpEntity entity) {
        InputStream inputStream = null;
        try {
            inputStream = entity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileAddress = getImageName();
        File newFile = new File(fileAddress);

        //使用字节存储图片，但是这里的大小只有1024B = 1kB，是否会导致数组溢出？
        byte[] image = new byte[1024];

        int length;
        FileOutputStream fileOutputStream = null;
        try {
            if (inputStream != null) {
                fileOutputStream = new FileOutputStream(newFile);
                while ((length = inputStream.read(image)) != -1) {
                    fileOutputStream.write(image, 0, length);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //simulate browser post some information to server
    //the most important parameter is paramsMap
    public HttpEntity doPost(String url, Map<String, Object> paramsMap, String charset) {
        //use HttpPost
        HttpPost httpPost = new HttpPost(url);

        //HttpHost proxy = new HttpHost("115.153.146.73", 8060);

        RequestConfig config = RequestConfig
                .custom()
                //.setProxy(proxy)
//                .setConnectTimeout(10000)//连接超时
//                .setSocketTimeout(10000)//读取超时
                .build();
        httpPost.setConfig(config);

        //set some parameters
        List<NameValuePair> list = new ArrayList<NameValuePair>();

        //the paramsMap is in parameter. in order to traverse paramsMap's value
        Iterator iterator = paramsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Object> elem = (Map.Entry<String, Object>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), (String) elem.getValue()));
        }
        //if request's NameValuePair > 0
        if (list.size() > 0) {
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
                HttpResponse response = httpClient.execute(httpPost);
                if (response != null) {
                    HttpEntity httpEntity = response.getEntity();
                    if (httpEntity != null) {
                        return httpEntity;
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }




//=========================================use proxy ip==================================================

    //get html entity from specific url
    public HttpEntity getEntityByProxt(String url,String ip,int port) {
        //use get
        HttpGet httpGet = new HttpGet(url);
        HttpHost proxy = new HttpHost(ip, port);

        RequestConfig config = RequestConfig
                .custom()
                .setProxy(proxy)
                .setConnectTimeout(1000)//连接超时
                .setSocketTimeout(1000)//读取超时
                .build();
        httpGet.setConfig(config);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:63.0) Gecko/20100101 Firefox/63.0");

        //get the request's response
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        return entity;
    }

    public String getEntityContentByProxy(String url,String ip, int port,String charset) {
        String jsonContent = null;

        //use get
        HttpGet httpGet = new HttpGet(url);
        HttpHost proxy = new HttpHost(ip, port);

        RequestConfig config = RequestConfig
                .custom()
                .setProxy(proxy)
                .setConnectTimeout(1000)//连接超时
                .setSocketTimeout(1000)//读取超时
                .build();
        httpGet.setConfig(config);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:63.0) Gecko/20100101 Firefox/63.0");

        //get the request's response
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();

        try {
            jsonContent = EntityUtils.toString(entity, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonContent;
    }


    //get picture from specific url
    public void getPictureByProxy(String url,String ip,int port) {
        HttpEntity entity = this.getEntityByProxt(url,ip,port);
        this.writeImageInDisk(entity);//write image to Disk
    }
}

