package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WeboramaBotDataTest {
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
        assertEquals(WeboramaBotData.getInstance().getIdentifier(), "WEBORAMABOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("weborama-fetcher (+http://www.weborama.com)");
        assertTrue(WeboramaBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("weborama-fetcher/1.0 (+http://www.weborama.com)");
        assertTrue(WeboramaBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Good browser");
        assertFalse(WeboramaBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(WeboramaBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertTrue(WeboramaBotData.getInstance().getHostnames().isEmpty());
    }
}