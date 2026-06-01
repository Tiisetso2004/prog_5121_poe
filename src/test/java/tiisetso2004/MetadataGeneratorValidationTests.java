package tiisetso2004;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MetadataGeneratorValidationTests {

    @ParameterizedTest
    @ValueSource(strings = {
        "1234567890",
        "1597306527",
        "7913264552",
        "2514789652"
    })
    @DisplayName("Success case message ID validation tests")
    void testMetadataGeneratorIDValidationSuccess(String input) {
        assertTrue(MessageHandler.validateMessageID(input));
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
    @DisplayName("Failure case message ID validation tests")
    void testMetadataGeneratorIDValidationFail(String input) {
        assertFalse(MessageHandler.validateMessageID(input));
    }
    
    @Test
    @DisplayName("Success case for message length validation")
    void testMetadataGeneratorLengthSuccess() {
        assertTrue(MessageHandler.messageValidator("Hi Keegan did you receive the payment?"));
        assertTrue(MessageHandler.messageValidator("    This a test message     "));
    }

    @Test
    @DisplayName("Failure case for message length validation")
    void testMetadataGeneratorLengthFail() {
        //use message ID generator to generate 251 char string
        assertFalse(MessageHandler.messageValidator(MetadataGenerator.GenerateMessageID(251)));
        assertFalse(MessageHandler.messageValidator(""));
        assertFalse(MessageHandler.messageValidator(null));
    }
}