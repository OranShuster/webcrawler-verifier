package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Resources:
 * http://en.wikipedia.org/wiki/Googlebot
 * https://support.google.com/webmasters/answer/80553
 */
public class GoogleAdsBotData implements CrawlerData {

    private static final Predicate<String> PREDICATE = userAgent -> userAgent != null && userAgent.contains("AdsBot-Google");

    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of("googlebot.com", "google.com");


    private static final GoogleAdsBotData INSTANCE = new GoogleAdsBotData();

    public static GoogleAdsBotData getInstance() {
        return INSTANCE;
    }

    private GoogleAdsBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "GOOGLEADSBOT";
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
