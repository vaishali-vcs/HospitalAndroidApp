package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Patient;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.repository.PatientRepository;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private LiveData<List<Patient>> allPatients;

    public PatientViewModel(Application application) {
        super((application));
        patientRepository = new PatientRepository(application);
        allPatients = patientRepository.getAllPatients();
    }

    public  LiveData<Patient> findByPatientID(int patientID) {return patientRepository.findbyPatientID(patientID); }

    public void insert(Patient patient) { patientRepository.insert(patient); }

    public void update(Patient patient){
        patientRepository.update(patient);
    }

    public void delete(Patient patient){
        patientRepository.delete(patient);
    }

    public LiveData<List<Patient>> getAllPatients() { return allPatients; }
}

