package com.demoqa.test.automatic.main.models;

public class Date {
    private int day;
    private int month;
    private int year;

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    /**
     * Date constructor for data filling.
     * @param day day of our required date (No validation is done)
     * @param month month of our required date (No validation is done)
     * @param year  year of our required data(No validation is done)
     */
    public Date(int day, int month, int year) {
        super();
        this.day = day;
        this.month = month;
        this.year = year;
    }

}
