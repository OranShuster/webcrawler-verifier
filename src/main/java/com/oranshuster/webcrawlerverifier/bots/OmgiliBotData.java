package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class OmgiliBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("DeviceName").contains("Omgili");

    private static final OmgiliBotData INSTANCE = new OmgiliBotData();

    public static OmgiliBotData getInstance() {
        return INSTANCE;
    }

    private OmgiliBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "OMGILIBOT";
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
