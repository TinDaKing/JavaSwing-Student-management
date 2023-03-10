package view_controller;

import dao.StudentDAO;
import model.Student;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dashboard extends JFrame {
    private JButton sortByIDUp;
    private JButton sortByIDDown;
    private JButton sortByScoreUp;
    private JButton sortByScoreDown;
    private JButton submitAddStudent;
    private JButton exportDataCSV;
    private JLabel idData;
    private JLabel nameData;
    private JLabel scoreData;
    private JLabel addressData;
    private JLabel imageData;
    private JLabel noteData;
    private Container studentDataPanel;
    private JTextField id;
    private JTextField name;
    private JTextField score;
    private JTextField address;
    private JButton image;
    private JLabel imageName;
    private JTextField note;
    private byte[] imgAsBytes;
    private boolean addStatus;
    private JTextField idInteract;
    private JButton showImageBtn;
    private JButton deleteStudentBtn;
    private JButton editStudentBtn;
    private JFrame popUpFrame;
    private JTextField idEdit;
    private JTextField nameEdit;
    private JTextField scoreEdit;
    private JTextField addressEdit;
    private JButton imageEdit;
    private byte[] imageEditAsByte;
    private JTextField noteEdit;
    private JButton submitChangeBtn;
    private int currentEditStudentId;

    public Dashboard() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Student management");
        setTable();

        showStudentList();

        addStudent();

        sortByID();
        sortByScore();

        showImage();
        deleteStudent();
        editStudentFrame();

        exportDataToCSV();

        setLayout(null);
        setSize(1040, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setTable() {

        // create labels
        JLabel labelList = new JLabel("List of Student");

        JLabel labelId = new JLabel("Id");
        JLabel labelName = new JLabel("Name");
        JLabel labelScore = new JLabel("Score");
        JLabel labelAddress = new JLabel("Address");
        JLabel labelImage = new JLabel("Image");
        JLabel labelNote = new JLabel("Note");

        labelList.setBounds(20, 10, 100, 30);

        labelId.setBounds(20, 60, 70, 20);
        labelName.setBounds(90, 60, 180, 20);
        labelScore.setBounds(270, 60, 50, 20);
        labelAddress.setBounds(350, 60, 300, 20);
        labelImage.setBounds(650, 60, 100, 20);
        labelNote.setBounds(750, 60, 150, 20);

        add(labelList);
        add(labelId);
        add(labelName);
        add(labelScore);
        add(labelAddress);
        add(labelImage);
        add(labelNote);

    }

    private void showStudentList() {
        clearDataShown();
        List<Student> studentList = StudentDAO.getInstance().getAllStudents();

        studentDataPanel = new Container();
        studentDataPanel.setBounds(0, 0, 1040, 500);
        int step = 110;

        for (Student stu : studentList) {

            idData = new JLabel(String.valueOf(stu.getId()));
            nameData = new JLabel(stu.getName());
            scoreData = new JLabel(String.valueOf(stu.getScore()));
            addressData = new JLabel(stu.getAddress());
            imageData = new JLabel(Arrays.toString(stu.getImage()));
            noteData = new JLabel(stu.getNote());

            idData.setBounds(20, step, 70 - 5, 20);
            nameData.setBounds(90, step, 180 - 5, 20);
            scoreData.setBounds(270, step, 80 - 5, 20);
            addressData.setBounds(350, step, 300 - 5, 20);
            imageData.setBounds(650, step, 100 - 5, 20);
            noteData.setBounds(750, step, 150 - 5, 20);

            studentDataPanel.add(idData);
            studentDataPanel.add(nameData);
            studentDataPanel.add(scoreData);
            studentDataPanel.add(addressData);
            studentDataPanel.add(imageData);
            studentDataPanel.add(noteData);

            step += 20;
        }
        add(studentDataPanel);
        repaint();
    }

    private void showStudentList(String element, boolean order) {
        clearDataShown();
        List<Student> studentList = new ArrayList<>();
        if (element.equals("id")) {
            if (order)
                studentList = StudentDAO.getInstance().getAllStudentsAscendingByID();
            else
                studentList = StudentDAO.getInstance().getAllStudentsDescendingByID();
        } else if (element.equals("score")) {
            if (order)
                studentList = StudentDAO.getInstance().getAllStudentsAscendingByScore();
            else
                studentList = StudentDAO.getInstance().getAllStudentsDescendingByScore();
        }

        studentDataPanel = new Container();
        studentDataPanel.setBounds(0, 0, 1040, 500);

        int step = 110;

        for (Student stu : studentList) {
            idData = new JLabel(String.valueOf(stu.getId()));
            nameData = new JLabel(stu.getName());
            scoreData = new JLabel(String.valueOf(stu.getScore()));
            addressData = new JLabel(stu.getAddress());
            imageData = new JLabel(Arrays.toString(stu.getImage()));
            noteData = new JLabel(stu.getNote());

            idData.setBounds(20, step, 70 - 5, 20);
            nameData.setBounds(90, step, 180 - 5, 20);
            scoreData.setBounds(270, step, 80 - 5, 20);
            addressData.setBounds(350, step, 300 - 5, 20);
            imageData.setBounds(650, step, 100 - 5, 20);
            noteData.setBounds(750, step, 150 - 5, 20);

            studentDataPanel.add(idData);
            studentDataPanel.add(nameData);
            studentDataPanel.add(scoreData);
            studentDataPanel.add(addressData);
            studentDataPanel.add(imageData);
            studentDataPanel.add(noteData);

            step += 20;
        }
        add(studentDataPanel);
        repaint();
    }

    private void clearDataShown() {
        if (studentDataPanel != null && studentDataPanel.isDisplayable())
            remove(studentDataPanel);
        repaint();
    }

    private void addStudent() {
        id = new JTextField();
        name = new JTextField();
        score = new JTextField();
        address = new JTextField();
        note = new JTextField();

        image = new JButton("Browse");
        image.setMargin(new Insets(0, 5, 0, 5));
        browseImage();

        id.setBounds(20, 90, 70, 20);
        name.setBounds(90, 90, 180, 20);
        score.setBounds(270, 90, 80, 20);
        address.setBounds(350, 90, 300, 20);
        image.setBounds(652, 90, 90, 19);
        note.setBounds(750, 90, 150, 20);

        add(id);
        add(name);
        add(score);
        add(address);
        add(image);
        add(note);


        submitAddStudent = new JButton("Add Student");
        submitAddStudent.setBounds(910, 90, 100, 19);
        submitAddStudent.setMargin(new Insets(0, 5, 0, 5));
        submitAddStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idInput = Integer.parseInt(id.getText());
                    String nameInput = name.getText();
                    double scoreInput = Math.round(Double.parseDouble(score.getText()) * 100.0) / 100.0;
                    String addressInput = address.getText();
                    String noteInput = note.getText();

                    if (nameInput == null || addressInput == null) {
                        JOptionPane.showMessageDialog(null, "You should fill data in ^^");
                    } else if (idInput < 0 || idInput > 99999999) {
                        JOptionPane.showMessageDialog(null, "ID should > 0 and < 99999999");
                    } else if (scoreInput < 0 || scoreInput > 10) {
                        JOptionPane.showMessageDialog(null, "Score must >=0 and <=10");
                    } else if (noteInput == null && imgAsBytes == null) {
                        StudentDAO.getInstance().addStudent(idInput, nameInput, scoreInput, addressInput);
                        showStudentList();
                        addStatus = true;
                    } else if (imgAsBytes == null) {
                        StudentDAO.getInstance().addStudent(idInput, nameInput, scoreInput, addressInput, noteInput);
                        showStudentList();
                        addStatus = true;
                    } else if (noteInput == null) {
                        StudentDAO.getInstance().addStudent(idInput, nameInput, scoreInput, addressInput, imgAsBytes);
                        showStudentList();
                        imgAsBytes = null;
                        addStatus = true;
                        remove(imageName);
                        add(image);
                    } else {
                        StudentDAO.getInstance().addStudent(idInput, nameInput, scoreInput, addressInput, imgAsBytes, noteInput);
                        imgAsBytes = null;
                        showStudentList();
                        addStatus = true;
                        remove(imageName);
                        add(image);
                    }

                    // refactor
                    if (addStatus) {
                        id.setText("");
                        name.setText("");
                        score.setText("");
                        address.setText("");
                        note.setText("");
                        addStatus = false;
                        repaint();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "You should fill the blank with right datatype ^^");
                }
            }
        });


        add(submitAddStudent);
    }

    private void browseImage() {

        image.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser browseImageFile = new JFileChooser();
                    FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
                    browseImageFile.addChoosableFileFilter(fnef);
                    int showOpenDialogue = browseImageFile.showOpenDialog(null);

                    if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
                        File selectedImageFile = browseImageFile.getSelectedFile();
                        String selectedImagePath = selectedImageFile.getAbsolutePath();
                        // JOptionPane.showMessageDialog(null,selectedImagePath);

                        String fileName = selectedImagePath.substring(selectedImagePath.lastIndexOf('\\') + 1);
                        String fileExtension = selectedImagePath.substring(selectedImagePath.lastIndexOf('.') + 1);

                        remove(image);
                        imageName = new JLabel(fileName);
                        imageName.setBounds(652, 90, 90, 20);
                        add(imageName);
                        repaint();

                        BufferedImage imm = ImageIO.read(new File(selectedImagePath));
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(imm, fileExtension, baos);
                        baos.flush();
                        imgAsBytes = baos.toByteArray();
                        baos.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        });

        add(image);

    }


    private void sortByID() {
        sortByIDUp = new JButton("▲");
        sortByIDUp.setBounds(35, 60, 20, 19);
        sortByIDUp.setMargin(new Insets(0, 0, 0, 0));

        sortByIDDown = new JButton("▼");
        sortByIDDown.setBounds(56, 60, 20, 19);
        sortByIDDown.setMargin(new Insets(0, 0, 0, 0));

        sortByIDUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showStudentList("id", true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        sortByIDDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showStudentList("id", false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(sortByIDUp);
        add(sortByIDDown);
    }

    private void sortByScore() {
        sortByScoreUp = new JButton("▲");
        sortByScoreUp.setBounds(305, 60, 20, 19);
        sortByScoreUp.setMargin(new Insets(0, 0, 0, 0));

        sortByScoreDown = new JButton("▼");
        sortByScoreDown.setBounds(325, 60, 20, 19);
        sortByScoreDown.setMargin(new Insets(0, 0, 0, 0));

        sortByScoreUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showStudentList("score", true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        sortByScoreDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showStudentList("score", false);
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        });

        add(sortByScoreUp);
        add(sortByScoreDown);
    }

    private void showImage() {
        JLabel idInteractStudent = new JLabel("Id");
        idInteractStudent.setBounds(650, 10, 15, 20);

        idInteract = new JTextField();
        idInteract.setBounds(665, 10, 75, 20);

        showImageBtn = new JButton("Show IMG");
        showImageBtn.setBounds(750, 10, 80, 19);
        showImageBtn.setMargin(new Insets(0, 5, 0, 5));

        showImageBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (StudentDAO.getInstance().findStudentById(Integer.parseInt(idInteract.getText())) != null) {
                        byte[] img = StudentDAO.getInstance().getImageStudentAsByte(Integer.parseInt(idInteract.getText()));
                        if (img == null) {
                            JOptionPane.showMessageDialog(null, "This student has not provided image yet.");
                        } else {
                            ByteArrayInputStream bis = new ByteArrayInputStream(img);
                            Image image = ImageIO.read(bis);

                            //display the image
                            ImageIcon icon = new ImageIcon(image);
                            JOptionPane.showMessageDialog(null, icon);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "The id you inputted might be wrong or empty");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "You should fill the blank with right data ^^");

                }
            }
        });

        add(idInteractStudent);
        add(idInteract);
        add(showImageBtn);

    }

    private void deleteStudent() {
        deleteStudentBtn = new JButton("Delete");
        deleteStudentBtn.setBounds(840, 10, 80, 19);
        deleteStudentBtn.setMargin(new Insets(0, 5, 0, 5));
        deleteStudentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (StudentDAO.getInstance().findStudentById(Integer.parseInt(idInteract.getText())) != null) {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to permanently Delete this student?", "Warning", JOptionPane.YES_NO_OPTION);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            StudentDAO.getInstance().deleteStudent(Integer.parseInt(idInteract.getText()));
                            repaint();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "The id you inputted might be wrong or empty");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "You should fill the blank with right data ^^");
                }
            }
        });
        add(deleteStudentBtn);
    }

    private void editStudentFrame() {
        editStudentBtn = new JButton("Edit");
        editStudentBtn.setBounds(930, 10, 80, 19);
        editStudentBtn.setMargin(new Insets(0, 5, 0, 5));

        editStudentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (StudentDAO.getInstance().findStudentById(Integer.parseInt(idInteract.getText())) != null) {
                        popUpFrame = new JFrame("Edit data student");

                        currentEditStudentId = Integer.parseInt(idInteract.getText());
                        JLabel labelTitle = new JLabel("Student " + currentEditStudentId);

                        JLabel labelId = new JLabel("Id");
                        JLabel labelName = new JLabel("Name");
                        JLabel labelScore = new JLabel("Score");
                        JLabel labelAddress = new JLabel("Address");
                        JLabel labelImage = new JLabel("Image");
                        JLabel labelNote = new JLabel("Note");

                        labelTitle.setBounds(20, 10, 100, 20);

                        labelId.setBounds(20, 50, 50, 20);
                        labelName.setBounds(20, 72, 50, 20);
                        labelScore.setBounds(20, 94, 50, 20);
                        labelAddress.setBounds(20, 116, 50, 20);
                        labelImage.setBounds(20, 138, 50, 20);
                        labelNote.setBounds(20, 160, 40, 20);

                        idEdit = new JTextField();
                        nameEdit = new JTextField();
                        scoreEdit = new JTextField();
                        addressEdit = new JTextField();
                        imageEdit = new JButton("Browse");
                        noteEdit = new JTextField();


                        idEdit.setBounds(70, 50, 70, 20);
                        nameEdit.setBounds(70, 72, 180, 20);
                        scoreEdit.setBounds(70, 94, 50, 20);
                        addressEdit.setBounds(70, 116, 300, 20);
                        noteEdit.setBounds(70, 160, 150, 20);

                        imageEdit.setBounds(70, 138, 90, 19);
                        imageEdit.setMargin(new Insets(0, 5, 0, 5));
                        addListenerBrowseImageButton();

                        submitChangeBtn = new JButton("Submit change");
                        submitChangeBtn.setMargin(new Insets(0, 5, 0, 5));
                        submitChangeBtn.setBounds(200, 200, 100, 30);

                        addListenerSubmitChangeButton();

                        popUpFrame.add(labelTitle);

                        popUpFrame.add(labelId);
                        popUpFrame.add(labelName);
                        popUpFrame.add(labelScore);
                        popUpFrame.add(labelAddress);
                        popUpFrame.add(labelImage);
                        popUpFrame.add(labelNote);

                        popUpFrame.add(idEdit);
                        popUpFrame.add(nameEdit);
                        popUpFrame.add(scoreEdit);
                        popUpFrame.add(addressEdit);
                        popUpFrame.add(imageEdit);
                        popUpFrame.add(noteEdit);

                        popUpFrame.add(submitChangeBtn);

                        popUpFrame.setLayout(null);
                        popUpFrame.setSize(500, 300);
                        popUpFrame.setVisible(true);
                        popUpFrame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "The id you inputted might be wrong or empty");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "You should fill the blank with right data ^^");
                }
            }
        });

        add(editStudentBtn);
    }

    private void addListenerSubmitChangeButton(){
        submitChangeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Student stu = StudentDAO.getInstance().findStudentById(currentEditStudentId);
                    if (idEdit != null && !idEdit.getText().equals("")) {
                        stu.setId(Integer.parseInt(idEdit.getText()));
                        idEdit = null;
                    }
                    if (nameEdit != null && !nameEdit.getText().equals("")) {
                        stu.setName(nameEdit.getText());
                        nameEdit = null;
                    }
                    if (scoreEdit != null && !scoreEdit.getText().equals("")) {
                        stu.setScore(Double.parseDouble(scoreEdit.getText()));
                        scoreEdit = null;
                    }
                    if (addressEdit != null && !addressEdit.getText().equals("")) {
                        stu.setAddress(addressEdit.getText());
                        addressEdit = null;
                    }
                    if (imageEditAsByte != null) {
                        stu.setImage(imageEditAsByte);
                        imageEditAsByte = null;
                    }
                    if (noteEdit != null && !noteEdit.getText().equals("")) {
                        stu.setNote(noteEdit.getText());
                        noteEdit = null;
                    }
                    StudentDAO.getInstance().updateData();
                    popUpFrame.setVisible(false);
                    showStudentList();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "You should fill the blank with right data ^^");
                }
                currentEditStudentId = 0;
            }
        });
    }

    private void addListenerBrowseImageButton(){
        imageEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser browseImageFile = new JFileChooser();
                    FileNameExtensionFilter fnef = new FileNameExtensionFilter("images", "png", "jpg", "jpeg");
                    browseImageFile.addChoosableFileFilter(fnef);
                    int showOpenDialogue = browseImageFile.showOpenDialog(null);

                    if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
                        File selectedImageFile = browseImageFile.getSelectedFile();
                        String selectedImagePath = selectedImageFile.getAbsolutePath();
                        // JOptionPane.showMessageDialog(null,selectedImagePath);

                        String fileName = selectedImagePath.substring(selectedImagePath.lastIndexOf('\\') + 1);
                        String fileExtension = selectedImagePath.substring(selectedImagePath.lastIndexOf('.') + 1);

                        popUpFrame.remove(imageEdit);
                        imageName = new JLabel(fileName);
                        imageName.setBounds(70, 138, 200, 20);;
                        popUpFrame.add(imageName);
                        popUpFrame.repaint();

                        BufferedImage imm = ImageIO.read(new File(selectedImagePath));
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(imm, fileExtension, baos);
                        baos.flush();
                        imageEditAsByte = baos.toByteArray();
                        baos.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        });
    }

    private void exportDataToCSV() {
        exportDataCSV = new JButton("Export to csv");
        exportDataCSV.setBounds(890, 430, 120, 19);
        exportDataCSV.setMargin(new Insets(0, 5, 0, 5));
        exportDataCSV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("data.csv"))){
                    writer.write("Id,");
                    writer.write("Name,");
                    writer.write("Score,");
                    writer.write("Address,");
                    writer.write("Image,");
                    writer.write("Note,\n");
                    for (Student stu : StudentDAO.getInstance().getAllStudents()) {
                        writer.write(String.valueOf(stu.getId()));
                        writer.write(",");
                        writer.write(stu.getName());
                        writer.write(",");
                        writer.write(String.valueOf(stu.getScore()));
                        writer.write(",");
                        writer.write(stu.getAddress());
                        writer.write(",");
                        writer.write(String.valueOf(stu.getImage()));
                        writer.write(",");
                        writer.write(stu.getNote());
                        writer.write("\n");

                    }
                    JOptionPane.showMessageDialog(null, "Export successfully! ^^");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(exportDataCSV);
    }
}
