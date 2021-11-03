plugins {
    kotlin("jvm") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
}

group = "io.github.blugon09"
version = "1.0.6-SNAPSHOT"




repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    implementation("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-api:4.9.2")
}

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }

    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        dependsOn("dokkaHtml")
        from("$buildDir/dokka/html")
    }
}


publishing {
    publications {
        create<MavenPublication>(rootProject.name) {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            repositories {
                maven {
                    val releasesRepoUrl = "https://repo.projecttl.net/repository/maven-public/"
                    val snapshotsRepoUrl = "https://repo.projecttl.net/repository/maven-snapshots/"
                    url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

                    credentials.runCatching {
                        username = project.properties["username"] as String?
                        password = project.properties["password"] as String?
                    }
                }

                pom {
                    name.set(rootProject.name)
                    description.set("")
                    url.set("https://github.com/blugon09/ItemHelper")
//                    licenses {
//                        license {
//                            name.set("")
//                            url.set("")
//                        }
//                    }
                    developers {
                        developer {
                            id.set("blugon09")
                            name.set("Blugon")
                            email.set("blugon0921@blugon.kro.kr")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/blugon09/ItemHelper.git")
                        developerConnection.set("scm:git:https://github.com/blugon09/ItemHelper.git")
                        url.set("https://github.com/blugon09/ItemHelper.git")
                    }
                }
            }
        }
    }
}