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
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{number}")
    public String rollDice(@PathVariable int number, Model model) {
        Dice dice = new Dice();

        model.addAttribute("userGuess", number);
        model.addAttribute("diceResult", dice.getValue());
        return "rolldice-result";
    }
}
