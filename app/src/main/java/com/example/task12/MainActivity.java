package com.example.task12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private EditText mEditText;
    private Button mButtonApply;
    private Switch mSwitch;
    private Button mButtonSave;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private String text;
    private boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView1);
        mEditText = (EditText) findViewById(R.id.editText);
        mButtonApply = (Button) findViewById(R.id.button_apply);
        mButtonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(mEditText.getText().toString());
            }
        });
        mSwitch = (Switch) findViewById(R.id.switch1);
        mButtonSave = (Button) findViewById(R.id.button_save);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        loadData();
        updateViews();
    }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, mTextView.getText().toString());
        editor.putBoolean(SWITCH1, mSwitch.isChecked());

        editor.apply();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
    }

    public void updateViews() {
        mTextView.setText(text);
        mSwitch.setChecked(switchOnOff);
    }
}