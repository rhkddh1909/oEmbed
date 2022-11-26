package com.example.oemded.oEmbed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OEmbedController {
    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }
}
