package dao;

import model.Student;
import util.Connector;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentDAO {
    private List<Student> studentList;
    private static StudentDAO instance;

    public static StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    private StudentDAO() {
        studentList = Connector.getInstance().readFileBinary();
    }

    public List<Student> getAllStudents() {
        return studentList;
    }

    public List<Student> getAllStudentsAscendingByID() {
        Comparator<Student> comparator = Comparator.comparing(Student::getId);
        Collections.sort(studentList, comparator);
//        System.out.println(studentList);
        return studentList;
    }

    public List<Student> getAllStudentsDescendingByID() {
        Comparator<Student> comparator = Comparator.comparing(Student::getId).reversed();
        Collections.sort(studentList, comparator);
//        System.out.println(studentList);
        return studentList;
    }

    public List<Student> getAllStudentsAscendingByScore() {
        Comparator<Student> comparator = Comparator.comparing(Student::getScore);
        Collections.sort(studentList, comparator);
//        System.out.println(studentList);
        return studentList;
    }

    public List<Student> getAllStudentsDescendingByScore() {
        Comparator<Student> comparator = Comparator.comparing(Student::getScore).reversed();
        Collections.sort(studentList, comparator);
//        System.out.println(studentList);
        return studentList;
    }

    public Student addStudent(int id, String name, double score, String address, byte[] image, String note) {
        Student newStudent = new Student(id, name, score, address, image, note);
        studentList.add(newStudent);
        Connector.getInstance().writeFileBinary(studentList);
        return newStudent;
    }

    public Student addStudent(int id, String name, double score, String address, byte[] image) {
        Student newStudent = new Student(id, name, score, address, image);
        studentList.add(newStudent);
        Connector.getInstance().writeFileBinary(studentList);
        return newStudent;
    }
    public Student addStudent(int id, String name, double score, String address) {
        Student newStudent = new Student(id, name, score, address);
        studentList.add(newStudent);
        Connector.getInstance().writeFileBinary(studentList);
        return newStudent;
    }

    public Student addStudent(int id, String name, double score, String address, String note) {
        Student newStudent = new Student(id, name, score, address, note);
        studentList.add(newStudent);
        Connector.getInstance().writeFileBinary(studentList);
        return newStudent;
    }

    public void deleteStudent(int studentId) {
        for (Student stu : studentList) {
            if (stu.getId() == studentId) {
                studentList.remove(stu);
                Connector.getInstance().writeFileBinary(studentList);
                break;
            }
        }
    }

    public byte[] getImageStudentAsByte(int studentId) {
        for (Student stu : studentList) {
            if (stu.getId() == studentId) {
                return stu.getImage();
            }
        }
        return null;
    }
    public void updateStudent(int index, Student newInfoStudent){
        studentList.set(index, newInfoStudent);
        Connector.getInstance().writeFileBinary(studentList);
    }

    public Student findStudentById(int studentId){
        for (Student stu : studentList) {
            if (stu.getId() == studentId) {
                return stu;
            }
        }
        return null;
    }

    public void updateData(){
        Connector.getInstance().writeFileBinary(studentList);
    }

}
