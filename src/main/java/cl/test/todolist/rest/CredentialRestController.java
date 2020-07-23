package cl.test.todolist.rest;

import cl.test.todolist.model.Credential;
import cl.test.todolist.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/admin/credentials")
public class CredentialRestController {

    @Autowired
    private CredentialService credentialService;

    @PostMapping
    public void insert(@RequestBody CredentialContext credentialContext) {
        Credential credential = credentialContext.getCredential();
        Long userId = credentialContext.getUserId();
        credentialService.save(userId, credential);
    }

    @GetMapping
    public Collection<Credential> readAll() {
        return credentialService.findAll();
    }

    @GetMapping("/{id}")
    public Credential readOne(@PathVariable("id") Long userId) {
        return credentialService.findOne(userId);
    }

    @PutMapping
    public Credential updateOne(@RequestBody Credential credential) {
        return credentialService.update(credential);
    }

    @DeleteMapping
    public void deleteOne(@RequestBody Credential credential) {
        credentialService.delete(credential);
    }
}

class CredentialContext {
    private Credential credential;
    private Long userId;

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
