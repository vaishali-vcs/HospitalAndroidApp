package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.dao.PatientDao;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.database.HospitalDatabase;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Patient;

import java.util.List;

public class PatientRepository {

    private PatientDao patientDao;
    private LiveData<List<Patient>> allPatients;

    public PatientRepository(Application application) {
        HospitalDatabase db = HospitalDatabase.getInstance(application);
        patientDao = db.patientDao();
        allPatients = patientDao.getAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }

    public void insert(Patient patient) {
        new InsertPatientAsyncTask(patientDao).execute(patient);
    }

    public void delete(Patient patient){
        new DeletePatientAsyncTask(patientDao).execute(patient);
    }

    public LiveData<Patient> findbyPatientID(int patientID) {
        return patientDao.getByPatientID(patientID);
    }

    public void update(Patient patient){
        new UpdatePatientAsyncTask(patientDao).execute(patient);
    }

    private static class InsertPatientAsyncTask extends AsyncTask<Patient, Void, Void> {
        private PatientDao patientDao;

        private InsertPatientAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.insert(patients[0]);

            return null;
        }
    }

    private static class UpdatePatientAsyncTask extends AsyncTask<Patient, Void, Void>
    {
        private PatientDao patientDao;

        private UpdatePatientAsyncTask(PatientDao patientDao)
        {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.update(patients[0]);

            return null;
        }
    }

    private static class DeletePatientAsyncTask extends AsyncTask<Patient, Void, Void>
    {
        private PatientDao patientDao;

        private DeletePatientAsyncTask(PatientDao patientDao)
        {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.delete(patients[0]);

            return null;
        }
    }
}
