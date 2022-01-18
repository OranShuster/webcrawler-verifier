package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Based on https://developers.facebook.com/docs/sharing/webmasters/crawler
 */
public class FacebookBotData implements CrawlerData {
    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("DeviceBrand").equals("Facebook");

    private static final FacebookBotData INSTANCE = new FacebookBotData();

    public static FacebookBotData getInstance() {
        return INSTANCE;
    }

    private FacebookBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "FACEBOOKBOT";
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
        return Collections.emptySet();
    }
}
