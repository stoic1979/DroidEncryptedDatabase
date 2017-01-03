/*
 *  This file is part of Droid Encrypted Database(using SQLCipher) sample app.
 *
 *  Droid Encrypted Database is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Droid Encrypted Database is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with SocialDashboard.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 *  ------------------------------------------------------------------------------
 *     About: Weavebytes Infotech Pvt Ltd, India
 *   Website: www.weavebytes.com
 *  ------------------------------------------------------------------------------
 */

package com.weavebytes.droidencrypteddatabase.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.weavebytes.droidencrypteddatabase.R;
import com.weavebytes.droidencrypteddatabase.db.StudentDbOperation;
import com.weavebytes.droidencrypteddatabase.db.StudentModel;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

/**
 * class MainActivity handling main UI of the app, and using database stuff for storing Student Record(name, class, roll-no). Here, roll-no is serving as the primary key for student database.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //UI components
    Button btnInsert, btnRetrieve, btnDelete, btnUpdate;
    EditText edtClass, edtName, edtRollNo;

    //creating instance for database operations
    StudentDbOperation studentDbOperation;

    //arrayList for studentModel object
    ArrayList<StudentModel> studentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing components
        btnInsert = (Button) findViewById(R.id.btn_saveToDb);
        btnRetrieve = (Button) findViewById(R.id.btn_retrieveFromDb);
        btnDelete = (Button) findViewById(R.id.btn_deleteFromDb);
        btnUpdate = (Button) findViewById(R.id.btn_updateDb);

        edtName = (EditText) findViewById(R.id.edt_enter_name);
        edtClass = (EditText) findViewById(R.id.edt_enter_class);
        edtRollNo = (EditText) findViewById(R.id.edt_enter_roll_no);

        //setting listener on buttons
        btnInsert.setOnClickListener(this);
        btnRetrieve.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        //loading SQLiteDatabase library
        SQLiteDatabase.loadLibs(this);

        //initializing StudentDbOperation object
        studentDbOperation = new StudentDbOperation(this);

    }//onCreate


    @Override
    public void onClick(View view) {

        //saving student record into strings
        String stdName = edtName.getText().toString();
        String stdClass = edtClass.getText().toString();
        String stdRollNo = edtRollNo.getText().toString();

        switch (view.getId()) {

            //inserting record to database, here, roll-no is served as the primary key
            case R.id.btn_saveToDb:

                //checking for empty fields
                if (TextUtils.isEmpty(stdName) || TextUtils.isEmpty(stdClass) || TextUtils.isEmpty(stdRollNo)) {
                    Toast.makeText(MainActivity.this, "some fields are empty!", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        //inserting data into database and clearing the fields after insertion
                        if (studentDbOperation.insertStudentRecord(new StudentModel(Integer.parseInt(stdRollNo), stdName, stdClass))) {
                            Toast.makeText(MainActivity.this, "data inserted to db", Toast.LENGTH_SHORT).show();

                            edtName.setText("");
                            edtClass.setText("");
                            edtRollNo.setText("");


                        } else {

                            //toast message in case data not inserted
                            Toast.makeText(MainActivity.this, "data not inserted", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                        //toast for error case if it occurs
                        Toast.makeText(MainActivity.this, "an problem occur " + e, Toast.LENGTH_SHORT).show();

                    }

                }

                break;

            //retrieving record from database by using roll-no as the primary key
            case R.id.btn_retrieveFromDb:

                try {

                    //checking roll-no in case it is empty
                    if (TextUtils.isEmpty(edtRollNo.getText().toString())) {
                        Toast.makeText(MainActivity.this, "roll-no empty! ", Toast.LENGTH_SHORT).show();

                    } else {

                        //fetching record for particular number
                        studentData = studentDbOperation.getParticularStudentRecord(Integer.parseInt(edtRollNo.getText().toString()));

                        //showing message in case if it doesn't exists
                        if (studentData.size() == 0) {
                            Toast.makeText(MainActivity.this, " data not exist ", Toast.LENGTH_SHORT).show();

                        } else {
                            //setting record to UI components and making arrayList clear
                            edtName.setText(studentData.get(0).getStudentName());
                            edtClass.setText(studentData.get(0).getStudentClass());

                            studentData.clear();
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();

                    //toast for error case if it occurs
                    Toast.makeText(MainActivity.this, "an problem occur " + e, Toast.LENGTH_SHORT).show();

                }

                break;

            //deleting record from database by using roll-no as the primary key
            case R.id.btn_deleteFromDb:

                try {
                    //checking roll-no in case it is empty
                    if (TextUtils.isEmpty(edtRollNo.getText().toString())) {
                        Toast.makeText(MainActivity.this, " roll-no is empty ", Toast.LENGTH_SHORT).show();

                    } else {

                        //deleting record from database and showing message
                        if (studentDbOperation.deleteStudentRecord(Integer.parseInt(edtRollNo.getText().toString()))) {
                            Toast.makeText(MainActivity.this, " deleted ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, " data not exist ", Toast.LENGTH_SHORT).show();

                        }
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                    //toast for error case if it occurs
                    Toast.makeText(MainActivity.this, "an problem occur " + e, Toast.LENGTH_SHORT).show();

                }

                break;

            //updating record from database by using roll-no as the primary key
            case R.id.btn_updateDb:

                try {

                    //checking fields for empty case
                    if (TextUtils.isEmpty(stdName) || TextUtils.isEmpty(stdClass) || TextUtils.isEmpty(stdRollNo)) {
                        Toast.makeText(MainActivity.this, "some fields are empty!", Toast.LENGTH_SHORT).show();

                    } else {

                        //updating record for student
                        if (studentDbOperation.updateStudentRecord(Integer.parseInt(edtRollNo.getText().toString()), edtName.getText().toString(), edtClass.getText().toString())) {
                            Toast.makeText(MainActivity.this, "  updated ", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, " not updated ", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();

                    //toast for error case if it occurs
                    Toast.makeText(MainActivity.this, "an problem occur " + e, Toast.LENGTH_SHORT).show();

                }

                break;

        }//switch

    }//onClick


}//MainActivity
