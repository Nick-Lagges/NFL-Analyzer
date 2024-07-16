package org.nfl.data;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class ProxyTester {
    public static void main(String[] args) {
        String[] proxies = {
                "152.26.229.86:9443",
                "152.26.229.42:9443",
                "35.185.196.38:3128",
                "47.252.29.28:11222"
        };

        for (String proxy : proxies) {
            try {
                String[] proxyParts = proxy.split(":");
                String proxyHost = proxyParts[0];
                int proxyPort = Integer.parseInt(proxyParts[1]);

                Connection connection = Jsoup.connect("https://www.pro-football-reference.com/teams/min/2024.htm")
                        .proxy(proxyHost, proxyPort)
                        .timeout(20000); // Set a reasonable timeout

                connection.get();
                System.out.println("Proxy " + proxy + " is valid.");
            } catch (Exception e) {
                System.out.println("Proxy " + proxy + " is invalid: " + e.getMessage());
            }
        }
    }
}
