package crawler.httpClient;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.net.URL;

public abstract class Html {
    //根据url从网络获取网页文本
    public Document getHtmlTextByUrl(String url) {
        /*
        1.Document:A HTML Document.
         */
        Document doc = null;
        try {
            int i = (int) (Math.random() * 1000); //做一个随机延时，防止网站屏蔽
            while (i != 0) {
                i--;
            }
            /*
            1.The core public access point to the jsoup functionality.【访问jsoup 功能的核心开放接口】
            2.connect(url) -> 连接到某个url上的方法
            3.data():Add a request data parameter
            4.cookie():Set a cookie to be sent in the request
            5.post():Execute the request as a POST, and parse the result.
            */
            doc = Jsoup.connect(url).data("query", "Java")
                    .userAgent("Mozilla").cookie("auth", "token")
                    .timeout(300000).post();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                doc = Jsoup.connect(url).timeout(5000000).get();
            } catch (IOException e1) { // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return doc;
    }

    //get html by local url,if local url not exists ,get url through network and save in local
    public Document getHtmlTextByPath(String name, String url,String charset ) {
        String path = "D:/htmlCrawler.Html/" + name + ".htm"; //the local url's path
        Document doc = null;
        File input = new File(path);
        try {
            /*
            1.(optional) character set of file contents. Set to null to determine from http-equiv meta tag,
            if present, or fall back to UTF-8 (which is often safe to do).
             */
            doc = Jsoup.parse(input,charset);
            if (!doc.children().isEmpty()) {
                //doc = null;
                System.out.println("已经存在");
            }
        } catch (IOException e) {
            System.out.println("文件未找到，正在从网络获取.......");
            doc = this.getHtmlTextByUrl(url);
            //并且保存到本地
            this.saveHtml(url,name);
        }
        return doc;
    }

    //将网页保存在本地（通过url,和保存的名字）
    public void saveHtml(String url,String name) {
        try {
            name = name + ".htm";//set file's name
            // System.out.print(name);
            File dest = new File("D:\\Html" + name);//D:\htmlCrawler.Html
            //接收字节输入流
            InputStream is;
            //字节输出流
            FileOutputStream fos = new FileOutputStream(dest);
            URL temp = new URL(url);
            is = temp.openStream();
            //为字节输入流加缓冲
            BufferedInputStream bis = new BufferedInputStream(is);
            //为字节输出流加缓冲
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            int length;
            byte[] bytes = new byte[1024 * 20];
            while ((length = bis.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, length);
            }
            bos.close();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据元素属性获取某个元素内的elements列表
    public Elements getEleByClass(Document doc,String className)
    {
        Elements elements= null;
        elements = doc.select(className);//这里把我们获取到的html文本doc，和工具class名，注意<tr class="provincetr">
        return elements;   //此处返回的就是所有的tr集合
    }
    public abstract Object getSpecificInfo(String name, String url , String type,String charset);
}