package date;

import java.util.Calendar;

public class CalendarEx7 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage : Java CalendarEx7 2015 11");
            return;
        }

        int year = Integer.parseInt(args[0]);
        int month = Integer.parseInt(args[1]);

        Calendar sDay = Calendar.getInstance(); // 시작일
        Calendar eDay = Calendar.getInstance(); // 끝일

        sDay.set(year, month - 1, 1);
        eDay.set(year, month - 1, sDay.getActualMaximum(Calendar.DATE));

        sDay.add(Calendar.DATE, -sDay.get(Calendar.DAY_OF_WEEK) + 1); // 1일이 속한 주의 일요일로 날짜 설정
        eDay.add(Calendar.DATE, 7 - eDay.get(Calendar.DAY_OF_WEEK)); // 말일이 속한 주의 토요일로 날짜 설정

        System.out.println("      " + year + "년 " + month + "월");
        System.out.println(" SU MO TU WE TH FR SA");

        for (int n = 1; sDay.before(eDay) || sDay.equals(eDay); sDay.add(Calendar.DATE, 1)) {
            int day = sDay.get(Calendar.DATE); // 며칠인지
            System.out.print((day < 10) ? "  " + day : " "+ day);
            if (n++ % 7 == 0) {
                System.out.println();
            }

        }



    }


}
