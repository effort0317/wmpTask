package hwi.wmpTaskHwi.controller;

import hwi.wmpTaskHwi.domain.EffectVO;
import hwi.wmpTaskHwi.domain.InfoVO;
import hwi.wmpTaskHwi.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("server")
public class PrintController {
    private final PrintService printService;

    @Autowired
    public PrintController(PrintService printService){
        this.printService = printService;
    }

    @PostMapping("/printController.do")
    public String print(Model model, InfoVO infoVO) {
        EffectVO effectVO = printService.getEffect(infoVO.getUrl(), infoVO.getType(), infoVO.getUnit());
        model.addAttribute("effectVO", effectVO);

        return "view";
    }
}
