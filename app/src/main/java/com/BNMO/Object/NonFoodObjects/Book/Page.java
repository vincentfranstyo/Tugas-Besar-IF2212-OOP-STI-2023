package com.BNMO.Object.NonFoodObjects.Book;

public class Page {
    private int pageID;
    private String content;

    public Page(int pageID, String content) {
        this.pageID = pageID;
        this.content = content;
    }

    public int getPageID() {
        return pageID;
    }

    public String getContent() {
        return content;
    }

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }

    public void setContent(String content) {
        this.content = content;
    }
}