package tiisetso2004;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MessageValidationTests {

    @ParameterizedTest
    @ValueSource(strings = {
        "1234567890",
        "1597306527",
        "7913264552",
        "2514789652"
    })
    @DisplayName("Success case message ID validation tests")
    void testMessageIDValidationSuccess(String input) {
        assertTrue(Message.checkMessageID(input));        
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1234567890544", //too long
        "15973065274545988565651",
        "791326455251144",
        "251478965254446877",
        "450", //short
        "55",
    })
    @DisplayName("Success case message ID validation tests")
    void testMessageIDValidationFail(String input) {
        assertFalse(Message.checkMessageID(input));        
    }
    
    @Test
    @DisplayName("Success case for message length validation")
    void testMessageLengthSuccess() {
        assertTrue(Validator.messageValidator("Hi Keegan did you receive the payment?"));
        assertTrue(Validator.messageValidator("    This a test message     "));
    }

    @Test
    @DisplayName("Failure case for message length validation")
    void testMessageLengthFail() {
        //use message ID generator to generate 251 char string
        assertFalse(Validator.messageValidator(Message.GenerateMessageID(251)));
        assertFalse(Validator.messageValidator(""));
        assertFalse(Validator.messageValidator(null));
    }
}