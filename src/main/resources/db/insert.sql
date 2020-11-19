SET FOREIGN_KEY_CHECKS = 0;

truncate table pet;
truncate table store;

INSERT INTO store(`id`, `name`, `location`, `contact_number`)
VALUES(21, 'super store', 'benue', '0908765432');

INSERT INTO pet(`id`, `name`, `color`, `breed`, `age`, `pet_sex`, `store_id`)
VALUES(31, 'bill', 'blue', 'parrot', 4, 'MALE', 21),
(41, 'jack', 'black', 'dog', 3, 'MALE', 21),
(51, 'loveth', 'white', 'parlour', 2, 'FEMALE', 21),
(61, 'sally', 'green', 'cat', 2, 'FEMALE', 21),
(71, 'ross', 'ash', 'rat', 1, 'MALE', 21),
(81, 'mill', 'red', 'parrot', 3, 'FEMALE', 21),
(91, 'gen', 'purple', 'dog', 5, 'MALE', 21);

SET FOREIGN_KEY_CHECKS = 1;
