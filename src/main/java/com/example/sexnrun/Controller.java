package com.example.sexnrun;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Duration;
import java.time.Instant;

@org.springframework.stereotype.Controller
public class Controller {
    private String beforeStop = "0";
    private Instant startTime;

    @GetMapping("/")
    public String index(Model model) {
        String time = beforeStop;
        model.addAttribute("time", time);
        return "index";
    }

    @PostMapping("/start")
    @ResponseBody
    public String start() {
        startTime = Instant.now();
        return "Started";
    }

    @PostMapping("/stop")
    @ResponseBody
    public String stop() {
        if (startTime != null) {
            Duration duration = Duration.between(startTime, Instant.now());
            startTime = null;
            beforeStop = duration.getSeconds() + ":" + duration.minusSeconds(duration.getSeconds()).toMillisPart();
            return beforeStop;
        } else {
            return "0";
        }
    }
    @GetMapping("/getTime")
    @ResponseBody
    public String getTime() {
        if (startTime != null) {
            Duration duration = Duration.between(startTime, Instant.now());
            return duration.getSeconds() + ":" + duration.minusSeconds(duration.getSeconds()).toMillisPart();
        } else {
            return beforeStop;
        }
    }
    @GetMapping("/toContinue")
    @ResponseBody
    public String toContinue() {
        if (startTime != null) {
            Duration duration = Duration.between(startTime, Instant.now());
            return duration.getSeconds() + ":" + duration.minusSeconds(duration.getSeconds()).toMillisPart();
        } else {
            return "0";
        }
    }

}
