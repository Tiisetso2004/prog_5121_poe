package tiisetso2004;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("POE Login Validation Test Data")
public class LoginTestAssertEquals {

    //checkFullName tests
    @ParameterizedTest
    @ValueSource(strings = {
        "J1son", 
        " J@son", 
        "Lar}ry", 
        "____",
        "Kell_y",
    })   
    @DisplayName("Check for invalid names")
    void invalidName(String names) {
        //rejects names with numbers special characters and underscores.
        assertFalse(Login.checkFullName(names));

    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Javone Matin-Roberts", 
        "Thabang Monare", 
        "Lee Ja-Hyaeoun", 
        "Nico O'Reily",
        "José de la Cruz",
        "Chloë"
    })
    @DisplayName("Check for valid names")
    void validName(String names) {
        //accepts names with hyphenated, accented and additional unicode characters. 
        assertTrue(Login.checkFullName(names));

    }

    //checkUsername test
    @ParameterizedTest
    @ValueSource(strings = {
        "kyle!!!!!!!", //too long, has no underscore[POE test data]
        "us_r", //too short
        "      ", //rejects blank strings
        "@Us_r", // should reject special characters
        "" //rejects empty strings
    })
    @DisplayName("Check for incorrectly formatted usernames")
    void invalidUsername(String invalidUsernames) {
        assertFalse(Login.checkUsername(invalidUsernames));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "kyl_1", //[POE test data]
        "JH_45",
        "US_ER", 
        "pP_as",
        "f1_VE" 
    })
    @DisplayName("Check for correctly formatted usernames")
    void validUsername(String validUsernames) {
        assertTrue(Login.checkUsername(validUsernames));

    }

    //checkCellphoneNumber test
    @ParameterizedTest
    @ValueSource(strings = {
        "    ", //only whitespace
        "08966553", //no international code'+27'[POE test data]
        "+2712345678901254", //too long
        "+2782461", //too short
        "+27 825 639 280", // whitespace in-between
        "+47761234582" //correct format, incorrect country code
    })
    @DisplayName("Check for incorrectly formatted cellphone numbers")
    void invalidCellphoneNumber(String invalidCellNumbers) {
        assertFalse(Login.checkCellphoneNumber(invalidCellNumbers));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "+27000000000",
        "+27123456789",
        "+27838968976", //[POE test data]
        "+27782156493",
        "+27258147369"
    })    @DisplayName("Check for correctly formatted cellphone numbers")
    void validCellphoneNumber(String validCellNumbers) {
        assertTrue(Login.checkCellphoneNumber(validCellNumbers));
    }

    //checkPasswordComplexity test
    @ParameterizedTest
    @ValueSource(strings = {
        "password", //does not meet complexity requirements[POE test data]
        "!@Qw", //correct format, too short
        "Qwert@ 1", //contains whitespace
        "782988772_jhdocwuob!", // has no uppercase chars
        "JJJ_Okocha_12!", //3+ consecutive repeating chars
        "This_is_a_v3ry_long_p@22word_but_correctly_ formatted_passworD" //exceeds 30 character limit

    })    @DisplayName("Check for invalid password formats")
    void invalidPasswordFormat(String invalidPasswords) {
        assertFalse(Login.checkPasswordComplexity(invalidPasswords));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "P@ssword!2",
        "#Special#-1y31",
        "RANDOM_CODE-47",
        "This_is_still_a_valid_p@22word",
        "Ch&&sec@ke99" //[POE test data]
    })    @DisplayName("Check for valid password formats")
    void validPasswordFormat(String validPasswords) {
        assertTrue(Login.checkPasswordComplexity(validPasswords));
    }
}