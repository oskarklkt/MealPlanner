package mealplanner;

public enum DaysOfTheWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    @Override
    public String toString() {
        String name = this.name();
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
