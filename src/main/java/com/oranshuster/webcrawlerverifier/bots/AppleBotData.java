package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Resources:
 * https://support.apple.com/en-us/HT204683
 */
public class AppleBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getUserAgentString().contains("Applebot");

    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of("applebot.apple.com");


    private static final AppleBotData INSTANCE = new AppleBotData();

    public static AppleBotData getInstance() {
        return INSTANCE;
    }

    private AppleBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "APPLEBOT";
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
