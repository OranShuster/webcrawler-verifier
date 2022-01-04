package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IntegralAdScienceBotDataTest {
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
        assertEquals(IntegralAdScienceBotData.getInstance().getIdentifier(), "IASBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("IAS Wombles (ias_wombles; https://integralads.com/site-indexing-policy/)");
        assertTrue(IntegralAdScienceBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("IAS Crawler (ias_crawler; https://integralads.com/site-indexing-policy/)");
        assertTrue(IntegralAdScienceBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("ias-or/3.1 (+https://www.admantx.com/service-fetcher.html)");
        assertTrue(IntegralAdScienceBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("ias-or (+https://www.admantx.com/service-fetcher.html)");
        assertTrue(IntegralAdScienceBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("ias-va/3.1 (+https://www.admantx.com/service-fetcher.html)");
        assertTrue(IntegralAdScienceBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("ias-va (+https://www.admantx.com/service-fetcher.html)");
        assertTrue(IntegralAdScienceBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("admantx-ussy04/3.2 (+http://www.admantx.com/service-fetcher.html)");
        assertTrue(IntegralAdScienceBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("admantx-ussy (+http://www.admantx.com/service-fetcher.html)");
        assertTrue(IntegralAdScienceBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Good browser");
        assertFalse(IntegralAdScienceBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(IntegralAdScienceBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertTrue(IntegralAdScienceBotData.getInstance().getHostnames().isEmpty());
    }

}