package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

/**
 * Resources:
 * http://en.wikipedia.org/wiki/Googlebot
 * https://support.google.com/webmasters/answer/80553
 */
public class GoogleBotData implements CrawlerData {

    private static final Predicate<String> PREDICATE = new Predicate<String>() {
        @Override
        public boolean apply(String userAgent) {
            return userAgent != null && userAgent.contains("Googlebot");
        }
    };

    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of("googlebot.com");


    private static final GoogleBotData INSTANCE = new GoogleBotData();
    public static GoogleBotData getInstance() {
        return INSTANCE;
    }
    private GoogleBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "GOOGLEBOT";
    }

    @NotNull
    @Override
    public Predicate<String> getUserAgentChecker() {
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
