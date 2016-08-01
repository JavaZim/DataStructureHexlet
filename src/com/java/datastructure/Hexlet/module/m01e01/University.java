package com.java.datastructure.Hexlet.module.m01e01;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class University implements Iterable<Student> {

    private final Student[] students;

    public University(final Student[] students) {
        this.students = students;
    }

    public Iterator<Student> iterator() {
        return new StudentsIterator();
    }

    private class StudentsIterator implements Iterator<Student> {
        // BEGIN (write your solution here)
        int index = 0;

        public boolean hasNext() {
            return University.this.students.length > index;
        }

        public Student next() throws NoSuchElementException{
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            else return University.this.students[index++];

        }
        // END
    }

}
