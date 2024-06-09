insert into players(id, user_name, password, email, avatar, last_login_date, created_at, updated_at)
values (1000000, 'APP', null, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into players(id, user_name, password, email, avatar, last_login_date, created_at, updated_at)
values (100, 'Tobias', 'Password#03', 'email@email.com', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--------------------------------------------------------------------------------------------------
insert into games(id, code, name, description, rules)
values (100000, 'RPS', 'Rock Paper Scissors',
        'Is a simple hand game typically played between two individuals ' ||
        'The game involves each player simultaneously forming one of three shapes with their hand: ' ||
        'a closed fist representing rock, an open hand with the palm facing downward representing ' ||
        'a sheet of paper, or an extended index and middle finger forming ' ||
        'a V shape to represent scissors. ' ||
        'To play this game we will replace the shapes of the hands with letters, ' ||
        'R for rock, P for paper and S for scissors',
        'The goal of the game is to defeat the opponent by selecting a hand shape that defeats their choice according to a ' ||
        'set of predetermined rules: ' ||
        'rock crushes scissors, scissors cuts paper, and paper covers rock. ' ||
        'The outcome of each round is determined by comparing the hand shapes, ' ||
        'and the player with the winning shape scores a point.');
--------------------------------------------------------------------------------------------------
insert into matches(id, game_id, player_id, created_at, updated_at, status)
values (100000, 100000, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STARTED');

insert into matches(id, game_id, player_id, created_at, updated_at, status)
values (100001, 100000, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FINISHED');

insert into matches(id, game_id, player_id, created_at, updated_at, status)
values (100002, 100000, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'CANCELED');
--------------------------------------------------------------------------------------------------
insert into matches_rps (id, number_of_plays, remainder_plays, player1score, player2score)
values (100000, 10, 5, 3, 2);

insert into matches_rps (id, number_of_plays, remainder_plays, player1score, player2score, winner_id)
values (100001, 10, 0, 6, 4, 100);

insert into matches_rps (id, number_of_plays, remainder_plays, player1score, player2score)
values (100002, 10, 5, 3, 2);
--------------------------------------------------------------------------------------------------
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(100000,100000,'ROCK','PAPER',1000000);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(100001,100000,'PAPER','ROCK',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(100002,100000,'PAPER','ROCK',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(100003,100000,'ROCK','SCISSORS',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(100004,100000,'ROCK','PAPER',1000000);
--------------------------------------------------------------------------------------------------
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000010,100001,'ROCK','PAPER',1000000);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000011,100001,'PAPER','ROCK',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000012,100001,'PAPER','ROCK',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000013,100001,'ROCK','SCISSORS',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000014,100001,'ROCK','PAPER',1000000);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000015,100001,'PAPER','ROCK',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000016,100001,'PAPER','ROCK',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000017,100001,'ROCK','SCISSORS',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000018,100001,'ROCK','PAPER',1000000);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000019,100001,'ROCK','PAPER',1000000);
--------------------------------------------------------------------------------------------------
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000020,100002,'ROCK','PAPER',1000000);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000021,100002,'PAPER','ROCK',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000022,100002,'PAPER','ROCK',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000023,100002,'ROCK','SCISSORS',100);
insert into plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
values(1000024,100002,'ROCK','PAPER',1000000);