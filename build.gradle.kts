import org.gradle.internal.classpath.Instrumented.systemProperty
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
	java
	id("org.springframework.boot") version "4.1.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "sag.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<JavaExec> {
	systemProperty("sun.stdout.encoding", "UTF-8")
	systemProperty("sun.stderr.encoding", "UTF-8")
	systemProperty("court.testimony", "Мавр у Клары украл кораллы.")
	systemProperty("spring.profiles.active", "lie")
}

tasks.named<BootRun>("bootRun") {
	environment("COURT_TESTIMONY", "Амдал у Клары украл кораллы.")
}
