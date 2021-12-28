package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CriteoBotDataTest {
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
        assertEquals(CriteoBotData.getInstance().getIdentifier(), "CRITEOBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("CriteoBot/0.1 (+https://www.criteo.com/criteo-crawler/)");
        assertTrue(CriteoBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("CriteoBot/1.0 (+https://www.criteo.com/criteo-crawler/)");
        assertTrue(CriteoBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("FakeBot/0.1 (+https://www.criteo.com/criteo-crawler/)");
        assertFalse(CriteoBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(CriteoBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertTrue(CriteoBotData.getInstance().getHostnames().isEmpty());
    }

}