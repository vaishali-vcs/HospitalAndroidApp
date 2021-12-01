package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.dao.NurseDao;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.dao.PatientDao;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.dao.TestDao;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Nurse;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Patient;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Test;

@Database(entities = {Patient.class, Nurse.class, Test.class},
        version = 1, exportSchema = false)
public abstract class HospitalDatabase extends RoomDatabase {

    public abstract PatientDao patientDao();
    public abstract NurseDao nurseDao();
    public abstract TestDao testDao();

    private static volatile HospitalDatabase INSTANCE;

    public static HospitalDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    HospitalDatabase.class, "hospital_database")
                    .addCallback(roomCallBack)
                    .build();

        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private PatientDao patientDao;
        private TestDao testDao;

        private PopulateDbAsyncTask(HospitalDatabase db){
            patientDao = db.patientDao();
            testDao = db.testDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            patientDao.insert(new Patient("Tom", "Jones", "Cardio", 12, "A12"));
            patientDao.insert(new Patient("Joyce", "Stringfellow", "Paedatrics", 123, "G21"));
            patientDao.insert(new Patient("Fernando", "Young", "Physio", 1011, "D34"));
            patientDao.insert(new Patient("Patrick", "Miller", "Cancer", 1011, "T56"));
            patientDao.insert(new Patient("Greg", "Enriquez", "Respiratory", 1011, "R78"));

            return null;
        }
    }
}
