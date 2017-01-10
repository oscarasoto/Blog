package us.oscarsoto.security;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author oscarsoto on 1/10/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
