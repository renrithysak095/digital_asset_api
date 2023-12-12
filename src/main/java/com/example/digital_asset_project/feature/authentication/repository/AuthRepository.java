package com.example.digital_asset_project.feature.authentication.repository;
import com.example.digital_asset_project.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<Member, UUID> {


}
