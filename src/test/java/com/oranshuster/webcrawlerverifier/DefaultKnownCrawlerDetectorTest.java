package com.oranshuster.webcrawlerverifier;

import com.oranshuster.webcrawlerverifier.annotations.RetryCountIfFailed;
import com.oranshuster.webcrawlerverifier.bots.BuiltInCrawlers;
import com.oranshuster.webcrawlerverifier.bots.CrawlerData;
import com.oranshuster.webcrawlerverifier.bots.KnownHostBotVerifier;
import com.oranshuster.webcrawlerverifier.bots.KnownHostBotVerifierBuilder;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.apache.commons.collections4.map.LRUMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * These are not unit tests, they are real world data tests. They may fail if
 * - a provider has changed their network setup
 * - internet drops
 */
@SuppressWarnings("ALL")
public class DefaultKnownCrawlerDetectorTest {
    DefaultKnownCrawlerDetector detector;

    @BeforeClass
    public void beforeClass() {
        detector = all();
    }

    @Test
    public void none() throws Exception {
        assertFalse(detector.detect("", "127.0.0.1").isPresent());
        assertFalse(detector.detect(" ", "127.0.0.1").isPresent());
        assertFalse(detector.detect(null, "127.0.0.1").isPresent());
    }

    /**
     * see https://support.google.com/webmasters/answer/1061943?hl=en
     */
    @Test
    public void googlebot() throws Exception {
        KnownCrawlerResult r = new KnownCrawlerResult("GOOGLEBOT", KnownCrawlerResultStatus.VERIFIED);
        assertEquals(detector.detect("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)", "66.249.66.1").get(), r);
        assertEquals(detector.detect("Googlebot/2.1 (+http://www.google.com/bot.html)", "66.249.66.1").get(), r);
        assertEquals(detector.detect("Googlebot-News", "66.249.66.1").get(), r);
        assertEquals(detector.detect("Googlebot-Image/1.0", "66.249.66.1").get(), r);
        assertEquals(detector.detect("Googlebot-Video/1.0", "66.249.66.1").get(), r);
        assertEquals(detector.detect("Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)", "66.249.66.1").get(), r);

        r = new KnownCrawlerResult("GOOGLEADSENSEBOT", KnownCrawlerResultStatus.VERIFIED);
        assertEquals(detector.detect("Mediapartners-Google", "66.249.66.1").get(),r);

        //failing by ip:
        r = new KnownCrawlerResult("GOOGLEBOT", KnownCrawlerResultStatus.IMPERSONATOR);
        assertEquals(detector.detect("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)", "55.55.55.55").get(), r);
    }

    @Test
    public void googleAdBot() throws Exception {
        KnownCrawlerResult r = new KnownCrawlerResult("GOOGLEADSBOT", KnownCrawlerResultStatus.VERIFIED);
        assertEquals(detector.detect("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1 (compatible; AdsBot-Google-Mobile; +http://www.google.com/mobile/adsbot.html)", "74.125.150.93").get(), r);
        assertEquals(detector.detect("AdsBot-Google (+http://www.google.com/adsbot.html)", "74.125.150.85").get(), r);

        //failing by ip:
        r = new KnownCrawlerResult("GOOGLEADSBOT", KnownCrawlerResultStatus.IMPERSONATOR);
        assertEquals(detector.detect("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1 (compatible; AdsBot-Google-Mobile; +http://www.google.com/mobile/adsbot.html)", "55.55.55.55").get(), r);
        assertEquals(detector.detect("AdsBot-Google (+http://www.google.com/adsbot.html)", "55.55.55.55").get(), r);
    }

    /**
     * see http://www.bing.com/webmaster/help/which-crawlers-does-bing-use-8c184ec0
     */
    @Test
    public void bingBot() throws Exception {
        KnownCrawlerResult r = new KnownCrawlerResult("BINGBOT", KnownCrawlerResultStatus.VERIFIED);
        assertEquals(detector.detect("Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)", "13.66.139.82").get(), r);
        assertEquals(detector.detect("msnbot/2.0b (+http://search.msn.com/msnbot.htm)", "13.66.139.82").get(), r);
        assertEquals(detector.detect("msnbot-media/1.1 (+http://search.msn.com/msnbot.htm)", "13.66.139.82").get(), r);
        assertEquals(detector.detect("adidxbot/1.1 (+http://search.msn.com/msnbot.htm)", "13.66.139.82").get(), r);

        //disputable. currently string is not matched.
        assertFalse(detector.detect("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534+ (KHTML, like Gecko) BingPreview/1.0b", "13.66.139.82").isPresent());
        assertFalse(detector.detect("Mozilla/5.0 (Windows Phone 8.1; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 530) like Gecko BingPreview/1.0b", "13.66.139.82").isPresent());

        //failing by ip:
        r = new KnownCrawlerResult("BINGBOT", KnownCrawlerResultStatus.IMPERSONATOR);
        assertEquals(detector.detect("Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)", "55.55.55.55").get(), r);
    }

