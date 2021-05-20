INSERT INTO league_table (id, name, description, available) VALUES (1, 'super', 'super', true);

INSERT INTO season_table (id, name, league_name, league_identification, description, available, closed, active_round, league_id) VALUES (1, 'season', 'super', 1, 'season', true, false, 0, 1);

INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (1, 1, 'zvezda', 1);
INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (2, 2, 'partizan', 1);
INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (3, 3, 'radnicki', 1);
INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (4, 4, 'tsc', 1);
INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (5, 5, 'spartak', 1);
INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (6, 6, 'hajduk', 1);
INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (7, 7, 'dorcol', 1);
INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (8, 8, 'nbg', 1);
INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (9, 9, 'nis', 1);
INSERT INTO representation_table (id, club_id, club_name, season_id) VALUES (10, 10, 'gepardi', 1);