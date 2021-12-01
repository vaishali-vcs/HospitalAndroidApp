package com.example.vaishali_tatsat_comp304sec003_lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditTestActivity extends AppCompatActivity {
    private EditText edit_text_Temperature, edit_text_PatientNurseId, edit_text_BloodGroup, edit_text_BPL, edit_text_BPH, edit_text_TestName,
            edit_text_PID, edit_text_FName, edit_text_LName;

    public static final String EXTRA_TESTID = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_TESTID";
    public static final String EXTRA_PATIENTID = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTID";
    public static final String EXTRA_PATIENTFNAME = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTFNAME";
    public static final String EXTRA_PATIENTLNAME = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTLNAME";
    public static final String EXTRA_PATIENTBPH = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTBPH";
    public static final String EXTRA_PATIENTBPL = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTBPL";
    public static final String EXTRA_TESTNAME = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_TESTNAME";
    public static final String EXTRA_BLOOD_GROUP = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_BLOOD_GROUP";
    public static final String EXTRA_TEMPERATURE = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_TEMPERATURE";
    public static final String EXTRA_PATIENTNURSEID = "com.example.vaishalisiddeshwar_tatsattendulkar_comp304sec003_lab4_ex1.EXTRA_PATIENTNURSEID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_test);

        edit_text_Temperature = findViewById(R.id.edit_text_Temperature);
        edit_text_PatientNurseId = findViewById(R.id.edit_text_PNurseId);
        edit_text_BloodGroup = findViewById(R.id.edit_text_BloodGroup);
        edit_text_BPL = findViewById(R.id.edit_text_BPL);
        edit_text_BPH = findViewById(R.id.edit_text_BPH);
        edit_text_TestName = findViewById(R.id.edit_text_TestName);
        edit_text_PID = findViewById(R.id.edit_text_PID);
        edit_text_LName = findViewById(R.id.edit_text_LName);
        edit_text_FName = findViewById(R.id.edit_text_FName);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_BLOOD_GROUP)){
            edit_text_Temperature.setText(String.valueOf(intent.getFloatExtra(EXTRA_TEMPERATURE, -1)));
            edit_text_PatientNurseId.setText(String.valueOf(intent.getIntExtra(EXTRA_PATIENTNURSEID, -1)));
            edit_text_BloodGroup.setText(intent.getStringExtra(EXTRA_BLOOD_GROUP));
            edit_text_BPL.setText(String.valueOf(intent.getIntExtra(EXTRA_PATIENTBPL, -1)));
            edit_text_BPH.setText(String.valueOf(intent.getIntExtra(EXTRA_PATIENTBPH, -1)));
            edit_text_TestName.setText(intent.getStringExtra(EXTRA_TESTNAME));
            edit_text_LName.setText("LName: " + intent.getStringExtra(EXTRA_PATIENTLNAME));
            edit_text_FName.setText("FName: " + intent.getStringExtra(EXTRA_PATIENTFNAME));
            edit_text_PID.setText("PatientID: " + String.valueOf(intent.getIntExtra(EXTRA_PATIENTID, -1)));

            setTitle("Edit Test");
        }

        else if (intent.hasExtra(EXTRA_PATIENTID)) {
            setTitle("Add Test");
            edit_text_PID.setText("PatientID: " + String.valueOf(intent.getIntExtra(EXTRA_PATIENTID, -1)));
            edit_text_LName.setText("LName: " + intent.getStringExtra(EXTRA_PATIENTLNAME));
            edit_text_FName.setText("FName: " + intent.getStringExtra(EXTRA_PATIENTFNAME));
        }

        else setTitle("Add Test");
    }

    public void btnSubmitClick(View view) {
        float temperature = Float.parseFloat(edit_text_Temperature.getText().toString());
        int PatientNurseID = Integer.parseInt(edit_text_PatientNurseId.getText().toString());
        String BloodGroup = edit_text_BloodGroup.getText().toString();
        int bph = Integer.parseInt(edit_text_BPH.getText().toString());
        int bpl = Integer.parseInt(edit_text_BPL.getText().toString());
        String TestName = edit_text_TestName.getText().toString();
        int PatientID = Integer.parseInt(edit_text_PID.getText().toString().split(":")[1].trim());
        String PatientFName = edit_text_FName.getText().toString().toString().split(":")[1].trim();
        String PatientLName = edit_text_LName.getText().toString().toString().split(":")[1].trim();

        if(PatientID == 1 || PatientNurseID == -1 ||temperature == -1 || bph == -1 || bpl == -1 ||
                TestName.trim().isEmpty() ||
                PatientFName.trim().isEmpty() || PatientLName.trim().isEmpty() ||
                BloodGroup.trim().isEmpty() ) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int testid = getIntent().getIntExtra(EXTRA_TESTID, -1);

        Intent data = new Intent();

        if (testid != -1)
            data.putExtra(EXTRA_TESTID, testid);

        data.putExtra(EXTRA_PATIENTID, PatientID);
        data.putExtra(EXTRA_TESTNAME, TestName);
        data.putExtra(EXTRA_PATIENTFNAME, PatientFName);
        data.putExtra(EXTRA_PATIENTLNAME, PatientLName);
        data.putExtra(EXTRA_PATIENTNURSEID, PatientNurseID);
        data.putExtra(EXTRA_BLOOD_GROUP, BloodGroup);
        data.putExtra(EXTRA_PATIENTBPH, bph);
        data.putExtra(EXTRA_PATIENTBPL, bpl);
        data.putExtra(EXTRA_TEMPERATURE, temperature);

        setResult(RESULT_OK, data);
        finish();
    }
}