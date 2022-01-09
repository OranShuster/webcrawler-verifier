package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Based on https://www.upcontent.com/robots
 */
public class SottopopBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("AgentName").contains("Sottopop");

    private static final SottopopBotData INSTANCE = new SottopopBotData();

    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of("upcontent.com");


    public static SottopopBotData getInstance() {
        return INSTANCE;
    }

    private SottopopBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "SOTTOPOPBOT";
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
    public Set<String> getHostnames() { return HOSTNAMES; }
}
