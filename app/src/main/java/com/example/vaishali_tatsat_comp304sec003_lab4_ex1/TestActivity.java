package com.example.vaishali_tatsat_comp304sec003_lab4_ex1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.adapter.TestAdapter;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Test;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.viewmodel.TestViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    public static final int ADD_TEST_REQUEST = 1;
    public static final int EDIT_TEST_REQUEST = 2;
    private int PatientID;
    private String PatientFName;
    private String PatientLName;

    private TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        PatientFName = intent.getExtras().getString("PatientFName");
        PatientLName = intent.getExtras().getString("PatientLName");
        PatientID = intent.getExtras().getInt("PatientID");

        FloatingActionButton buttonAddTest = findViewById(R.id.button_add_test);
        buttonAddTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestActivity.this, AddEditTestActivity.class);
                intent.putExtra(AddEditTestActivity.EXTRA_PATIENTFNAME, PatientFName);
                intent.putExtra(AddEditTestActivity.EXTRA_PATIENTLNAME, PatientLName);
                intent.putExtra(AddEditTestActivity.EXTRA_PATIENTID, PatientID);

                startActivityForResult(intent, ADD_TEST_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.test_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TestAdapter adapter = new TestAdapter();
        recyclerView.setAdapter(adapter);

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        testViewModel.findTestByPatientID(PatientID).observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                //update our RecyclerView
                adapter.setTests(tests);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                testViewModel.delete(adapter.getTestAt(viewHolder.getAdapterPosition()));

                Toast.makeText(TestActivity.this, "Test Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Test test) {
                Intent intent = new Intent(TestActivity.this, AddEditTestActivity.class);

                intent.putExtra(AddEditTestActivity.EXTRA_TESTID, test.getTestId());
                intent.putExtra(AddEditTestActivity.EXTRA_PATIENTID, PatientID);
                intent.putExtra(AddEditTestActivity.EXTRA_PATIENTFNAME, PatientFName);
                intent.putExtra(AddEditTestActivity.EXTRA_PATIENTLNAME, PatientLName);
                intent.putExtra(AddEditTestActivity.EXTRA_PATIENTBPH, test.getBPH());
                intent.putExtra(AddEditTestActivity.EXTRA_PATIENTBPL, test.getBPL());
                intent.putExtra(AddEditTestActivity.EXTRA_TESTNAME, test.getTestName());
                intent.putExtra(AddEditTestActivity.EXTRA_BLOOD_GROUP, test.getBloodGroup());
                intent.putExtra(AddEditTestActivity.EXTRA_TEMPERATURE, test.getTemperature());
                intent.putExtra(AddEditTestActivity.EXTRA_PATIENTNURSEID, test.getNurseId());

                startActivityForResult(intent, EDIT_TEST_REQUEST);
            }
        });

        setTitle(PatientFName + "," + PatientLName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TEST_REQUEST && resultCode == RESULT_OK) {

            int patientid = data.getIntExtra(AddEditTestActivity.EXTRA_PATIENTID, -1);

            int bph = data.getIntExtra(AddEditTestActivity.EXTRA_PATIENTBPH, -1);
            int bpl = data.getIntExtra(AddEditTestActivity.EXTRA_PATIENTBPL, -1);
            String testname = data.getStringExtra(AddEditTestActivity.EXTRA_TESTNAME);
            String bloodgroup = data.getStringExtra(AddEditTestActivity.EXTRA_BLOOD_GROUP);
            float temp = data.getFloatExtra(AddEditTestActivity.EXTRA_TEMPERATURE, -1);
            String patientLFame = data.getStringExtra(AddEditTestActivity.EXTRA_PATIENTLNAME);
            String patientFFame = data.getStringExtra(AddEditTestActivity.EXTRA_PATIENTFNAME);
            int patientNurseID = data.getIntExtra(AddEditTestActivity.EXTRA_PATIENTNURSEID, -1);

            Test test = new Test(patientid, patientNurseID, testname, bpl, bloodgroup, bph, temp);
            testViewModel.insert(test);

            Toast.makeText(this, "Test Inserted", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_TEST_REQUEST && resultCode == RESULT_OK) {
            int testid = data.getIntExtra(AddEditTestActivity.EXTRA_TESTID, -1);
            int patientid = data.getIntExtra(AddEditTestActivity.EXTRA_PATIENTID, -1);

            int bph = data.getIntExtra(AddEditTestActivity.EXTRA_PATIENTBPH, -1);
            int bpl = data.getIntExtra(AddEditTestActivity.EXTRA_PATIENTBPL, -1);
            String testname = data.getStringExtra(AddEditTestActivity.EXTRA_TESTNAME);
            String bloodgroup = data.getStringExtra(AddEditTestActivity.EXTRA_BLOOD_GROUP);
            float temp = data.getFloatExtra(AddEditTestActivity.EXTRA_TEMPERATURE, -1);
            String patientLFame = data.getStringExtra(AddEditTestActivity.EXTRA_PATIENTLNAME);
            String patientFFame = data.getStringExtra(AddEditTestActivity.EXTRA_PATIENTFNAME);
            int patientNurseID = data.getIntExtra(AddEditTestActivity.EXTRA_PATIENTNURSEID, -1);

            if (patientid == -1) {
                Toast.makeText(this, "Test Cannot be Updated", Toast.LENGTH_SHORT).show();
                return;
            }

            Test test = new Test(patientid, patientNurseID, testname, bpl, bloodgroup, bph, temp);
            test.setTestId(testid);

            testViewModel.update(test);
            Toast.makeText(this, "Test Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Test Not Saved", Toast.LENGTH_SHORT).show();
        }

    }

}