    @Test
    public void baiduSpider() throws Exception {
        //Forward DNS lookup on the host does not work, so these tests will check for IMPERSONATOR instead
        KnownCrawlerResult r = new KnownCrawlerResult("BAIDUSPIDER", KnownCrawlerResultStatus.IMPERSONATOR);
        assertEquals(detector.detect("Baiduspider+(+http://www.baidu.com/search/spider.htm)", "123.125.66.120").get(), r);
        assertEquals(detector.detect("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)", "123.125.66.120").get(), r);

        //see https://github.com/optimaize/webcrawler-verifier/issues/4 this fails, open task.
        //assertEquals(detector.detect("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)", "180.76.15.14").get(), r);

        //failing by ip:
        r = new KnownCrawlerResult("BAIDUSPIDER", KnownCrawlerResultStatus.IMPERSONATOR);
        assertEquals(detector.detect("Baiduspider+(+http://www.baidu.com/search/spider.htm)", "55.55.55.55").get(), r);
    }

    @Test
    @RetryCountIfFailed(3)
    public void yandexBot() throws Exception {
        KnownCrawlerResult r = new KnownCrawlerResult("YANDEXBOT", KnownCrawlerResultStatus.VERIFIED);
        //previously the test successfully checked the ip "141.8.189.111" but as of 2016-02-18 that ip has no reverse dns anymore pointing to yandex.
        //so i replaced it with 5.255.253.173 found here https://udger.com/resources/ua-list/bot-detail?bot=YandexBot
        assertEquals(detector.detect("Mozilla/5.0 (compatible; YandexDirect/3.0; +http://yandex.com/bots)", "5.255.253.173").get(), r);

        //failing by ip:
        r = new KnownCrawlerResult("YANDEXBOT", KnownCrawlerResultStatus.IMPERSONATOR);
        assertEquals(detector.detect("Mozilla/5.0 (compatible; YandexDirect/3.0; +http://yandex.com/bots)", "55.55.55.55").get(), r);
    }

    @Test
    public void duckduckBot() throws Exception {
        KnownCrawlerResult r = new KnownCrawlerResult("DUCKDUCKBOT", KnownCrawlerResultStatus.VERIFIED);
        assertEquals(detector.detect("DuckDuckBot/1.0; (+http://duckduckgo.com/duckduckbot.html)", "72.94.249.35").get(), r);

        //failing by ip:
        r = new KnownCrawlerResult("DUCKDUCKBOT", KnownCrawlerResultStatus.IMPERSONATOR);
        assertEquals(detector.detect("DuckDuckBot/1.0; (+http://duckduckgo.com/duckduckbot.html)", "55.55.55.55").get(), r);
    }

    @Test
    public void yahooSlurp() throws Exception {
        KnownCrawlerResult r;

        r = new KnownCrawlerResult("YAHOOSLURP", KnownCrawlerResultStatus.VERIFIED);
        assertEquals(detector.detect("Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)", "74.6.168.164").get(), r);

        //failing by ip:
        r = new KnownCrawlerResult("YAHOOSLURP", KnownCrawlerResultStatus.IMPERSONATOR);
        assertEquals(detector.detect("Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)", "55.55.55.55").get(), r);
    }

    @Test
    public void appleBot() throws Exception {
        KnownCrawlerResult r;

        r = new KnownCrawlerResult("APPLEBOT", KnownCrawlerResultStatus.VERIFIED);
        assertEquals(detector.detect("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/600.2.5 (KHTML, like Gecko) Version/8.0.2 Safari/600.2.5 (Applebot/0.1)", "17.58.101.179").get(), r);

        //failing by ip:
        r = new KnownCrawlerResult("APPLEBOT", KnownCrawlerResultStatus.IMPERSONATOR);
        assertEquals(detector.detect("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/600.2.5 (KHTML, like Gecko) Version/8.0.2 Safari/600.2.5 (Applebot/0.1)", "55.55.55.55").get(), r);
    }


    private DefaultKnownCrawlerDetector all() {
        UserAgentAnalyzer uaa = UserAgentAnalyzer
                .newBuilder()
                .hideMatcherLoadStats()
                .withCacheInstantiator(cacheSize -> Collections.synchronizedMap(new LRUMap<>(cacheSize)))
                .withoutCache()
                .build();

        List<KnownHostBotVerifier> verifiers = new ArrayList<>();
        for (CrawlerData crawlerData : BuiltInCrawlers.get()) {
            verifiers.add(new KnownHostBotVerifierBuilder()
                    .crawlerData(crawlerData)
                    .dnsVerifierDefault()
                    .dnsResultCacheDefault()
                    .build());
        }
        return new DefaultKnownCrawlerDetector(verifiers, uaa);
    }

}