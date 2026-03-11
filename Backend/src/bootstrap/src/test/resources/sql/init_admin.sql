INSERT INTO users (
    first_name, 
    last_name, 
    first_surname, 
    last_surname, 
    email, 
    username, 
    password, 
    is_active, 
    created_at, 
    role
) VALUES (
    'Admin', 
    'System', 
    'Arka', 
    'Root', 
    'admin@arka.com', 
    'admin', 
    '$2a$10$TQzN2qvQJ0y3lXyeBev7VuvVtqtJZSz1S.rCIkN3P1dE5xso8RpuG', -- password: admin123
    true, 
    NOW(), 
    'Administrador'
);