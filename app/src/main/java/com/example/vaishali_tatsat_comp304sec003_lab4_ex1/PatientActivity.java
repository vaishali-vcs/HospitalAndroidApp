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

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.adapter.PatientAdapter;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Patient;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.viewmodel.PatientViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PatientActivity extends AppCompatActivity {
    public static final int ADD_PATIENT_REQUEST = 1;
    public static final int EDIT_PATIENT_REQUEST = 2;

    private PatientViewModel patientviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        FloatingActionButton buttonAddPatient = findViewById(R.id.button_add_patient);
        buttonAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity.this, AddEditPatientActivity.class);
                startActivityForResult(intent, ADD_PATIENT_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        PatientAdapter adapter = new PatientAdapter();
        recyclerView.setAdapter(adapter);

        patientviewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        patientviewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                //update our RecyclerView
                adapter.setPatients(patients);
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
                patientviewModel.delete(adapter.getPatientAt(viewHolder.getAdapterPosition()));

                Toast.makeText(PatientActivity.this, "Patient Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient patient) {
                Intent intent = new Intent(PatientActivity.this, AddEditPatientActivity.class);


                intent.putExtra(AddEditPatientActivity.EXTRA_PATIENTID, patient.getPatientID());
                intent.putExtra(AddEditPatientActivity.EXTRA_PATIENTFNAME, patient.getFirstName());
                intent.putExtra(AddEditPatientActivity.EXTRA_PATIENTLNAME, patient.getLastName());
                intent.putExtra(AddEditPatientActivity.EXTRA_PATIENTDEPARTMENT, patient.getDepartment());
                intent.putExtra(AddEditPatientActivity.EXTRA_PATIENTNURSEID, patient.getNurseID());
                intent.putExtra(AddEditPatientActivity.EXTRA_PATIENTROOM, patient.getRoom());

                startActivityForResult(intent, EDIT_PATIENT_REQUEST);
            }
        });

        setTitle("Patients List");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_PATIENT_REQUEST && resultCode ==RESULT_OK)
        {
            String patientFFame = data.getStringExtra(AddEditPatientActivity.EXTRA_PATIENTFNAME);
            String patientRoom = data.getStringExtra(AddEditPatientActivity.EXTRA_PATIENTROOM);
            String patientLFame = data.getStringExtra(AddEditPatientActivity.EXTRA_PATIENTLNAME);
            String patientDepartment = data.getStringExtra(AddEditPatientActivity.EXTRA_PATIENTDEPARTMENT);
            int patientNurseID = data.getIntExtra(AddEditPatientActivity.EXTRA_PATIENTNURSEID, -1);

            Patient patient = new Patient(patientFFame, patientLFame, patientDepartment, patientNurseID, patientRoom);
            patientviewModel.insert(patient);

            Toast.makeText(this, "Patient Inserted", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == EDIT_PATIENT_REQUEST && resultCode ==RESULT_OK)
        {
            int id = data.getIntExtra(AddEditPatientActivity.EXTRA_PATIENTID, -1);

            if (id == -1) {
                Toast.makeText(this, "Patient Cannot be Updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String patientFFame = data.getStringExtra(AddEditPatientActivity.EXTRA_PATIENTFNAME);
            String patientRoom = data.getStringExtra(AddEditPatientActivity.EXTRA_PATIENTROOM);
            String patientLFame = data.getStringExtra(AddEditPatientActivity.EXTRA_PATIENTLNAME);
            String patientDepartment = data.getStringExtra(AddEditPatientActivity.EXTRA_PATIENTDEPARTMENT);
            int patientNurseID = data.getIntExtra(AddEditPatientActivity.EXTRA_PATIENTNURSEID, -1);

            Patient patient = new Patient(patientFFame, patientLFame, patientDepartment, patientNurseID, patientRoom);
            patient.setPatientID(id);

            patientviewModel.update(patient);
            Toast.makeText(this, "Patient Updated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Test Not Saved", Toast.LENGTH_SHORT).show();
        }

    }

}