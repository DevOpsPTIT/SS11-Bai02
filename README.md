# Bài 2: Biên dịch đóng gói tệp ứng dụng .jar với Gradle và lưu trữ Artifacts

## 1. Cấu trúc thư mục dự án

```text
Bai02/
├── .github/
│   └── workflows/
│       └── ci.yml               # Workflow đóng gói JAR và upload Artifact
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── DemoApplication.java
│   │   │   └── service/
│   │   │       └── CalculatorService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/demo/
│           ├── DemoApplicationTests.java
│           └── service/
│               └── CalculatorServiceTest.java
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle
```

## 2. Chi tiết cấu hình Workflow (`.github/workflows/ci.yml`)

Workflow đã được bổ sung thêm 2 bước:
1. `./gradlew bootJar`: Biên dịch và đóng gói ứng dụng Spring Boot thành tệp `.jar` thực thi nằm trong thư mục `build/libs/`.
2. `actions/upload-artifact@v4`: Đính kèm và lưu trữ file `.jar` với tên artifact là `app-jar`.

```yaml
name: Java Spring Boot CI (Gradle)

on:
  push:
    branches: [ "main" ]

jobs:
  test:
    name: Run Unit Tests
    runs-on: ubuntu-latest

    steps:
      - name: Checkout mã nguồn
        uses: actions/checkout@v5

      - name: Thiết lập môi trường Java JDK 17
        uses: actions/setup-java@v5
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Cấp quyền cho Gradle Wrapper
        run: chmod +x gradlew

      - name: Thực thi JUnit Test với Gradle
        run: ./gradlew test

      - name: Đóng gói tệp JAR với Gradle
        run: ./gradlew bootJar

      - name: Lưu trữ thành phẩm JAR Artifact
        uses: actions/upload-artifact@v4
        with:
          name: app-jar
          path: build/libs/*.jar
```

## 3. Các bước thực hiện & Kiểm tra

1. **Commit và đẩy lên GitHub:**
   ```bash
   git add .
   git commit -m "feat: add bootJar step and upload-artifact to CI workflow"
   git push origin main
   ```

2. **Kiểm tra trên GitHub Actions:**
   - Vào repository trên GitHub, chọn tab **Actions**.
   - Chọn lần chạy mới nhất của workflow **Java Spring Boot CI (Gradle)**.
   - Khi workflow hoàn tất thành công (tích xanh):
     - Truy cập vào trang **Summary** của lượt chạy.
     - Kiểm tra phần **Artifacts** ở cuối trang Summary: xuất hiện tệp artifact mang tên **`app-jar`** và có thể nhấn tải về máy (.zip chứa file `.jar`).
