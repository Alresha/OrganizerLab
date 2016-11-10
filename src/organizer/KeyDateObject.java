package organizer;

public class KeyDateObject {
    private Object day;
    private String month;
    private int year;

    public Object getDay() {return this.day;}
    public void setDay(Object newDay) {this.day = newDay;}

    public String getMonth() {return this.month;}
    public void setMonth(String newMonth) {this.month = newMonth;}

    public int getYear() {return this.year;}
    public void setYear(int newYear) {this.year = newYear;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyDateObject)) return false;

        KeyDateObject that = (KeyDateObject) o;

        if (year != that.year) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        return month != null ? month.equals(that.month) : that.month == null;

    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }
}
