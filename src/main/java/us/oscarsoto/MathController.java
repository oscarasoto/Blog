package us.oscarsoto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author oscarsoto on 1/4/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
@Controller
public class MathController {

    @GetMapping("/add/{numberOne}/and/{numberTwo}")
    @ResponseBody
    public String addOperation(@PathVariable int numberOne, @PathVariable int numberTwo) {
        return numberOne + " + " + numberTwo + " = " + (numberOne+numberTwo);
    }

    @GetMapping("/subtract/{numberOne}/from/{numberTwo}")
    @ResponseBody
    public String subtractOperation(@PathVariable int numberOne, @PathVariable int numberTwo) {
        return numberTwo + " - " + numberOne + " = " + (numberTwo-numberOne);
    }

    @GetMapping("/multiply/{numberOne}/and/{numberTwo}")
    @ResponseBody
    public String multiplyOperation(@PathVariable int numberOne, @PathVariable int numberTwo) {
        return numberOne + " X " + numberTwo + " = " + (numberOne*numberTwo);
    }

    @GetMapping("/divide/{numberOne}/by/{numberTwo}")
    @ResponseBody
    public String divideOperation(@PathVariable int numberOne, @PathVariable int numberTwo) {
        return numberOne + " / " + numberTwo + " = " + (numberOne/numberTwo);
    }
}
