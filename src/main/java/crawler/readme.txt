
爬虫实战
1.多线程获取高匿代理，并存入HashMap 中。使用部分策略，检测代理ip的有效性
01.httpClient连接超时 => 会重试
02.httpClient读取超时
03.存取介质的选择，使用HashMap？ 或者是 Queue？

2.多线程爬取知乎的图片
01.保证

3.使用jsoup jar 包



<img data-rawheight="198"
src="https://pic3.zhimg.com/80/v2-c8a8b3b11bcf9a038a23b0a1e1ec287a_hd.jpg" data-rawwidth="194"
class="content_image lazy"
data-actualsrc="https://pic3.zhimg.com/v2-c8a8b3b11bcf9a038a23b0a1e1ec287a_b.jpg" width="194">

4.层级关系
01.选择器Css / jQuery
02.获取元素属性值
03.

5.知乎页面
01.动态加载的，也就是说每次只加载20条
02.所以现在需要手写代码模拟请求
解决办法：其实这就是一个类似于一个分页信息一样。那么我只要在java代码中每次模仿页面的请求给知乎发送ajax请求，
然后解析返回的json结果就可以获取其中的问题信息了。其实就只有有两个需求需要解决。

    在java代码中模仿ajax发送请求。采用的是Httpclient。
    解析返回的json，Gson jar包可以完美解决
03.但是这种方式存在一个问题，就是只能获取单个页面中的数据，而未加载的数据就不能显示出来

6.参考文章
- https://www.jianshu.com/p/cb22e13cc7b6
