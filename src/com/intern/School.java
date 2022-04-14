package com.intern;

import java.util.*;

public class School {

    private HashMap<String, Set<String>> teachers;
    private HashMap<String, Set<String>> pupils;
    private HashMap<String, Set<String>> subjects;

    //Constructor: Initialization of variables
    public School() {
        this.teachers = new HashMap<>();
        this.pupils = new HashMap<>();
        this.subjects = new HashMap<>();
    }

    //Add teacher
    public void addTeacher(String teacher) {
        Set<String> subjects = new HashSet<>();
        teachers.put(teacher, subjects);
    }

    //Add subject
    public void addSubject(String teacher, String subject) {
        if (teachers.containsKey(teacher)) {
            Set<String> s = teachers.get(teacher);
            s.add(subject);
            teachers.put(teacher, s);
            if (!subjects.containsKey(subject)) {
                subjects.put(subject, new HashSet<>());
            }
        }
    }

    //Add pupil
    public void addPupil(String pupil, String subject) {
        Set<String> list = new HashSet<>();
        Set<String> l;
        l = subjects.get(subject);
        if (subjects.containsKey(subject)) {
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
    public Iterator<String> getTeachers(String pupil) {

        Set<String> ans = new HashSet<>();
        Set<String> list = pupils.get(pupil);
        if (list == null) return null;
        for (String s : list) {
            for (String key : teachers.keySet()) {
                if (teachers.get(key).contains(s)) {
                    ans.add(key);
                }
            }
        }
        return ans.iterator();
    }

    //Get pupil
    public Iterator<String> getPupils(String teacher) {
        Set<String> ans = new HashSet<>();
        Set<String> list = teachers.get(teacher);
        if (list == null) return null;
        for (String s : list) {
            for (String key : subjects.keySet()) {
                if (key.equals(s)) ans.addAll(subjects.get(key));
            }
        }
        return ans.iterator();
    }

    //Remove teacher
    public void removeTeacher(String teacher) {
        if (teachers.containsKey(teacher)) {
            Set<String> list = teachers.get(teacher);
            int k;
            for (String s : list) {
                k = 0;
                for (String key : teachers.keySet()) {
                    if (!key.equals(teacher) && teachers.get(key).contains(s)) k++;
                }
                if (k == 0) {
                    subjects.remove(s);
                    for (String pupil : pupils.keySet()) {
                        Set<String> subj = pupils.get(pupil);
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
}
