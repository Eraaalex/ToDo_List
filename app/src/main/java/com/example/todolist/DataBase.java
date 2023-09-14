package com.example.todolist;

import java.util.ArrayList;
import java.util.Random;

public class DataBase {
    private ArrayList<Note> notes = new ArrayList<Note>();
    private static DataBase instance = null;
    public static DataBase getInstance(){
        if (instance == null){
            instance = new DataBase();
        }
        return instance;

    }
    private DataBase(){
        Random rnd = new Random();
        for (int i = 0; i<20; ++i){
            Note note = new Note(i, "Note "+ i, rnd.nextInt(3));
            notes.add(note);
        }
    }

    public void add(Note note){
        notes.add(note);
    }
    public void remove(int id){
        for (int i = 0; i < notes.size(); ++i){
            Note note = notes.get(i);
            if (note.getId() == id){
                notes.remove(note);
            }
        }

    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<Note>(notes);
    }



}
