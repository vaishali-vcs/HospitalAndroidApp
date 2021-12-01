package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_table")
public class Test {
    @PrimaryKey(autoGenerate = true)
    private int testId;

    @NonNull
    private int patientId;

    @NonNull
    private int nurseId;

    @NonNull
    private String testName;


    @NonNull
    private int BPL;

    @NonNull
    private String bloodGroup;

    @NonNull
    private int BPH;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    @NonNull
    public String getTestName() {
        return testName;
    }

    public void setTestName(@NonNull String testName) {
        this.testName = testName;
    }

    public int getBPL() {
        return BPL;
    }

    public void setBPL(int BPL) {
        this.BPL = BPL;
    }

    @NonNull
    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(@NonNull String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getBPH() {
        return BPH;
    }

    public void setBPH(int BPH) {
        this.BPH = BPH;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @NonNull
    private float temperature;

    public Test(int patientId, int nurseId, @NonNull String testName, int BPL, @NonNull String bloodGroup, int BPH, float temperature) {
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.testName = testName;
        this.BPL = BPL;
        this.bloodGroup = bloodGroup;
        this.BPH = BPH;
        this.temperature = temperature;
    }


}
