package com.example.android.pets.data;

import android.provider.BaseColumns;

/**
 * Created by limeg_000 on 7/28/2017.
 */

public final class PetContract {

    public static abstract class PetEntry implements BaseColumns {

        //setting constants for table name and column names
        public static final String TABLE_NAME = "pets";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_PET_SEX = "sex";
        public static final String COLUMN_PET_WEIGHT = "weight";

        //possible values for the sex
        public static final int SEX_UNKNOWN = 0;
        public static final int SEX_MALE = 1;
        public static final int SEX_FEMALE = 2;
    }

}
