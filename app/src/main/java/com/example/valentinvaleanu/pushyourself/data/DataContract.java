package com.example.valentinvaleanu.pushyourself.data;

import android.provider.BaseColumns;

/**
 * Created by Tina on 15-Nov-17.
 */

public class DataContract {
    private DataContract() {
    }

    public class DataEntry implements BaseColumns {
        public static final String TABLE_LEGS_NAME = "legs";

        public static final String LEGS_ID = BaseColumns._ID;
        public static final String COLUMN_LEGS_NAME="name";
        public static final String COLUMN_LEGS_MUSCLE_GROUP="muscle_group";
        public static final String COLUMN_LEGS_DESCRIPTION="description";
        public static final String COLUMN_LEGS_DIFFICULTY="difficulty";

        public static final String TABLE_ARMS_NAME = "arms";

        public static final String ARMS_ID = BaseColumns._ID;
        public static final String COLUMN_ARMS_NAME="name";
        public static final String COLUMN_ARMS_MUSCLE_GROUP="muscle_group";
        public static final String COLUMN_ARMS_DESCRIPTION="description";
        public static final String COLUMN_ARMS_DIFFICULTY="difficulty";

        public static final String TABLE_BACK_NAME = "back";

        public static final String BACK_ID = BaseColumns._ID;
        public static final String COLUMN_BACK_NAME="name";
        public static final String COLUMN_BACK_MUSCLE_GROUP="muscle_group";
        public static final String COLUMN_BACK_DESCRIPTION="description";
        public static final String COLUMN_BACK_DIFFICULTY="difficulty";

        public static final String TABLE_ABS_NAME = "abs";

        public static final String ABS_ID = BaseColumns._ID;
        public static final String COLUMN_ABS_NAME="name";
        public static final String COLUMN_ABS_MUSCLE_GROUP="muscle_group";
        public static final String COLUMN_ABS_DESCRIPTION="description";
        public static final String COLUMN_ABS_DIFFICULTY="difficulty";

        public static final String TABLE_CHEST_NAME = "core";

        public static final String CHEST_ID = BaseColumns._ID;
        public static final String COLUMN_CHEST_NAME="name";
        public static final String COLUMN_CHEST_MUSCLE_GROUP="muscle_group";
        public static final String COLUMN_CHEST_DESCRIPTION="description";
        public static final String COLUMN_CHEST_DIFFICULTY="difficulty";

        public static final String TABLE_CARDIO_NAME = "cardio";

        public static final String CARDIO_ID = BaseColumns._ID;
        public static final String COLUMN_CARDIO_NAME="name";
        public static final String COLUMN_CARDIO_MUSCLE_GROUP="muscle_group";
        public static final String COLUMN_CARDIO_DESCRIPTION="description";
        public static final String COLUMN_CARDIO_DIFFICULTY="difficulty";

    }
}
