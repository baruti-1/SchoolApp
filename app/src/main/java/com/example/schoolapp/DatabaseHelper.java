package com.example.schoolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

 class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "school.db";
    public static final String DBTABLE = "student";
    public static final String COL1 = "ID";
    public static final String COL2 = "FIRST";
    public static final String COL3 = "MIDDLE";
    public static final String COL4 = "LAST";
    public static final String COL5 = "EMAIL";
    public static final String COL6 = "PHONE";
    public static final String COL7 = "GENDER";
    public static final String COL8 = "DOB";
    public static final String COL9 = "USERNAME";
    public static final String COL10 = "PASSWORD";

     public static final String STTABLE = "staff";
     public static final String COLID = "ID1";
     public static final String COLFIRST = "FIRST1";
     public static final String COLMIDDLE = "MIDDLE1";
     public static final String COLLAST = "LAST1";
     public static final String COLMAIL = "EMAIL1";
     public static final String COLPHONE = "PHONE1";
     public static final String COLGENDER = "GENDER1";
     public static final String COLDOB = "DOB1";
     public static final String COLUSER = "USERNAME1";
     public static final String COLPASS = "PASSWORD1";

     public static final String TBCOURSE = "course";
     public static final String COLCODE = "CODE";
     public static final String COLNAME = "NAME";
     public static final String COLCRDTS = "CRDTS";
     public static final String COLSEMESTER = "SEMESTER";
     public static final String COLYEAR = "YEAR";
     public static final String COLOPT = "OPTION";


     public DatabaseHelper(Context context){
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + DBTABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST TEXT, MIDDLE TEXT, LAST TEXT, EMAIL TEXT, PHONE TEXT, GENDER TEXT, DOB TEXT, USERNAME TEXT, PASSWORD TEXT)");
        db.execSQL("create table " + STTABLE + " (ID1 INTEGER PRIMARY KEY AUTOINCREMENT, FIRST1 TEXT, MIDDLE1 TEXT, LAST1 TEXT, EMAIL1 TEXT, PHONE1 TEXT, GENDER1 TEXT, DOB1 TEXT, USERNAME1 TEXT, PASSWORD1 TEXT)");
        db.execSQL("create table " + TBCOURSE + " (CODE TEXT PRIMARY KEY, NAME TEXT, CRDTS TEXT, SEMESTER TEXT, YEAR TEXT, OPTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + DBTABLE);
        db.execSQL("drop table if exists " + TBCOURSE);
        db.execSQL("drop table if exists " + STTABLE);
        onCreate(db);
    }
    // method to generate studentIdc
//    private String generateStudentRegNo(){
//
//         SQLiteDatabase db = this.getReadableDatabase ();
//
//         Cursor c = db.rawQuery ("SELECT * FROM " + DBTABLE , null);
//         int C = (c.getCount ())+1;
//         String cc = String.valueOf (C);
//         String reg = "0000".substring (cc.length()) + cc;
//
//         return reg;
//     }

     public boolean insertCourse(String code, String name, String crdts, String semester, String year, String option){
         SQLiteDatabase db = this.getWritableDatabase();

//        String reg = generateStudentRegNo ();
//        String RegNo = "2019-04-" + reg;

         ContentValues values = new ContentValues();
         values.put(COLCODE, code);
         values.put(COLNAME, name);
         values.put(COLCRDTS, crdts);
         values.put(COLSEMESTER, semester);
         values.put(COLYEAR, year);
         values.put(COLOPT, option);

         long result = db.insert(TBCOURSE, null, values);
         if(result == -1){
             return false;
         }else{
             return true;
         }
     }

    public boolean insertData(String first, String middle, String last, String email, String phone, String gender, String dob, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

//        String reg = generateStudentRegNo ();
//        String RegNo = "2019-04-" + reg;

        ContentValues contentValue = new ContentValues();
        contentValue.put(COL2, first);
        contentValue.put(COL3, middle);
        contentValue.put(COL4, last);
        contentValue.put(COL5, email);
        contentValue.put(COL6, phone);
        contentValue.put(COL7, gender);
        contentValue.put(COL8, dob);
        contentValue.put(COL9, username);
        contentValue.put(COL10, password);

        long result = db.insert(DBTABLE, null, contentValue);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

     public boolean insertStaff(String first1, String middle1, String last1, String email1, String phone1, String gender1, String dob1, String username1, String password1){
         SQLiteDatabase db = this.getWritableDatabase();

//        String reg = generateStudentRegNo ();
//        String RegNo = "2019-04-" + reg;

         ContentValues contentValue = new ContentValues();
         contentValue.put(COLFIRST, first1);
         contentValue.put(COLMIDDLE, middle1);
         contentValue.put(COLLAST, last1);
         contentValue.put(COLMAIL, email1);
         contentValue.put(COLPHONE, phone1);
         contentValue.put(COLGENDER, gender1);
         contentValue.put(COLDOB, dob1);
         contentValue.put(COLUSER, username1);
         contentValue.put(COLPASS, password1);

         long result = db.insert(STTABLE, null, contentValue);
         if(result == -1){
             return false;
         }else{
             return true;
         }
     }

    public Cursor getStudents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + DBTABLE, null);
        return res;
    }

     public Cursor getStaffs(){
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor res = db.rawQuery("SELECT * FROM " + STTABLE, null);
         return res;
     }

     public Cursor getCourses(){
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor res = db.rawQuery("SELECT * FROM " + TBCOURSE, null);
         return res;
     }
}
