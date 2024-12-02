plugins {
    kotlin("jvm") version "2.1.0"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
    test {
        kotlin.srcDir("test")
    }
}

dependencies{
    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}
tasks {
    wrapper {
        gradleVersion = "8.11.1"
    }
}
