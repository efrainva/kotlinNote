import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.6.3")

	implementation("org.springframework.boot:spring-boot-starter:2.6.3")
	implementation("org.springframework.boot:spring-boot-starter-security:2.6.3")
//	implementation("org.springframework.boot:spring-boot-starter-security-test ")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")

	implementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	implementation("org.glassfish.jaxb:jaxb-runtime:3.0.1")
	implementation("junit:junit:4.13.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")


}
tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
