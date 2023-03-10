package util;

import dao.StudentDAO;
import model.Student;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Connector {
    private static Connector instance;

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    private Connector() {
    }
    private static final String fileName = "data.bin";
    private static final String IMG_PATH = "C:/Users/tranh/OneDrive/Pictures/Screenshots/Screenshot_20230228_030737.png";

    public boolean writeFileBinary(List<Student> studentList) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(file);

//            BufferedImage imm = ImageIO.read(new File(IMG_PATH));
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(imm, "png", baos);
//            baos.flush();
//            byte[] immAsBytes = baos.toByteArray();
//            baos.close();
//
//            Student stu = new Student(20120385, "Tran Hoang Tin", 8.89,
//                    "82/2/9 Quang Trung, phuong 10, Go Vap", immAsBytes, "hoc sinh ngoan");
//            Student stu2 = new Student(42533, "Tin Dep Trai", 8.43,
//                    "82/2/9 Quang Trung, phuong 10, Go Vap", immAsBytes, "hoc sinh ngoan");
//            os.writeObject(stu);
//            os.writeObject(stu2);

            for(Student s: studentList){
                os.writeObject(s);
            }
            file.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Done writing");
        return true;
    }

    public List<Student> readFileBinary() {
        List<Student> studentList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream is = new ObjectInputStream(file);


            while (file.available() > 0) {
                studentList.add((Student) is.readObject());
            }

//            //Load the byte array into a BufferedImage.
//            BufferedImage img = ImageIO.read(new ByteArrayInputStream(studentList.get(1).getImage()));
//
//            //Write the BufferedImage as a PNG.
//            ImageIO.write(img, "png", new File("image.png"));
//            System.out.println(studentList.get(1).getName());

            file.close();
            is.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done reading");
        return studentList;
    }
}
