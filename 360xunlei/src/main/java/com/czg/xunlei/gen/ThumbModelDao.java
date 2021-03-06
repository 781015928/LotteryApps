package com.czg.xunlei.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.czg.xunlei.model.ThumbModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "THUMB_MODEL".
*/
public class ThumbModelDao extends AbstractDao<ThumbModel, Long> {

    public static final String TABLENAME = "THUMB_MODEL";

    /**
     * Properties of entity ThumbModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Api = new Property(1, String.class, "api", false, "API");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Image = new Property(3, String.class, "image", false, "IMAGE");
        public final static Property SearchId = new Property(4, String.class, "searchId", false, "searchid");
        public final static Property Ratio = new Property(5, Float.class, "ratio", false, "RATIO");
    }


    public ThumbModelDao(DaoConfig config) {
        super(config);
    }
    
    public ThumbModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"THUMB_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"API\" TEXT," + // 1: api
                "\"TITLE\" TEXT," + // 2: title
                "\"IMAGE\" TEXT," + // 3: image
                "\"searchid\" TEXT," + // 4: searchId
                "\"RATIO\" REAL);"); // 5: ratio
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"THUMB_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ThumbModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String api = entity.getApi();
        if (api != null) {
            stmt.bindString(2, api);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(4, image);
        }
 
        String searchId = entity.getSearchId();
        if (searchId != null) {
            stmt.bindString(5, searchId);
        }
 
        Float ratio = entity.getRatio();
        if (ratio != null) {
            stmt.bindDouble(6, ratio);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ThumbModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String api = entity.getApi();
        if (api != null) {
            stmt.bindString(2, api);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(4, image);
        }
 
        String searchId = entity.getSearchId();
        if (searchId != null) {
            stmt.bindString(5, searchId);
        }
 
        Float ratio = entity.getRatio();
        if (ratio != null) {
            stmt.bindDouble(6, ratio);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ThumbModel readEntity(Cursor cursor, int offset) {
        ThumbModel entity = new ThumbModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // api
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // image
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // searchId
            cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5) // ratio
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ThumbModel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setApi(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setImage(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSearchId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRatio(cursor.isNull(offset + 5) ? null : cursor.getFloat(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ThumbModel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ThumbModel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ThumbModel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
