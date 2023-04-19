public class Piano extends NonFoodObjects {
    private boolean isBeingUsed;

    public Piano(String name, int length, int width, int price) {
        super(name, length, width, price);
        this.isBeingUsed = false;
    }

    public void setStatus(boolean isBeingUsed) {
        this.isBeingUsed = isBeingUsed;
    }

    public boolean getStatus() {
        return isBeingUsed;
    }

    public void playPiano(Time, Sim) {
        if (getStatus() == false) {
            setStatus(true);
            if (Sim.getStatus().equals("Nothing")) {
                int duration = Time.convertToSecond();
                Sim.setStatus("Playing piano");
                Sim.setMood(Sim.getMood() + 5*duration/20);
            }
            else {
                System.out.println("You can't play the piano while you are " + Sim.getStatus());
            }
        }
    }
}