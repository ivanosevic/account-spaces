INSERT INTO public.account(id, name, lastname, email_address, profile_summary, password, is_verified, last_sign_in_at)
VALUES (1, 'Ivanosevic', 'Garcia Consuegra', 'garcia@ivanosevic.com', NULL,
        '$2a$12$ciLBHJqOct7qcKROSUgjte6N2yN8lvD.E.WrjJHtcgG0qcU1PQ80y', TRUE, NULL);
INSERT INTO public.account_role(account_id, role_id) VALUES (1, 1);
INSERT INTO public.account_role(account_id, role_id) VALUES (1, 2);