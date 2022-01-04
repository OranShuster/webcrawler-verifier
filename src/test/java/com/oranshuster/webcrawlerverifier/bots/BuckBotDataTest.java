package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BuckBotDataTest {
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
        assertEquals(BuckBotData.getInstance().getIdentifier(), "BUCKBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("Buck/2.2; (+https://app.hypefactors.com/media-monitoring/about.html)");
        assertTrue(BuckBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Buck; (+https://app.hypefactors.com/media-monitoring/about.html)");
        assertTrue(BuckBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Good browser");
        assertFalse(BuckBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(BuckBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertTrue(BuckBotData.getInstance().getHostnames().isEmpty());
    }
}