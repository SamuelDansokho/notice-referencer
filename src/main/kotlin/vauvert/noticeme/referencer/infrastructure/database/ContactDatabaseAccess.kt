package vauvert.noticeme.referencer.infrastructure.database

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import vauvert.noticeme.referencer.domain.entity.Contact
import vauvert.noticeme.referencer.domain.utils.ContactId

@Repository
class ContactDatabaseAccess(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) {

    fun getContact(contactId: ContactId): Contact? {
        val sql = """
           SELECT * FROM REFERENCER.T_CONTACT WHERE ID = :contactId 
        """
        val result = namedParameterJdbcTemplate.query(sql, sqlParameterSource(contactId)) { rs, _ ->
            Contact(rs.getEmail(), rs.getAddress(), listOf(rs.getPrimaryPhone(), rs.getSecondaryPhone()))
        }

        return result.singleOrNull()
    }


    private fun sqlParameterSource(
        lambda: MapSqlParameterSource.() -> Unit = {}
    ): MapSqlParameterSource {
        val mapSqlParameterSource = MapSqlParameterSource()
        mapSqlParameterSource.apply(lambda)
        return mapSqlParameterSource
    }

    private fun sqlParameterSource(contactId: ContactId) = sqlParameterSource {
        addValue("contactId", contactId)
    }


}