package com.BNMO.Object.NonFoodObjects.Book;

import java.util.List;

import com.BNMO.SIMS.Sim;

public class Journal extends Book {
    private int countWritten;
    private List<Page> pages;

    public Journal(String title) {
        super(title);
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

    public void writeJournal(Journal journal, Sim sim, String content) {
        setAuthor(sim.getName());
        List<Page> pages = journal.getPages();
        setCountWritten(getCountWritten() + 1);
        if (!getIsOccupied()){
            setIsOccupied(true);
            try{
                System.out.println(sim.getName() + " is writing journal");
                Thread.sleep(60000);
                System.out.println(sim.getName() + " has finished writing journal");
                sim.setFullness(sim.getFullness() - 10);
                sim.setMood(sim.getMood() + 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                setIsOccupied(false);
            }
        }
        Page newPage = new Page(getCountWritten(), content);
        pages.add(newPage);
    }

    public void readJournal(Journal journal, Sim sim, int pageID) {
        String content = journal.getPages().get(pageID).getContent();
        if (!getIsOccupied()){
            setIsOccupied(true);
            try{
                System.out.println("Reading page " + pageID);
                System.out.println(content);
                Thread.sleep(60000);
                System.out.println("Finished reading page " + pageID);
                sim.setMood(sim.getMood() + 10);
                sim.setFullness(sim.getFullness() - 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                setIsOccupied(false);
            }
        }
    }
}