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
    // Use JUnit Jupiter API for testing.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")

    // Use JUnit Jupiter Engine for testing.
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

//javafx {
//    version = "19"
//    modules = [ 'javafx.controls' ]
//}

application {
    mainClass.set("it.tbt.TurnBasedTunnels")
}

