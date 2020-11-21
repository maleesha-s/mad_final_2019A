package com.example.it19004778mad2019a.DATABASE;

import android.provider.BaseColumns;

public final class DatabaseMaster {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseMaster() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_Name = "users";
        public static final String COLUMN_Username = "Username";
        public static final String COLUMN_Password = "Password";
        public static final String COLUMN_UserType = "UserType";

    }
    public static class Movies implements BaseColumns {
        public static final String TABLE_Name = "movies";
        public static final String COLUMN_MovieName = "movie_name";
        public static final String COLUMN_MovieYear = "movie_year";

    }
    public static class Comments implements BaseColumns {
        public static final String TABLE_Name = "comments";
        public static final String COLUMN_MovieName = "movie_name";
        public static final String COLUMN_MovieRating = "movie_rating";
        public static final String COLUMN_MovieComments = "movie_comments";

    }
}