package fr.josselin.memo.memo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jos_b on 23/02/2018.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public DatabaseOpenHelper(Context context) {
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE memo(id INTEGER PRIMARY KEY AUTOINCREMENT, titre TEXT, content TEXT)"); //aujouter colonne contenu memo

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
