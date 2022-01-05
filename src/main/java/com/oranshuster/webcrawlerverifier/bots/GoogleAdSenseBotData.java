package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Resources:
 * http://en.wikipedia.org/wiki/Googlebot
 * https://support.google.com/webmasters/answer/80553
 */
public class GoogleAdSenseBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getUserAgentString().contains("Mediapartners-Google");

    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of("googlebot.com", "google.com");


    private static final GoogleAdSenseBotData INSTANCE = new GoogleAdSenseBotData();

    public static GoogleAdSenseBotData getInstance() {
        return INSTANCE;
    }

    private GoogleAdSenseBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "GOOGLEADSENSEBOT";
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
