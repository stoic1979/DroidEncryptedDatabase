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

import java.io.Serializable;

/**
 * Model class for student database
 */

public class StudentModel implements Serializable {

    public int studentRollNo;
    public String studentName;
    public String studentClass;

    public StudentModel() {

    }//empty constructor

    /**
     * constructor
     */
    public StudentModel( int studentRollNo, String studentName, String studentClass ) {

        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentRollNo = studentRollNo;

    }//constructor

    /**
     * getter and setters
     */

    public void setStudentRollNo(int studentRollNo) {

        this.studentRollNo = studentRollNo;

    }

    public int getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentName(String studentName) {

        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentClass(String studentClass) {

        this.studentClass = studentClass;
    }

    public String getStudentClass() {
        return studentClass;
    }

}//LocationModel
