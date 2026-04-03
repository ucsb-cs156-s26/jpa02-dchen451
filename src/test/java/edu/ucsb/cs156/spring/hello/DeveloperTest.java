package edu.ucsb.cs156.spring.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

public class DeveloperTest {

    @Test
    public void testPrivateConstructor() throws Exception {
        // this hack is from https://www.timomeinen.de/2013/10/test-for-private-constructor-to-get-full-code-coverage/
        Constructor<Developer> constructor = Developer.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()),"Constructor is not private");

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void getName_returns_correct_name() {
        assertEquals("David", Developer.getName());
    }

    @Test
    public void getGithubId_returns_correct_githubId() {
        assertEquals("dchen451", Developer.getGithubId());
    }

    @Test
    public void getTeam_returns_team_with_correct_name() {
        Team  t = Developer.getTeam();
        assertEquals("s26-02", t.getName());
    }

    @Test
    public void getTeam_returns_team_with_correct_members() {
        Team  t = Developer.getTeam();
        assertTrue(t.getMembers().contains("Andrew Raymond"),"Team should contain Andrew Raymond");
        assertTrue(t.getMembers().contains("Arman"),"Team should contain Arman");
        assertTrue(t.getMembers().contains("Justin Shing-Chit"),"Team should contain Justin Shing-Chit");
        assertTrue(t.getMembers().contains("Kai"),"Team should contain Kai");
        assertTrue(t.getMembers().contains("Kevin"),"Team should contain Kevin");
        assertTrue(t.getMembers().contains("David"),"Team should contain David");
    }

    @Test
    public void equals_returns_true_for_same_object() {
        Team t = new Team("test-team");
        assertTrue(t.equals(t));
    }

    @Test
    public void equals_returns_false_for_different_object() {
        Team t1 = new Team("test-team");
        Team t2 = new Team("test-team");
        assertTrue(t1.equals(t2));
    }

    @Test
    public void equals_returns_true_when_fields_match() {
        Team t1 = new Team("s26-00");
        t1.addMember("Alice");
        Team t2 = new Team("s26-00");
        t2.addMember("Alice");
        
        assertTrue(t1.equals(t2));
    }

    @Test
    public void equals_returns_false_when_names_differ() {
        Team t1 = new Team("s26-00");
        t1.addMember("Alice");
        Team t2 = new Team("s26-01");
        t2.addMember("Alice");

        assertFalse(t1.equals(t2));
    }

    @Test
    public void equals_returns_false_when_members_differ() {
        Team t1 = new Team("s26-00");
        t1.addMember("Alice");
        Team t2 = new Team("s26-00");
        t2.addMember("Bob");
        
        assertFalse(t1.equals(t2));
    }

    @Test
    public void equals_returns_false_for_non_team_object() {
        Team t = new Team("s26-00");
        assertFalse(t.equals("not a team"));
    }

    @Test
    public void hashCode_returns_correct_value() {
        Team t = new Team("s26-00");
        t.addMember("Alice");
        int expectedResult = -942168065; 
        
        assertEquals(expectedResult, t.hashCode());
    }

    @Test
    public void toString_returns_correct_format() {
        Team t = new Team("s26-00");
        t.addMember("Alice");
        String expected = "Team(name=s26-00, members=[Alice])";
        
        assertEquals(expected, t.toString());
    }

    @Test
    public void toString_returns_correct_format_empty_members() {
        Team t = new Team("s26-00");
        String expected = "Team(name=s26-00, members=[])";
        
        assertEquals(expected, t.toString());
    }
}
