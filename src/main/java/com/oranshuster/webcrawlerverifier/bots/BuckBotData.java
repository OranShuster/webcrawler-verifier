package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Based on https://app.hypefactors.com/media-monitoring/about.html
 */
public class BuckBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("DeviceName").contains("Hypefactors Buck");

    private static final BuckBotData INSTANCE = new BuckBotData();

    public static BuckBotData getInstance() {
        return INSTANCE;
    }

    private BuckBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "BUCKBOT";
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
