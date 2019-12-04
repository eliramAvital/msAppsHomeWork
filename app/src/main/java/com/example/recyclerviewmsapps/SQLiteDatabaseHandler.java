package com.example.recyclerviewmsapps;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MoviesDB";
    private static final String TABLE_NAME = "Movies";
    private static final String KEY_TITLE = "title";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_RATING = "rating";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_RELEASE_YEAR = "releaseyear";
    private static final String[] COLUMNS = { KEY_TITLE, KEY_RATING, KEY_RELEASE_YEAR,
            KEY_GENRE, KEY_IMAGE };


    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Movies ( "
                + "title TEXT PRIMARY KEY, " + "rating TEXT, "
                + "releaseyear TEXT, " + "genre TEXT, " + " image TEXT )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteOne(Movie movie) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "title = ?", new String[] { String.valueOf(movie.getTitle()) });
        db.close();
    }

    public Movie getMovie(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " title = ?", // c. selections
                new String[] { String.valueOf(title) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Movie movie = new Movie();
        movie.setTitle(cursor.getString(0));
        movie.setRating(cursor.getString(1));
        movie.setReleaseYear(cursor.getString(2));
        movie.setGenre(cursor.getString(3));
        movie.setImage(cursor.getString(4));

        return movie;
    }

    public List<Movie> allMovies() {

        List<Movie> movies = new LinkedList<Movie>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Movie movie = null;

        if (cursor.moveToFirst()) {
            do {
                movie = new Movie();
                movie.setTitle(cursor.getString(0));
                movie.setRating(cursor.getString(1));
                movie.setReleaseYear(cursor.getString(2));
                movie.setGenre(cursor.getString(3));
                movie.setImage(cursor.getString(4));
                movies.add(movie);
            } while (cursor.moveToNext());
        }

        return movies;
    }

    public void addMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_RATING, movie.getRating());
        values.put(KEY_RELEASE_YEAR, movie.getReleaseYear());
        values.put(KEY_GENRE, movie.getGenre());
        values.put(KEY_IMAGE, movie.getImage());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public int updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_RATING, movie.getRating());
        values.put(KEY_RELEASE_YEAR, movie.getReleaseYear());
        values.put(KEY_GENRE, movie.getGenre());
        values.put(KEY_IMAGE, movie.getImage());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "title = ?", // selections
                new String[] { String.valueOf(movie.getTitle()) });

        db.close();

        return i;
    }

}
