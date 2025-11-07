-- ==========================================
-- DATA.BDD : Jeu de données initial
-- ==========================================

-- ========================
-- TABLE : PROJECT_OWNER
-- ========================
INSERT INTO users (id, name, email, password, description)
VALUES 
(1, 'Alice Martin', 'alice.martin@creadev.com', 'hashed_pw1', 'Entrepreneure passionnée de tech.'),
(2, 'Bruno Lefevre', 'bruno.lefevre@webimpact.com', 'hashed_pw2', 'Expert en gestion de projets web.');

INSERT INTO project_owner (id, company)
VALUES
(1, 'CreaDev'),
(2, 'WebImpact');

-- ========================
-- TABLE : DEV
-- ========================
INSERT INTO users (id, name, email, password, description)
VALUES
(3, 'Clara Dupont', 'clara.dupont@devmail.com', 'hashed_pw3', 'Développeuse fullstack curieuse.'),
(4, 'David Bernard', 'david.bernard@devmail.com', 'hashed_pw4', 'Passionné par le backend et les API.'),
(5, 'Eva Morel', 'eva.morel@devmail.com', 'hashed_pw5', 'Freelance spécialisée en UX et front-end.');

INSERT INTO dev (id, experience, skills)
VALUES
(3, 5, '["Java", "Spring Boot", "Angular"]'),
(4, 3, '["Python", "Django", "React"]'),
(5, 7, '["HTML", "CSS", "Vue.js", "Node.js"]');

-- ========================
-- TABLE : PROJECT
-- ========================
INSERT INTO project (id, owner_id, name, description, theme, delivery_date, budget)
VALUES
(1, 1, 'Plateforme Freelance', 'Application pour connecter freelances et clients.', 'Web', '2025-06-30', 15000),
(2, 2, 'API de réservation', 'Système REST pour gérer des réservations hôtelières.', 'Backend', '2025-08-15', 12000),
(3, 1, 'Application mobile santé', 'App Android/iOS pour le suivi médical.', 'Mobile', '2025-09-10', 20000);

-- ========================
-- TABLE : CANDIDACY
-- ========================
INSERT INTO candidacy (id, dev_id, project_id, submit_date, status)
VALUES
(1, 3, 1, '2025-01-15 10:30:00', 'PENDING'),
(2, 4, 2, '2025-02-10 14:45:00', 'ACCEPTED'),
(3, 5, 1, '2025-03-05 09:00:00', 'DECLINED'),
(4, 3, 3, '2025-04-12 11:20:00', 'PENDING'),
(5, 5, 2, '2025-05-01 16:00:00', 'PENDING');