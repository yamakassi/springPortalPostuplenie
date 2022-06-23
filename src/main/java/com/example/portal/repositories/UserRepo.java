package com.example.portal.repositories;

import com.example.portal.domain.enums.Role;
import com.example.portal.domain.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    /*@Query(
            value = "select * from job.public.user_role u join job.public.users ur on u.user_id = ur.id where u.roles='ROLE_ADMIN'",
            nativeQuery = true)
     List<User> findAllUsersIsRoleAdmin();
*/
}
