import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

object Versions {
	const val JACKSON_DATABIND_NULLABLE = "0.2.6"
	const val SPRINGDOC_OPENAPI_MVC_UI = "2.3.0"
	const val MAPSTRUCT = "1.5.5.Final"
	const val AUTH0_JWT = "4.4.0"
	const val ORG_JSON = "20240305"
}

plugins {
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.google.cloud.tools.jib") version "3.4.1"
	id("org.openapi.generator") version "7.3.0"
	java
}

group = "com.ratethisthing"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot Dependencies
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Database
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.testcontainers:postgresql")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// OpenAPI Documentation
	implementation("org.openapitools:jackson-databind-nullable:${Versions.JACKSON_DATABIND_NULLABLE}")
	implementation("jakarta.validation:jakarta.validation-api")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.SPRINGDOC_OPENAPI_MVC_UI}")

	// Testing Dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.withType<Test> {
	useJUnitPlatform()
}

// Open API spec generator config
val openApiGenerationDir = "$buildDir/generated/openapi"

val openApiSpecs = listOf(
	OpenApiSpecDef(packageName = "test", specFile = "$rootDir/src/main/api-spec/test.yaml")
)

val openApiGenerateTasks = generateOpenApiSpec(openApiSpecs, openApiGenerationDir)

tasks.register<Copy>("copySpecFile") {
	into(openApiGenerationDir.plus("/src/main/resources/static/swagger-resources/"))

	for (spec in openApiSpecs) {
		into("${spec.packageName}/${spec.version}") {
			from(spec.specFile)
			rename { "open-api.yaml" }
		}
	}
}

tasks.compileJava {
	dependsOn(openApiGenerateTasks)
}

tasks.processResources {
	dependsOn(openApiGenerateTasks)
	dependsOn(tasks.named("copySpecFile"))
}

sourceSets {
	main {
		java.srcDir(openApiGenerationDir.plus("/src/main/java"))
		resources.srcDir(openApiGenerationDir.plus("/src/main/resources"))
	}
}

data class OpenApiSpecDef(
	val packageName: String,
	val specFile: String,
	val version: String = "v1"
)

fun generateOpenApiSpec(openApiSpecs: List<OpenApiSpecDef>, openApiGenerationDir: String): List<TaskProvider<Task>> {
	val taskList: MutableList<TaskProvider<Task>> = mutableListOf()

	for (spec in openApiSpecs) {
		val taskName = "openApiGenerate${spec.packageName}"

		tasks.create<GenerateTask>(taskName) {
			generatorName.set("spring")
			inputSpec.set(spec.specFile)
			outputDir.set(openApiGenerationDir)

			packageName.set("com.ratethisthing.ratethisthing.openapi.${spec.packageName}")
			modelPackage.set("com.ratethisthing.ratethisthing.openapi.${spec.packageName}.dto")
			apiPackage.set("com.ratethisthing.ratethisthing.openapi.${spec.packageName}.api")

			configOptions.put("useTags", "true")
			configOptions.put("disallowAdditionalPropertiesIfNotPresent", "false")
			configOptions.put("interfaceOnly", "true")
			configOptions.put("useSpringBoot3", "true")
		}

		taskList.add(tasks.named(taskName))
	}

	return taskList
}

tasks.test