import java.util.*;

class Student {
    int id; // شناسه دانشجو
    String firstName; // نام
    String lastName; // نام خانوادگی
    char gender; // جنسیت
    String dob; // تاریخ تولد
    ArrayList<Integer> lessonIds; // لیست شناسه دروس

    public Student(int id, String firstName, String lastName, char gender, String dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.lessonIds = new ArrayList<>();
    }

    // نمایش جزئیات دانشجو به همراه دروس ثبت نام شده
    public void displayDetails(ArrayList<Lesson> lessons) {
        System.out.println("ID: " + id);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Gender: " + gender);
        System.out.println("Date of Birth: " + dob);
        if (lessonIds.isEmpty()) {
            System.out.println("Student is not enrolled in any lessons.");
        } else {
            System.out.println("Lessons enrolled:");
            for (int lessonId : lessonIds) {
                for (Lesson lesson : lessons) {
                    if (lesson.id == lessonId) {
                        System.out.println("- " + lesson.lessonName + " (Teacher: " + lesson.teacherName + ")");
                    }
                }
            }
        }
    }
}

class Lesson {
    int id; // شناسه درس
    String lessonName; // نام درس
    String teacherName; // نام معلم
    String startTime; // زمان شروع
    ArrayList<Integer> studentIds; // لیست شناسه دانشجویان

    public Lesson(int id, String lessonName, String teacherName, String startTime) {
        this.id = id;
        this.lessonName = lessonName;
        this.teacherName = teacherName;
        this.startTime = startTime;
        this.studentIds = new ArrayList<>();
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Lesson> lessons = new ArrayList<>();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add new student"); // اضافه دانشجو جدید
            System.out.println("2. Add new lesson"); // اضافه درس جدید
            System.out.println("3. View students"); // مشاهده دانشجویان
            System.out.println("4. View lessons"); // مشاهده دروس
            System.out.println("5. Add student to lesson"); // اضافه دانشجو به درس
            System.out.println("6. Remove student from lesson"); // حذف دانشجو از درس
            System.out.println("7. Edit student details"); // ویرایش جزئیات دانشجو
            System.out.println("8. Edit lesson details"); // ویرایش جزئیات درس
            System.out.println("9. Delete student"); // حذف دانشجو
            System.out.println("10. Delete lesson"); // حذف درس
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter student details:"); // وارد کردن جزئیات دانشجو
                    System.out.print("ID (7 digits): "); // شناسه (7 رقم):
                    int id = scanner.nextInt();
                    while (String.valueOf(id).length() != 7) {
                        System.out.println("ID must be 7 digits. Please re-enter:"); // شناسه باید 7 رقم باشد. لطفا مجددا وارد کنید:
                        id = scanner.nextInt();
                    }
                    System.out.print("First Name: "); // نام:
                    String firstName = scanner.next();
                    System.out.print("Last Name: "); // نام خانوادگی:
                    String lastName = scanner.next();
                    System.out.print("Gender (m/f): "); // جنسیت (m/f):
                    char gender = scanner.next().charAt(0);
                    System.out.print("Date of Birth (dd/mm/yyyy): "); // تاریخ تولد (dd/mm/yyyy):
                    String dob = scanner.next();
                    students.add(new Student(id, firstName, lastName, gender, dob));
                    break;
                    case 2:
                    System.out.println("Enter lesson details:"); // وارد کردن جزئیات درس
                    System.out.print("Lesson ID (up to 5 digits): "); // شناسه درس (حداکثر 5 رقم):
                    int lessonId = scanner.nextInt();
                    while (String.valueOf(lessonId).length() > 5) {
                        System.out.println("Lesson ID must be up to 5 digits. Please re-enter:"); // شناسه درس باید حداکثر 5 رقم باشد. لطفا مجددا وارد کنید:
                        lessonId = scanner.nextInt();
                    }
                    System.out.print("Lesson Name: "); // نام درس:
                    String lessonName = scanner.next();
                    System.out.print("Teacher Name: "); // نام معلم:
                    String teacherName = scanner.next();
                    System.out.print("Start Time: "); // زمان شروع:
                    String startTime = scanner.next();
                    lessons.add(new Lesson(lessonId, lessonName, teacherName, startTime));
                    break;

                case 3:
                    System.out.println("Students:"); // دانشجویان:
                    for (Student student : students) {
                        student.displayDetails(lessons);
                    }
                    break;

                case 4:
                    System.out.println("Lessons:"); // دروس:
                    for (Lesson lesson : lessons) {
                        System.out.println("ID: " + lesson.id);
                        System.out.println("Lesson Name: " + lesson.lessonName);
                        System.out.println("Teacher Name: " + lesson.teacherName);
                        System.out.println("Start Time: " + lesson.startTime);
                        if (lesson.studentIds.isEmpty()) {
                            System.out.println("No students enrolled in this lesson."); // هیچ دانشجویی در این درس ثبت نام نشده است.
                        } else {
                            System.out.println("Students enrolled:"); // دانشجویان ثبت نام شده:
                            for (int studentId : lesson.studentIds) {
                                for (Student student : students) {
                                    if (student.id == studentId) {
                                        System.out.println("- " + student.firstName + " " + student.lastName);
                                    }
                                }
                            }
                        }
                        System.out.println();
                    }
                    break;

                case 5:
                    System.out.print("Enter student ID: "); // شناسه دانشجو را وارد کنید:
                    int studentIdToAdd = scanner.nextInt();
                    System.out.print("Enter lesson ID: "); // شناسه درس را وارد کنید:
                    int lessonIdToAdd = scanner.nextInt();
                    addStudentToLesson(students, lessons, studentIdToAdd, lessonIdToAdd);
                    break;

                case 6:
                    System.out.print("Enter student ID: "); // شناسه دانشجو را وارد کنید:
                    int studentIdToRemove = scanner.nextInt();
                    System.out.print("Enter lesson ID: "); // شناسه درس را وارد کنید:
                    int lessonIdToRemove = scanner.nextInt();
                    removeStudentFromLesson(students, lessons, studentIdToRemove, lessonIdToRemove);
                    break;
