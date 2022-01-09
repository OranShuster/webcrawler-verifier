package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MaxPointBotDataTest {
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
        assertEquals(MaxPointBotData.getInstance().getIdentifier(), "MAXPOINTBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("MaxPointCrawler/Nutch-1.14 (maxpoint.crawler at maxpointinteractive dot com)");
        assertTrue(MaxPointBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("MaxPointCrawler/Nutch-1.17 (valassis.crawler at valassis dot com)");
        assertTrue(MaxPointBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("MaxPointCrawler/Nutch (valassis.crawler at valassis dot com)");
        assertTrue(MaxPointBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("MaxPointCrawler/Nutch (maxpoint.crawler at maxpointinteractive dot com)");
        assertTrue(MaxPointBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Good browser");
        assertFalse(MaxPointBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(MaxPointBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertTrue(MaxPointBotData.getInstance().getHostnames().isEmpty());
    }


}