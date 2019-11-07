package com.thedistantblue.triaryapp.database;

public class DatabaseScheme {
    public static final class UserTable {
        public static final String NAME = "user";

        public static final class Columns {
            public static final String UUID = "id";
            public static final String Name = "name";
            public static final String Password = "password";
        }
    }

    public static final class TrainingTable {
        public static final String NAME = "training";

        public static final class Columns {
            public static final String UUID = "id";
            public static final String UUID_USER = "id_user";
            //public static final String Date = "date";
            public static final String Name = "name";
        }
    }

    public static final class ExerciseTable {
        public static final String NAME = "exercise";

        public static final class Columns {
            public static final String UUID = "id";
            public static final String UUID_TRAINING = "id_training";
            public static final String Name = "name";
            public static final String Dates = "date_column";
            public static final String Comments = "comments";
        }
    }

    public static final class SetTable {
        public static final String NAME = "settable";

        public static final class Columns {
            public static final String UUID = "id";
            public static final String UUID_EXERCISE = "id_exercise";
            public static final String Set = "set_column";
            public static final String Repetitions = "repetitions";
            public static final String Weight = "weight";
        }
    }

    public static final class RunningTable {
        public static final String NAME = "running";

        public static final class Columns {
            public static final String UUID = "id";
            public static final String UUID_USER = "id_user";
            public static final String Name = "name";
            public static final String Date = "date";
            public static final String Distance = "distance";
            public static final String Speed = "speed";
            public static final String Time = "time";
            public static final String Calories = "calories";
            public static final String Comments = "comments";
        }
    }

    public static final class DateTable {
        public static final String NAME = "datetable";

        public static final class Columns {
            public static final String UUID_TRAINING = "id_training";
            public static final String Dates = "date_column";
        }
    }
}