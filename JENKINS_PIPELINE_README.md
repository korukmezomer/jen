# Jenkins Pipeline - URL Erişilebilirlik Kontrolü

Bu proje, Jenkins pipeline içinde çalışan bir JUnit testi ile URL erişilebilirlik kontrolü yapar.

## Özellikler

- ✅ JUnit testi ile `https://example.com` adresine erişim kontrolü
- ✅ URL erişilebilirse deploy adımı çalışır
- ✅ URL erişilemezse pipeline fail olur

## Pipeline Adımları

1. **Build**: Proje derlenir (`mvn clean compile`)
2. **Test**: URL erişilebilirlik testi çalıştırılır (`UrlAccessibilityTest`)
3. **Deploy**: Test başarılıysa deploy adımı çalışır

## Jenkins'te Kullanım

### 1. Jenkins'te Pipeline Job Oluşturma

1. Jenkins'e giriş yapın
2. "New Item" seçin
3. Job adı verin (örn: "url-accessibility-pipeline")
4. "Pipeline" seçin ve "OK" tıklayın
5. "Pipeline" sekmesine gidin
6. "Definition" kısmında "Pipeline script from SCM" seçin
7. SCM olarak "Git" seçin
8. Repository URL'inizi girin
9. "Script Path" olarak `Jenkinsfile` yazın
10. "Save" tıklayın

### 2. Pipeline'ı Çalıştırma

1. Oluşturduğunuz job'ı seçin
2. "Build Now" tıklayın
3. Pipeline çalışmaya başlayacak

### 3. Pipeline Sonuçları

- **Başarılı**: URL erişilebilir ve deploy tamamlandı
- **Başarısız**: URL erişilemiyor veya test başarısız oldu

## Testi Yerel Olarak Çalıştırma

```bash
# Sadece URL erişilebilirlik testini çalıştır
mvn test -Dtest=UrlAccessibilityTest

# Tüm testleri çalıştır
mvn test
```

## Deploy Script Özelleştirme

`deploy.sh` dosyasını gerçek deploy işlemlerinize göre özelleştirebilirsiniz:

- Docker image build/push
- Kubernetes deployment
- FTP upload
- SSH deployment
- vb.

`Jenkinsfile` içindeki Deploy stage'inde de deploy komutlarınızı ekleyebilirsiniz.

## Test Detayları

`UrlAccessibilityTest` sınıfı:
- `https://example.com` adresine HTTP GET isteği yapar
- 5 saniye timeout kullanır
- HTTP 200-299 arası response code'ları başarılı kabul eder
- Bağlantı hatası durumunda test fail olur

## Özelleştirme

URL'yi değiştirmek için `UrlAccessibilityTest.java` dosyasındaki `TARGET_URL` sabitini değiştirin:

```java
private static final String TARGET_URL = "https://example.com";
```

