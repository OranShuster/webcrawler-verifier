package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Contains the data for one web crawler.
 * See the implementations for examples.
 *
 * <p>The data returned by the methods does not need to stay the same over multiple method calls.
 * This allows an implementation to use live data, or to reload data from files, and therefore
 * adjust at runtime without requiring a software restart (or redeployment).</p>
 */
public interface CrawlerData {

    /**
     * A string identifying this crawler.
     * No hasty changes shall be made to these identifiers.
     * A String is used instead of an enum to be flexible, this way users can implement their own easily.
     *
     * Specifications:
     * The string is in upper case, only using A-Z 0-9 and the hyphen "-", from 2 to 20 characters.
     * The string is the name of the bot, eg "GOOGLEBOT" not "GOOGLE".
     * @return A string identifying this crawler
     */
    @NotNull
    String getIdentifier();

    /**
     * @return true to interpret as that crawler, false for not.
     */
    @NotNull
    Predicate<UserAgent> getUserAgentChecker();

    /**
     * Entries look like "66.249.66.1".
     * Empty if no hardcoded IPs shall be allowed.
     * @return Crawler known IPs
     */
    @NotNull
    Set<String> getIps();

    /**
     * Entries look like "example.com" or "foo.example.com" in lower case, without trailing dot.
     * Empty if there is no known host name with reverse dns. For example DuckDuckGo currently doesn't provide this.
     * @return Crawler known host names
     */
    @NotNull
    Set<String> getHostnames();

}
