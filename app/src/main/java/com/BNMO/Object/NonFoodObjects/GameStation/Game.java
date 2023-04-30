public class Game {
    private String name;
    private int gameID;
    private int funPoint;

    public Game (String name, int gameID, int funPoint) {
        this.name = name;
        this.gameID = gameID;
        this.funPoint = funPoint;
    }

    public String getName() {
        return name;
    }

    public int getGameID() {
        return gameID;
    }

    public int getFunPoint() {
        return funPoint;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setFunPoint(int funPoint) {
        this.funPoint = funPoint;
    }
    
}