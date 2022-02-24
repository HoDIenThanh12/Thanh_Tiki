package com.example.tiki.ggmap.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ggMapDAO  {

    @Insert
    void insertGgMap(ggMapEntity g);

    @Query("SELECT * FROM ggMap")
    List<ggMapEntity> getListGgMap();

    @Query("DELETE FROM ggMap")
    void deleteAllGgMap();
}
