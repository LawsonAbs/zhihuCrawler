package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class GovHtml extends Html {
    //获取省 、市 、县等的信息
    public ArrayList getSpecificInfo(String name, String url , String type,String charset)
    {
        ArrayList result= new ArrayList();
        String classType = "tr." +type;
        //"tr.provincetr"  => 要获取标签为<tr class="provincetr">的信息
        System.out.println("classType is :" + classType);
        //从网络上获取网页
        // Document doc = this.getHtmlTextByUrl(url);
        //从本地获取网页,如果没有则从网络获取
        Document doc2 = this.getHtmlTextByPath(name,url,charset);
        System.out.println(name);

        if(doc2!=null){ //如果存在集合，则取出数据
            System.out.println("doc2!=null");
            Elements es =this.getEleByClass(doc2,classType);  //根据上述的classType得到tr的集合
            //System.out.println("es is :"+es.toString());
            for(Element e : es)   //依次循环每个元素，也就是一个tr
            {
                if(e!=null){
                    //System.out.printf("element!=null"); //->for test
                    for(Element ec : e.children())  //一个tr的子元素td，td内包含a标签
                    {
                        //身份的信息： 原来的url（当前url）  名称（北京） 现在url（也就是北京的url）  类型（prv）省
                        String[] prv = new String[4];
                        if(ec.children().first()!=null)
                        {
                            //原来的url
                            prv[0]=url;  //就是参数url
                            /* 获取城市名
                            1.first()：Get the first matched element.
                            2.ownText(): Gets the text owned by this element only; does not get the combined text of all children.
                             */
                            prv[1]=ec.children().first().ownText();  //a标签文本  如:北京
                            System.out.println(prv[1]);

                            /* 获取子url地址
                            */
                            prv[2]=ec.children().first().attr("abs:href");  //北京的url
                            System.out.println(prv[2]);

                            /*级别
                            prv[3]=type; //就是刚刚传的类型，后面会有city 、county等
                            result.add(prv);//将所有身份加入list中
                            */
                        }
                    }
                }
            }
        }
        //返回所有的省份信息集合，存数据库，字段类型为： baseurl  name ownurl levelname（type） updatetime
        return result;
    }
}
