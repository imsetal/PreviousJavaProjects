plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

//plugins {
//    java
//    application
//}
//
//application {
//    mainClass.set("OpenAIQuickstart")
//}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(group = "com.azure", name = "azure-ai-openai", version = "1.0.0-beta.3")
    implementation("org.slf4j:slf4j-simple:1.7.9")
}

tasks.test {
    useJUnitPlatform()
}


