plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.discord)
    compileOnly(libs.slf4j)
}

tasks.test {
    useJUnitPlatform()
}