package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    @Autowired
    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentialsByUserId(Integer UserId) {
        return credentialMapper.findByUserId(UserId);
    }

    public int insertCredential(Credential newCredential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(newCredential.getPassword(), encodedKey);
        newCredential.setKey(encodedKey);
        newCredential.setPassword(encryptedPassword);
        return credentialMapper.addCredential(newCredential);
    }

    public int updateCredential(Credential updateCredential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedUpdateKey = Base64.getEncoder().encodeToString(key);
        String encryptedUpdatePassword = encryptionService.encryptValue(updateCredential.getPassword(), encodedUpdateKey);
        updateCredential.setKey(encodedUpdateKey);
        updateCredential.setPassword(encryptedUpdatePassword);
        return credentialMapper.updateCredential(updateCredential);
    }


    public Credential getCredential(int credentialId){
        return credentialMapper.findCredential(credentialId);
    }

    public int deleteCredential(int credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }

    public String decryptPassword(Credential credential) {
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }

    public List<String> getAllDecryptedPasswords(Integer userId) {
        List<Credential> credentialList = getCredentialsByUserId(userId);
        List<String> unencryptedPasswords = new ArrayList<>();
        if (credentialList != null && !credentialList.isEmpty()) {
            for (Credential credential : credentialList) {
                String encodedKey = credential.getKey();
                unencryptedPasswords.add(encryptionService.decryptValue(credential.getPassword(), encodedKey));
            }
            return unencryptedPasswords;
        }
        return new ArrayList<>();
    }

}
