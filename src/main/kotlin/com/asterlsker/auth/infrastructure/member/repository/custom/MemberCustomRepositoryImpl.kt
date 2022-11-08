package com.asterlsker.auth.infrastructure.member.repository.custom

import com.asterlsker.auth.domain.model.Phone
import com.asterlsker.auth.infrastructure.member.entity.MemberEntity
import com.asterlsker.auth.infrastructure.member.entity.MemberRoleEntity
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class MemberCustomRepositoryImpl(
    private val client: DatabaseClient
): MemberCustomRepository {


    override fun findByEmail(email: String): Mono<MemberEntity> {
//        val query =
//            "SELECT " +
//                "m.id as member_id, m.user_name, m.phone, " +
//                "mr.id as member_role_id, mr.role, " +
//                "msl.id as member_social_login_id, msl.email, msl.provider" +
//            "FROM member as m " +
//            "LEFT JOIN member_role as mr " +
//                "ON m.id = mr.member_id" +
//            "LEFT JOIN member_social_login as msl " +
//                "ON m.id = msl.member_id" +
//            "WHERE msl.email = :email"
//
//        val result = client.sql(query)
//                .bind("email", email)
//                .fetch().all().bufferUntilChanged { result -> }
//            .map { rows -> MemberEntity(
//                id = rows["member_id"].toString(),
//                userName = rows["user_name"].toString(),
//                phone = Phone(rows["phone"].toString()),
//                memberRoles = rows.map {
//                    roles -> MemberRoleEntity(
//                        id = ,
//                    )
//                }
//            )}
        return Mono.empty()
    }
}