import java.util.*;

public class Main {

    static String[] subjects = {"DS", "AI", "CN"};
    static String[] teachers = {"Sanjay", "Vishwas", "Sharan"};
    static String[] labs = {"Lab1", "Lab2", "Lab3"};
    static String[] slots = {"Morning", "Afternoon", "Evening"};

    static String[][] timetable = new String[subjects.length][2];

    static boolean isValid(int subjectIndex, int slotIndex, int labIndex) {

        for (int i = 0; i < subjectIndex; i++) {

            if (timetable[i][0] != null) {

                String usedSlot = timetable[i][0];
                String usedLab = timetable[i][1];

                if (usedSlot.equals(slots[slotIndex]) &&
                        usedLab.equals(labs[labIndex])) {
                    return false;
                }

                if (usedSlot.equals(slots[slotIndex]) &&
                        teachers[i].equals(teachers[subjectIndex])) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean generate(int subjectIndex) {

        if (subjectIndex == subjects.length) {
            return true;
        }

        for (int i = 0; i < slots.length; i++) {
            for (int j = 0; j < labs.length; j++) {

                if (isValid(subjectIndex, i, j)) {

                    timetable[subjectIndex][0] = slots[i];
                    timetable[subjectIndex][1] = labs[j];

                    if (generate(subjectIndex + 1)) {
                        return true;
                    }

                    timetable[subjectIndex][0] = null;
                    timetable[subjectIndex][1] = null;
                }
            }
        }
        return false;
    }

    static void show() {
        System.out.println("\n--- Smart Timetable Generated ---");

        for (int i = 0; i < subjects.length; i++) {
            System.out.println("Subject: " + subjects[i]
                    + " | Teacher: " + teachers[i]
                    + " | Slot: " + timetable[i][0]
                    + " | Lab: " + timetable[i][1]);
        }
    }

    public static void main(String[] args) {

        if (generate(0)) {
            show();
        } else {
            System.out.println("No valid timetable found");
        }
    }
}