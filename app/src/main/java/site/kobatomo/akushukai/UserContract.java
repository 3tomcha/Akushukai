package site.kobatomo.akushukai;

import android.provider.BaseColumns;

/**
 * Created by taguchi on 2015/05/13.
 */
public final class UserContract {

    public UserContract() {}

    public static abstract class Users implements BaseColumns { // _id
        public static final String TABLE_NAME = "event";
        public static final String COL_TYPE = "type";
        public static final String COL_DATE = "date";
        public static final String COL_LOC = "location";
        public static final String COL_TICKET = "ticket";

    }

    public static abstract class Kobetsu implements BaseColumns {
        public static final String TABLE_NAME = "kobetsu";
        public static final String EVENT_ID = "event_id";
        public static final String MEMBER = "member";
        public static final String NANBU = "nanbu";
        public static final String BUSUU = "busuu";
    }
    public static abstract class Zenkoku implements BaseColumns {
        public static final String TABLE_NAME = "zenkoku";
        public static final String EVENT_ID = "event_id";
        public static final String MEMBER = "member";
        public static final String BUSUU = "busuu";
    }
    public static abstract class Member implements BaseColumns {
        public static final String TABLE_NAME = "member";
        public static final String NAME = "name";
        public static final String URL = "url";
    }




}