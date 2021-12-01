package com.example.vaishali_tatsat_comp304sec003_lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Nurse;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.viewmodel.NurseViewModel;

public class RegisterNurseActivity extends AppCompatActivity {

    EditText edit_text_NurseID, edit_text_NurseFName, edit_text_NurseLName, edit_text_NurseDepartment, edit_text_NursePassword;
    private NurseViewModel nurseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_nurse);
        nurseViewModel = new ViewModelProvider(this).get(NurseViewModel.class);

        edit_text_NurseID = findViewById(R.id.edit_text_NurseID);
        edit_text_NurseFName = findViewById(R.id.edit_text_NurseFName);
        edit_text_NurseLName = findViewById(R.id.edit_text_NurseLName);
        edit_text_NurseDepartment = findViewById(R.id.edit_text_NurseDepartment);
        edit_text_NursePassword = findViewById(R.id.edit_text_NursePassword);
    }

    public void btnSubmitClick(View view)
    {
        if(edit_text_NurseID.getText().toString().trim().isEmpty() ||
           edit_text_NurseFName.getText().toString().trim().isEmpty() ||
           edit_text_NurseLName.getText().toString().trim().isEmpty() ||
           edit_text_NurseDepartment.getText().toString().trim().isEmpty() ||
                edit_text_NursePassword.getText().toString().trim().isEmpty())
            Toast.makeText(this, "Please fill the empty fields", Toast.LENGTH_SHORT).show();
        else {
            nurseViewModel.insert(new Nurse( Integer.parseInt(edit_text_NurseID.getText().toString()), edit_text_NurseFName.getText().toString(),
                    edit_text_NurseLName.getText().toString(), edit_text_NurseDepartment.getText().toString(),
                    edit_text_NursePassword.getText().toString()));
            finish();

            TextView textview_RegisterStatus = findViewById(R.id.textview_RegisterStatus);
            textview_RegisterStatus.setText(getResources().getString(R.string.strRegisterstatus));

            Toast.makeText(this, "Success. Please go back and Login", Toast.LENGTH_SHORT).show();
        }

    }
}