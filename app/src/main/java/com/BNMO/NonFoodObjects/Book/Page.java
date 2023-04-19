package NonFoodObjects.Book;

public class Page {
    private String title;
    private int pageID;
    private String content;

    public Page(String title, int pageID, String content) {
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

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }

    public void setContent(String content) {
        this.content = content;
    }
}