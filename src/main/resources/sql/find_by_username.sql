SELECT 
    u.id, 
    u.name, 
    u.password, 
    c.email, 
    c.cellphone,
    r.role
FROM "user" u
INNER JOIN contact c ON c.user_id = u.id
INNER JOIN role r ON r.user_id = u.id;