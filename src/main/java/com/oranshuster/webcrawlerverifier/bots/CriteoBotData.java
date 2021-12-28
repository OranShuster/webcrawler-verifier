package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Based on https://www.criteo.com/criteo-crawler/
 */
public class CriteoBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("DeviceName").contains("Criteo BOT");

    private static final CriteoBotData INSTANCE = new CriteoBotData();

    public static CriteoBotData getInstance() {
        return INSTANCE;
    }

    private CriteoBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "CRITEOBOT";
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
