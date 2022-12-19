package vauvert.noticeme.referencer.infrastructure.config

import com.opentable.db.postgres.embedded.EmbeddedPostgres
import liquibase.database.Database.databaseChangeLogLockTableName
import liquibase.database.Database.databaseChangeLogTableName
import liquibase.integration.spring.SpringLiquibase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

@Configuration
@Profile("development")
class PostgreConfig {

    @Bean
    @Primary
    fun datasource(): DataSource {
        val postgreDatabase = EmbeddedPostgres
                .builder()
                .setPort(5432)
                .start().postgresDatabase

        try {
            postgreDatabase.connection.use {
                val s = it.createStatement()
                s.execute("CREATE SCHEMA IF NOT EXISTS REFERENCER;")
                s.execute("CREATE ROLE REFERENCER_ROLE")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return postgreDatabase
    }

    @Bean
    @Primary
    fun springLiquibase(zeDataSource: DataSource): SpringLiquibase =
        SpringLiquibase().apply {
            dataSource = zeDataSource
            changeLog = "classpath:/liquibase/db.changelog.json"
            defaultSchema = "referencer"
            isDropFirst = true
            liquibaseSchema = "referencer"
            databaseChangeLogTable = "databaseChangeLogTable"
            databaseChangeLogLockTable = "databaseChangeLogLockTable" }
}