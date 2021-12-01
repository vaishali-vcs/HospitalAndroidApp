package com.example.vaishali_tatsat_comp304sec003_lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Nurse;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.viewmodel.NurseViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText edit_text_NurseID, edit_text_NursePassword;
    public static final String PREFERENCE_FILE_NURSEID = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.PREFERENCE_FILE_NURSEID";

    private NurseViewModel nurseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nurseViewModel = new ViewModelProvider(this).get(NurseViewModel.class);

        edit_text_NurseID = findViewById(R.id.edit_text_loginID);
        edit_text_NursePassword = findViewById(R.id.edit_text_password);

        try {
            SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
            int preference_nurseid = sharedPref.getInt(PREFERENCE_FILE_NURSEID, -1);

            if (preference_nurseid > 0)
            {
                Intent intent = new Intent(this, PatientActivity.class);
                startActivity(intent);
            }
        }
        catch (Exception ex)
        {

        }
    }

    public void btnLoginClick(View view)
    {
        if(edit_text_NurseID.getText().toString().trim().isEmpty() ||
                edit_text_NursePassword.getText().toString().trim().isEmpty())
            Toast.makeText(this, "Please fill the empty fields", Toast.LENGTH_SHORT).show();
        else
        {

            TextView textview_LoginStatus = findViewById(R.id.textview_LoginStatus);

            FindNurseAsyncTask task = new FindNurseAsyncTask(nurseViewModel, Integer.parseInt(edit_text_NurseID.getText().toString().trim()),
                    edit_text_NursePassword.getText().toString().trim());
            List<Nurse> nurse;
            try {
                nurse = task.execute().get();

                if (nurse.size() > 0)
                {

                    SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(PREFERENCE_FILE_NURSEID, nurse.get(0).getNurseId());
                    editor.apply();

                    Intent intent = new Intent(this, PatientActivity.class);
                    startActivity(intent);
                }
                else
                {
                    edit_text_NurseID.getText().clear();
                    edit_text_NursePassword.getText().clear();
                    textview_LoginStatus.setText(getResources().getString(R.string.strloginfail));

                    Toast.makeText(this, "Unsuccessful Login. Please retry.", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            catch (Exception ex)
            {
                edit_text_NurseID.getText().clear();
                edit_text_NursePassword.getText().clear();
                textview_LoginStatus.setText(getResources().getString(R.string.strloginfail));

                edit_text_NurseID.getText().clear();
                edit_text_NursePassword.getText().clear();
                Toast.makeText(this, "Unsuccessful Login. Please retry.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private class FindNurseAsyncTask extends AsyncTask<Void, Void, List<Nurse>>
    {
        private NurseViewModel nurseViewModel;
        int id;
        String pwd;

        FindNurseAsyncTask(NurseViewModel nurseViewModel, int nurseID, String password)
        {
            this.nurseViewModel = nurseViewModel;
            this.id = nurseID;
            this.pwd = password;
        }

        @Override
        protected List<Nurse> doInBackground(Void... params) {
            return nurseViewModel.findByNurseID(id, pwd);
        }

        @Override
        protected void onPostExecute(List<Nurse> listLiveData) {
            super.onPostExecute(listLiveData);
        }
    }

}