package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FacebookBotDataTest {
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
        assertEquals(FacebookBotData.getInstance().getIdentifier(), "FACEBOOKBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("facebookexternalhit/1.1 (+http://www.facebook.com/externalhit_uatext.php)");
        assertTrue(FacebookBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("facebookexternalhit/1.1");
        assertTrue(FacebookBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("facebookcatalog/1.0");
        assertTrue(FacebookBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Good browser");
        assertFalse(FacebookBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(FacebookBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertTrue(FacebookBotData.getInstance().getHostnames().isEmpty());
    }

}