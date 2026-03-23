package tiisetso2004;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Login Validation Tests")
class LoginTest {

    // Tests for nullCheck()
    @Test
    @DisplayName("nullCheck should return false for null input")
    void testNullCheckWithNull() {
        assertFalse(Login.nullCheck(null));
    }

    @Test
    @DisplayName("nullCheck should return false for empty string")
    void testNullCheckWithEmpty() {
        assertFalse(Login.nullCheck(""));
    }

    @Test
    @DisplayName("nullCheck should return false for blank string")
    void testNullCheckWithBlank() {
        assertFalse(Login.nullCheck("   "));
    }

    @Test
    @DisplayName("nullCheck should return true for valid input")
    void testNullCheckWithValidInput() {
        assertTrue(Login.nullCheck("valid input"));
    }

    // Tests for checkUsername()
    @Test
    @DisplayName("checkUsername should accept valid username")
    void testCheckUsernameValid() {
        assertTrue(Login.checkUsername("john_doe"));
        assertTrue(Login.checkUsername("user123"));
    }

    @Test
    @DisplayName("checkUsername should reject username with less than 5 characters")
    void testCheckUsernameTooShort() {
        assertFalse(Login.checkUsername("john"));
        assertFalse(Login.checkUsername("ab_cd"));
    }

    @Test
    @DisplayName("checkUsername should reject username longer than 20 characters")
    void testCheckUsernameTooLong() {
        assertFalse(Login.checkUsername("this_is_a_very_long_username_123"));
    }

    @Test
    @DisplayName("checkUsername should reject username with special characters")
    void testCheckUsernameWithSpecialChars() {
        assertFalse(Login.checkUsername("john@doe"));
        assertFalse(Login.checkUsername("user-123"));
    }

    // Tests for checkCellphoneNumber()
    @Test
    @DisplayName("checkCellphoneNumber should accept valid SA number")
    void testCheckCellphoneNumberValid() {
        assertTrue(Login.checkCellphoneNumber("+27123456789"));
    }

    @Test
    @DisplayName("checkCellphoneNumber should reject invalid format")
    void testCheckCellphoneNumberInvalidFormat() {
        assertFalse(Login.checkCellphoneNumber("27123456789")); // missing +
        assertFalse(Login.checkCellphoneNumber("+26123456789")); // wrong country code
        assertFalse(Login.checkCellphoneNumber("+27123456")); // too short
    }

    // Tests for checkFirstName()
    @Test
    @DisplayName("checkFirstName should accept valid names")
    void testCheckFirstNameValid() {
        assertTrue(Login.checkFirstName("John Doe"));
        assertTrue(Login.checkFirstName("Mary-Jane"));
        assertTrue(Login.checkFirstName("O'Brien"));
    }

    @Test
    @DisplayName("checkFirstName should reject single character")
    void testCheckFirstNameTooShort() {
        assertFalse(Login.checkFirstName("A"));
    }

    @Test
    @DisplayName("checkFirstName should reject names with numbers")
    void testCheckFirstNameWithNumbers() {
        assertFalse(Login.checkFirstName("John123"));
    }

    // Tests for checkPasswordComplexity()
    @Test
    @DisplayName("checkPasswordComplexity should accept strong password")
    void testCheckPasswordComplexityValid() {
        assertTrue(Login.checkPasswordComplexity("StrongPass1!"));
    }

    @Test
    @DisplayName("checkPasswordComplexity should reject password without uppercase")
    void testCheckPasswordComplexityNoUppercase() {
        assertFalse(Login.checkPasswordComplexity("strongpass1!"));
    }

    @Test
    @DisplayName("checkPasswordComplexity should reject password without special character")
    void testCheckPasswordComplexityNoSpecialChar() {
        assertFalse(Login.checkPasswordComplexity("StrongPass1"));
    }

    @Test
    @DisplayName("checkPasswordComplexity should reject short password")
    void testCheckPasswordComplexityTooShort() {
        assertFalse(Login.checkPasswordComplexity("P@ss1"));
    }
}
