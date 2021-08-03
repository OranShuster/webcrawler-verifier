package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * For the Yahoo search engine bot.
 * <p>
 * Yahoo changes their search provider every now and then. First they run their own (ones they bought),
 * then they let Bing do the job, then Google ...
 * <p>
 * Although they had announced to take their own Slurp offline, they never did. And recently the spidering
 * has increased again.
 * <p>
 * Resources:
 * http://en.wikipedia.org/wiki/Yahoo!_Slurp
 * https://help.yahoo.com/kb/search/slurp-crawling-page-sln22600.html
 * http://webmasters.stackexchange.com/questions/22565/is-there-any-reason-to-allow-yahoo-slurp-to-crawl-my-site
 */
public class YahooSlurpData implements CrawlerData {

    private static final Predicate<String> PREDICATE = userAgent -> {
        //see http://en.wikipedia.org/wiki/Yahoo!_Slurp
        return userAgent != null && (userAgent.contains("Yahoo! Slurp") || userAgent.contains("Yahoo Slurp"));
    };

    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of(
            //They also recommended the reverse and forward DNS verification method:
            //http://www.ysearchblog.com/2007/06/05/yahoo-search-crawler-slurp-has-a-new-address-and-signature-card/
            //in 2007 they moved from inktomisearch.com to crawl.yahoo.net
            "crawl.yahoo.net", //this used to be the one before switching to bing. i'll leave it in. yahoo changes every now and then.
            "yse.yahoo.net" //this is used as of 2016-02-19 and they resolve back. ("yse" standing for Yahoo Search Engine I suppose)
    );


    private static final YahooSlurpData INSTANCE = new YahooSlurpData();

    public static YahooSlurpData getInstance() {
        return INSTANCE;
    }

    private YahooSlurpData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "YAHOOSLURP";
    }

    @NotNull
    @Override
    public java.util.function.Predicate<String> getUserAgentChecker() {
        return PREDICATE;
    }

    @NotNull
    @Override
    public Set<String> getIps() {
        return Collections.emptySet();
    }

    @NotNull
    @Override
    public Set<String> getHostnames() {
        return HOSTNAMES;
    }
}
