package infosecadventures.allsafe.challenges;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDatabaseHelper extends SQLiteOpenHelper {

    public NoteDatabaseHelper(@Nullable Context context) {
        super(context, "notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists note");
        db.execSQL("create table note ( id integer primary key autoincrement, user text, note text )");
        db.execSQL("insert into note ( user, note ) values ('admin', 'I can not believe that Jill is still using 123456 as her password...')");
        db.execSQL("insert into note ( user, note ) values ('elliot.alderson', 'A bug is never just a mistake. It represents something bigger. An error of thinking. That makes you who you are.')");
        db.execSQL("insert into note ( user, note ) values ('darlene.alderson', 'That’s the trick about money. Banks care more about it than anything else.')");
        db.execSQL("insert into note ( user, note ) values ('gideon.goddard', 'You’re never sure about anything unless there’s something to be sure about.')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
