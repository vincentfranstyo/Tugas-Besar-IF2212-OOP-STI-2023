public class Channel {
    private String name;
    private int channelID;
    private String genre;

    public Channel(String name, int channelID, String genre) {
        this.name = name;
        this.channelID = channelID;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public int getChannelID() {
        return channelID;
    }

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}