package com.coplaying.hgtest2;

/**
 * Created by hyung on 2017-05-17.
 */

public class Note {
    private String noteText;

    public Note() {
    }

    public Note(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
