package com.example.tiki.ggmap.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = ggMapEntity.class, version = 1)
public abstract class ggMapDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "GGMAP";
    private static ggMapDatabase instance;
    public static synchronized ggMapDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ggMapDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract ggMapDAO GgMapDAO();
}
