package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    private List<File> files;
    private List<Note> notes;
    private List<Credential> credentials;

    @Autowired
    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @PostConstruct
    public void postConstruct(){
        files = new ArrayList<>();
        notes = new ArrayList<>();
        credentials = new ArrayList<>();
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Authentication auth, Model model) {
        Integer uid = userService.getUserById(auth.getName());

        files = fileService.getFilesByUserId(uid);
        notes = noteService.getNotesByUserId(uid);
        credentials = credentialService.getCredentialsByUserId(uid);
        
        model.addAttribute("note",new Note());
        model.addAttribute("credential",new Credential());
        model.addAttribute("credentialService", credentialService);

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
