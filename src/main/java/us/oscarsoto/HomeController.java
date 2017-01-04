package us.oscarsoto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author oscarsoto on 1/4/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
@Controller
public class HomeController {
    @GetMapping("/home")
    public String welcome() {
        return "home";
    }

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

}
