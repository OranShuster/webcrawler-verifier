package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableList;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@SuppressWarnings("ALL")
public class GoogleBotDataTest {
    private UserAgentAnalyzer uaa;

    @BeforeClass
    public void beforeClass() {
        uaa = UserAgentAnalyzer.newBuilder()
                .hideMatcherLoadStats()
                .withCache(10000)
                .build();
    }

    @Test
    public void testGetIdentifier() throws Exception {
        assertEquals(GoogleBotData.getInstance().getIdentifier(), "GOOGLEBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("foo Googlebot bar");
        assertTrue(GoogleBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("foo Google bar");
        assertFalse(GoogleBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(GoogleBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertEquals(GoogleBotData.getInstance().getHostnames(), ImmutableList.of("googlebot.com", "google.com"));
    }
}