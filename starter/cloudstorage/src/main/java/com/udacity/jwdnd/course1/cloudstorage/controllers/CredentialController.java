package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialMapper credentialMapper;
    private CredentialService credentialService;
    private UserService userService;

    @Autowired
    public CredentialController(CredentialMapper credentialMapper, CredentialService credentialService, UserService userService) {
        this.credentialMapper = credentialMapper;
        this.credentialService = credentialService;
        this.userService = userService;
    }


    @PostMapping("/add")
    public String AddEditCredential(@ModelAttribute Credential credential, Authentication auth, Model model) {
        if(credential.getCredentialId() == null) {
            credential.setUserId(userService.getUserById(auth.getName()));
            int addrow = credentialService.insertCredential(credential);
            if (addrow == 1) {
                model.addAttribute("successResult", true);
                return "result";
            }
            else {
                model.addAttribute("errorResult", true);
                return "result";
            }
        }
        else {
            int updaterow = credentialService.updateCredential(credential);
            if (updaterow == 1) {
                model.addAttribute("successResult", true);
                return "result";
            }
            else {
                model.addAttribute("errorResult", true);
                return "result";
            }
        }
    }

    @GetMapping("/delete/{credentialId:.+}")
    public String deleteCredential(@PathVariable Integer credentialId, Model model) {
        int delrow = credentialService.deleteCredential(credentialId);
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
