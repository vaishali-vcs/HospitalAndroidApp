package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.R;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {

    private List<Patient> patients = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_item, parent, false);

        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient currentPatient = patients.get(position);
        holder.text_view_PatientNurseid.setText( "Nurse: " + String.valueOf(currentPatient.getNurseID()));
        holder.text_view_PatientRoom.setText("Room: " + currentPatient.getRoom());
        holder.text_view_PatientDepartment.setText(currentPatient.getDepartment());
        holder.text_view_PatientName.setText(currentPatient.getFirstName() + ", " + currentPatient.getLastName());
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }

    public Patient getPatientAt(int position) {
        return patients.get(position);
    }

    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView text_view_PatientRoom;
        private TextView text_view_PatientDepartment;
        private TextView text_view_PatientName;
        private TextView text_view_PatientNurseid;

        public PatientHolder(@NonNull View itemView) {
            super(itemView);
            text_view_PatientRoom = itemView.findViewById(R.id.text_view_PatientRoom);
            text_view_PatientDepartment = itemView.findViewById(R.id.text_view_PatientDepartment);
            text_view_PatientName = itemView.findViewById(R.id.text_view_PatientName);
            text_view_PatientNurseid = itemView.findViewById(R.id.text_view_PatientNurseid);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(patients.get(position));
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
