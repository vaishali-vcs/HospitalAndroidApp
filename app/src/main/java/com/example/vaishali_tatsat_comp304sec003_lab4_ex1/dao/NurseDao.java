package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.dao;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Nurse;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NurseDao {
    @Insert
    void insert(Nurse nurse);

    @Query("Select * FROM nurse_table where nurseId = :nurseID AND password = :password")
    List<Nurse> getByNurseID(int nurseID, String password);
}
