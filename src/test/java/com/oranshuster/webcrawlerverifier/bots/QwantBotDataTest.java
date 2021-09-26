package com.oranshuster.webcrawlerverifier.bots;


import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author thisismana
 */
@SuppressWarnings("ALL")
class QwantBotDataTest {
    private UserAgentAnalyzer uaa;

    @BeforeClass
    public void beforeClass() {
        uaa = UserAgentAnalyzer.newBuilder()
                .hideMatcherLoadStats()
                .withCache(10000)
                .build();
    }

    @Test
    void validIpIsValid() {
        assertTrue(QwantBotData.getInstance().getIps().contains("194.187.171.56"));
    }

    @Test
    void userAgentCheckingWorks() {
        UserAgent ua = uaa.parse("Mozilla/5.0 (compatible; Qwant-news/2.0; +https://www.qwant.com/)");
        assertTrue(QwantBotData.getInstance().getUserAgentChecker().test(ua));
    }
}