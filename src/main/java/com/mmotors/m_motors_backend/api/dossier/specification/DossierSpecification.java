package com.mmotors.m_motors_backend.api.dossier.specification;

import com.mmotors.m_motors_backend.api.dossier.entity.Dossier;
import com.mmotors.m_motors_backend.api.dossier.entity.DossierStatus;
import com.mmotors.m_motors_backend.api.dossier.entity.DossierType;
import org.springframework.data.jpa.domain.Specification;

public class DossierSpecification {

    public static Specification<Dossier> hasStatus(DossierStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<Dossier> hasType(DossierType type) {
        return (root, query, criteriaBuilder) -> {
            if (type == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    public static Specification<Dossier> hasUserEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("user").get("email")),
                    "%" + email.toLowerCase() + "%"
            );
        };
    }
}