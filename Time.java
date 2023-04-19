public class Time {
    private int day;
    private int hour;
    private int minute;
    private int second;

    public Time(int day, int hour, int minute, int second) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Time(int hour, int minute, int second) {
        this(0, hour, minute, second);
    }

    public Time(int minute, int second) {
        this(0, 0, minute, second);
    }

    public Time(int second) {
        this(0, 0, 0, second);
    }

    public Time() {
        this(0, 0, 0, 0);
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String toString() {
        return "Day: " + day + " Hour: " + hour + " Minute: " + minute + " Second: " + second;
    }

    public int convertToSecond() {
        return (day * 24 * 60 * 60) + (hour * 60 * 60) + (minute * 60) + second;
    }

    public Time addTime(Time time) {
        int inSec1 = this.convertToSecond();
        int inSec2 = time.convertToSecond();

        int totalSec = inSec1 + inSec2;

        int day = totalSec / (24 * 3600);
        totalSec = totalSec % (24 * 3600);
        int hour = totalSec / 3600;
        totalSec = totalSec % 3600;
        int minute = totalSec / 60;
        int second = totalSec % 60;

        return new Time(day, hour, minute, second);
    }
}
