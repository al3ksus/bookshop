package shop.onlinebookshop.utils;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Validator {

    private final Set<Character> acceptableSymbols = Set.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '_', '-', '.', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '/',
            ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '`', '{', '|', '}', '~'
    );

    public boolean isLoginCorrect(String login) {
        if (login.length() < 1 || login.length() > 128) {
            return false;
        }

        for(char symbol: login.toCharArray()) {
            if (!acceptableSymbols.contains(symbol)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPasswordCorrect(String password) {
        if (password.length() < 8 || password.length() > 32) {
            return false;
        }

        for(char symbol: password.toCharArray()) {
            if (!acceptableSymbols.contains(symbol)) {
                return  false;
            }
        }

        return true;
    }

}

