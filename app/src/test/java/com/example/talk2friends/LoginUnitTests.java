package com.example.talk2friends;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginUnitTests {

    public void setup(){

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testValidEmail() {
        assertTrue(LoginActivity.isValidEmail("test@usc.edu"));
        assertTrue(LoginActivity.isValidEmail("1@usc.edu"));
        assertTrue(LoginActivity.isValidEmail("abc@usc.edu"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(LoginActivity.isValidEmail("invalidemail"));

        //this case is handled by other code
        //assertFalse(LoginActivity.isValidEmail("invalid.email@usc.edu"));

        //also handled elsewhere
        //assertFalse(LoginActivity.isValidEmail("missing@gmail.com"));

        assertFalse(LoginActivity.isValidEmail("@usc.edu"));
        assertFalse(LoginActivity.isValidEmail("user@usc..edu"));
        assertFalse(LoginActivity.isValidEmail("user @usc.edu"));
    }

    @Test
    public void testNullEmail() {
        assertFalse(LoginActivity.isValidEmail(null));
    }

    @Test
    public void testEmptyEmail() {
        assertFalse(LoginActivity.isValidEmail(""));
    }


}