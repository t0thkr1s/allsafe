package infosecadventures.allsafe.challenges;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    NoteDatabaseHelper noteDatabaseHelper;

    @Override
    public boolean onCreate() {
        noteDatabaseHelper = new NoteDatabaseHelper(getContext());
        uriMatcher.addURI("infosecadventures.allsafe.dataprovider", "note", 123);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables("note");
        return queryBuilder.query(noteDatabaseHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return ContentUris.withAppendedId(uri, noteDatabaseHelper.getWritableDatabase().insert("note", null, values));
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return noteDatabaseHelper.getWritableDatabase().delete("note", selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return noteDatabaseHelper.getWritableDatabase().update("note", values, selection, selectionArgs);
    }
}
