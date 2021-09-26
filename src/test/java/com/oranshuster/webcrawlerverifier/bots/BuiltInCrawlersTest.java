package com.oranshuster.webcrawlerverifier.bots;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@SuppressWarnings("ALL")
public class BuiltInCrawlersTest {

    @Test
    public void expectCount() throws Exception {
        assertEquals(BuiltInCrawlers.get().size(), 9); //change when new ones are added
    }

    @Test
    public void expectBingbot() throws Exception {
        for (CrawlerData crawlerData : BuiltInCrawlers.get()) {
            if (crawlerData.getIdentifier().equals("BINGBOT")) {
                return;
            }
        }
        fail("Missing expected BINGBOT");
    }

    @Test
    public void validIdentifiers() throws Exception {
        for (CrawlerData crawlerData : BuiltInCrawlers.get()) {
            if (!crawlerData.getIdentifier().matches("[A-Z0-9\\-]{2,20}")) {
                fail("Invalid identifier: >>>"+crawlerData.getIdentifier()+"<<<");
            }
        }
    }

    private static final String IPADDRESS_PATTERN = //copied from mkyong
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    @Test
    public void validateIpHostnames() throws Exception {
        for (CrawlerData crawlerData : BuiltInCrawlers.get()) {
            if (crawlerData.getIps().isEmpty() && crawlerData.getHostnames().isEmpty() && crawlerData.getUserAgentChecker() == null) {
                fail("Does not provide any data: >>>"+crawlerData.getIdentifier()+"<<<");
            }
            for (String ip : crawlerData.getIps()) {
                if (!ip.matches(IPADDRESS_PATTERN)) {
                    fail("Invalid ip: "+ip);
                }
            }
            for (String hostname : crawlerData.getHostnames()) {
                if (!hostname.matches("[a-z.\\-]+")) { //feel free to improve...
                    fail("Invalid hostname: "+hostname);
                }
            }
        }
    }

}