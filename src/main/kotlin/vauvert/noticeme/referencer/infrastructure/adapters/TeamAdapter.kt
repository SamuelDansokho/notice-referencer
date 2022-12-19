package vauvert.noticeme.referencer.infrastructure.adapters

import org.springframework.stereotype.Repository
import vauvert.noticeme.referencer.domain.entity.Contact
import vauvert.noticeme.referencer.domain.entity.TeamMember
import vauvert.noticeme.referencer.domain.port.repository.ContactRepository
import vauvert.noticeme.referencer.domain.port.repository.TeamRepository
import vauvert.noticeme.referencer.domain.utils.ContactId
import vauvert.noticeme.referencer.domain.utils.TeamId
import vauvert.noticeme.referencer.infrastructure.database.ContactDatabaseAccess
import vauvert.noticeme.referencer.infrastructure.database.TeamDatabaseAccess

@Repository
class TeamAdapter(private val teamDatabaseAccess: TeamDatabaseAccess) : TeamRepository {
    override fun getTeamMembers(teamId: TeamId): List<TeamMember> = teamDatabaseAccess.getTeamMembers(teamId)
}