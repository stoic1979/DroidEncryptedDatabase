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

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * creation of student database. Notice that imported packages are from "net.sqlcipher" instead "android.database".
 */

public class StudentDbCreation extends SQLiteOpenHelper {


    //variables for database
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StudentDb.db";
    public static final String STUDENT_TABLE_NAME = "Students";
    public static final String STUDENT_COLUMN_NAME = "name";
    public static final String STUDENT_COLUMN_CLASS = "class";
    public static final String STUDENT_COLUMN_ROLLNO = "rollNo";


    //constructor
    public StudentDbCreation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                " CREATE TABLE " + STUDENT_TABLE_NAME
                        + "(" + STUDENT_COLUMN_ROLLNO + " INTEGER PRIMARY KEY," //roll-no as the primary-key
                        + STUDENT_COLUMN_NAME + " TEXT, "
                        + STUDENT_COLUMN_CLASS + " TEXT"
                        + ");"
        );

    }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + STUDENT_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }//onUpgrade

}//LocationDbCreation
