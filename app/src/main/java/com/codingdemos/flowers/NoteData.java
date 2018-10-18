package com.codingdemos.flowers;

public class NoteData {

    private String _id;
    private String title;
    private String content;

//    public NoteData(String noteId, String noteTitle, String noteContent) {
//        this.set_id(noteId);
//        this.setTitle(noteTitle);
//        this.setContent(noteContent);
//    }

    public NoteData(){}

//    public String getNoteId() {
//        return get_id();
//    }
//
//    public String getNoteTitle() {
//        return getTitle();
//    }
//
//    public String getNoteContent() {
//        return getContent();
//    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}