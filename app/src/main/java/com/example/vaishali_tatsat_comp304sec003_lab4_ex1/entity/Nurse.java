package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nurse_table")
public class Nurse {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    private int nurseId;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String department;

    @NonNull
    private String password;

    public Nurse(@NonNull int nurseId, @NonNull String firstname, @NonNull String lastname, @NonNull String department, @NonNull String password) {
        this.nurseId = nurseId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }

    @NonNull
    public int getNurseId() {
        return nurseId;
    }

    @NonNull
    public String getFirstname() {
        return firstname;
    }

    @NonNull
    public String getLastname() {
        return lastname;
    }

    @NonNull
    public String getDepartment() {
        return department;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
}
