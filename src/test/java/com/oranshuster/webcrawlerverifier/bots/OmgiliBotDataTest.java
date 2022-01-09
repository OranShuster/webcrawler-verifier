package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OmgiliBotDataTest {
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
        assertEquals(OmgiliBotData.getInstance().getIdentifier(), "OMGILIBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("omgili/0.5 +http://omgili.com");
        assertTrue(OmgiliBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("omgili/1.0 +http://omgili.com");
        assertTrue(OmgiliBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("omgili +http://omgili.com");
        assertTrue(OmgiliBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Good browser");
        assertFalse(OmgiliBotData.getInstance().getUserAgentChecker().test(ua));
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