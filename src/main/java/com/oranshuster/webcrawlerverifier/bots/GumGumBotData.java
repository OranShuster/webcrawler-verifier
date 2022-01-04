package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class GumGumBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("DeviceName").contains("Gumgum BOT");

    private static final GumGumBotData INSTANCE = new GumGumBotData();

    public static GumGumBotData getInstance() {
        return INSTANCE;
    }

    private GumGumBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "GUMGUMBOT";
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
