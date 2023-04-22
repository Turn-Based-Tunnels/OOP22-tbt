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

    // JavaFx
    val javaFxVersion = "19"
    val javaFxModules = listOf("base","controls","fxml","swing","graphics")
    val supportedSystems = listOf("win", "linux", "mac")
    for (module in javaFxModules) {
        for (sys in supportedSystems) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$sys")
        }
    }

}

tasks.test {
    useJUnitPlatform()
    testLogging { events("passed", "skipped", "failed") }
    testLogging.showStandardStreams = true
}

application {
    mainClass.set("it.tbt.TurnBasedTunnels")
}


