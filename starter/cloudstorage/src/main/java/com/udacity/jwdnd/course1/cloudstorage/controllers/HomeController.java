package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
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
    private EncryptionService encryptionService;

    private List<File> files;
    private List<Note> notes;
    private List<Credential> credentials;
    List<String> unencryptedPasswords;

    @Autowired
    public HomeController(UserService userService, FileService fileService, NoteService noteService,
                          CredentialService credentialService,EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @PostConstruct
    public void postConstruct(){
        files = new ArrayList<>();
        notes = new ArrayList<>();
        credentials = new ArrayList<>();
        unencryptedPasswords = new ArrayList<>();
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Authentication auth, Model model) {
        Integer uid = userService.getUserById(auth.getName());

        files = fileService.getFilesByUserId(uid);
        notes = noteService.getNotesByUserId(uid);
        credentials = credentialService.getCredentialsByUserId(uid);
        unencryptedPasswords = credentialService.getAllDecryptedPasswords(uid);
        
        model.addAttribute("note",new Note());
        model.addAttribute("credential",new Credential());
        model.addAttribute("credentialService", credentialService);

        model.addAttribute("files",files);
        model.addAttribute("notes",notes);
        model.addAttribute("credentials",credentials);
        model.addAttribute("encryptionService", encryptionService);

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
