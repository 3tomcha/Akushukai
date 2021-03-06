package site.kobatomo.akushukai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by bicpc on 2017/11/12.
 */

public class KobetsuAddEvent extends Activity {


    private String uriImg = null;
    private Drawable drawImg = null;
    private Handler imgHandler = null;
    private int imageID;
    private android.database.sqlite.SQLiteDatabase sqLiteDatabase;
    private int current_num = 0;
    private TextView count;
    private String radio_value;
    private String selected_member1;
    private String selected_member2;
    private String selected_member3;
    private String clicked_id;
    private static String[] mArray;
    private static String[] mArray2;
    private ListView dialog_list;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    public EditText member1;
    public EditText member2;
    public EditText member3;
    public View convertView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kobetsu_add_event);

        Intent intent2 = getIntent();
        clicked_id = intent2.getStringExtra("clicked_id");

        member1 = findViewById(R.id.member1);
        member2 = findViewById(R.id.member2);
        member3 = findViewById(R.id.member3);

        member1.setText("未選択");
        member2.setText("未選択");
        member3.setText("未選択");

        member1.setOnClickListener(new ClickListener_moreadd());
        member2.setOnClickListener(new ClickListener_moreadd());
        member3.setOnClickListener(new ClickListener_moreadd());

        setTitle();
        ticketCount();
        nextPage();

    }


