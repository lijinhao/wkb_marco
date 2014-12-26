package contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import entity.CourserItem;

public class ProviderUtils {

    private static String tag = "Provider Tester";

    private Context mContext;

    public ProviderUtils(Context ctx) {
        this.mContext =  ctx;
    }

    public void addCourseItem(CourserItem item) {
        Log.d(tag, "Adding a course item");
        ContentValues cv = new ContentValues();

        cv.put(TodoTable.COLUMN_ID,item.getId());
        cv.put(TodoTable.COLUMN_GMT_CREATE ,item.getGmtCreate());
        cv.put(TodoTable.COLUMN_TYPE ,item.getType());
        cv.put(TodoTable.COLUMN_CONTENT_TYPE ,item.getContentType());
        cv.put(TodoTable.COLUMN_CONTENT_URL ,item.getContentUrl());
        cv.put(TodoTable.COLUMN_CONTENT_ID ,item.getContentId());
        cv.put(TodoTable.COLUMN_TITLE ,item.getTitle());
        cv.put(TodoTable.COLUMN_PICURL ,item.getPicUrl());
        cv.put(TodoTable.COLUMN_TAG ,item.getTag());
        cv.put(TodoTable.COLUMN_TAG_COLOR ,item.getTagColor());
        cv.put(TodoTable.COLUMN_TAG_COLOR_BG,item.getTagColorBg());
        cv.put(TodoTable.COLUMN_TOP,item.getTop());
        cv.put(TodoTable.COLUMN_TYPE_NAME,item.getTypeName());

        ContentResolver cr = this.mContext.getContentResolver();
        Uri uri = MyTodoContentProvider.CONTENT_URI;
        Log.d(tag, "course item insert uri:" + uri);
        Uri insertedUri = cr.insert(uri, cv);
        Log.d(tag, "course uri:" + insertedUri);
    }

    public void removeCourseItem() {
        //int i = getCount();
        ContentResolver cr = this.mContext.getContentResolver();
        Uri uri = MyTodoContentProvider.CONTENT_URI;
        //Uri delUri = Uri.withAppendedPath(uri, Integer.toString(i));
        cr.delete(uri, null, null);
    }

    public List<CourserItem> showCourseItems(String type) {
        Uri uri = MyTodoContentProvider.CONTENT_URI;
        Activity a = (Activity) this.mContext;
        Cursor c = mContext.getContentResolver().query(uri,
                null, //projection
                TodoTable.COLUMN_TYPE + "=?",
                new String[] { type }, //selection args array of strings
                null); //sort order

        int col1 = c.getColumnIndex(TodoTable.COLUMN_ID);
        int col2 = c.getColumnIndex(TodoTable.COLUMN_GMT_CREATE);
        int col3 = c.getColumnIndex(TodoTable.COLUMN_TYPE);
        int col4 = c.getColumnIndex(TodoTable.COLUMN_CONTENT_TYPE);
        int col5 = c.getColumnIndex(TodoTable.COLUMN_CONTENT_URL);
        int col6 = c.getColumnIndex(TodoTable.COLUMN_CONTENT_ID);
        int col7 = c.getColumnIndex(TodoTable.COLUMN_TITLE);
        int col8 = c.getColumnIndex(TodoTable.COLUMN_PICURL);
        int col9 = c.getColumnIndex(TodoTable.COLUMN_TAG);
        int col10 = c.getColumnIndex(TodoTable.COLUMN_TAG_COLOR);
        int col11 = c.getColumnIndex(TodoTable.COLUMN_TAG_COLOR_BG);
        int col12 = c.getColumnIndex(TodoTable.COLUMN_TOP);
        int col13 = c.getColumnIndex(TodoTable.COLUMN_TYPE_NAME);

        List<CourserItem> items  = new ArrayList<CourserItem>();
        //walk through the rows based on indexes
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            //Gather values
            CourserItem item  = new CourserItem();
            int id = c.getInt(col1);
            item.setId(String.valueOf(id));
            item.setGmtCreate(c.getString(col2));
            item.setType(c.getString(col3));
            item.setContentType(c.getString(col4));
            item.setContentUrl(c.getString(col5));
            item.setContentId(c.getString(col6));
            item.setTitle(c.getString(col7));
            item.setPicUrl(c.getString(col8));
            item.setTag(c.getString(col9));
            item.setTagColor(c.getString(col10));
            item.setTagColorBg(c.getString(col11));
            item.setTop(c.getString(col12));
            item.setTypeName(c.getString(col13));
            items.add(item);

        }



        //Close the cursor
        //ideally this should be done in
        //a finally block.
        c.close();
        return items;
    }

    public int getCount() {
        Uri uri = MyTodoContentProvider.CONTENT_URI;
        Activity a = (Activity) this.mContext;
        Cursor c =  mContext.getContentResolver().query(uri,
                null, //projection
                null, //selection string
                null, //selection args array of strings
                null);
        int numberOfRecords = c.getCount();
        c.close();
        return numberOfRecords;
    }
}
