public class Page {
    private String title;
    private int pageID;
    private String content;

    public Page(title, pageID, content) {
        this.title = title;
        this.pageID = pageID;
        this.content = content;        
    }

    public String getTitle() {
        return title;
    }

    public int getPageID() {
        return pageID;
    }

    public Strig getContent() {
        return content;
    }

    public setTitle(String title) {
        this.title = title;
    }

    public setPageID(int pageID) {
        this.pageID = pageID;
    }

    public setContent(String content) {
        this.content = content;
    }
}