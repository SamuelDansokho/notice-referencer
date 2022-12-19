package vauvert.noticeme.referencer.infrastructure.database

import vauvert.noticeme.referencer.domain.entity.TeamMemberRole
import vauvert.noticeme.referencer.domain.utils.*
import java.sql.ResultSet

fun ResultSet.getId() : String = requireNotNull(getString("ID"))
fun ResultSet.getTeamId() : TeamId = requireNotNull(getString("TEAM_ID"))
fun ResultSet.getContactId() : String = requireNotNull(getString("CONTACT_ID"))
fun ResultSet.getName() : CompanyName = requireNotNull(getString("NAME"))
fun ResultSet.getRole() : String = requireNotNull(getString("ROLE"))
fun ResultSet.getDescription() : CompanyName = requireNotNull(getString("DESCRIPTION"))
fun ResultSet.getEmail() : Email = requireNotNull(getString("EMAIL"))
fun ResultSet.getAddress() : Address = requireNotNull(getString("ADDRESS"))
fun ResultSet.getPrimaryPhone() : PhoneNumber = requireNotNull(getString("PRIMARY_PHONE"))
fun ResultSet.getSecondaryPhone() : PhoneNumber? = getString("SECONDARY_PHONE")