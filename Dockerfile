
# ---- Stage 1: Build ----
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

# Compile main + tests, and copy dependencies (including test scope)
# Ensures /app/target/classes (main resources) and /app/target/test-classes exist
RUN mvn -q -DskipTests -DincludeScope=test \
    dependency:copy-dependencies compile test-compile

# ---- Stage 2: Runtime ----
FROM eclipse-temurin:17-jre
WORKDIR /app

# Install Chrome + dependencies (Ubuntu 24.04 / noble → libasound2t64)
RUN apt-get update && apt-get install -y --no-install-recommends \
    wget gnupg ca-certificates unzip xvfb fonts-liberation \
    libasound2t64 libatk-bridge2.0-0 libatk1.0-0 \
    libcups2 libdbus-1-3 libdrm2 libgbm1 libgtk-3-0 libnspr4 libnss3 \
    libx11-xcb1 libxcomposite1 libxdamage1 libxrandr2 libxss1 libxshmfence1 \
    libvulkan1 xdg-utils curl \
 && install -m 0755 -d /etc/apt/keyrings \
 && wget -qO- https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /etc/apt/keyrings/google-linux-signing-keyring.gpg \
 && chmod 0644 /etc/apt/keyrings/google-linux-signing-keyring.gpg \
 && sh -c 'echo "deb [arch=amd64 signed-by=/etc/apt/keyrings/google-linux-signing-keyring.gpg] https://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list' \
 && apt-get update && apt-get install -y --no-install-recommends google-chrome-stable \
 && rm -rf /var/lib/apt/lists/*

# Writable dirs used by tests
RUN mkdir -p /app/target /app/downloads /tmp/chrome-user-data

# Wrap Chrome: always use container-safe flags (no code changes needed)
RUN if [ -x "/usr/bin/google-chrome" ]; then mv /usr/bin/google-chrome /usr/bin/google-chrome.orig; fi \
 && printf '%s\n' '#!/bin/sh' \
 'FLAGS="--headless=new --no-sandbox --disable-dev-shm-usage --window-size=1920,1080 --user-data-dir=/tmp/chrome-user-data"' \
 'exec /opt/google/chrome/google-chrome $FLAGS "$@"' \
 > /usr/bin/google-chrome \
 && chmod +x /usr/bin/google-chrome

# Selenium Manager will pick matching ChromeDriver
ENV CHROME_BIN=/usr/bin/google-chrome
# Verbose ChromeDriver logs to /app/target/chromedriver.log
ENV JAVA_TOOL_OPTIONS="-Dwebdriver.chrome.logfile=/app/target/chromedriver.log -Dwebdriver.chrome.verboseLogging=true"

# Copy EVERYTHING under target (includes classes, test-classes, dependency/)
COPY --from=builder /app/target /app/target

# Copy your feature files (your structure uses src/test/java/features)
COPY src/test/java/features /app/features

# Glue and features path
ENV CUCUMBER_GLUE="stepdefinitions"
ENV CUCUMBER_FEATURES="/app/features"

# Default base URL (override at run time if needed)
ENV BASE_URL="https://the-internet.herokuapp.com/"

# Run Cucumber CLI; inject baseUrl JVM property (harmless even if not used)
# NOTE: xvfb-run is not needed since Chrome is headless via wrapper
CMD ["sh", "-c", "java -DbaseUrl=$BASE_URL -cp '/app/target/classes:/app/target/test-classes:/app/target/dependency/*' io.cucumber.core.cli.Main --strict --glue $CUCUMBER_GLUE --plugin pretty --plugin html:/app/target/cucumber.html $CUCUMBER_FEATURES"]
