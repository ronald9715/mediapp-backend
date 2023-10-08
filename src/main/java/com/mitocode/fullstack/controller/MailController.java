package com.mitocode.fullstack.controller;

import com.mitocode.fullstack.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    //private final ILoginService loginService;
    //private final IResetMailService resetMailService;
    private final EmailUtil emailUtil;

    @PostMapping(value = "/sendMail")
    public ResponseEntity<Integer> sendMail(@RequestBody String username) throws Exception{
        /*int rpta = 0;
        User us = loginService.checkUsername(username);
        if (us != null && us.getIdUser()>0) {
            ResetMail resetMail = new ResetMail();
            resetMail.setRandom(UUID.randomUUID().toString());
            resetMail.setUsers(us);
            resetMail.setExpiration(10);
            resetMailService.save(resetMail);

            Mai
        }*/
        return null;
    }
}
