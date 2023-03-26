plugins {
    java
    application
    id("org.danilopianini.gradle-java-qa") version "1.4.0"
    //id(org.openjfx.javafxplugin") version "0.0.13"
    id("com.github.johnrengelman.shadow") version "8.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    val junitVersion = "5.9.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    // SpotBugs
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")
}

//javafx {
//    version = "19"
//    modules = [ 'javafx.controls' ]
//}

tasks.test {
    useJUnitPlatform()
    testLogging { events("passed", "skipped", "failed") }
    testLogging.showStandardStreams = true
}

application {
    mainClass.set("it.tbt.TurnBasedTunnels")
}

