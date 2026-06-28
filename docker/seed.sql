-- =============================================================
--  PIZZA PLAZA — Seed de dados iniciais
--  Execute APÓS todas as aplicações subirem (tabelas criadas pelo Hibernate)
--  Idempotente: ON CONFLICT DO NOTHING evita duplicatas em reexecuções
-- =============================================================
--
--  Credenciais inseridas:
--    Admin  → admin@pizzaplaza.com  / admin123
--    Cliente → cliente@pizzaplaza.com / client123
-- =============================================================


-- ─────────────────────────────────────────────────────────────
--  AUTH SERVICE — usuários para autenticação/JWT
-- ─────────────────────────────────────────────────────────────

INSERT INTO auth_service.pizzaplaza_user
    (oid, name, cpf, email, password, authenticated, user_type, created_at, id_user_who_created)
VALUES
    (
        '51f84af1-bc8c-5bf3-b857-210b4e109e8c',
        'Administrador',
        '000.000.000-00',
        'admin@pizzaplaza.com',
        '$2a$12$jULm6MATcRf46CpwZnvkg.Qz5LWlh43yKTHzJZR41PHw0wrC3A4mS',
        false,
        'ADMIN',
        NOW(),
        '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        'c1e4a2f0-9d3b-4e7c-8a1f-2b5d6e8f0a3c',
        'Cliente Teste',
        '111.111.111-11',
        'cliente@pizzaplaza.com',
        '$2a$12$BdEaOY0I/LmMwTwh1dR4FezG3DvkKJMY2QEZQQL.cd4G3BiXjkV6q',
        false,
        'CLIENT',
        NOW(),
        '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    )
ON CONFLICT DO NOTHING;


-- ─────────────────────────────────────────────────────────────
--  USER SERVICE — espelho de usuários + tabelas de perfil
-- ─────────────────────────────────────────────────────────────

INSERT INTO user_service.pizzaplaza_user
    (oid, name, cpf, email, password, authenticated, created_at, id_user_who_created)
VALUES
    (
        '51f84af1-bc8c-5bf3-b857-210b4e109e8c',
        'Administrador',
        '000.000.000-00',
        'admin@pizzaplaza.com',
        '$2a$12$jULm6MATcRf46CpwZnvkg.Qz5LWlh43yKTHzJZR41PHw0wrC3A4mS',
        false,
        NOW(),
        '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        'c1e4a2f0-9d3b-4e7c-8a1f-2b5d6e8f0a3c',
        'Cliente Teste',
        '111.111.111-11',
        'cliente@pizzaplaza.com',
        '$2a$12$BdEaOY0I/LmMwTwh1dR4FezG3DvkKJMY2QEZQQL.cd4G3BiXjkV6q',
        false,
        NOW(),
        '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    )
ON CONFLICT DO NOTHING;

INSERT INTO user_service.admin
    (oid, user_oid, created_at, id_user_who_created)
VALUES
    (
        'a00f6db3-bce3-5df2-9b78-fc1c6b13d326',
        '51f84af1-bc8c-5bf3-b857-210b4e109e8c',
        NOW(),
        '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    )
ON CONFLICT DO NOTHING;

INSERT INTO user_service.client
    (oid, user_oid, created_at, id_user_who_created)
VALUES
    (
        'd9f3b1e2-5a4c-4d8e-9b2f-3c6a7e0d1f4b',
        'c1e4a2f0-9d3b-4e7c-8a1f-2b5d6e8f0a3c',
        NOW(),
        '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    )
ON CONFLICT DO NOTHING;


-- ─────────────────────────────────────────────────────────────
--  PRODUCT SERVICE — categorias
-- ─────────────────────────────────────────────────────────────

INSERT INTO product_service.category
    (oid, description, created_at, id_user_who_created)
VALUES
    ('688df2d9-69e0-5065-a3cd-e22838ba2024', 'Salgada',  NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    ('306b0fe2-50a8-53dd-a0cd-c2098ebba74b', 'Doce',     NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    ('d62181b5-489b-5c79-a9d4-43610055417b', 'Especial', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c')
ON CONFLICT DO NOTHING;


-- ─────────────────────────────────────────────────────────────
--  PRODUCT SERVICE — sabores
-- ─────────────────────────────────────────────────────────────

INSERT INTO product_service.flavor
    (oid, name, description, price, created_at, id_user_who_created)
VALUES
    (
        'd0d13322-d1e9-5adf-8c01-27d11df56b50',
        'Margherita',
        'Molho de tomate, mussarela e manjericão fresco',
        45.90, NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        'ed0289a3-7ab4-529c-9c65-f8978e6061c0',
        'Calabresa',
        'Molho de tomate, mussarela e calabresa fatiada com cebola',
        48.90, NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        '27283bb0-982b-57b5-abb8-d0c5177aa504',
        'Mussarela',
        'Molho de tomate, mussarela e orégano',
        44.90, NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        'e55cdad4-d558-5575-8fa6-3231460a7fde',
        'Frango com Catupiry',
        'Molho de tomate, frango desfiado e catupiry cremoso',
        52.90, NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        '31afa75e-44e7-532d-8d6f-1214d29eefa5',
        'Quatro Queijos',
        'Mussarela, parmesão, provolone e gorgonzola',
        56.90, NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        '5da396e3-3af3-50b3-840e-25367f291222',
        'Pepperoni',
        'Molho de tomate, mussarela e pepperoni picante',
        54.90, NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        'b6ab0069-8bf4-5798-a2c2-fd6e7f8e0757',
        'Portuguesa',
        'Molho de tomate, mussarela, presunto, ovos, cebola e azeitona',
        50.90, NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        'fe97dc6b-4240-5924-b09f-5a37a5bd150b',
        'Chocolate',
        'Chocolate ao leite com granulado e leite condensado',
        46.90, NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    ),
    (
        'db88aaf4-4904-57a2-a8ef-025ddaa765d6',
        'Nutella com Morango',
        'Nutella com morangos frescos fatiados',
        58.90, NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'
    )
ON CONFLICT DO NOTHING;


-- ─────────────────────────────────────────────────────────────
--  PRODUCT SERVICE — sabor × categoria
-- ─────────────────────────────────────────────────────────────

INSERT INTO product_service.flavor_category
    (oid, flavor_oid, category_oid, created_at, id_user_who_created)
VALUES
    -- Margherita
    ('38deb881-6cac-5763-9e14-135328a8860c', 'd0d13322-d1e9-5adf-8c01-27d11df56b50', '688df2d9-69e0-5065-a3cd-e22838ba2024', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    ('89504523-a254-5d3d-9dc6-d310f2b0a273', 'd0d13322-d1e9-5adf-8c01-27d11df56b50', 'd62181b5-489b-5c79-a9d4-43610055417b', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    -- Calabresa
    ('4c634821-f21c-59d2-9513-c12f67e7e0ff', 'ed0289a3-7ab4-529c-9c65-f8978e6061c0', '688df2d9-69e0-5065-a3cd-e22838ba2024', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    -- Mussarela
    ('a14b028e-e9b5-595f-ae48-765eb7cf3cf6', '27283bb0-982b-57b5-abb8-d0c5177aa504', '688df2d9-69e0-5065-a3cd-e22838ba2024', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    -- Frango com Catupiry
    ('3790e112-a8e3-56c6-9748-704bbd77ca74', 'e55cdad4-d558-5575-8fa6-3231460a7fde', 'd62181b5-489b-5c79-a9d4-43610055417b', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    -- Quatro Queijos
    ('6604b032-6b73-5785-89fb-34e16dde78ca', '31afa75e-44e7-532d-8d6f-1214d29eefa5', '688df2d9-69e0-5065-a3cd-e22838ba2024', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    ('4e8e5690-7f12-582f-bb13-b8878336df01', '31afa75e-44e7-532d-8d6f-1214d29eefa5', 'd62181b5-489b-5c79-a9d4-43610055417b', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    -- Pepperoni
    ('0bd2af81-c4c8-5c54-83cd-7944e010b8ab', '5da396e3-3af3-50b3-840e-25367f291222', '688df2d9-69e0-5065-a3cd-e22838ba2024', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    -- Portuguesa
    ('57203253-2ce5-5c41-804f-903e5eac7bcf', 'b6ab0069-8bf4-5798-a2c2-fd6e7f8e0757', '688df2d9-69e0-5065-a3cd-e22838ba2024', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    -- Chocolate
    ('952a8cde-bd3b-504d-b39d-67301949d128', 'fe97dc6b-4240-5924-b09f-5a37a5bd150b', '306b0fe2-50a8-53dd-a0cd-c2098ebba74b', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    -- Nutella com Morango
    ('714f144c-ae8e-5fda-8638-c5954366c00a', 'db88aaf4-4904-57a2-a8ef-025ddaa765d6', '306b0fe2-50a8-53dd-a0cd-c2098ebba74b', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c'),
    ('ec83514a-4edc-5bd3-87cc-4e99c27113c9', 'db88aaf4-4904-57a2-a8ef-025ddaa765d6', 'd62181b5-489b-5c79-a9d4-43610055417b', NOW(), '51f84af1-bc8c-5bf3-b857-210b4e109e8c')
ON CONFLICT DO NOTHING;
