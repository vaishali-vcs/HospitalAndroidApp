package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Test;

import java.util.List;

@Dao
public interface TestDao {
    @Insert
    void insert(Test test);

    @Update
    void update(Test test);

    @Delete
    void delete(Test test);

    @Query("Select * FROM test_table where patientID = :patientID")
    LiveData<List<Test>> getTestByPatientID(int patientID);

}
