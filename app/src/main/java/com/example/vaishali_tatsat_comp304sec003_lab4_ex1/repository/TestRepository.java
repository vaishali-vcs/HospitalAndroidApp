package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.dao.TestDao;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.database.HospitalDatabase;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Test;

import java.util.List;

public class TestRepository {

    private TestDao testDao;

    public TestRepository(Application application) {
        HospitalDatabase db = HospitalDatabase.getInstance(application);
        testDao = db.testDao();
    }

    public LiveData<List<Test>> findTestbyPatientID(int patientID) {
        return testDao.getTestByPatientID(patientID);
    }

    public void insert(Test test){
        new InsertTestAsyncTask(testDao).execute(test);
    }

    public void update(Test test){
        new UpdateUpdateAsyncTask(testDao).execute(test);
    }

    public void delete(Test test){
        new DeleteTestAsyncTask(testDao).execute(test);
    }

    private static class InsertTestAsyncTask extends AsyncTask<Test, Void, Void>
    {
        private TestDao testDao;

        private InsertTestAsyncTask(TestDao testDao)
        {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... tests) {
            testDao.insert(tests[0]);

            return null;
        }
    }

    private static class UpdateUpdateAsyncTask extends AsyncTask<Test, Void, Void>
    {
        private TestDao testDao;

        private UpdateUpdateAsyncTask(TestDao testDao)
        {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... tests) {
            testDao.update(tests[0]);

            return null;
        }
    }
    private static class DeleteTestAsyncTask extends AsyncTask<Test, Void, Void>
    {
        private TestDao testDao;

        private DeleteTestAsyncTask(TestDao testDao)
        {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... tests) {
            testDao.delete(tests[0]);

            return null;
        }
    }

}
