package app.otavio.voteRestaurante.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView init(){
        ModelAndView mv = new ModelAndView("index");
        
        return mv;
    }
}
