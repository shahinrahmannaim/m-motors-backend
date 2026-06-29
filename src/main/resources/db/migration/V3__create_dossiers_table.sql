CREATE TABLE dossiers (
                          id BIGSERIAL PRIMARY KEY,

                          user_id BIGINT NOT NULL,
                          vehicle_id BIGINT NOT NULL,

                          status VARCHAR(50) NOT NULL,
                          type VARCHAR(50) NOT NULL,

                          message VARCHAR(2000),

                          created_date TIMESTAMPTZ,
                          updated_date TIMESTAMPTZ,

                          CONSTRAINT fk_dossiers_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id),

                          CONSTRAINT fk_dossiers_vehicle
                              FOREIGN KEY (vehicle_id)
                                  REFERENCES vehicles(id)
);