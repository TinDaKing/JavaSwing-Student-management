package model;
import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {
    private int id;
    private String name;
    private double score;

    private String address;
    private byte[] image;
    private String note;

    private Student (){
        super();
    }
    private Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(int id, String name, double score, String address, byte[] image, String note) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.address = address;
        this.image = image;
        this.note = note;
    }

    public Student(int id, String name, double score, String address, byte[] image) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.address = address;
        this.image = image;
    }

    public Student(int id, String name, double score, String address) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.address = address;
    }
    public Student(int id, String name, double score, String address, String note) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.address = address;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return id+" " + name +" "+ score +" "+  address +" "+ image +" "+ note;
    }

}
