package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayoutNotes;
    private FloatingActionButton buttonAddNote;
    private DataBase dataBase = DataBase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void initViews(){
        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }
    private void showNotes(){
        linearLayoutNotes.removeAllViews();
        for (Note note: dataBase.getNotes()){
            // перевод xml в объект (указание файла, куда добавляется элемент, false)
            View view = getLayoutInflater().inflate(R.layout.note_item,
                    linearLayoutNotes,
                    false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataBase.remove(note.getId());
                    showNotes();
                }
            });
            TextView textView = view.findViewById(R.id.textViewNote);
            textView.setText(note.getText());
            int colorResId;

            switch (note.getPriority()){
                case 0: colorResId = android.R.color.holo_green_light;
                break;
                case 1: colorResId = android.R.color.holo_orange_light; break;
                default: colorResId = android.R.color.holo_red_light;
            }
            int color = ContextCompat.getColor(this, colorResId);
            textView.setBackgroundColor(color);
            linearLayoutNotes.addView(view);
        }
    }
}