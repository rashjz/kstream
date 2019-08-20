package rashjz.info.kstream.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api")
public class FlagController {

    @GetMapping("/info")
    public String info() {
        return "app is running";
    }
}
