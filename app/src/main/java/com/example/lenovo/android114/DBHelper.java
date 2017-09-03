package com.example.lenovo.android114;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by lenovo on 8/17/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
   //All static variable
    //database name
    public static final String DATABASE_NAME ="employeeDBName.db";
    //databse table name
    public static final String DATABASE_TABLE_NAME = "employees";
    //employee id
    public static final String EMPLOYEE_ID = "id";
    //employee first name
    public static final String EMPLOYEE_FIRST_NAME = "employeeFirstName";
    public static final String EMPLOYEE_LAST_NAME = "employeeLasttName";
    public static final String EMPLOYEE_AGE = "employeeAge";
    public static final String EMPLOYEE_SEX = "employeeSex";
    private static final String CREATE_TABLE =
            "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                    EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EMPLOYEE_FIRST_NAME + " text,"
                    + EMPLOYEE_LAST_NAME + " text,"
                    + EMPLOYEE_AGE + " text,"
                    + EMPLOYEE_SEX + " text);";

    DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    //oncreate method
    public void onCreate(SQLiteDatabase db) {
        //here we creating table in database
        db.execSQL(CREATE_TABLE);

    }

    @Override
    //onupgrade method
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS employees");
        onCreate(db);


    }
    //here we are creating insertEmployee() method
    public boolean insertEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        //here we are creating object of contentValues
        ContentValues contentValues = new ContentValues();
        //here we are putting firt name
        contentValues.put(EMPLOYEE_FIRST_NAME, employee.employeeFirstName);
        //here we are putting last name
        contentValues.put(EMPLOYEE_LAST_NAME, employee.employeeLastName);
        //here we are putting age
        contentValues.put(EMPLOYEE_AGE, employee.employeeAge);
        //here we are putting sex
        contentValues.put(EMPLOYEE_SEX, employee.employeeSex);
        //here we are inserting database table name in db
        db.insert(DATABASE_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from employees where id=" + id + "", null );
        return res;
    }

    public int numberOfRows(){
        int numRows;
        SQLiteDatabase db = this.getReadableDatabase();

        return numRows = (int) DatabaseUtils.queryNumEntries(db, DATABASE_TABLE_NAME);
    }

    public ArrayList<Employee> getAllEmployee() {
        //here we creating arraylist of employee
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "Select * From employees";
        SQLiteDatabase db = this.getWritableDatabase();
        //here we are creating object of cursor
        Cursor cursor = db.rawQuery(selectQuery, null);
        //here we are apllying condition
        // for looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setEmployeeFirstName(cursor.getString(1));
                employee.setEmployeeLastName(cursor.getString(2));
                employee.setEmployeeAge(cursor.getString(3));
                employee.setEmployeeSex(cursor.getString(4));
                //here we are Adding employee to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        //here we are close inserting data from database
        db.close();
        // return employee list
        return employeeList;

    }

}
