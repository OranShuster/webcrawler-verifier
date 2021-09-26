package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class InternetArchiveBotData implements CrawlerData {
    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("DeviceName").equals("Archive .org BOT");

    private static final InternetArchiveBotData INSTANCE = new InternetArchiveBotData();

    public static InternetArchiveBotData getInstance() {
        return INSTANCE;
    }

    private InternetArchiveBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "INTERNETARCHIVEBOT";
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
