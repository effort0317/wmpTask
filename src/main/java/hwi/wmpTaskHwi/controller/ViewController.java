package hwi.wmpTaskHwi.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String home() throws Exception {
        return "view";
    }
}
