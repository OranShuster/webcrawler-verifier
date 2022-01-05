package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableList;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GoogleAdsBotDataTest {
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
        assertEquals(GoogleAdsBotData.getInstance().getIdentifier(), "GOOGLEADSBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("AdsBot-Google");
        assertTrue(GoogleAdsBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("AdsBot-Google (+http://www.google.com/adsbot.html)");
        assertTrue(GoogleAdsBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("foo Google bar");
        assertFalse(GoogleAdsBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(GoogleAdsBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertEquals(GoogleAdsBotData.getInstance().getHostnames(), ImmutableList.of("googlebot.com", "google.com"));
    }

}