package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.dao.NurseDao;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.database.HospitalDatabase;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Nurse;

import java.util.List;

public class NurseRepository {

    public NurseDao nurseDao;
    public LiveData<List<Nurse>> nurse;

    public NurseRepository(Application application)
    {
        HospitalDatabase db = HospitalDatabase.getInstance(application);
        nurseDao = db.nurseDao();
    }

    public void insert(Nurse nurse){
        new InsertNurseAsyncTask(nurseDao).execute(nurse);
    }

    public List<Nurse> findbyNurseID(int nurseID, String password) {return nurseDao.getByNurseID(nurseID, password); }



    private static class InsertNurseAsyncTask extends AsyncTask<Nurse, Void, Void>
    {
        private NurseDao nurseDao;

        private InsertNurseAsyncTask(NurseDao nurseDao)
        {
            this.nurseDao = nurseDao;
        }

        @Override
        protected Void doInBackground(Nurse... nurses) {
            nurseDao.insert(nurses[0]);

            return null;
        }
    }


}
