package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Test;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.repository.TestRepository;

import java.util.List;

public class TestViewModel extends AndroidViewModel {

    private TestRepository testRepository;

    public TestViewModel(@NonNull Application application) {
        super(application);

        testRepository = new TestRepository(application);
    }

    public void insert(Test test){
        testRepository.insert(test);
    }

    public void update(Test test){
        testRepository.update(test);
    }

    public void delete(Test test){
        testRepository.delete(test);
    }

    public  LiveData<List<Test>> findTestByPatientID(int patientID) {return testRepository.findTestbyPatientID(patientID); }

}
