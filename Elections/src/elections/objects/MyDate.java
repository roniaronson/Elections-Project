package elections.objects;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class MyDate {
	private int year, month, day;
	private final static int[] DAYS_MONTHS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public MyDate(int day, int month, int year) {
		if (year < 2000 || year > 2020)
			year = 2020;
		this.year = year;
		if (month < 1 || month > 12)
			month = 1;
		this.month = month;
		if (day < 1 || day > DAYS_MONTHS[month + 1])
			day = 1;
		this.day = day;
	}

	public String getDate() {
		return day + "/" + month + "/" + year;
	}

	public int daysCount(MyDate d) {
		LocalDate enter = LocalDate.of(year, month, day);
		LocalDate out = LocalDate.of(d.year, d.month, d.day);
		Period period = Period.between(enter, out);
		int diff = Math.abs(period.getDays() + period.getMonths() * DAYS_MONTHS[month - 1] + period.getYears() * 365);
		return diff;
	}

	public MyDate(Messageable ui) {
		this.day = ui.getInt("enter day:");
		this.month = ui.getInt("enter month:");
		this.year = ui.getInt("enter year:");
	}
}
