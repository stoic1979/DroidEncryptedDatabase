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

package com.weavebytes.droidencrypteddatabase.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

/**
 * class to perform CRUD operations on student database And encrypting it using the password provided. Notice that imported packages are from "net.sqlcipher" instead "android.database".
 */

public class StudentDbOperation {

    //creating instance for SQLiteDatabase and StudentDbCreation
    StudentDbCreation studentDbCreation;
    SQLiteDatabase sqLiteDatabase;

    /**
     * constructor
     */

    public StudentDbOperation(Context context) {

        //initializing variables
        studentDbCreation = new StudentDbCreation(context);
        sqLiteDatabase = studentDbCreation.getWritableDatabase("somePassword"); //enter your password here for encrypting the database

        Log.d("Location Database", "both database and table created");

    }//constructor

    /**
     * insert
     */

    public boolean insertStudentRecord(StudentModel studentModel) {

        ContentValues values = new ContentValues();
        values.put(StudentDbCreation.STUDENT_COLUMN_NAME, studentModel.getStudentName());
        values.put(StudentDbCreation.STUDENT_COLUMN_CLASS, studentModel.getStudentClass());
        values.put(StudentDbCreation.STUDENT_COLUMN_ROLLNO, studentModel.getStudentRollNo());

        long row = sqLiteDatabase.insert(StudentDbCreation.STUDENT_TABLE_NAME, null, values);

        return row > 0;
    }

    /**
     * delete
     */

    public boolean deleteStudentRecord(int rollNo) {

        long row = sqLiteDatabase.delete(StudentDbCreation.STUDENT_TABLE_NAME,
                StudentDbCreation.STUDENT_COLUMN_ROLLNO + " = ?", new String[]{String.valueOf(rollNo)});
        return row > 0;
    }

    /**
     * update
     */

    public boolean updateStudentRecord(int rollNo, String name, String stdClass) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("class", stdClass);
        int row = sqLiteDatabase.update(StudentDbCreation.STUDENT_TABLE_NAME, contentValues,StudentDbCreation.STUDENT_COLUMN_ROLLNO + " = ?", new String[]{Integer.toString(rollNo)});

        return row > 0;
    }

    /**
     * retrieve
     */

    public ArrayList<StudentModel> getAllStudentRecord() {

        ArrayList<StudentModel> arrayListStudentRecord = new ArrayList<>();

        String selectQuery2 = "SELECT * FROM " + StudentDbCreation.STUDENT_TABLE_NAME;
        Cursor cursor2 = sqLiteDatabase.rawQuery(selectQuery2, null);

        if (cursor2.moveToFirst()) {

            do {
                StudentModel studentModel = new StudentModel();
                studentModel.setStudentRollNo(cursor2.getInt(0));
                studentModel.setStudentName(cursor2.getString(1));
                studentModel.setStudentClass(cursor2.getString(2));

                arrayListStudentRecord.add(studentModel);

            } while (cursor2.moveToNext());
        }
        return arrayListStudentRecord;
    }

    /**
     * retrieve particular record
     */

    public ArrayList<StudentModel> getParticularStudentRecord(int rollNo) {

        ArrayList<StudentModel> arrayListStudentRecord = new ArrayList<>();

        String selectQuery2 = "SELECT * FROM " + StudentDbCreation.STUDENT_TABLE_NAME + " WHERE " + StudentDbCreation.STUDENT_COLUMN_ROLLNO + "= " + rollNo;
        Cursor cursor2 = sqLiteDatabase.rawQuery(selectQuery2, null);

        if (cursor2.moveToFirst()) {

            do {
                StudentModel studentModel = new StudentModel();

                studentModel.setStudentRollNo(cursor2.getInt(0));
                studentModel.setStudentName(cursor2.getString(1));
                studentModel.setStudentClass(cursor2.getString(2));

                arrayListStudentRecord.add(studentModel);

            } while (cursor2.moveToNext());

        }
        return arrayListStudentRecord;

    }//getParticularLocation

}//StudentDbOperation
