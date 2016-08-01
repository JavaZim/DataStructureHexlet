package com.java.datastructure.Hexlet.module.m01e01;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Group implements Iterable<Student> {

    private Student[] students;

    public Group(final Student[] students) {
        this.students = students;
    }

    public Iterator<Student> iterator() {
        return new StudentsIterator(students);
    }

    private static class StudentsIterator implements Iterator<Student> {
        // BEGIN (write your solution here)
        private  Student [] students;
        private int index = 0;

        private StudentsIterator(final Student [] students) {
            this.students = students;
        }

        public boolean hasNext() {
            return this.students.length > index;
        }

        public Student next() throws NoSuchElementException{

            if(!hasNext())
                throw new NoSuchElementException();
            else
                return this.students[this.index++];

        }
        // END
    }

}
