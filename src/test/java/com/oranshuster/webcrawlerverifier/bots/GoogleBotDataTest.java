package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@SuppressWarnings("ALL")
public class GoogleBotDataTest {

    @Test
    public void testGetIdentifier() throws Exception {
        assertEquals(GoogleBotData.getInstance().getIdentifier(), "GOOGLEBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        assertTrue(GoogleBotData.getInstance().getUserAgentChecker().apply("foo Googlebot bar"));
        assertFalse(GoogleBotData.getInstance().getUserAgentChecker().apply("foo Google bar"));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(GoogleBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertEquals(GoogleBotData.getInstance().getHostnames(), ImmutableList.of("googlebot.com"));
    }
}