package com.kalbe.mobiledevknlibs.library.badgeall.badge;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.kalbe.mobiledevknlibs.library.badgeall.viewbadger.BadgerShortCut;
import com.kalbe.mobiledevknlibs.library.badgeall.viewbadger.CloseHelper;
import com.kalbe.mobiledevknlibs.library.badgeall.viewbadger.ShortcutBadgeException;

import java.util.Arrays;
import java.util.List;

/**
 * Deprecated, Samesung devices will use DefaultBadger
 */
@Deprecated
public class SamsungHomeBadger implements BadgerShortCut {
    private static final String CONTENT_URI = "content://com.sec.badge/apps?notify=true";
    private static final String[] CONTENT_PROJECTION = new String[]{"_id","class"};

    @Override
    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        Uri mUri = Uri.parse(CONTENT_URI);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(mUri, CONTENT_PROJECTION, "package=?", new String[]{componentName.getPackageName()}, null);
            if (cursor != null) {
                String entryActivityName = componentName.getClassName();
                boolean entryActivityExist = false;
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    ContentValues contentValues = getContentValues(componentName, badgeCount, false);
                    contentResolver.update(mUri, contentValues, "_id=?", new String[]{String.valueOf(id)});
                    if (entryActivityName.equals(cursor.getString(cursor.getColumnIndex("class")))) {
                        entryActivityExist = true;
                    }
                }

                if (!entryActivityExist) {
                    ContentValues contentValues = getContentValues(componentName, badgeCount, true);
                    contentResolver.insert(mUri, contentValues);
                }
            }
        } finally {
            CloseHelper.close(cursor);
        }
    }

    private ContentValues getContentValues(ComponentName componentName, int badgeCount, boolean isInsert) {
        ContentValues contentValues = new ContentValues();
        if (isInsert) {
            contentValues.put("package", componentName.getPackageName());
            contentValues.put("class", componentName.getClassName());
        }

        contentValues.put("badgecount", badgeCount);

        return contentValues;
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList(
                "com.sec.android.app.launcher",
                "com.sec.android.app.twlauncher",
                "com.sec.android.app.touchWizlauncher",
                "com.sec.android.app.easylauncher"
        );
    }
}
