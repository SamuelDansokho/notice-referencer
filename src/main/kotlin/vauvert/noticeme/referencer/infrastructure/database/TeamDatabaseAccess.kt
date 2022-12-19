package vauvert.noticeme.referencer.infrastructure.database

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import vauvert.noticeme.referencer.domain.entity.TeamMember
import vauvert.noticeme.referencer.domain.entity.TeamMemberRole
import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.domain.utils.TeamId

@Repository
class TeamDatabaseAccess(private val namedParameterJdbcTemplate : NamedParameterJdbcTemplate) {

    fun getTeamMembers(companyId: CompanyId): List<TeamMember> {
        val sql = """
           SELECT * FROM T_TEAM_MEMBERS WHERE TEAM_ID = :companyId 
        """
        val rowMapper = RowMapper { rs, _ ->
            TeamMember(
                id = rs.getId(),
                teamId = rs.getTeamId(),
                name = rs.getName(),
                role= TeamMemberRole.valueOf(rs.getRole())
            )
        }

        return namedParameterJdbcTemplate.query(sql, sqlParameterSource(companyId), rowMapper)

    }

    private fun sqlParameterSource(
        lambda: MapSqlParameterSource.() -> Unit = {}): MapSqlParameterSource {
        val mapSqlParameterSource = MapSqlParameterSource()
        mapSqlParameterSource.apply(lambda)
        return mapSqlParameterSource
    }

    private fun sqlParameterSource(companyId : CompanyId) = sqlParameterSource {
        addValue("companyId", companyId)
    }



}