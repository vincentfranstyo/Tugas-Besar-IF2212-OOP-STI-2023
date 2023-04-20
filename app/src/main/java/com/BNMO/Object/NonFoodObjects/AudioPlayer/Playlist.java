import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private String desc;
    private int playlistID;
    private Time length;
    private List<Music> music;

    public Playlist(String name, String desc, int playlistID) {
        this.name = name;
        this.desc = desc;
        this.playlistID = playlistID;
        this.music = new ArrayList<>();
        this.length = new Time(0, 0, 0, 0);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public List<Music> getMusic() {
        return music;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public void addMusic(Music musicEntry) {
        if (!music.contains(musicEntry)) {
            music.add(musicEntry);
            setLength(getLength().addTime(musicEntry.getLength()));
        } else {
            System.out.println("Music is already in the playlist.");
        }
    }

    public void removeMusic(Music musicEntry) {
        music.remove(musicEntry);
        setLength(getLength().addTime(new Time(0, 0, 0, -musicEntry.getLength().convertToSecond())));
    }

    public Time getLength() {
        return length;
    }

    public void setLength(Time length) {
        this.length = length;
    }

    public void showMusic() {
        System.out.println("Music in playlist: ");
        for (Music musicEntry : music) {
            System.out.println(musicEntry.getName());
        }
    }

    public void showPlaylist() {
        System.out.println("Playlist: " + getName());
        System.out.println("Description: " + getDesc());
        System.out.println("Playlist ID: " + getPlaylistID());
        System.out.println("Length: " + getLength().displayTime());
        showMusic();
    }
}