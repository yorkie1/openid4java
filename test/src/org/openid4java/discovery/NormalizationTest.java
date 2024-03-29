/*
 * Copyright 2006-2007 Sxip Identity Corporation
 */

package org.openid4java.discovery;

import junit.framework.TestCase;

/**
 * @author Marius Scurtescu, Johnny Bufu
 */
public class NormalizationTest extends TestCase
{
    public NormalizationTest(String membersitePath)
    {
        super(membersitePath);
    }

    public void testCaseNormalization() throws DiscoveryException
    {
        Identifier identifier = Discovery.parseIdentifier("HTTP://EXAMPLE.COM/");
        assertEquals("http://example.com/", identifier.getIdentifier());

        identifier = Discovery.parseIdentifier("HTTP://EXAMPLE.COM/A/B?Q=Z#END");
        assertEquals("http://example.com/A/B?Q=Z#END", identifier.getIdentifier());
    }

    public void testPercentCaseNormalization() throws DiscoveryException
    {
        Identifier identifier = Discovery.parseIdentifier("HTTP://EXAMPLE.COM/%3d");
        assertEquals("http://example.com/%3D", identifier.getIdentifier());

        identifier = Discovery.parseIdentifier("HTTP://EXAMPLE.COM/a?%3d");
        assertEquals("http://example.com/a?%3D", identifier.getIdentifier());

        identifier = Discovery.parseIdentifier("HTTP://EXAMPLE.COM/a?q#%3d");
        assertEquals("http://example.com/a?q#%3D", identifier.getIdentifier());
    }

    public void testPercentNormalization() throws DiscoveryException
    {
        Identifier identifier = Discovery.parseIdentifier("HTTP://EXAMPLE.COM/%63");
        assertEquals("http://example.com/c", identifier.getIdentifier());
    }

    public void testPortNormalization() throws DiscoveryException
    {
        Identifier identifier = Discovery.parseIdentifier("HTTP://EXAMPLE.COM:80/A/B?Q=Z#");
        assertEquals("http://example.com/A/B?Q=Z#", identifier.getIdentifier());

        identifier = Discovery.parseIdentifier("https://example.com:443");
        assertEquals("https://example.com/", identifier.getIdentifier());
    }

    public void testPathNormalization() throws DiscoveryException
    {
        Identifier identifier = Discovery.parseIdentifier("http://example.com//a/./b/../b/c/");
        assertEquals("http://example.com/a/b/c/", identifier.getIdentifier());

        identifier = Discovery.parseIdentifier("http://example.com");
        assertEquals("http://example.com/", identifier.getIdentifier());

        identifier = Discovery.parseIdentifier("http://example.com?bla");
        assertEquals("http://example.com/?bla", identifier.getIdentifier());

        identifier = Discovery.parseIdentifier("http://example.com#bla");
        assertEquals("http://example.com/#bla", identifier.getIdentifier());
    }
}
