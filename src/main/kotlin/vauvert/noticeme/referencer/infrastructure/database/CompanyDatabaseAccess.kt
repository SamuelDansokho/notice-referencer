package vauvert.noticeme.referencer.infrastructure.database

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.domain.utils.CompanyId
import java.sql.ResultSet

@Repository
class CompanyDatabaseAccess(private val namedParameterJdbcTemplate : NamedParameterJdbcTemplate) {


    fun getCompany(companyId: CompanyId) : Company? {

        val sql = """
            SELECT * FROM T_COMPANY WHERE ID = :companyId
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
            SELECT * FROM T_COMPANY
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

    private fun sqlParameterSource(
        lambda: MapSqlParameterSource.() -> Unit = {}): MapSqlParameterSource {
        val mapSqlParameterSource = MapSqlParameterSource()
        mapSqlParameterSource.apply(lambda)
        return mapSqlParameterSource
    }

    private fun sqlParameterSource(companyId : String) = sqlParameterSource {
        addValue("companyId", companyId)
    }



}