//    private String loadData() {
//        UserOpenHelper userOpenHelper = new UserOpenHelper(this);
//        SQLiteDatabase db = userOpenHelper.getReadableDatabase();
//
//        String query = "select * from " + UserContract.Users.TABLE_NAME;
//        String result = "";
//        if (db != null) {
//            Cursor c = db.rawQuery(query, null);
//            if (c.moveToFirst()) {
//                result = c.getString(1);
//            }
//        }
//        return result;
//    }


    private void nextPage() {
        Button button = findViewById(R.id.insertButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDatabase();
                Intent intent = new Intent(KobetsuAddEvent.this, KobetsuEvent.class);
                intent.putExtra("clicked_id", clicked_id);
                startActivity(intent);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        String[] nanbu=getResources().getStringArray(R.array.nanbu);

        switch (view.getId()) {
            case R.id.more_add_radio1:
                if (checked)
                    radio_value = nanbu[0];
                break;
            case R.id.more_add_radio2:
                if (checked)
                    radio_value = nanbu[1];
                break;
            case R.id.more_add_radio3:
                if (checked)
                    radio_value = nanbu[2];
                break;
            case R.id.more_add_radio4:
                if (checked)
                    radio_value = nanbu[3];
                break;
            case R.id.more_add_radio5:
                if (checked)
                    radio_value = nanbu[4];
                break;
        }
    }



    private void insertDatabase() {
        UserOpenHelper userOpenHelper = new UserOpenHelper(this);
        SQLiteDatabase db = userOpenHelper.getWritableDatabase();


        if (selected_member1 != null) {
            ContentValues newEvent = new ContentValues();
            newEvent.put(UserContract.Kobetsu.EVENT_ID, clicked_id);
            newEvent.put(UserContract.Kobetsu.MEMBER, selected_member1);
            newEvent.put(UserContract.Kobetsu.NANBU, radio_value);
            newEvent.put(UserContract.Kobetsu.BUSUU, current_num);

            long newId = db.insert(
                    UserContract.Kobetsu.TABLE_NAME,
                    null,
                    newEvent
            );
        }


        if (selected_member2 != null) {
            ContentValues newEvent2 = new ContentValues();
            newEvent2.put(UserContract.Kobetsu.EVENT_ID, clicked_id);
            newEvent2.put(UserContract.Kobetsu.MEMBER, selected_member2);
            newEvent2.put(UserContract.Kobetsu.NANBU, radio_value);
            newEvent2.put(UserContract.Kobetsu.BUSUU, current_num);

            long newId2 = db.insert(
                    UserContract.Kobetsu.TABLE_NAME,
                    null,
                    newEvent2
            );
        }
        if (selected_member3 != null) {
            ContentValues newEvent3 = new ContentValues();
            newEvent3.put(UserContract.Kobetsu.EVENT_ID, clicked_id);
            newEvent3.put(UserContract.Kobetsu.MEMBER, selected_member3);
            newEvent3.put(UserContract.Kobetsu.NANBU, radio_value);
            newEvent3.put(UserContract.Kobetsu.BUSUU, current_num);

            long newId3 = db.insert(
                    UserContract.Kobetsu.TABLE_NAME,
                    null,
                    newEvent3
            );
        }
    }

    public class ClickListener_moreadd implements View.OnClickListener{


        private String uriImg = null;
        private Drawable drawImg = null;
        private Handler imgHandler = null;
        private int imageID;
        private android.database.sqlite.SQLiteDatabase sqLiteDatabase;
        private int current_num = 0;
        private TextView count;
        private String radio_value;
        private String clicked_id;
        private String[] mArray;
        private String[] mArray2;
        private ListView dialog_list;
        private AlertDialog.Builder builder;
        private AlertDialog dialog;
        public View convertView;


        //ボタンのクリックリスナーを


        public void onClick(View v) {
            LayoutInflater inflater = (LayoutInflater) KobetsuAddEvent.this.getSystemService(LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(
                    R.layout.memberview, (ViewGroup) findViewById(R.id.dialog_custom));
            builder = new AlertDialog.Builder(KobetsuAddEvent.this);
            builder.setView(convertView);

            mArray = getResources().getStringArray(R.array.more_add_list);
            dialog_list = convertView.findViewById(R.id.dialog_list);


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    KobetsuAddEvent.this, android.R.layout.simple_list_item_1);
            for (int i = 0; i < mArray.length; i++) {
                adapter.add(mArray[i]);
            }
            dialog_list.setAdapter(adapter);
            dialog = builder.create();
            dialog.show();

            //
            Button hiragana = convertView.findViewById(R.id.hiragana);
            hiragana.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mArray = getResources().getStringArray(R.array.more_add_list2);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(KobetsuAddEvent.this, android.R.layout.simple_list_item_1);
                    for (int i = 0; i < mArray.length; i++) {
                        adapter.add(mArray[i]);
                    }
                    dialog_list.setAdapter(adapter);

                }
            });

            // ボタン処理（漢字）
            Button kanji = convertView.findViewById(R.id.kanji);
            kanji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mArray = getResources().getStringArray(R.array.more_add_list);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(KobetsuAddEvent.this, android.R.layout.simple_list_item_1);
                    for (int i = 0; i < mArray.length; i++) {
                        adapter.add(mArray[i]);
                    }
                    dialog_list.setAdapter(adapter);

                }
            });
            if (v == member1) {
                dialog_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        selected_member1 = (String) dialog_list.getItemAtPosition(position);
                        dialog.dismiss();
                        member1.setText(selected_member1);
                    }
                });
            }

            if (v == member2) {
                dialog_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        selected_member2 = (String) dialog_list.getItemAtPosition(position);
                        dialog.dismiss();
                        member2.setText(selected_member2);
                    }
                });
            }

            if (v == member3) {
                dialog_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        selected_member3 = (String) dialog_list.getItemAtPosition(position);
                        dialog.dismiss();
                        member3.setText(selected_member3);
                    }
                });
            }
        }


    }
    private void setTitle() {
        ImageView menu = findViewById(R.id.menu);
        menu.setVisibility(View.GONE);
        TextView package_title = findViewById(R.id.package_title);
        package_title.setText(R.string.package_more_add);
        RelativeLayout title=findViewById(R.id.title);
        title.setBackgroundResource(R.color.kobetsucolor);
        LinearLayout plus=findViewById(R.id.plus_ticket_zenkoku);
        plus.setBackgroundResource(R.color.bgdeepcolor);
        LinearLayout minus=findViewById(R.id.minus_ticket_zenkoku);
        minus.setBackgroundResource(R.color.bgdeepcolor);


    }
    private void ticketCount(){
        count = (TextView) findViewById(R.id.count);
        ImageView plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                current_num += 1;
                count.setText(String.valueOf(current_num));
            }
        });

        ImageView minus = findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(current_num>0) {
                    current_num -= 1;
                    count.setText(String.valueOf(current_num));
                }
            }
        });

    }




}




















