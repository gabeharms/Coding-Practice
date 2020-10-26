package com.gabeharms.projectName.api.rest.root;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @RequestMapping("/")
    public String index() {
        return "Hello world\n";
    }
}
