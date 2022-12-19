package vauvert.noticeme.referencer.domain.port.repository

import vauvert.noticeme.referencer.domain.entity.TeamMember
import vauvert.noticeme.referencer.domain.utils.TeamId

interface TeamRepository {

    fun getTeamMembers(teamId : TeamId) : List<TeamMember>
}