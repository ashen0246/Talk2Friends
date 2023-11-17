package com.example.talk2friends;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MeetingUnitTests {

    private Meeting testMeeting;

    @Before
    public void setUp() {
        ArrayList<String> attendants = new ArrayList<>();
        attendants.add("user1@example.com");
        attendants.add("user2@example.com");

        testMeeting = new Meeting("123456", "Conversation", "Leavey Library", "14:00", attendants);
    }

    @Test
    public void testGetters() {
        assertEquals("123456", testMeeting.getMeetingID());
        assertEquals("Conversation", testMeeting.getTopic());
        assertEquals("Leavey Library", testMeeting.getLocation());
        assertEquals("14:00", testMeeting.getTime());
        assertNotNull(testMeeting.getAttendants());
        assertTrue(testMeeting.getAttendants().contains("user1@example.com"));
        assertTrue(testMeeting.getAttendants().contains("user2@example.com"));
    }

    @Test
    public void testSetters() {
        testMeeting.setMeetingID("789012");
        assertEquals("789012", testMeeting.getMeetingID());
    }

    @Test
    public void testAddAttendant() {
        testMeeting.addAttendant("user3@example.com");
        assertTrue(testMeeting.getAttendants().contains("user3@example.com"));
    }

    @Test
    public void testRemoveAttendant() {
        testMeeting.removeAttendant("user1@example.com");
        assertFalse(testMeeting.getAttendants().contains("user1@example.com"));
    }

    @Test
    public void testAttendantsListNotNull() {
        Meeting newMeeting = new Meeting();
        assertNotNull(newMeeting.getAttendants());
    }
}