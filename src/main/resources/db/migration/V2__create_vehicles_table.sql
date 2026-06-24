CREATE TABLE vehicles (
                          id BIGSERIAL PRIMARY KEY,
                          brand VARCHAR(100) NOT NULL,
                          model VARCHAR(100) NOT NULL,
                          year INTEGER NOT NULL,
                          mileage INTEGER NOT NULL,
                          price NUMERIC(12, 2) NOT NULL,
                          fuel_type VARCHAR(50),
                          transmission VARCHAR(50),
                          status VARCHAR(50) NOT NULL,
                          type VARCHAR(50) NOT NULL,
                          image_url VARCHAR(500),
                          description VARCHAR(2000),
                          created_date TIMESTAMPTZ,
                          updated_date TIMESTAMPTZ
);