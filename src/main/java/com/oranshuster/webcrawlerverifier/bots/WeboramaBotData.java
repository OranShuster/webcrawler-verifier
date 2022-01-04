package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class WeboramaBotData implements CrawlerData {
    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("DeviceName").equals("Weborama Robot");

    private static final WeboramaBotData INSTANCE = new WeboramaBotData();

    public static WeboramaBotData getInstance() {
        return INSTANCE;
    }

    private WeboramaBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "WEBORAMABOT";
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
