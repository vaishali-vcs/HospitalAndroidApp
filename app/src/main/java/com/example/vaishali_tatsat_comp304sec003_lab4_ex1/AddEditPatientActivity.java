package com.example.vaishali_tatsat_comp304sec003_lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditPatientActivity extends AppCompatActivity {

    private EditText edit_text_PatientID, edit_text_PatientFName, edit_text_PatientRoom,
            edit_text_PatientLName, edit_text_PatientDepartment,  edit_text_PatientNurseId;


    public static final String EXTRA_PATIENTID = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTID";
    public static final String EXTRA_PATIENTFNAME = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTFNAME";
    public static final String EXTRA_PATIENTLNAME = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTLNAME";
    public static final String EXTRA_PATIENTDEPARTMENT = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTDEPARTMENT";
    public static final String EXTRA_PATIENTNURSEID= "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTNURSEID";
    public static final String EXTRA_PATIENTROOM= "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTROOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_patient);

        edit_text_PatientID = findViewById(R.id.edit_text_PatientID);
        edit_text_PatientFName = findViewById(R.id.edit_text_PatientFName);
        edit_text_PatientRoom = findViewById(R.id.edit_text_PatientRoom);
        edit_text_PatientLName = findViewById(R.id.edit_text_PatientLName);
        edit_text_PatientDepartment = findViewById(R.id.edit_text_PatientDepartment);
        edit_text_PatientNurseId = findViewById(R.id.edit_text_PatientNurseId);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();


        if (intent.hasExtra(EXTRA_PATIENTID)) {
            setTitle("Edit Patient");

            View btnTest = findViewById(R.id.btnTest);
            btnTest.setVisibility(View.VISIBLE);

            edit_text_PatientID.setText(String.valueOf(intent.getIntExtra(EXTRA_PATIENTID, -1)));
            edit_text_PatientFName.setText(intent.getStringExtra(EXTRA_PATIENTFNAME));
            edit_text_PatientRoom.setText(intent.getStringExtra(EXTRA_PATIENTROOM));
            edit_text_PatientLName.setText(intent.getStringExtra(EXTRA_PATIENTLNAME));
            edit_text_PatientDepartment.setText(intent.getStringExtra(EXTRA_PATIENTDEPARTMENT));
            edit_text_PatientNurseId.setText(String.valueOf(intent.getIntExtra(EXTRA_PATIENTNURSEID, -1)));
        }

        else {
            View btnTest = findViewById(R.id.btnTest);
            btnTest.setVisibility(View.GONE);
            setTitle("Add Patient");
        }
    }

    public void btnSubmitClick(View view)
    {
        String PatientFName = edit_text_PatientFName.getText().toString();
        String PatientLName = edit_text_PatientLName.getText().toString();
        String PatientRoom = edit_text_PatientRoom.getText().toString();
        String PatientDepartment = edit_text_PatientDepartment.getText().toString();
        int PatientNurseId = Integer.parseInt(edit_text_PatientNurseId.getText().toString());

        if(PatientNurseId == -1 ||
                PatientFName.trim().isEmpty() || PatientLName.trim().isEmpty() ||
                PatientRoom.trim().isEmpty() || PatientDepartment.trim().isEmpty() ) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = getIntent().getIntExtra(EXTRA_PATIENTID, -1);

        Intent data = new Intent();

        if (id != -1)
            data.putExtra(EXTRA_PATIENTID, id);

        data.putExtra(EXTRA_PATIENTFNAME, PatientFName);
        data.putExtra(EXTRA_PATIENTLNAME, PatientLName);
        data.putExtra(EXTRA_PATIENTROOM, PatientRoom);
        data.putExtra(EXTRA_PATIENTDEPARTMENT, PatientDepartment);
        data.putExtra(EXTRA_PATIENTNURSEID, PatientNurseId);

        setResult(RESULT_OK, data);
        finish();
    }

    public void btnTestClick(View view)
    {
        Intent newActivity1 = new Intent(this, TestActivity.class);
        newActivity1.putExtra("PatientFName", edit_text_PatientFName.getText().toString());
        newActivity1.putExtra("PatientLName", edit_text_PatientLName.getText().toString());
        newActivity1.putExtra("PatientID", Integer.parseInt(edit_text_PatientID.getText().toString()));
        startActivity(newActivity1);
    }
}