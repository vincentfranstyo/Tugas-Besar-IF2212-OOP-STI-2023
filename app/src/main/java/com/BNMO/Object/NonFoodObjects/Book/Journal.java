package com.BNMO.Object.NonFoodObjects.Book;

import java.util.List;

public class Journal extends Book {
    private int countWritten;
    private List<Page> pages;

    public Journal(String title, int price, int numOfPages, String author) {
        super(title, price, numOfPages, author);
        this.setType("Journal");
    }

    public int getCountWritten() {
        return countWritten;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setCountWritten(int countWritten) {
        this.countWritten = countWritten;
    }

    public void writeJournal(Journal journal, String content) {
        List<Page> pages = journal.getPages();
        setCountWritten(getCountWritten() + 1);
        Page newPage = new Page(getCountWritten(), content);
        pages.add(newPage);
    }

    public String showPage(Journal journal, int pageID) {
        return journal.getPages().get(pageID).getContent();
    }
}