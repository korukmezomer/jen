package com.example.odev;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("URL Erişilebilirlik Testi")
class UrlAccessibilityTest {

    private static final String TARGET_URL = "https://example.com";
    private static final int TIMEOUT_MS = 5000; // 5 saniye timeout

    @Test
    @DisplayName("example.com adresine erişim kontrolü")
    void testUrlAccessibility() throws IOException {
        URL url = new URL(TARGET_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Connection ayarları
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(TIMEOUT_MS);
        connection.setReadTimeout(TIMEOUT_MS);
        connection.setInstanceFollowRedirects(true);
        
        try {
            // Bağlantıyı aç ve response code'u al
            int responseCode = connection.getResponseCode();
            
            // HTTP 200-299 arası başarılı sayılır
            assertTrue(responseCode >= 200 && responseCode < 300, 
                String.format("URL erişilebilir değil! Response Code: %d", responseCode));
            
            System.out.println(String.format("✓ URL başarıyla erişilebilir: %s (HTTP %d)", 
                TARGET_URL, responseCode));
            
        } catch (IOException e) {
            System.err.println(String.format("✗ URL erişilemiyor: %s - Hata: %s", 
                TARGET_URL, e.getMessage()));
            throw new AssertionError(
                String.format("URL erişilebilir değil: %s - %s", TARGET_URL, e.getMessage()), e);
        } finally {
            connection.disconnect();
        }
    }
}

