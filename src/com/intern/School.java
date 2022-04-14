package com.intern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class School {

    private HashMap<String, List<String>> teachers;
    private HashMap<String, List<String>> pupils;
    private HashMap<String, List<String>> subjects;

    //Constructor: Initialization of variables
    public School() {
        this.teachers = new HashMap<>();
        this.pupils = new HashMap<>();
        this.subjects = new HashMap<>();
    }

    //Add teacher
    public void addTeacher(String teacher) {
        List<String> subjects = new ArrayList<>();
        teachers.put(teacher, subjects);
    }

    //Add subject
    public void addSubject(String teacher, String subject) {
        if (teachers.containsKey(teacher)) {
            List<String> s = teachers.get(teacher);
            s.add(subject);
            teachers.put(teacher, s);
            if (!subjects.containsKey(subject)) {
                subjects.put(subject, new ArrayList<>());
            }
        }
    }

    //Add pupil
    public void addPupil(String pupil, String subject) {
        List<String> list = new ArrayList<>();
        List<String> l;
        l = subjects.get(subject);
        if (l != null) {
            l.add(pupil);
            subjects.put(subject, l);
            if (pupils.containsKey(pupil)) {
                list = pupils.get(pupil);
            }
            list.add(subject);
            pupils.put(pupil, list);
        }
    }

    //Get teacher
    public Iterator getTeachers(String pupil) {
        List<String> ans = new ArrayList<>();
        List<String> list = pupils.get(pupil);
        for (String s : list) {
            for (String key : teachers.keySet()) {
                if (teachers.get(key).contains(s)) {
                    System.out.println(key);
                    ans.add(key);
                }
            }
        }
        return ans.iterator();
    }

    //Get pupil
    public Iterator getPupils(String teacher) {
        List<String> ans = new ArrayList<>();
        List<String> list = teachers.get(teacher);
        for (String s : list) {
            for (String key : subjects.keySet()) {
                if (key.equals(s)) ans.addAll(subjects.get(key));
            }
        }
        return ans.iterator();
    }

    //Remove teacher
    public void removeTeacher(String teacher) {
        List<String> list = teachers.get(teacher);
        int k;
        for (String s : list) {
            k = 0;
            for (String key : teachers.keySet()) {
                if (!key.equals(teacher) && teachers.get(key).contains(s)) k++;
            }
            if (k == 0) {
                subjects.remove(s);
                for (String pupil : pupils.keySet()) {
                    List<String> subj = pupils.get(pupil);
                    if (subj.contains(s)) {
                        subj.remove(s);
                        pupils.put(pupil, subj);
                    }
                }
            }
        }
        teachers.remove(teacher);
    }
}
