package com.intern;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Graphics {

    public static void main(String[] args) {
        School school = new School();

        //Creating frame
        JFrame frame = new JFrame("School");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        //Creating text fields and labels
        JPanel panel = new JPanel();
        JLabel teach = new JLabel("Teach:");
        JTextField teacher = new JTextField(5);
        JLabel subj = new JLabel("Subj:");
        JTextField subject = new JTextField(5);
        JLabel pup = new JLabel("Pupil:");
        JTextField pupil = new JTextField(5);

        //Creating buttons
        JButton AddT = new JButton("Add Teach");
        JButton AddS = new JButton("Add Subj");
        JButton AddP = new JButton("Add Pupil");
        JButton disT = new JButton("Display Teachers");
        JButton disP = new JButton("Display Pupils");
        JButton remove = new JButton("Remove Teacher");

        //Adding created components to the panel
        panel.add(teach);
        panel.add(teacher);
        panel.add(subj);
        panel.add(subject);
        panel.add(pup);
        panel.add(pupil);
        panel.add(AddT);
        panel.add(AddS);
        panel.add(AddP);
        panel.add(disP);
        panel.add(disT);
        panel.add(remove);

        //Creating textarea for displaying pupils and teachers
        JTextArea ta = new JTextArea();

        //Adding created Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

        //Adding action listeners and making buttons functioning
        AddT.addActionListener(e -> school.addTeacher(teacher.getText()));
        AddS.addActionListener(e -> school.addSubject(teacher.getText(), subject.getText()));
        AddP.addActionListener(e -> school.addPupil(pupil.getText(), subject.getText()));
        remove.addActionListener(e -> {
            ta.setText("");
            school.removeTeacher(teacher.getText());
        });

        //Adding action listeners to display buttons and displaying content
        disT.addActionListener(e -> {
            ta.setText("");
            Iterator<String> it = school.getTeachers(pupil.getText());
            if (it != null) {
                while (it.hasNext()) {
                    ta.append(it.next() + "\n");
                }
            }
        });
        disP.addActionListener(e -> {
            ta.setText("");
            Iterator<String> it = school.getPupils(teacher.getText());
            if (it != null) {
                while (it.hasNext()) {
                    ta.append(it.next() + "\n");
                }
            }
        });
    }
}
