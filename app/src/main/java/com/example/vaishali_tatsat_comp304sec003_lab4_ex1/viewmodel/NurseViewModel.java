package com.example.vaishali_tatsat_comp304sec003_lab4_ex1.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.entity.Nurse;
import com.example.vaishali_tatsat_comp304sec003_lab4_ex1.repository.NurseRepository;

import java.util.List;

public class NurseViewModel  extends AndroidViewModel {

    private NurseRepository nurseRepository;

    public NurseViewModel(Application application) {
        super((application));
        nurseRepository = new NurseRepository(application);
    }

    public List<Nurse> findByNurseID(int nurseID, String password) {
        return nurseRepository.findbyNurseID(nurseID, password); }

    public void insert(Nurse nurse) { nurseRepository.insert(nurse); }
}
