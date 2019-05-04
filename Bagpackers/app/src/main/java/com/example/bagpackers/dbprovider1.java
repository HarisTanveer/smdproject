package com.example.bagpackers;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.bagpackers.Daos.PlaceDao;
import com.example.bagpackers.RoomDB.AppDatabase;

import java.util.ArrayList;

public class dbprovider1 extends ContentProvider {

    /** The authority of this content provider. */
    public static final String AUTHORITY = "com.example.bagpacker.dbProvider";

    /** The URI for the Cheese table. */
    public static final Uri URI_CHEESE = Uri.parse(
            "content://" + AUTHORITY + "/" + "Place");

    /** The match code for some items in the Cheese table. */
    private static final int CODE_CHEESE_DIR = 1;

    /** The match code for an item in the Cheese table. */
    private static final int CODE_CHEESE_ITEM = 2;

    /** The URI matcher. */
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, "Place", CODE_CHEESE_DIR);
        MATCHER.addURI(AUTHORITY, "Place" + "/*", CODE_CHEESE_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {


//        val db = AppDatabase.openHelper.readableDatabase
//        db.query(...)

        final int code = MATCHER.match(uri);
        if (code == CODE_CHEESE_DIR || code == CODE_CHEESE_ITEM) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }
           PlaceDao cheese= AppDatabase.getInstance(context).placeDao();
            final Cursor cursor;
            if (code == CODE_CHEESE_DIR) {
                cursor = cheese.selectAll();
            } else {

                cursor = cheese.selectById(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_CHEESE_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + "Place";
            case CODE_CHEESE_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + "Place";
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
//        switch (MATCHER.match(uri)) {
//            case CODE_CHEESE_DIR:
//                final Context context = getContext();
//                if (context == null) {
//                    return null;
//                }
//                final long id = SampleDatabase.getInstance(context).cheese()
//                        .insert(Place.fromContentValues(values));
//                context.getContentResolver().notifyChange(uri, null);
//                return ContentUris.withAppendedId(uri, id);
//            case CODE_CHEESE_ITEM:
//                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
//            default:
        throw new IllegalArgumentException("Unknown URI: " + uri);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case CODE_CHEESE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_CHEESE_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final int count = AppDatabase.getInstance(context).placeDao()
                        .deleteById(ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
//        switch (MATCHER.match(uri)) {
//            case CODE_CHEESE_DIR:
//                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
//            case CODE_CHEESE_ITEM:
//                final Context context = getContext();
//                if (context == null) {
//                    return 0;
//                }
//                final Place cheese = Cheese.fromContentValues(values);
//                cheese.id = ContentUris.parseId(uri);
//                final int count = SampleDatabase.getInstance(context).cheese()
//                        .update(cheese);
//                context.getContentResolver().notifyChange(uri, null);
//                return count;
//            default:
//                throw new IllegalArgumentException("Unknown URI: " + uri);
        return 0;
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(
            @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
//        final Context context = getContext();
//        if (context == null) {
//
//        }
//        final SampleDatabase database = SampleDatabase.getInstance(context);
//        database.beginTransaction();
//        try {
//            final ContentProviderResult[] result = super.applyBatch(operations);
//            database.setTransactionSuccessful();
//            return result;
//        } finally {
//            database.endTransaction();
        return new ContentProviderResult[0];
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] valuesArray) {
        return 0;

    }

}


