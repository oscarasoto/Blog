package us.oscarsoto.exercises;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author oscarsoto on 1/4/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
@Controller
public class DemoController {
    @GetMapping("/conditions/{status}")
    public String conditions(@PathVariable int status, Model model) {
        model.addAttribute("status", status);
        return "demos/conditions";
    }

    @GetMapping("/formatting")
    public String formatting(Model model) {
        model.addAttribute("productName", "Example");
        model.addAttribute("productPrice", "1500");
        model.addAttribute("productDate", "04/12/2017");
        return "demos/formatting";
    }

}
