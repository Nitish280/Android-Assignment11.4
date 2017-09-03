package com.example.lenovo.android114;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declaration
    TextView textView;
    Employee employee;
    String[]  empFirstName, empLastName, empSex, empAge;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //taking first name
        empFirstName = new String[]{"Nitish", "Snigdha", "Abhishek", "Vikash", "Aditya"};
        //taking last name
        empLastName = new String[]{"Singh","Singh","Kumar","Rathore","Singh"};
        //taking age
        empAge = new String[]{"21","22","23","21","22"};
        //taking sex
        empSex = new String[]{"Male","Femlae","Male","Male","Male"};

        textView = (TextView) findViewById(R.id.textView);
        Log.d("Insert: ", "Inserting ..");
        //here we are Saving to Database
        dbHelper = new DBHelper(this);
        if(dbHelper.numberOfRows()>0){
            Log.e("DB","Database already exist.");
        }
        else{
            saveDataInDB();
            Log.e("DB ","Data Saved in Database.");

        }

        //here we are blob to image conversion
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadDataFromDB()) {
                    Log.e("DB ","Data Loaded from Database.");
                }
            }
        }, 0);

    }

    Boolean saveDataInDB() {
        employee = new Employee();
        //here we are initializing employee
        for (int i=0;i<empFirstName.length;i++){
            //setting firstname
            employee.setEmployeeFirstName(empFirstName[i]);
            //setting Lastname
            employee.setEmployeeLastName(empLastName[i]);
            //setting Age
            employee.setEmployeeAge(empAge[i]);
            //Setting sex
            employee.setEmployeeSex(empSex[i]);

            Log.d("Insert: ", "Inserting ..");
            dbHelper.insertEmployee(employee);
        }

        return true;

    }
    //here we are load data
    Boolean loadDataFromDB() {
        try {
            //here we are  getting all employee
            ArrayList array_list = dbHelper.getAllEmployee();
            Log.e("Employee Size ", String.valueOf(array_list.size()));

            if(!array_list.isEmpty()){

                StringBuilder stringBuilderFull,stringBuilderEmployee;
                //here we are creating object of StringBuilder
                stringBuilderFull = new StringBuilder();
                //here we are initializing arraylist
                for (int i=0;i<array_list.size();i++){
                    Employee employee1 = (Employee) array_list.get(i);
                    stringBuilderEmployee=  new StringBuilder().append("Emp Id : ").append(employee1.getId()).append(", ")
                            .append("First Name: ").append(employee1.getEmployeeFirstName()).append(", ")
                            .append("Last Name: ").append(employee1.getEmployeeLastName()).append(", ")
                            .append("Age: ").append(employee1.getEmployeeAge()).append(", ")
                            .append("Sex: ").append(employee1.getEmployeeSex()).append(".")
                            .append("\n").append("\n");
                    stringBuilderFull.append(stringBuilderEmployee);
                }

                textView.setText(stringBuilderFull);
                Log.e("DB ", "Full details displayed.");
            }else {
                Log.e("DB ", "No Employee available.");
            }

            return true;
        } catch (Exception e) {

            return false;
        }

    }
}
