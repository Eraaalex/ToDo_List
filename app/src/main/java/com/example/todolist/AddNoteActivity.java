package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    Button buttonSave;
    RadioGroup radioGroupPriority;
    RadioButton radioButtonLow;
    RadioButton radioButtonMedium;
    EditText editText;
    private DataBase dataBase = DataBase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void initViews() {
        buttonSave = findViewById(R.id.buttonSave);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        editText = findViewById(R.id.editTextNote);

    }

    private void saveNote() {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(AddNoteActivity.this, R.string.warning_empty, Toast.LENGTH_SHORT).show();
        }
        int priority = getPriority();
        int id = dataBase.getNotes().size();
        Note note = new Note(id, text, priority);
        dataBase.add(note);
        finish(); // Завершение Activity
    }

    private int getPriority() {
        int priority;
        if (radioButtonLow.isChecked()) {
            priority = 0;
        } else if (radioButtonMedium.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;
    }

    public static Intent newIntent(Context context){
       Intent intent =  new Intent(context, AddNoteActivity.class);
       return intent;
    }

}