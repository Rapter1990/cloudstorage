package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredientialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredientialService credientialService;

    @Autowired
    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredientialService credientialService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credientialService = credientialService;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Authentication auth, Model model) {
        Integer uid = userService.getUserById(auth.getName());

        List<File> files;
        try {
            files = fileService.getFilesByUserId(uid);
        } catch (NullPointerException e){
            files = new ArrayList<>();
        }

        List<Note> notes;
        try {
            notes = noteService.getNotesByUserId(uid);
        } catch (NullPointerException e){
            notes = new ArrayList<>();
        }

        List<Credential> credentials;
        try {
            credentials = credientialService.getCredentialsByUserId(uid);
        } catch (NullPointerException e){
            credentials = new ArrayList<>();
        }

        model.addAttribute("files",files);
        model.addAttribute("notes",notes);
        model.addAttribute("credentials",credentials);
        return "home";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/login?logout";
    }

    @GetMapping("/logout")
    public String logoutView() {
        return "redirect:/login?logout";
    }
}
