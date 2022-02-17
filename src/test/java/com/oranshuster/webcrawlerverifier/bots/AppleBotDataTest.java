package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableList;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AppleBotDataTest {
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
        assertEquals(AppleBotData.getInstance().getIdentifier(), "APPLEBOT");
    }

    @Test
    public void testGetUserAgentChecker() throws Exception {
        UserAgent ua = uaa.parse("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/600.2.5 (KHTML, like Gecko) Version/8.0.2 Safari/600.2.5 (Applebot/0.1)");
        assertTrue(AppleBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.1.1 Safari/605.1.15 (Applebot/0.1)");
        assertTrue(AppleBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Mozilla/5.0 (iPhone; CPU iPhone OS 8_1 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12B410 Safari/600.1.4 (Applebot/0.1; +http://www.apple.com/go/applebot)");
        assertTrue(AppleBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Mozilla/5.0 (iPhone; CPU iPhone OS 13_4_1 like Mac OS X) AppleWebKit/605.1.15Z (KHTML, like Gecko) Version/13.1 Mobile/15E148 Safari/604.1 (Applebot/0.1)");
        assertTrue(AppleBotData.getInstance().getUserAgentChecker().test(ua));

        ua = uaa.parse("Good browser");
        assertFalse(AppleBotData.getInstance().getUserAgentChecker().test(ua));
    }

    @Test
    public void testGetIps() throws Exception {
        assertTrue(AppleBotData.getInstance().getIps().isEmpty());
    }

    @Test
    public void testGetHostnames() throws Exception {
        assertEquals(AppleBotData.getInstance().getHostnames(), ImmutableList.of("applebot.apple.com"));
    }

}