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

    public Time(int day1, int hour1, int minute1, int second1, int day2, int hour2, int minute2, int second2) {
        this(day1, hour1, minute1, second1);
        this.day += day2;
        this.hour += hour2;
        this.minute += minute2;
        this.second += second2;
    }

    public Time(int hour1, int minute1, int second1, int hour2, int minute2, int second2) {
        this(hour1, minute1, second1);
        this.hour += hour2;
        this.minute += minute2;
        this.second += second2;
    }

    public Time(int minute1, int second1, int minute2, int second2) {
        this(minute1, second1);
        this.minute += minute2;
        this.second += second2;
    }

    public Time(int second1, int second2) {
        this(second1);
        this.second += second2;
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
}
