package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    @Autowired
    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotesByUserId(Integer userId){
        List<Note> notes = noteMapper.getUserNotesBuUserId(userId);
        return notes;
    }

    public Note getNote(Integer noteId){
        return noteMapper.findNote(noteId);
    }

    public int addNote(Note newNote, int userId){
        return noteMapper.addNote(new Note(0,newNote.getNotetitle(),newNote.getNotedescription(),userId));
    }

    public int update(Note updateNote) {
        return noteMapper.updateNote(updateNote);
    }

    public int delete(Integer noteId) {
        return noteMapper.deleteNote(noteId);
    }
}
