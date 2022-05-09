package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableList;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GoogleWebLightBotDataTest {
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
        assertEquals(GoogleAdsBotData.getInstance().getIdentifier(), "GOOGLEWEBLIGHTBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("googleweblight");
        assertTrue(GoogleAdsBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko; googleweblight) Chrome/38.0.1025.166 Mobile Safari/535.19");
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