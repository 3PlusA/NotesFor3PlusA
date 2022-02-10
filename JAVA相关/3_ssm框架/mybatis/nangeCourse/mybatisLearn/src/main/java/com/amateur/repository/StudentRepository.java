package com.amateur.repository;

import com.amateur.entity.Student;

public interface StudentRepository {
    public Student findById(long id);
}
