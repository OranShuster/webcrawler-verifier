package com.oranshuster.webcrawlerverifier.bots;


import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author thisismana
 */
@SuppressWarnings("ALL")
class QwantBotDataTest {
    @Test
    void validIpIsValid() {
        assertTrue(QwantBotData.getInstance().getIps().contains("194.187.171.56"));
    }

    @Test
    void userAgentCheckingWorks() {
        assertTrue(QwantBotData.getInstance().getUserAgentChecker().test("Mozilla/5.0 (compatible; Qwant-news/2.0; +https://www.qwant.com/)"));
    }
}