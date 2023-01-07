package com.shakirov.http.controller.async;

import com.shakirov.model.async.AsyncUser;
import com.shakirov.service.async.AsyncUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@RequestMapping("/api/rest")
@RequiredArgsConstructor
@Profile(value = "dev")
public class AsyncRestController {

    private final AsyncUserService asyncUserService;

    @GetMapping("/get/{id}")
    public ResponseEntity<AsyncUser> get(@PathVariable(value = "id") Integer id) {
        AsyncUser user = asyncUserService.getUserByIdSync(String.valueOf(id));
        return ResponseEntity.ok(user);
    }
}
