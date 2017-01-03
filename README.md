# DroidEncryptedDatabase
A handy example of SQLite database encryption in Android

# Docs
Project show a demo table of student with name, roll no and class fields.
Note: roll no is primary key.

You need to set the salt/passwork inside StudentDbOperation.java:

sqLiteDatabase = studentDbCreation.getWritableDatabase("somePassword"); 

# Screenshot
![Alt text] (screenshot.png?raw=true "Demo screenshot")
