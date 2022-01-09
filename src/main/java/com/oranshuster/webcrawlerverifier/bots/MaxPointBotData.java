package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class MaxPointBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("DeviceName").contains("MaxpointCrawler");

    private static final MaxPointBotData INSTANCE = new MaxPointBotData();

    public static MaxPointBotData getInstance() {
        return INSTANCE;
    }

    private MaxPointBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "MAXPOINTBOT";
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
