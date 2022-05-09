package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Resources:
 * <a href="https://en.wikipedia.org/wiki/Google_Web_Light">https://en.wikipedia.org/wiki/Google_Web_Light</a>
 * <a href="https://developers.google.com/search/docs/advanced/crawling/overview-google-crawlers">https://developers.google.com/search/docs/advanced/crawling/overview-google-crawlers</a>
 */
public class GoogleWebLightBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getUserAgentString().contains("googleweblight");

    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of("googlebot.com", "google.com");


    private static final GoogleWebLightBotData INSTANCE = new GoogleWebLightBotData();

    public static GoogleWebLightBotData getInstance() {
        return INSTANCE;
    }

    private GoogleWebLightBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "GOOGLEWEBLIGHTBOT";
    }

    @NotNull
    @Override
    public Predicate<UserAgent> getUserAgentChecker() {
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
