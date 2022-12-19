package vauvert.noticeme.referencer.domain.entity

import vauvert.noticeme.referencer.domain.utils.MemberId
import vauvert.noticeme.referencer.domain.utils.MemberName
import vauvert.noticeme.referencer.domain.utils.TeamId

data class TeamMember(
    val id : MemberId,
    val teamId: TeamId,
    val name: MemberName,
    val role: TeamMemberRole
)

enum class TeamMemberRole {
    LEADER,
    SUPPORT
}

data class CompleteTeamMember(
    val teamMember : TeamMember,
    val contact : Contact?
)