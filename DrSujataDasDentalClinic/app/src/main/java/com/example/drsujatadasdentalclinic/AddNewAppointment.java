package com.example.drsujatadasdentalclinic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewAppointment extends AppCompatActivity {

    public void addAppointmentDetails(View view){
        EditText nameEditText = (EditText) findViewById(R.id.editTextTextPersonName);
        String name = nameEditText.getText().toString();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int checkedID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedID);
        String gender = radioButton.getText().toString();

        EditText phoneEditText = (EditText) findViewById(R.id.editTextPhone);
        String phoneNumber = phoneEditText.getText().toString();
        
        long id = MainActivity.db.insertPatient(name, gender, phoneNumber);
        NewAppointment.adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_appointment);
        MainActivity.db = new DatabaseHelper(this);
    }

    public void getPatientRecord(View view){
        EditText idxNumber = (EditText) findViewById(R.id.idNumber);
        PatientRecords patient = MainActivity.db.getPatientRecord(Long.parseLong(idxNumber.getText().toString()));
        Log.i("ID", String.valueOf(patient.getId()));
        Log.i("Name", patient.getPatientName());
        Log.i("Name", patient.getGender());
        Log.i("Name", patient.getPhone());
    }
}