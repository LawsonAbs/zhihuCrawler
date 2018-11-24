package utils.ip;


import crawler.httpClient.HttpClientUtils;
import org.apache.http.HttpEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *01.ip utils
 * This method is uses to get useful ip from internet.
 *
 * 02.the obtained results couldn't use hashMap,because the ip address has nothing to do with the port
 * so use
 */
public class IpUtils {
    //urls' list
    private List<String> urls = new ArrayList<String>();

    public void initial(){
        //url list
        //add three free url to get ip
        urls.add("http://www.ip3366.net/");
//        urls.add("http://www.xicidaili.com");
//        urls.add("https://www.kuaidaili.com/free/");
    }

    //get some ip address and port from specific url
    public HashMap<String,Integer> getIp(String url) {
        //get the efficient ip's map
        HashMap<String, Integer> ipMap = new HashMap<String, Integer>();
        HttpClientUtils httpClientUtils = new HttpClientUtils();

        //
        for(int i = 0;i< urls.size();i++) {
            HttpEntity entity = httpClientUtils.getEntity(urls.get(i));


        }

        return null;
    }

    public HashMap<String,Integer> getIpPortList(){
        return  null;
    }
}
