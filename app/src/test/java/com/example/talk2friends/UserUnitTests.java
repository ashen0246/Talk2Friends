package com.example.talk2friends;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserUnitTests {

    private User testUser;

    @Before
    public void setUp() {
        // Create a new User instance for testing
        testUser = new User("John Doe", "01-01-1990", true, true,
                true, false, true, false);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("John Doe", testUser.getName());
        assertEquals("01-01-1990", testUser.getBday());
        assertTrue(testUser.isStudent());
        assertTrue(testUser.isProficient());
        assertTrue(testUser.isSports());
        assertFalse(testUser.isGaming());
        assertTrue(testUser.isMusic());
        assertFalse(testUser.isArt());

        // Test setters
        testUser.setName("Jane Doe");
        testUser.setBday("02-02-1995");
        testUser.setIsStudent(false);
        testUser.setIsProficient(false);
        testUser.setSports(false);
        testUser.setGaming(true);
        testUser.setMusic(false);
        testUser.setArt(true);

        assertEquals("Jane Doe", testUser.getName());
        assertEquals("02-02-1995", testUser.getBday());
        assertFalse(testUser.isStudent());
        assertFalse(testUser.isProficient());
        assertFalse(testUser.isSports());
        assertTrue(testUser.isGaming());
        assertFalse(testUser.isMusic());
        assertTrue(testUser.isArt());
    }

    @Test
    public void testFriendsAndFriendRequests() {
        // Initial friends and friend requests lists should not be null
        assertNotNull(testUser.getFriends());
        assertNotNull(testUser.getIncomingFriendRequests());

        // Test adding a friend
        testUser.getFriends().add("Friend1");
        assertTrue(testUser.getFriends().contains("Friend1"));

        // Test adding an incoming friend request
        testUser.getIncomingFriendRequests().add("FriendRequest1");
        assertTrue(testUser.getIncomingFriendRequests().contains("FriendRequest1"));

        // Test accepting a friend request
        testUser.acceptFriend("FriendRequest1");
        assertTrue(testUser.getFriends().contains("FriendRequest1"));
        assertFalse(testUser.getIncomingFriendRequests().contains("FriendRequest1"));
    }

    @Test
    public void testConstructors() {
        // Test constructor with all parameters
        User user1 = new User("Alice", "03-03-2000", true, false,
                true, true, false, true);
        assertEquals("Alice", user1.getName());
        assertEquals("03-03-2000", user1.getBday());
        assertTrue(user1.isStudent());
        assertFalse(user1.isProficient());
        assertTrue(user1.isSports());
        assertTrue(user1.isGaming());
        assertFalse(user1.isMusic());
        assertTrue(user1.isArt());
        assertNotNull(user1.getFriends());
        assertNotNull(user1.getIncomingFriendRequests());

        // Test constructor with partial parameters
        User user2 = new User("Bob", new ArrayList<>(), new ArrayList<>());
        assertEquals("Bob", user2.getName());
        assertNotNull(user2.getFriends());
        assertNotNull(user2.getIncomingFriendRequests());
    }
}
