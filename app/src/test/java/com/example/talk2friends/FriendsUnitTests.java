package com.example.talk2friends;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FriendsUnitTests {

    public void setup(){

    }

    @Test
    public void testValidEmail() {
        assertTrue(FriendsActivity.isValidEmail("a@usc.edu"));
        assertTrue(FriendsActivity.isValidEmail("ab@usc.edu"));
        assertTrue(FriendsActivity.isValidEmail("abc@usc.edu"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(FriendsActivity.isValidEmail("invalid"));
        assertFalse(FriendsActivity.isValidEmail("@usc.edu"));
        assertFalse(FriendsActivity.isValidEmail("user@usc.!edu"));
        assertFalse(FriendsActivity.isValidEmail("user @usc.edu"));
    }

    @Test
    public void testEmptyEmail() {
        assertFalse(FriendsActivity.isValidEmail(""));
        assertFalse(FriendsActivity.isValidEmail(null));
    }
}