package Random;


class DayOfWeek {

    public String  dayOfTheWeek(int day, int month, int year) {
        int daysOfMonth [] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String dayName [] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        int total = 1;

        for (int i = 1970; i < year; i++) {
            total += 365 + isLeapYear (i);
        }


        for (int i = 0; i < month - 1; i++) {
            if (i == 1) {
                total += daysOfMonth [i] + isLeapYear (year);
            }
            else {
                total += daysOfMonth [i];
            }
        }

        total += day;
        return dayName [(total + 2) % 7];
    }

    private int isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 1 : 0;
    }
};
