package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GumGumBotDataTest {
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
        assertEquals(GumGumBotData.getInstance().getIdentifier(), "GUMGUMBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("GumGum-Bot/1.0 (http://gumgum.com; verity-support@gumgum.com)");
        assertTrue(GumGumBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("GumGum-Bot (http://gumgum.com; verity-support@gumgum.com)");
        assertTrue(GumGumBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Good browser");
        assertFalse(GumGumBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(GumGumBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertTrue(GumGumBotData.getInstance().getHostnames().isEmpty());
    }
}