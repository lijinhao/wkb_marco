package contentprovider;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TodoTable {

    // Database table
    public static final String TABLE_TODO = "todo";

    public static final String COLUMN_ID= "id";
    public static final String COLUMN_GMT_CREATE = "gmtCreate";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_CONTENT_TYPE  = "contentType";
    public static final String COLUMN_CONTENT_URL  = "contentUrl";
    public static final String COLUMN_CONTENT_ID = "contentId";
    public static final String COLUMN_TITLE  = "title";
    public static final String COLUMN_PICURL = "picUrl";
    public static final String COLUMN_TAG  = "tag";
    public static final String COLUMN_TAG_COLOR = "tagColor";
    public static final String COLUMN_TAG_COLOR_BG = "tagColorBg";
    public static final String COLUMN_TOP  = "top";
    public static final String COLUMN_TYPE_NAME  = "type_name";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_TODO
            + "("
            + COLUMN_ID + " integer, "
            + COLUMN_GMT_CREATE + " text null, "
            + COLUMN_TYPE + " text null,"
            + COLUMN_CONTENT_TYPE + " text null,"
            + COLUMN_CONTENT_URL + " text null,"
            + COLUMN_CONTENT_ID + " text null,"
            + COLUMN_TITLE + " text null,"
            + COLUMN_PICURL + " text null,"
            + COLUMN_TAG + " text null,"
            + COLUMN_TAG_COLOR + " text null,"
            + COLUMN_TAG_COLOR_BG + " text null,"
            + COLUMN_TOP + " text null,"
            + COLUMN_TYPE_NAME + " text null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(TodoTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(database);
    }
}