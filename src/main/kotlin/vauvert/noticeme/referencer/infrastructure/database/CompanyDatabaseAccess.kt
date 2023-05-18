package vauvert.noticeme.referencer.infrastructure.database

import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.domain.entity.NewCompany
import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.domain.utils.Utils
import java.sql.ResultSet

@Repository
class CompanyDatabaseAccess(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) {

    private val log = LoggerFactory.getLogger(CompanyDatabaseAccess::class.java)

    fun getCompany(companyId: CompanyId): Company? {

        val sql = """
            SELECT * FROM REFERENCER.T_COMPANY WHERE ID = :companyId
        """

        val result = namedParameterJdbcTemplate.query(sql, sqlParameterSource(companyId)) { rs, _ ->
            Company(
                id = rs.getId(),
                name = rs.getName(),
                description = rs.getDescription()
            )
        }
        return result.singleOrNull()
    }

    fun getAllCompanies(): List<Company> {
        val sql = """
            SELECT * FROM REFERENCER.T_COMPANY
        """

        val rowMapper = RowMapper { rs: ResultSet, _ ->
            Company(
                id = rs.getId(),
                name = rs.getName(),
                description = rs.getDescription()
            )
        }
        return namedParameterJdbcTemplate.query(sql, sqlParameterSource(), rowMapper)
    }

    fun createCompany(company: NewCompany): CompanyId? {
        val newId = Utils.withRandomUuid()
        val sql = """
           INSERT INTO REFERENCER.T_COMPANY(ID, NAME, DESCRIPTION, TEAM_ID) VALUES (:id, :name, :description, '0')
        """
        namedParameterJdbcTemplate.update(sql, sqlParameterSource(company, newId))
        return newId
    }

    private fun sqlParameterSource(
        lambda: MapSqlParameterSource.() -> Unit = {}
    ): MapSqlParameterSource {
        val mapSqlParameterSource = MapSqlParameterSource()
        mapSqlParameterSource.apply(lambda)
        return mapSqlParameterSource
    }

    private fun sqlParameterSource(companyId: String) = sqlParameterSource {
        addValue("companyId", companyId)
    }

    private fun sqlParameterSource(company: NewCompany, newId: String) = sqlParameterSource {
        addValue("description", company.description)
        addValue("name", company.name)
        addValue("id", newId)
    }


}