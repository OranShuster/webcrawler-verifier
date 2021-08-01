package com.oranshuster.webcrawlerverifier.dns;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collection;

/**
 *
 */
public interface ReverseDnsVerifier {

    /**
     * Verifies that the given ip resolves to the given host name, and that the host name has a reverse dns
     * pointing back to the ip.
     *
     * <p>Example: if checking for "foo.example.com" then it allows "foo.example.com" and "anything.foo.example.com",
     * but not "example.com" nor "foofoo.example.com".</p>
     *
     * @param ip eg "127.0.0.1"
     * @param allowedHostName eg "example.com" lower case, without trailing dot.
     * @throws IOException in case of a dns lookup error (temporary network errors).
     *         Not if the ip misses hostname or reverse dns configurations.
     * @return if the IP matches any of the allowed host names
     */
    boolean verify(@NotNull String ip, @NotNull String allowedHostName) throws IOException;

    /**
     * Overloaded method that allows multiple domains.
     * @param ip ip to check
     * @param allowedHostNames a collection of host names to check for
     * @see #verify(String, String)
     * @return If ip matches any of the allowed host names
     * @throws IOException In case the DNS forward/reverse lookup failed
     */
    boolean verify(@NotNull String ip, @NotNull Collection<String> allowedHostNames) throws IOException;

}
