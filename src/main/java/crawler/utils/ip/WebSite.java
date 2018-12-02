package crawler.utils.ip;

/**
 * 01.abstract class
 */
public abstract class WebSite implements FreeIP{

    private String webName;
    private String url;
    private String keyword;//the keyword to get the ip
    private String charset;
    private int page = 1;// the first page to visit

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    /**
     * 01. get the web's next Url
     */
    public String getNextUrl(String prefix){

        String nextUrl ;
        page ++;
        if(page > 10 ){// reset to 0
            page = 0;
        }
        nextUrl = prefix + page; // get a new ip and Port
        this.setUrl(nextUrl);//the url is a variable
        return  nextUrl;
    }

}
