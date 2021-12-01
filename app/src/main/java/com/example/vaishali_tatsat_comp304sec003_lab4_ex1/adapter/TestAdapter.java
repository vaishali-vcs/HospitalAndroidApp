package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.R;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Test;

import java.util.ArrayList;
import java.util.List;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    private List<Test> tests = new ArrayList<>();
    private TestAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item, parent, false);

        return new TestHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
        notifyDataSetChanged();
    }

    public Test getTestAt(int position) {
        return tests.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        Test currentTest = tests.get(position);
        holder.text_view_temperature.setText("Temp: " + String.valueOf(currentTest.getTemperature()));
        holder.text_view_BloodPressure.setText("Blood Pressure: " + String.valueOf(currentTest.getBPH()) + "/" + String.valueOf(currentTest.getBPL()));
        holder.text_view_BloodGroup.setText(currentTest.getBloodGroup());
        holder.text_view_TestName.setText(currentTest.getTestName());
    }

    class TestHolder extends RecyclerView.ViewHolder {
        private TextView text_view_temperature;
        private TextView text_view_BloodPressure;
        private TextView text_view_BloodGroup;
        private TextView text_view_TestName;

        public TestHolder(@NonNull View itemView) {
            super(itemView);
            text_view_TestName = itemView.findViewById(R.id.text_view_TestName);
            text_view_BloodGroup = itemView.findViewById(R.id.text_view_BloodGroup);
            text_view_BloodPressure = itemView.findViewById(R.id.text_view_BloodPressure);
            text_view_temperature = itemView.findViewById(R.id.text_view_temperature);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(tests.get(position));
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Test test);
    }

    public void setOnItemClickListener(TestAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
