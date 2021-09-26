package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@SuppressWarnings("all")
public class InternetArchiveBotDataTest {
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
        assertEquals(InternetArchiveBotData.getInstance().getIdentifier(), "INTERNETARCHIVEBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("Mozilla/5.0 (compatible; archive.org_bot +http://www.archive.org/details/archive.org_bot)");
        assertTrue(InternetArchiveBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Mozilla/5.0 (compatible; heritrix/3.3.0-SNAPSHOT-20140702-2247 +http://archive.org/details/archive.org_bot)");
        assertFalse(InternetArchiveBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(InternetArchiveBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertTrue(InternetArchiveBotData.getInstance().getHostnames().isEmpty());
    }

}