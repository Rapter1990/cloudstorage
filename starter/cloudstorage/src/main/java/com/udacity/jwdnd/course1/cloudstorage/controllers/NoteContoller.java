package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteContoller {

    private NoteService noteService;
    private UserService userService;

    @Autowired
    public NoteContoller(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }


    @PostMapping("/add")
    public String AddAndEditNote(@ModelAttribute Note note, Authentication auth, Model model) {
        if(note.getNoteId() == null) {
            // note.setUserId(userService.getUserById(auth.getName()));
            int uid = userService.getUserById(auth.getName());
            int addrow = noteService.addNote(note,uid);
            if(addrow == 1 ) {
                model.addAttribute("successResult", true);
                return "result";
            }else {
                model.addAttribute("errorResult", true);
                return "result";
            }
        } else {
            int updaterow = noteService.update(note);
            if(updaterow == 1 ) {
                model.addAttribute("successResult", true);
                return "result";
            }else {
                model.addAttribute("errorResult", true);
                return "result";
            }
        }
    }

    @GetMapping("/delete/{noteId:.+}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {
        int delrow = noteService.delete(noteId);
        if(delrow == 1) {
            model.addAttribute("successResult", true);
            return "result";
        }
        else{
            model.addAttribute("errorResult", true);
            return "result";
        }
    }
}
